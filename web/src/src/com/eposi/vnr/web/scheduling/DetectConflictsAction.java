package com.eposi.vnr.web.scheduling;

import com.eposi.common.util.FormatUtil;
import com.eposi.common.web.AbstractAction;
import com.eposi.vnr.model.Stop;
import com.eposi.vnr.model.Train;
import com.eposi.vnr.service.CacheDB;
import com.eposi.vnr.service.ObjectCopier;

import java.io.Serializable;
import java.util.*;

public class DetectConflictsAction extends AbstractAction {
    private static final long serialVersionUID = -7767503044560615080L;
    private List<Train> trains=new ArrayList<Train>();
    private HashMap<Long, List<Stop>> mapTrain = new HashMap<Long, List<Stop>>();
    private CacheDB beanCacheDB;

    public String execute() throws Exception {

        if(!beanCacheDB.isSynch()) {
            this.addActionError("Đang trong quá trình xử lý xung đột!");
            return SUCCESS;
        }else{
            beanCacheDB.setSynch(false);
        }
        List<Stop> stops=getStopInDay();//lay toan bo stop trong ngay

        trains=getListTrainsByStops(stops);//lay toan bo train trong ngay

        SapXepTrainTheoDoUuTien(trains);//Sap xep danh sach trains theo do uu tien cao dung trc.

        mapTrain=getMapTrain(trains,stops);//Gan' ListStop cho tung` train

        boolean check=TimVaXulyXungDot(); // TIM XUNG DOT va Xu ly Xung Dot

        if(check){
            LocStopTruoc18H();
            List<Stop> listStopUpdate = getStopUpdate();
            for(Stop stop : listStopUpdate){
                beanCacheDB.putStop(stop);
            }
        }
        beanCacheDB.setSynch(true);
        return SUCCESS;
    }

    /**********************Tìm va xu ly xung dot*************************************/
    private boolean TimVaXulyXungDot() throws Exception{
        int  priority=500;//khoi dong voi do uu tien cao nhat
        boolean check=false;//Gia tri tra ve khi ham co xung dot va khong co xung dot
        while(priority>=100){
            long firstTimeConflict = 0;//Thoi gian dau tien xay ra xung dot
            List<Conflict> conflicts = detectConflictsSharingLine(priority,firstTimeConflict);
            if (conflicts.size() > 0 ) {
                check = true;
            }

            while (conflicts.size() > 0 ) {
                    if (conflicts.get(0).getStopA1().getDepartureTime() < conflicts.get(0).getStopB1().getDepartureTime()) {
                        firstTimeConflict=conflicts.get(0).getStopA1().getArrivalTime();
                    } else {
                        firstTimeConflict=conflicts.get(0).getStopB1().getArrivalTime();
                    }
                    resolveConflictsSharingLine(conflicts,priority);
                    conflicts = detectConflictsSharingLine(priority,firstTimeConflict);
            }
            priority-=100;
        }
        return check;
    }


