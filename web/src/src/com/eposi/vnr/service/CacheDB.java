package com.eposi.vnr.service;

import com.eposi.common.util.AbstractBean;
import com.eposi.vnr.model.*;
import com.eposi.vnr.persitence.VnrDao;
import edu.emory.mathcs.backport.java.util.LinkedList;

import java.util.*;

/**
 * Created by Tuan on 4/2/2015.
 */
public class CacheDB extends AbstractBean{
    private List<Stop>  mapStop = new LinkedList();
    private List<Stop>  mapStopDelete = new LinkedList();
    private List<Train>  mapTrain = new LinkedList();
    private List<Train>  mapTrainDelete = new LinkedList();
    private HashMap<Long,Stop>  listStopAll = new HashMap<Long, Stop>();
    private HashMap<Long,Train>  listTrainAll = new HashMap<Long, Train>();
    private boolean statusStop=true;//da dong bo stop chua
    private boolean statusTrain=true;//da dong bo train chua
    private boolean statusStopDelete=true;
    private List<Train>  mapTrainDeleteDB = new LinkedList();
    private List<Stop>  mapStopDeleteDB = new LinkedList();
    private boolean statusTrainDelete=true;
    private VnrDao beanVnrDao;
    private List<Workbench> workbenchList=new ArrayList<Workbench>();
    private List<Stage> stageList= new ArrayList<Stage>();
    private List<StationStage> stationStageList= new ArrayList<StationStage>();
    private long maxIdStop;
    private long maxIdTrain;
    private boolean synch=true;

    public void init(){
        beanVnrDao  =(VnrDao)this.getBean("beanVnrDao");
        Date date1=new Date(new Date().getTime()-30*86400000L);
        Date date2=new Date(new Date().getTime()-40*86400000L);

        List<Stop> StopAll=beanVnrDao.getListStopAll(date1);
        List<Train> TrainAll=beanVnrDao.getTrainsByTime(date2);
        for(Stop item:StopAll){
            item.setStatus(true);
            listStopAll.put(item.getId(),item);
        }
        for(Train item:TrainAll){
            item.setStatus(true);
            listTrainAll.put(item.getId(),item);
        }
        workbenchList=beanVnrDao.listWorkbenchs();
        stageList=beanVnrDao.getStages();
        stationStageList=beanVnrDao.listStationStage();
        SapXepStationStage(stationStageList);
        SapXepStage(stageList);
        maxIdStop=findMaxId(listStopAll);
        maxIdTrain=findMaxIdTrain(listTrainAll);
        if(maxIdStop>0){
            maxIdStop++;
        }else{
            maxIdStop=1;
        }
        if(maxIdTrain>0){
            maxIdTrain++;
        }else{
            maxIdTrain=1;
        }

    }

    public void putStop(Stop stop){
       mapStop.add(stop);
    }

    public void addStop(Stop item){
        item.setId(maxIdStop);
        mapStop.add(item);
        maxIdStop++;
    }

    public void deleteStop(Stop item){
        mapStopDeleteDB.add(item);
        mapStopDelete.add(item);
    }

    public void putTrain(Train train){
        mapTrain.add(train);
    }

    public void addTrain(Train train){
        train.setId(maxIdTrain);
        mapTrain.add(train);
        maxIdTrain++;
    }

    public void putTrainDelete(Train train){
        mapTrainDeleteDB.add(train);
        mapTrainDelete.add(train);
    }


    public synchronized void update(){
        //Update MapStop to DB
        if(mapTrain.size()>0){//add hay edit thi trc nhung delete thi sau stop
            for(Train item:mapTrain){
                item.setStatus(false);
                listTrainAll.put(item.getId(),item);
            }
            mapTrain.clear();
            statusTrain=false;
        }
        if(mapStop.size()>0){
            for(Stop item : mapStop){
                item.setStatus(false);
                listStopAll.put(item.getId(),item);
            }
            mapStop.clear();
            statusStop=false;
        }

        if(mapStopDelete.size()>0){
            for(Stop item : mapStopDelete){
                listStopAll.remove(item.getId());
            }
            mapStopDelete.clear();
        }
        if(mapTrainDelete.size()>0){
            for(Train item : mapTrainDelete){
                listTrainAll.remove(item.getId());
            }
            mapTrainDelete.clear();
        }

    }

