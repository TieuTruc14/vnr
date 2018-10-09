package com.eposi.vnr.web.scheduling;

import com.eposi.common.util.FormatUtil;
import com.eposi.common.web.AbstractAction;
import com.eposi.vnr.model.*;
import com.eposi.vnr.persitence.VnrDao;
import com.eposi.vnr.service.CacheDB;

import java.util.*;

public class DailyUpdateStopAction extends AbstractAction {
    private static final long serialVersionUID = 1910623380434416134L;
    private String start; // start date
    private String end; // end date
    private String workbenchId;
    private String check;
    private String checkEdit;
    private String stationId;
    private String checkVehicle;
    private String vehicle;
    ////////////////
    private String trainId;
    private String stationStageId;
    private String arrivalTime;
    private String departureTime;


    public String execute() throws Exception {
        CacheDB beanCacheDB=(CacheDB)this.getBean("beanCacheDB");
        String strDateFormat = "dd/MM/yyyy-HH:mm:ss";
        try {
            Date arrival = FormatUtil.parseDate(arrivalTime, strDateFormat);
            Date departure  = FormatUtil.parseDate(departureTime, strDateFormat);
            long trainid=Long.parseLong(trainId);

            List<Stop> stops=this.findStopByTrain(trainid);

            if(stops==null || stops.size()==0){
                return ERROR;
            }
            int indexStop=-1;
            long diff=0;
//            List<Workbench> workbenches=beanCacheDB.getWorkbenchList();
//            Workbench itemWk=new Workbench();
//            for(Workbench wk:workbenches){
//                if(wk.getId()==1){
//                    itemWk=wk;
//                }
//            }
            Date now= new Date();
            String dateNow= FormatUtil.formatDate(now, "dd/MM/yyyy");
            now=FormatUtil.parseDate(dateNow + "-18:00:00", strDateFormat);
            long time18=now.getTime()-86400000;

            for(int i=0;i<stops.size();i++){
                if(stops.get(i).getStationStage().getStation().getId().equals(stationId.trim())){
                    if(stops.get(i).getArrivalTime()<time18){
                        return ERROR;//neu nho qua thoi gian khoa ke hoach
                    }
                    indexStop =i;
                    diff = departure.getTime() - stops.get(i).getDepartureTime();
                    break;
                }
            }

            if(indexStop>=0){
                if(checkVehicle!=null && checkVehicle.equals("on")){
                    List<Stop> stopsInWorkbench=this.getStopByWorkbench(trainid,Integer.parseInt(workbenchId));//beanVnrDao.findStopByTrainAndListStationStage(beanVnrDao.getTrain(trainid),Integer.parseInt(workbenchId));

                    Stop stop= new Stop();
                    if(stopsInWorkbench!=null && stopsInWorkbench.size()>0){
                        for(int i=0;i<stopsInWorkbench.size();i++){
                            if(stopsInWorkbench.get(i).getStationStage().getStation().getId().equals(stationId.trim())){
                                stopsInWorkbench.get(i).setVehicle(vehicle);
                                stop=stopsInWorkbench.get(i);
                                break;
                            }
                        }
                        beanCacheDB.putStop(stop);
                    }
                }

                if(check!=null && check.equals("on")){
                    List<Stop> deleteList=new ArrayList<Stop>();
                    for(int i=indexStop-1;i>=0;i--){
                        Stop itemStop=stops.get(i);
                        if(itemStop.getStationStage().getStation().getId().equals(stationId.trim())){
                            continue;//bo qua khi stop nay chinh la ga do' ma dung' truoc trong stops nhung thuoc khu doan khac
                        }
                        deleteList.add(itemStop);
                    }
                    for(Stop item:deleteList){
                        beanCacheDB.deleteStop(item);
                    }
                }

                if(checkEdit!=null && checkEdit.equals("on")) {
                    List<Stop> stopList = new ArrayList<Stop>();
                    for (int i = indexStop; i < stops.size(); i++) {
                        Stop itemStop = stops.get(i);
                        if (i == indexStop) {
                            stops.get(i).setArrivalTime(arrival.getTime());
                            stops.get(i).setDepartureTime(departure.getTime());

                        } else if (itemStop.getStationStage().getStation().getId().equals(stops.get(i - 1).getStationStage().getStation().getId())) {
                            stops.get(i).setArrivalTime(stops.get(i - 1).getArrivalTime());
                            stops.get(i).setDepartureTime(stops.get(i - 1).getDepartureTime());
                        } else if (itemStop.getDepartureTime() > itemStop.getArrivalTime()) {
                            //ko can xet itemStop.wait!=null hay ko vi mac dinh =0 trong db
                            long time = itemStop.getDepartureTime() - (itemStop.getArrivalTime() + itemStop.getWait() * 60000);
                            stops.get(i).setArrivalTime(itemStop.getArrivalTime() + diff);
                            stops.get(i).setDepartureTime(itemStop.getDepartureTime() + diff - time);
                            diff = diff - time;
                        } else {
                            stops.get(i).setArrivalTime(itemStop.getArrivalTime() + diff);
                            stops.get(i).setDepartureTime(itemStop.getDepartureTime() + diff);
                        }
                        stopList.add(stops.get(i));
                    }
                    for (Stop stop : stopList) {
                        beanCacheDB.putStop(stop);
                    }
                }

                Thread.sleep(1500);//cho tam dung 1.5s de kip dong bo liststopAll, giup hien thi dung' luon khi delete
            }
        } catch(Exception e) {
            return ERROR;
        }
        return SUCCESS;
    }