    /***************************Xu ly xung dot***************************************/
    private void resolveConflictsSharingLine(List<Conflict> conflicts,int priority) throws Exception{
        if (conflicts == null) {
            return;
        }

        if (conflicts.size() == 0) {
            return;
        }

        while (conflicts.size() > 0) {
            // get first conflict
            Conflict firstConflict = conflicts.get(0);
            Train trainLowerPriority = new Train();
            if (isConfig(firstConflict)) {
                //////////////////////
                // Resolve conflict by delaying and depaturing with higher priority
                Stop stopFromLowerPriority = new Stop();
                Stop stopTopHigherPriority = new Stop();
                if(firstConflict.getTrainA().getTrainReference().getPriority() == firstConflict.getTrainB().getTrainReference().getPriority()){
                    boolean isTrainAHigherPriority=firstConflict.getStopA1().getDepartureTime()<firstConflict.getStopB1().getDepartureTime();
                    trainLowerPriority = isTrainAHigherPriority ? firstConflict.getTrainB() : firstConflict.getTrainA();
                    stopFromLowerPriority = isTrainAHigherPriority ? firstConflict.getStopB1() : firstConflict.getStopA1();
                    stopTopHigherPriority = isTrainAHigherPriority ? firstConflict.getStopA2() : firstConflict.getStopB2();
                }else{
                    boolean isTrainAHigherPriority = firstConflict.getTrainA().getTrainReference().getPriority() > firstConflict.getTrainB().getTrainReference().getPriority();
                    trainLowerPriority = isTrainAHigherPriority ? firstConflict.getTrainB() : firstConflict.getTrainA();
                    stopFromLowerPriority = isTrainAHigherPriority ? firstConflict.getStopB1() : firstConflict.getStopA1();
                    stopTopHigherPriority = isTrainAHigherPriority ? firstConflict.getStopA2() : firstConflict.getStopB2();
                }

                long diff = stopTopHigherPriority.getArrivalTime() + 180000 - stopFromLowerPriority.getDepartureTime();
                List<Stop> stops = mapTrain.get(trainLowerPriority.getId());
                int indexStop = 0;
                for (int i = 0; i < stops.size(); i++) {
                    if (stops.get(i).getStationStage().getStation().getId().equals(stopFromLowerPriority.getStationStage().getStation().getId())) {
                        indexStop = i;
                        break;
                    }
                }
                for (int i = indexStop; i < stops.size(); i++) {
                    Stop itemStop = stops.get(i);
                    if (i == indexStop) {
                        stops.get(i).setArrivalTime(itemStop.getArrivalTime());
                        stops.get(i).setDepartureTime(itemStop.getDepartureTime() + diff);
                    } else if (itemStop.getStationStage().getStation().getId().equals(stops.get(i - 1).getStationStage().getStation().getId())) {
                        stops.get(i).setArrivalTime(stops.get(i - 1).getArrivalTime());
                        stops.get(i).setDepartureTime(stops.get(i - 1).getDepartureTime());
                    } else {
                        if (itemStop.getDepartureTime() > itemStop.getArrivalTime()) {
                                long time = itemStop.getDepartureTime() - (itemStop.getArrivalTime() + itemStop.getWait() * 60000);
                                stops.get(i).setArrivalTime(itemStop.getArrivalTime() + diff);
                                stops.get(i).setDepartureTime(itemStop.getDepartureTime() + diff - time);
                                diff = diff - time;
                            //ko can xet itemStop.wait!=null hay ko vi mac dinh =0 trong db

                        } else {
                            stops.get(i).setArrivalTime(itemStop.getArrivalTime() + diff);
                            stops.get(i).setDepartureTime(itemStop.getDepartureTime() + diff);
                        }
                    }
                }
                mapTrain.put(trainLowerPriority.getId(), stops);
            }
            conflicts.remove(0);
            conflicts=XoaXungDotCuaTrain(conflicts,trainLowerPriority);
            List<Conflict> conflicts1=TimXungDotCuaTrain(trainLowerPriority.getId(),priority);
            if(conflicts1.size()>0){
                for (Conflict item:conflicts1){
                   conflicts.add(item);
                }
                SapXepXungDotTheoTime(conflicts);
            }

        }//end while

    }