    public synchronized  void updateDB(){
        if(!statusTrain){
            List<Train> items= new ArrayList<Train>();
            for(Long key:listTrainAll.keySet()){
                if(!listTrainAll.get(key).isStatus()){
                    listTrainAll.get(key).setStatus(true);
                    items.add(listTrainAll.get(key));
                }
            }
            if(items.size()>0){
                beanVnrDao.saveOrUpdateTrains(items);
            }
           statusTrain=true;
        }
        if(!statusStop){
            List<Stop> items= new ArrayList<Stop>();
            for(Long key:listStopAll.keySet()){
                if(!listStopAll.get(key).isStatus()){
                    listStopAll.get(key).setStatus(true);
                    items.add(listStopAll.get(key));
                }
            }
            if(items.size()>0){
                beanVnrDao.saveOrUpdateStops(items);
            }
            statusStop=true;
        }
        if(mapStopDeleteDB.size()>0){
            beanVnrDao.deleteStops(mapStopDeleteDB);
            mapStopDeleteDB.clear();
            statusStopDelete=true;
        }
        if(mapTrainDeleteDB.size()>0){
            beanVnrDao.deleteTrains(mapTrainDeleteDB);
            mapTrainDeleteDB.clear();
            statusTrainDelete=true;
        }

    }

    //sap xep theo do distance stationStage
    private void SapXepStationStage(List<StationStage> items){
        Collections.sort(items, new Comparator<StationStage>() {
            @Override
            public int compare(StationStage t1, StationStage t2) {
                if (t1.getDistance()<t2.getDistance()){
                    return -1;
                }else{
                    if (t1.getDistance()==t2.getDistance()){
                        return 0;
                    }else{
                        return 1;
                    }
                }
            }
        });
    }
    //sap xep theo stt
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

    //Tim max id stop trong list
    private long findMaxId(HashMap<Long,Stop> items){
        long max=0;
        for(Long key:items.keySet()){
            if(items.get(key).getId()>max){
                max=items.get(key).getId();
            }
        }
        return max;
    }
    private long findMaxIdTrain(HashMap<Long,Train> items){
        long max=0;
        for(Long key:items.keySet()){
            if(items.get(key).getId()>max){
                max=items.get(key).getId();
            }
        }
        return max;
    }

    public Long getMaxIdTrain() {
        return maxIdTrain;
    }

    public void setMaxIdTrain(Long maxIdTrain) {
        this.maxIdTrain = maxIdTrain;
    }

    public Long getMaxIdStop() {
        return maxIdStop;
    }

    public void setMaxIdStop(Long maxIdStop) {
        this.maxIdStop = maxIdStop;
    }

    public HashMap<Long, Stop> getListStopAll() {
        return listStopAll;
    }

    public HashMap<Long, Train> getListTrainAll() {
        return listTrainAll;
    }

    public boolean isStatusStop() {
        return statusStop;
    }

    public void setStatusStop(boolean statusStop) {
        this.statusStop = statusStop;
    }

    public boolean isStatusTrain() {
        return statusTrain;
    }

    public void setStatusTrain(boolean statusTrain) {
        this.statusTrain = statusTrain;
    }

    public boolean isStatusStopDelete() {
        return statusStopDelete;
    }

    public void setStatusStopDelete(boolean statusStopDelete) {
        this.statusStopDelete = statusStopDelete;
    }

    public boolean isStatusTrainDelete() {
        return statusTrainDelete;
    }

    public void setStatusTrainDelete(boolean statusTrainDelete) {
        this.statusTrainDelete = statusTrainDelete;
    }

    public List<Workbench> getWorkbenchList() {
        return workbenchList;
    }

    public List<Stage> getStageList() {
        return stageList;
    }

    public List<StationStage> getStationStageList() {
        return stationStageList;
    }

    public boolean isSynch() {
        return synch;
    }

    public void setSynch(boolean synch) {
        this.synch = synch;
    }
}
