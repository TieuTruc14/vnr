package com.eposi.vnr.web.scheduling;

import com.eposi.common.util.FormatUtil;
import com.eposi.common.web.AbstractAction;
import com.eposi.vnr.data.SampleDataDemo;
import com.eposi.vnr.model.*;
//import com.eposi.vnr.model.Stop;
//import com.eposi.vnr.model.Train;
import com.eposi.vnr.persitence.VnrDao;
import com.eposi.vnr.service.CacheDB;
import com.eposi.vnr.web.infrastructure.StationAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.*;

/**
 * Created by tuanpa on 6/21/14.
 */
public class DailyAction extends AbstractAction {
    private static final long serialVersionUID = -8114000184000653891L;

    private String start; // start date
    private String end; // end date
    private String workbenchId; //workbench Id
    private Date dateStart = null;
    private Date dateEnd = null;
    private String timelock;
    private CacheDB beanCacheDB;
    public void init(){
        beanCacheDB =(CacheDB)this.getBean("beanCacheDB");
    }

    public String execute() throws Exception {
        String strDateFormat = "dd/MM/yyyy-HH:mm:ss";
        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");

       /* List<Workbench> workbenches=beanCacheDB.getWorkbenchList();
        Workbench item=new Workbench();
        for(Workbench wk:workbenches){
            if(wk.getId()==1){
                item=wk;
            }
        }

        if((timelock==null)||(timelock.equals(""))){
            timelock=FormatUtil.formatDate(item.getTimeLock(), "dd/MM/yyyy");
        }else if(timelock.equals(FormatUtil.formatDate(item.getTimeLock(), "dd/MM/yyyy"))){

        }else{
            Date time=FormatUtil.parseDate(timelock+"-00:00:00",strDateFormat);
            item.setTimeLock(time);
            beanVnrDao.saveWorkbench(item);
        }*/

        // date
        if (StringUtils.isEmpty(start)) {
            dateStart = DateUtils.addMilliseconds(DateUtils.truncate(new Date(), Calendar.DATE), 64800000);
            start = FormatUtil.formatDate(dateStart, strDateFormat);
        }
        if (StringUtils.isEmpty(end)) {
            dateEnd = DateUtils.addMilliseconds(dateStart, 86400000);
            end = FormatUtil.formatDate(dateEnd, strDateFormat);
        }

        try {
            dateStart = FormatUtil.parseDate(start, strDateFormat);
        } catch(Exception e)  {
        }
        try {
            dateEnd = FormatUtil.parseDate(end, strDateFormat);
        } catch(Exception e)  {
        }

        return SUCCESS;
    }



    public List<StageTrainSchedule> getStageTrainScheduleListByStage(Stage stage) {
//        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");

        // Key is trainId
        Map<Long, List<Stop>> mapTrainStops = new HashMap<Long, List<Stop>>();
        List<StationStage> stationStageList=this.getlistStationStageByStage(stage);

        List<Stop> stops = this.getListStopByListStationStage(stationStageList);
        for (Stop stop : stops) {
            Long trainKey = stop.getTrain().getId();
            List<Stop> stopList = mapTrainStops.get(trainKey);
            if (stopList == null) {
                stopList = new ArrayList<Stop>();
            }
            stopList.add(stop);

            mapTrainStops.put(trainKey, stopList);
        }

        List<StageTrainSchedule> items = new ArrayList<StageTrainSchedule>();

        for (Long trainKey : mapTrainStops.keySet()) {
            Train train = this.getTrain(trainKey);
            StageTrainSchedule stageTrainSchedule = new StageTrainSchedule();
            stageTrainSchedule.setTrain(train);
            stageTrainSchedule.setStops(mapTrainStops.get(trainKey));

            items.add(stageTrainSchedule);
        }

        return items;
    }
    //ham random
   /* public  int getRandomNumberWithin(int lower, int upper) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(upper - lower) + lower;
       *//* if(randomNumber == lower) {
            return lower + 1;
        }
        else {*//*
          return randomNumber;
    }*/
    public  int getRandomNumber(int upper) {
        Random rand = new Random();
        int randomNumber=0;
        if(upper>0){
            randomNumber = rand.nextInt(upper-1);
        }//do truyen vao la size
        return randomNumber;
    }