    /***********************Tim xung dot*******************************************/
    private List<Conflict> detectConflictsSharingLine(int priority,long firstTimeConflict) {
        List<Conflict> conflicts = new ArrayList<Conflict>();
            for (int i = 0; i < trains.size() - 1; i++) {
                if (trains.get(i).getTrainReference().getPriority() == priority) {//tim hang loat xung dot doi voi muc uu tien =priority
                    Train currentTrain = trains.get(i);
                    List<Stop> currentTrainStops = mapTrain.get(currentTrain.getId());
                    long endStopcurrent = currentTrainStops.get(currentTrainStops.size() - 1).getDepartureTime();
                    if (endStopcurrent < firstTimeConflict) {
                        continue;
                    }
                    for (int j = 0; j < trains.size(); j++) {
                        if (trains.get(j).getId() == currentTrain.getId()) {
                            continue;
                        }
                        //tim de xu ly het voi tung muc xung dot mot
                        if (trains.get(j).getTrainReference().getPriority() >= priority) {
                            Train compareTrain = trains.get(j);
                            // compare currentTrain and compareTrain
                            List<Stop> compareTrainStops = mapTrain.get(compareTrain.getId());
                            long endStopCompare = compareTrainStops.get(compareTrainStops.size() - 1).getDepartureTime();
                            if (endStopCompare < firstTimeConflict) {
                                continue;
                            }

                            for (int m = 0; m < currentTrainStops.size() - 1; m++) {
                                Stop currentTrainStopFrom = currentTrainStops.get(m);
                                Stop currentTrainStopTo  = currentTrainStops.get(m + 1);
                                String currenFromStation = currentTrainStopFrom.getStationStage().getStation().getId();
                                String currenToStation = currentTrainStopTo.getStationStage().getStation().getId();
                                if (currenFromStation.equals(currenToStation)) {
                                    continue;
                                }

                                for (int n = 0; n < compareTrainStops.size() - 1; n++) {
                                    Stop compareTrainStopFrom = compareTrainStops.get(n);
                                    Stop compareTrainStopTo = compareTrainStops.get(n + 1);
                                    String compareFromStation = compareTrainStopFrom.getStationStage().getStation().getId();
                                    String compareToStation = compareTrainStopTo.getStationStage().getStation().getId();
                                    if (compareFromStation.equals(compareToStation)) {
                                        continue;
                                    }

                                    long a1 = currentTrainStopFrom.getDepartureTime();
                                    long a2 = currentTrainStopTo.getArrivalTime();
                                    long b1 = compareTrainStopFrom.getDepartureTime();
                                    long b2 = compareTrainStopTo.getArrivalTime();
                                    boolean conflicted = false;

                                    //////////////////////////////////////////////////////////
                                    // detect common stations (from-to), no-direction
                                    if ((currenFromStation.equals(compareFromStation))
                                            && (currenToStation.equals(compareToStation))) {
                                        if ((a1 == b1) || (a2 == b2) || ((a1 < b1) && (a2 > b2)) || ((a1 > b1) && (a2 < b2)) || ((a1 - 180000) < b1 && b1 < (a2 + 180000)) || ((b1 - 180000) < a1 && a1 < (b2 + 180000))) {
                                            conflicted = true;
                                        }
                                    } else if ((currenFromStation.equals(compareToStation))
                                            && (currenToStation.equals(compareFromStation))) { // reversed direction
                                        if ((a1 == b2) || (a2 == b1) || (a1 < b2 && a2 > b1) || ((a1 - 180000) < b1 && b1 < (a2 + 180000)) || ((b1 - 180000) < a1 && a1 < (b2 + 180000))) {
                                            conflicted = true;
                                        }
                                    }

                                    if (conflicted) {
                                        // Found RULE-CONFLICT-001: Sử dụng chung khu gian
                                        Conflict conflict = new Conflict();
                                        conflict.setTrainA(currentTrain);
                                        conflict.setTrainB(compareTrain);
                                        conflict.setStopA1(currentTrainStopFrom);
                                        conflict.setStopA2(currentTrainStopTo);
                                        conflict.setStopB1(compareTrainStopFrom);
                                        conflict.setStopB2(compareTrainStopTo);
                                        conflicts.add(conflict);
                                    }
                                }
                            }
                        }
                    }

                }

            }

        // sort conflicts by timing
        Collections.sort(conflicts, new Comparator<Conflict>() {
            @Override
            public int compare(Conflict o1, Conflict o2) {
                long o1Time = o1.getStopA1().getDepartureTime() < o1.getStopA2().getDepartureTime() ? o1.getStopA1().getDepartureTime() : o1.getStopA2().getDepartureTime();
                long o2Time = o2.getStopA1().getDepartureTime() < o2.getStopA2().getDepartureTime() ? o2.getStopA1().getDepartureTime() : o2.getStopA2().getDepartureTime();
                return new Date(o1Time).compareTo(new Date(o2Time));
            }
        });

        return conflicts;
    }

