package com.eposi.vnr.web.infrastructure;

import com.eposi.common.web.AbstractAction;
import com.eposi.vnr.model.StopReference;
import com.eposi.vnr.model.TrainReference;
import com.eposi.vnr.model.Stage;
import com.eposi.vnr.model.Station;
import com.eposi.vnr.persitence.VnrDao;

import java.util.List;

/**
 * Created by TieuTruc on 11/18/2014.
 */
public class LabelAction extends AbstractAction {
    private List<TrainReference> items;
    private TrainReference item;
    private String labelId;
    private List<StopReference> stopReferences;
    private StopReference stopReference;
    private String status="false";
    private String format="";
    private String result="";
    private String labelName="";
    private List<Station> stations;
    private List<Stage> stages;
    private Stage stage;
    private String stationId;
    private String stageId;
    private String arrival,departure;

    public String execute() throws Exception {
//        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");
//        stations=beanVnrDao.getStations();
//        stages=beanVnrDao.getStages();
//        if(!format.equals("")){
//            if(format.equals("add")){
//                if (item != null) {
//                    if (beanVnrDao.getTrainReference(item.getId()) == null) {
//                        String departure3=departure.split(":")[0];
//                        String departure4=departure.split(":")[1];
//                        long departureTime=Long.parseLong(departure3)*3600*1000+Long.parseLong(departure4)*60*1000;
//                        item.setDepartureTime(departureTime);
//                        beanVnrDao.saveTrainReference(item);
//                        format="";
//                    }
//                }
//            }else
//            if(format.equals("edit")){
//                if (item != null) {
//                    if (beanVnrDao.getTrainReference(item.getId()) != null) {
//                        String departure3=departure.split(":")[0];
//                        String departure4=departure.split(":")[1];
//                        long departureTime=Long.parseLong(departure3)*3600*1000+Long.parseLong(departure4)*60*1000;
//                        item.setDepartureTime(departureTime);
//                        beanVnrDao.saveTrainReference(item);
//                        format="";
//                    }
//                }
//            }else
//            if(format.equals("addPlan")){
//                if (stopReference != null) {
//                    if (beanVnrDao.getTrainReference(stopReference.getId()) == null) {
//                        if (labelId !=null){
//                            long id=Long.parseLong(labelId);
//                            TrainReference lb = beanVnrDao.getTrainReference(id);
//                            stopReference.setTrainReference(lb);
//                            String arrival1=arrival.split(":")[0];
//                            String arrival2=arrival.split(":")[1];
//                            String departure3=departure.split(":")[0];
//                            String departure4=departure.split(":")[1];
//                            long arrivalTime=Long.parseLong(arrival1)*3600*1000+Long.parseLong(arrival2)*60*1000;
//                            long departureTime=Long.parseLong(departure3)*3600*1000+Long.parseLong(departure4)*60*1000;
//
//                            stopReference.setArrivalTime(arrivalTime);
//                            stopReference.setDepartureTime(departureTime);
//                            Stage st=beanVnrDao.getStage(stageId);
//                            Station sta=beanVnrDao.getStation(stationId);
//                            stopReference.setStage(st);
//                            stopReference.setStation(sta);
//                            beanVnrDao.savePlan(stopReference);
//                            format = "";
//                        }
//                    }
//                }
//            }else
//            if(format.equals("editPlan")){
//                if (stopReference != null) {
//                    if (beanVnrDao.getPlan(stopReference.getId()) != null) {
//                        if(labelId!=null){
//                            TrainReference lb=beanVnrDao.getTrainReference(Long.parseLong(labelId));
//                            stopReference.setTrainReference(lb);
//                            String arrival1=arrival.split(":")[0];
//                            String arrival2=arrival.split(":")[1];
//                            String departure3=departure.split(":")[0];
//                            String departure4=departure.split(":")[1];
//                            long arrivalTime=Long.parseLong(arrival1)*3600*1000+Long.parseLong(arrival2)*60*1000;
//                            long departureTime=Long.parseLong(departure3)*3600*1000+Long.parseLong(departure4)*60*1000;
//
//                            stopReference.setArrivalTime(arrivalTime);
//                            stopReference.setDepartureTime(departureTime);
//
//                            beanVnrDao.savePlan(stopReference);
//                            format="";
//
//                        }
//
//                    }
//                }
//            }
//        }
//        items = beanVnrDao.getTrainReferences();
//        labelName="";
//        if(labelId!=null){
//            if(!labelId.isEmpty()||!labelId.equals("")){
//                item=beanVnrDao.getTrainReference(Long.parseLong(labelId));
//                stopReferences =beanVnrDao.findStopReferenceByTrainReference(item);
//                labelName=item.getName();
//            }
//        }

        return SUCCESS;
    }

    public List<TrainReference> getItems() {
        return items;
    }

    public void setItems(List<TrainReference> items) {
        this.items = items;
    }

    public TrainReference getItem() {
        return item;
    }

    public void setItem(TrainReference item) {
        this.item = item;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public List<StopReference> getStopReferences() {
        return stopReferences;
    }

    public void setStopReferences(List<StopReference> stopReferences) {
        this.stopReferences = stopReferences;
    }

    public StopReference getStopReference() {
        return stopReference;
    }

    public void setStopReference(StopReference stopReference) {
        this.stopReference = stopReference;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }
}