    public List<StationStage> getStationStages(Stage stage) {

        return this.getlistStationStageByStage(stage);
    }

    public List<Stage> getStages() {

        if(workbenchId==null){
            return null;
        }
        int workId= Integer.parseInt(workbenchId);
        List<Stage> items= beanCacheDB.getStageList();
        List<Stage> list=new ArrayList<Stage>();
        for(Stage item:items){
            if(item.getWorkbench().getId()==workId){
                list.add(item);
            }
        }
        SapXepStage(list);
        return list;
    }

    public List<Stop> getStopsByTrain(Train train){

        HashMap<Long,Stop> items= beanCacheDB.getListStopAll();
        List<Stop> list=new ArrayList<Stop>();
        long trainId=train.getId();
        for(Long key:items.keySet()){
            if(items.get(key).getTrain().getId()==trainId){
                list.add(items.get(key));
            }
        }
        SapXepStopsTheoTime(list);
        return list;
    }
    public Workbench getWorkbenchById(){
//        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");
        int wk=Integer.parseInt(workbenchId);
        List<Workbench> items= beanCacheDB.getWorkbenchList();
        for(Workbench item:items){
            if(item.getId()==wk){
                return item;
            }
        }
        return null;
    }

    /*********************************************************************/
    //loc stationstage theo stage
    public List<StationStage> getlistStationStageByStage(Stage item){
        List<StationStage> list=beanCacheDB.getStationStageList();
        List<StationStage> listStage=new ArrayList<StationStage>();
        for(StationStage stationStage:list){
            if(stationStage.getStage().getId().equals(item.getId())){
                listStage.add(stationStage);
            }
        }
        return listStage;
    }
    //loc stop
    public List<Stop> getListStopByListStationStage(List<StationStage> items){
        HashMap<String,String> map=new HashMap<String, String>();
        for(StationStage st:items){
            map.put(st.getId(),st.getId());
        }
        List<Stop> list= new ArrayList<Stop>();
        HashMap<Long,Stop> listAll=beanCacheDB.getListStopAll();
        for(Long key:listAll.keySet()){
            Stop stop=listAll.get(key);
            if((map.get(stop.getStationStage().getId())!=null) &&
                    ((stop.getArrivalTime()>=dateStart.getTime() &&stop.getArrivalTime()<=dateEnd.getTime()) ||
                            (stop.getDepartureTime()>=dateStart.getTime() &&stop.getDepartureTime()<=dateEnd.getTime()) )){
                list.add(stop);
            }
        }
        SapXepStopsTheoTime(list);
        return list;
    }

    /**********************Sắp xếp Stops theo time tang dan`**********************************/
    private void SapXepStopsTheoTime(List<Stop> stops){
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

    /*getTrain by id*/
    private Train getTrain(long id){
        HashMap<Long,Train> items= beanCacheDB.getListTrainAll();
        Train item= items.get(id);

        return item;
    }

    //sap xep theo stt stage
    private void SapXepStage(List<Stage> items){
        Collections.sort(items, new Comparator<Stage>() {
            @Override
            public int compare(Stage t1, Stage t2) {
                if (t1.getStt()<t2.getStt()){
                    return -1;
                }else{
                    if (t1.getStt()==t2.getStt()){
                        return 0;
                    }else{
                        return 1;
                    }
                }
            }
        });
    }
    /*********************************************************************/
    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getWorkbenchId() {
        return workbenchId;
    }

    public void setWorkbenchId(String workbenchId) {
        this.workbenchId = workbenchId;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public String getTimelock() {
        return timelock;
    }

    public void setTimelock(String timelock) {
        this.timelock = timelock;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public static class StageTrainSchedule {
        private Train train;
        private List<Stop> stops;

        public Train getTrain() {
            return train;
        }

        public void setTrain(Train train) {
            this.train = train;
        }

        public List<Stop> getStops() {
            return stops;
        }

        public void setStops(List<Stop> stops) {
            this.stops = stops;
        }
    }

}