    /***********************Kiem tra xem con xung dot ko**************************/
    private boolean isConfig(Conflict conflict) {
        long a1 = conflict.getStopA1().getDepartureTime();
        long a2 = conflict.getStopA2().getArrivalTime();
        long b1 = conflict.getStopB1().getDepartureTime();
        long b2 = conflict.getStopB2().getArrivalTime();
        //////////////////////////////////////////////////////////
        // detect common stations (from-to), no-direction
        if ((conflict.getStopA1().getStationStage().getStation().getId().equals(conflict.getStopB1().getStationStage().getStation().getId()))
                && (conflict.getStopA2().getStationStage().getStation().getId().equals(conflict.getStopB2().getStationStage().getStation().getId()))) {
            if ((a1 == b1) || (a2 == b2) || ((a1 < b1) && (a2 > b2)) || ((a1 > b1) && (a2 < b2)) || ((a1 - 180000) < b1 && b1 < (a2 + 180000)) || ((b1 - 180000) < a1 && a1 < (b2 + 180000))) {
                return true;
            }
        } else if ((conflict.getStopA1().getStationStage().getStation().getId().equals(conflict.getStopB2().getStationStage().getStation().getId()))
                && (conflict.getStopA2().getStationStage().getStation().getId().equals(conflict.getStopB1().getStationStage().getStation().getId()))) { // reversed direction
            if ((a1 == b2) || (a2 == b1) || (a1 < b2 && a2 > b1) || ((a1 - 180000) < b1 && b1 < (a2 + 180000)) || ((b1 - 180000) < a1 && a1 < (b2 + 180000))) {
                return true;
            }
        }
        return false;
    }
    /***********************Tim xung dot cho 1 tau` xac dinh*******************/


/*********************lay list trains tu listStop****************************/
    private List<Train> getListTrainsByStops(List<Stop> stops){
        List<Train> trainsList=new ArrayList<Train>();
        Map<Long, Long> mapListTrains = new HashMap<Long, Long>();
        for (Stop stopItem : stops) {
            long KEY = stopItem.getTrain().getId();
            if (mapListTrains.get(KEY) == null) {
                mapListTrains.put(KEY, KEY);
                trainsList.add(stopItem.getTrain());
            }
        }
        return trainsList;
    }

    /**********************Sắp xếp Trains**********************************/
    private void SapXepTrainTheoDoUuTien(List<Train> trains) throws Exception{
        Collections.sort(trains, new Comparator<Train>() {
            @Override
            public int compare(Train t1, Train t2) {
                if (t1.getTrainReference().getPriority()<t2.getTrainReference().getPriority()){
                    return 1;
                }else{
                    if (t1.getTrainReference().getPriority()==t2.getTrainReference().getPriority()){
                        return 0;
                    }else{
                        return -1;
                    }
                }

            }
        });
    }
    /**********************Sắp xếp Stops theo time tang dan`**********************************/
    private void SapXepStopsTheoTime(List<Stop> stops) throws Exception{
        Collections.sort(stops, new Comparator<Stop>() {
            @Override
            public int compare(Stop t1, Stop t2) {
                if (t1.getArrivalTime()<t2.getArrivalTime()){
                    return -1;
                }else{
                    if (t1.getArrivalTime()==t2.getArrivalTime()){
                        return 0;
                    }else{
                        return 1;
                    }
                }

            }
        });
    }
    /**********************Sắp xếp Xung Dot theo thoi gian**********************************/
    private  void SapXepXungDotTheoTime(List<Conflict> conflicts){
        // sort conflicts by timing
        Collections.sort(conflicts, new Comparator<Conflict>() {
            @Override
            public int compare(Conflict o1, Conflict o2) {
                long o1Time = o1.getStopA1().getDepartureTime() < o1.getStopA2().getDepartureTime() ? o1.getStopA1().getDepartureTime() : o1.getStopA2().getDepartureTime();
                long o2Time = o2.getStopA1().getDepartureTime() < o2.getStopA2().getDepartureTime() ? o2.getStopA1().getDepartureTime() : o2.getStopA2().getDepartureTime();
                return new Date(o1Time).compareTo(new Date(o2Time));
            }
        });
    }

