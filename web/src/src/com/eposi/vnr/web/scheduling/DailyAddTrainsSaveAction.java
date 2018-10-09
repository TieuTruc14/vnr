package com.eposi.vnr.web.scheduling;

import com.eposi.common.util.FormatUtil;
import com.eposi.common.web.AbstractAction;
import com.eposi.vnr.model.Stop;
import com.eposi.vnr.model.StopReference;
import com.eposi.vnr.model.Train;
import com.eposi.vnr.model.TrainReference;
import com.eposi.vnr.persitence.VnrDao;
import com.eposi.vnr.service.CacheDB;
import org.apache.commons.lang3.StringUtils;
import sun.misc.Cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DailyAddTrainsSaveAction extends AbstractAction {
    private static final long serialVersionUID = -4484267512086313573L;

    private List<TrainReferenceItem> trainReferences;
    private List<Stop> stops= new ArrayList<Stop>();
    private List<Train> trains=new ArrayList<Train>();
    private String datePlan="";

    public String execute() throws Exception {
        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");
        CacheDB beanCacheDB=(CacheDB)this.getBean("beanCacheDB");
        if (trainReferences != null) {
            if (datePlan == null || datePlan.isEmpty() ) {
                return SUCCESS;
            }
            Date date1 =null;
            try {
                date1 = FormatUtil.parseDate(datePlan, "dd/MM/yyyy");
            } catch (Exception e) {
                e.printStackTrace();
            }


            for (TrainReferenceItem item : trainReferences) {
                if (StringUtils.equals("on", item.getCheckbox())) {
                    // Thêm Train
                    TrainReference trainReference = beanVnrDao.getTrainReference(item.getId());
                    boolean addTrainOK = false;
                    Train train = new Train();
                    Date date =date1;
                    if (trainReference != null) {
                        if(trainReference.getDepartureTime()>=64800000){
                            date=new  Date(date.getTime() + trainReference.getDepartureTime());
                        }else{
                            date=new  Date(date.getTime() + trainReference.getDepartureTime()+86400000);
                        }
                        train.setBeginTime(date);
                        train.setTrainReference(trainReference);
                        if(checkTrainIsNull(train.getTrainReference(),train.getBeginTime())==null){
                            beanCacheDB.addTrain(train);
                            addTrainOK = true;
                        }else{
                            addTrainOK=false;
                        }
                    }
                    // Thêm Stops
                    if (addTrainOK) {
                        List<StopReference> stopReferenceList = beanVnrDao.getStopReferenceListByTrainReference(trainReference);
                        if (stopReferenceList != null) {
                            for (StopReference stopReference : stopReferenceList) {
                                Stop stop = new Stop();
                                stop.setArrivalTime(train.getBeginTime().getTime() + stopReference.getArrivalTime());
                                stop.setDepartureTime(train.getBeginTime().getTime() + stopReference.getDepartureTime());
                                stop.setStationStage(stopReference.getStationStage());
                                stop.setTrain(train);
                                stop.setWait(stopReference.getWait());
                                stops.add(stop);
                            }
                        }
                    }
                }
            }

            for(Stop item:stops){
                beanCacheDB.addStop(item);
            }
        }
        return SUCCESS;
    }
    public String delete() throws Exception {
        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");
        CacheDB beanCacheDB=(CacheDB)this.getBean("beanCacheDB");
        if (trainReferences != null) {
            if (datePlan == null || datePlan.isEmpty() ) {
                return SUCCESS;
            }
            for (TrainReferenceItem item : trainReferences) {
                if (StringUtils.equals("on", item.getCheckbox())) {
                    // Thêm Train
                    TrainReference trainReference = beanVnrDao.getTrainReference(item.getId());
                    boolean deleteTrainOK = false;
                    Train train = new Train();
                    if (trainReference != null) {
                        Date date =null;
                        try {
                            date = FormatUtil.parseDate(datePlan, "dd/MM/yyyy");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if(trainReference.getDepartureTime()>=64800000){
                            date=new  Date(date.getTime() + trainReference.getDepartureTime());
                        }else{
                            date=new  Date(date.getTime() + trainReference.getDepartureTime()+86400000);
                        }
                        train.setBeginTime(date);
                        train.setTrainReference(trainReference);
                        if(checkTrainIsNull(train.getTrainReference(),train.getBeginTime())!=null){
                            deleteTrainOK = true;
                        }else{
                            deleteTrainOK=false;
                        }
                    }
                    // Xóa Stops
                    if (deleteTrainOK) {
                        Train itemTrain=checkTrainIsNull(train.getTrainReference(),train.getBeginTime());
                        DailyAction beanDailyAction=(DailyAction)this.getBean("beanDailyAction");

                        List<Stop> stopsdelete=beanDailyAction.getStopsByTrain(itemTrain);
                        if (stopsdelete != null && stopsdelete.size()>0) {
                            for (Stop itemStop : stopsdelete) {
                                stops.add(itemStop);
                            }
                        }
                        trains.add(itemTrain);
                    }
                }
            }
            for (Stop item:stops){
                beanCacheDB.deleteStop(item);
            }
            for(Train item:trains){
                beanCacheDB.putTrainDelete(item);
            }
        }
        return SUCCESS;
    }

    /***************/
    private Train checkTrainIsNull(TrainReference item,Date beginTime){
        CacheDB beanCacheDB=(CacheDB)this.getBean("beanCacheDB");
        HashMap<Long,Train> mapTrain=beanCacheDB.getListTrainAll();
        for(Long key:mapTrain.keySet()){
            if(mapTrain.get(key).getBeginTime().getTime()==beginTime.getTime()&&
                    mapTrain.get(key).getTrainReference().getId().equals(item.getId())){
                return mapTrain.get(key);
            }
        }
        return null;

    }

    public List<TrainReferenceItem> getTrainReferences() {
        return trainReferences;
    }

    public void setTrainReferences(List<TrainReferenceItem> trainReferences) {
        this.trainReferences = trainReferences;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    public String getDatePlan() {
        return datePlan;
    }

    public void setDatePlan(String datePlan) {
        this.datePlan = datePlan;
    }


    public static class TrainReferenceItem {
        private String id;
        private String checkbox;

        public String getCheckbox() {
            return checkbox;
        }

        public void setCheckbox(String checkbox) {
            this.checkbox = checkbox;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