    /***********find stop by train*************/
    private List<Stop> findStopByTrain(long trainId){
        CacheDB beanCacheDB=(CacheDB)this.getBean("beanCacheDB");
        List<Stop> items=new ArrayList<Stop>();
        HashMap<Long,Stop> stops= beanCacheDB.getListStopAll();
        for(Long key:stops.keySet()){
            if(stops.get(key).getTrain().getId()==trainId){
                items.add(stops.get(key));
            }
        }
        SapXepStopsTheoTime(items);
        return  items;
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
    private List<Stop> getStopByWorkbench(long trainId,int workId){
        CacheDB beanCacheDB=(CacheDB)this.getBean("beanCacheDB");
        HashMap<Long,Stop> mapstops=beanCacheDB.getListStopAll();
        List<Stage> stages=this.getStages(workId);
        List<StationStage> stationStages=this.getlistStationStageByStages(stages);
        HashMap<String,String> mapStationStage= new HashMap<String, String>();
        for(StationStage item:stationStages){
            mapStationStage.put(item.getId(),item.getId());
        }
        List<Stop> items= new ArrayList<Stop>();
        for(Long key:mapstops.keySet()){
            Stop item= mapstops.get(key);
            if(mapStationStage.get(item.getStationStage().getId())!=null && item.getTrain().getId()==trainId){
                items.add(item);
            }
        }
        SapXepStopsTheoTime(items);
        return items;
    }
//get list stages
    public List<Stage> getStages(int workId) {
        CacheDB beanCacheDB=(CacheDB)this.getBean("beanCacheDB");

        List<Stage> items= beanCacheDB.getStageList();
        List<Stage> list=new ArrayList<Stage>();
        for(Stage item:items){
            if(item.getWorkbench().getId()==workId){
                list.add(item);
            }
        }
        return list;
    }
    //loc stationstage theo stages
    public List<StationStage> getlistStationStageByStages(List<Stage> items){
        CacheDB beanCacheDB=(CacheDB)this.getBean("beanCacheDB");
        List<StationStage> list=beanCacheDB.getStationStageList();
        List<StationStage> listAll=new ArrayList<StationStage>();
        HashMap<String,String> mapStage= new HashMap<String, String>();
        for(Stage item: items){
            mapStage.put(item.getId(),item.getId());
        }
        for(StationStage stationStage:list){
            if(mapStage.get(stationStage.getStage().getId())!=null){
                listAll.add(stationStage);
            }
        }
        return listAll;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

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

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getStationStageId() {
        return stationStageId;
    }

    public void setStationStageId(String stationStageId) {
        this.stationStageId = stationStageId;
    }

    public String getWorkbenchId() {
        return workbenchId;
    }

    public void setWorkbenchId(String workbenchId) {
        this.workbenchId = workbenchId;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getCheckVehicle() {
        return checkVehicle;
    }

    public void setCheckVehicle(String checkVehicle) {
        this.checkVehicle = checkVehicle;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getCheckEdit() {
        return checkEdit;
    }

    public void setCheckEdit(String checkEdit) {
        this.checkEdit = checkEdit;
    }
}