    public List<Stop> getStopInDay() throws Exception{
        String strDateFormat = "dd/MM/yyyy-HH:mm:ss";
        Date now= new Date();
        String dateNow= FormatUtil.formatDate(now, "dd/MM/yyyy");
        now=FormatUtil.parseDate(dateNow + "-17:59:59", strDateFormat);
        long start=now.getTime()-2*3600000;
        long end=now.getTime()+86400000;

        List<Stop> items= new ArrayList<Stop>();
        HashMap<Long,Stop> mapStop= new HashMap<Long, Stop>();
        mapStop=beanCacheDB.getListStopAll();
        for (Long key:mapStop.keySet()){
            if((mapStop.get(key).getArrivalTime()>=start && mapStop.get(key).getArrivalTime()<=end) ||
                    (mapStop.get(key).getDepartureTime()>=start && mapStop.get(key).getDepartureTime()<=end) ){
                items.add(mapStop.get(key));
            }
        }
        SapXepStopsTheoTime(items);
        List<Stop> itemsCopy= (List<Stop>) ObjectCopier.copy(items);
        return itemsCopy;
    }

    public List<Stop> getStopFromDay() throws Exception{
        String strDateFormat = "dd/MM/yyyy-HH:mm:ss";
        Date now= new Date();
        String dateNow= FormatUtil.formatDate(now, "dd/MM/yyyy");
        now=FormatUtil.parseDate(dateNow + "-17:59:59", strDateFormat);
        long start=now.getTime()-2*3600000;

        List<Stop> items= new ArrayList<Stop>();
        HashMap<Long,Stop> mapStop= new HashMap<Long, Stop>();
        mapStop=beanCacheDB.getListStopAll();
        for (Long key:mapStop.keySet()){
            if(mapStop.get(key).getArrivalTime()>=start ||
                    mapStop.get(key).getDepartureTime()>=start ){
                items.add(mapStop.get(key));
            }
        }
        SapXepStopsTheoTime(items);
        List<Stop> itemsCopy= (List<Stop>) ObjectCopier.copy(items);
        return itemsCopy;
    }
    /*************************Lay danh sach mapTrain*****************************/

    private HashMap<Long, List<Stop>> getMapTrain(List<Train> trains,List<Stop> stops){
        HashMap<Long, List<Stop>> trainPut= new HashMap<Long, List<Stop>>();
        for (Train train : trains) {
            List<Stop> stops1 = new ArrayList<Stop>();
            for (Stop item : stops) {
                if (item.getTrain().getId() == train.getId()) {
                    stops1.add(item);
                }
            }
            trainPut.put(train.getId(), stops1);
        }
        return trainPut;
    }
    /*********Loc conflicts de chi lay conflict dau tien voi moi~ tau`*******/
    private List<Conflict> XoaXungDotCuaTrain(List<Conflict> conflicts, Train train){
        HashMap<Long, Long> trainPut= new HashMap<Long, Long>();
        if(conflicts.size()==0){
            return conflicts;
        }
        for(int i=0;i<conflicts.size();i++){
            if ( conflicts.get(i).getTrainA().getId() == train.getId() || conflicts.get(i).getTrainB().getId() == train.getId()) {
                conflicts.remove(i);
            }
        }

        return conflicts;
    }
    /*************************Tim xung dot cho mot tau*****************************/
    private List<Conflict> TimXungDotCuaTrain(long trainId,int priority) throws  Exception{
        List<Conflict> conflicts = new ArrayList<Conflict>();
        Train currentTrain= new Train();
        for(Train item:trains){
            if(item.getId()==trainId){
                currentTrain=item;
            }
        }
        if(currentTrain.getId()==0){
            return conflicts;
        }
        List<Stop> currentTrainStops = mapTrain.get(currentTrain.getId());
        for (int j = 0; j < trains.size(); j++) {
            if (trains.get(j).getId() == currentTrain.getId()) {
                continue;
            }
            //tim de xu ly het voi tung muc xung dot mot
            if (trains.get(j).getTrainReference().getPriority() >= priority) {
                Train compareTrain = trains.get(j);
                // compare currentTrain and compareTrain
                List<Stop> compareTrainStops = mapTrain.get(compareTrain.getId());
                for (int m = 0; m < currentTrainStops.size() - 1; m++) {
                    Stop currentTrainStopFrom = currentTrainStops.get(m);
                    Stop currentTrainStopTo = currentTrainStops.get(m + 1);
                    String currenFromStation = currentTrainStopFrom.getStationStage().getStation().getId();
                    String currenToStation = currentTrainStopTo.getStationStage().getStation().getId();
                    if (currenFromStation.equals(currenToStation)) {
                        continue;
                    }

                    for (int n = 0; n < compareTrainStops.size() - 1; n++) {
                        Stop compareTrainStopFrom = compareTrainStops.get(n);
                        Stop compareTrainStopTo = compareTrainStops.get(n + 1);
                        String compareFromStation = compareTrainStopFrom.getStationStage().getStation().getId();
                        String compareToStation = compareTrainStopTo.getStationStage().getStation().getId();
                        if (compareFromStation.equals(compareToStation)) {
                            continue;
                        }

                        long a1 = currentTrainStopFrom.getDepartureTime();
                        long a2 = currentTrainStopTo.getArrivalTime();
                        long b1 = compareTrainStopFrom.getDepartureTime();
                        long b2 = compareTrainStopTo.getArrivalTime();
                        boolean conflicted = false;

                        //////////////////////////////////////////////////////////
                        // detect common stations (from-to), no-direction
                        if ((currenFromStation.equals(compareFromStation))
                                && (currenToStation.equals(compareToStation))) {
                            if ((a1 == b1) || (a2 == b2) || ((a1 < b1) && (a2 > b2)) || ((a1 > b1) && (a2 < b2)) || ((a1 - 180000) < b1 && b1 < (a2 + 180000)) || ((b1 - 180000) < a1 && a1 < (b2 + 180000))) {
                                conflicted = true;
                            }
                        } else if ((currenFromStation.equals(compareToStation))
                                && (currenToStation.equals(compareFromStation))) { // reversed direction
                            if ((a1 == b2) || (a2 == b1) || (a1 < b2 && a2 > b1) || ((a1 - 180000) < b1 && b1 < (a2 + 180000)) || ((b1 - 180000) < a1 && a1 < (b2 + 180000))) {
                                conflicted = true;
                            }
                        }

                        if (conflicted) {
                            // Found RULE-CONFLICT-001: Sử dụng chung khu gian
                            Conflict conflict = new Conflict();
                            conflict.setTrainA(currentTrain);
                            conflict.setTrainB(compareTrain);
                            conflict.setStopA1(currentTrainStopFrom);
                            conflict.setStopA2(currentTrainStopTo);
                            conflict.setStopB1(compareTrainStopFrom);
                            conflict.setStopB2(compareTrainStopTo);
                            conflicts.add(conflict);
                        }
                    }
                }
            }
        }
        return conflicts;
    }
    /*************************Loc lai nhung stop *****************************/

    private void LocStopTruoc18H() throws Exception{

        String strDateFormat = "dd/MM/yyyy-HH:mm:ss";
        Date now= new Date();
        String dateNow= FormatUtil.formatDate(now, "dd/MM/yyyy");
        now=FormatUtil.parseDate(dateNow + "-18:00:00", strDateFormat);
        long time18=now.getTime()+86400000L;
        for (Train train : trains) {
            List<Stop> stops = (List<Stop>)ObjectCopier.copy(mapTrain.get(train.getId()));
            int index=-1;
            for (int i=0;i<stops.size();i++) {
                if(stops.get(i).getArrivalTime()>time18){
                    index=i;
                    break;
                }
            }
            if(index>0){
                for(int i=stops.size()-1;i>=index;i--){
                    stops.remove(i);
                }
//                mapTrain.remove(train.getId());
                mapTrain.put(train.getId(),stops);
            }

        }
    }

    /*************************Luu ket qua*****************************/
    public List<Stop> getStopUpdate() throws Exception{
        List<Stop> stopsList = new ArrayList<Stop>();
        List<Stop> stopsFromDay=getStopFromDay();//lay toan bo stop ca? sau ngay
        List<Train> trainAll=getListTrainsByStops(stopsFromDay);//lay toan bo train ca sau ngay
        HashMap<Long, List<Stop>> mapTrainAll=getMapTrain(trainAll,stopsFromDay);//Gan' ListStop cho tung` train
        for (Train train : trains) {
            List<Stop> stops1 = mapTrain.get(train.getId());
            List<Stop> stops2=mapTrainAll.get(train.getId());
            int index=-1;
            long diff=0;
            for (Stop item : stops1) {
                stopsList.add(item);
            }
            if(stops1.size()<stops2.size()){
                Stop s1=stops1.get(stops1.size()-1);
                for(int i=0;i<stops2.size();i++){
                    if(s1.getId()==stops2.get(i).getId()){
                        index=i;
                        break;
                    }
                }
                if(index>0 && index<stops2.size()-1){
                    diff=s1.getDepartureTime()-stops2.get(index).getDepartureTime();

                    stops2.get(index).setArrivalTime(s1.getArrivalTime());
                    stops2.get(index).setDepartureTime(s1.getDepartureTime());

                    for(int i=index+1;i<stops2.size();i++){
                        Stop itemStop = stops2.get(i);
                        if(itemStop.getStationStage().getStation().getId().equals(stops2.get(i - 1).getStationStage().getStation().getId())) {
                            stops2.get(i).setArrivalTime(stops2.get(i - 1).getArrivalTime());
                            stops2.get(i).setDepartureTime(stops2.get(i - 1).getDepartureTime());
                        }else  {
                            if (itemStop.getDepartureTime() > itemStop.getArrivalTime()) {
                                long time = itemStop.getDepartureTime() - (itemStop.getArrivalTime() + itemStop.getWait() * 60000);
                                stops2.get(i).setArrivalTime(itemStop.getArrivalTime() + diff);
                                stops2.get(i).setDepartureTime(itemStop.getDepartureTime() + diff - time);
                                diff = diff - time;
                                //ko can xet itemStop.wait!=null hay ko vi mac dinh =0 trong db
                            } else {
                                stops2.get(i).setArrivalTime(itemStop.getArrivalTime() + diff);
                                stops2.get(i).setDepartureTime(itemStop.getDepartureTime() + diff);
                            }
                        }
                        stopsList.add(stops2.get(i));
                    }
                }
            }
        }
        this.addActionError("Đã xử lý xung đột!");
        return stopsList;
    }

    public void init(){
        beanCacheDB =(CacheDB)this.getBean("beanCacheDB");
    }

/**********************dinh nghia doi tuong xung dot*****************************/
    public static class Conflict implements Serializable {
        private static final long serialVersionUID = 5358485895404902078L;

        // 1: Sử dụng chung khu gian
        private int conflictType = 1;
        private Train trainA;
        private Train trainB;
        private Stop stopA1;
        private Stop stopA2;
        private Stop stopB1;
        private Stop stopB2;

        public Train getTrainA() {
            return trainA;
        }

        public void setTrainA(Train trainA) {
            this.trainA = trainA;
        }

        public Train getTrainB() {
            return trainB;
        }

        public void setTrainB(Train trainB) {
            this.trainB = trainB;
        }

        public Stop getStopA1() {
            return stopA1;
        }

        public void setStopA1(Stop stopA1) {
            this.stopA1 = stopA1;
        }

        public Stop getStopA2() {
            return stopA2;
        }

        public void setStopA2(Stop stopA2) {
            this.stopA2 = stopA2;
        }

        public Stop getStopB1() {
            return stopB1;
        }

        public void setStopB1(Stop stopB1) {
            this.stopB1 = stopB1;
        }

        public Stop getStopB2() {
            return stopB2;
        }

        public void setStopB2(Stop stopB2) {
            this.stopB2 = stopB2;
        }
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }


    public HashMap<Long, List<Stop>> getMapTrain() {
        return mapTrain;
    }

    public void setMapTrain(HashMap<Long, List<Stop>> mapTrain) {
        this.mapTrain = mapTrain;
    }
}
