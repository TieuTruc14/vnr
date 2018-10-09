package com.eposi.vnr.web.scheduling;

import com.eposi.common.web.AbstractAction;
import com.eposi.vnr.model.Station;
import com.eposi.vnr.model.StationStage;
import com.eposi.vnr.model.StopReference;
import com.eposi.vnr.model.TrainReference;
import com.eposi.vnr.persitence.VnrDao;

import java.util.List;

/**
 * Created by TieuTruc on 12/16/2014.
 */
public class StopReferenceAction extends AbstractAction {
    private String trainReferenceId;
    private StopReference item;
    private TrainReference trainReference;
    private String stationId;
    private String arrivalTime;
    private String departureTime;
    private Station station;
    private List<StationStage> stationStages;
    private String status = "";
    private String stopReferenceId;
    private String format = "";
    private String wait="";
    private String id;//dung de truyen tro lai trainReference.detail

    public String execute() throws Exception {
        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");
        trainReference = beanVnrDao.getTrainReference(trainReferenceId);

        status = "";
        if (trainReference != null) {
            if (stationId == null || arrivalTime == null || departureTime == null || stationId.isEmpty()
                    || stationId.equals("") || arrivalTime.isEmpty() || arrivalTime.equals("")
                    || departureTime.isEmpty() || departureTime.equals("")) {
                return SUCCESS;
            } else {
                station = beanVnrDao.getStation(stationId);
                if (station != null) {
                    stationStages = beanVnrDao.findByStation(station);
                    if (stationStages != null && stationStages.size() > 0) {
                        List<StopReference> items = beanVnrDao.getStopReferenceByTrainAndStation(trainReference, station);
                        if (items == null || items.size() == 0) {
                            String arival1 = arrivalTime.split(":")[0];
                            String arival2 = arrivalTime.split(":")[1];
                            long arrival = Long.parseLong(arival1) * 3600 * 1000 + Long.parseLong(arival2) * 60 * 1000;
                            String departure1 = departureTime.split(":")[0];
                            String departure2 = departureTime.split(":")[1];
                            long departure = Long.parseLong(departure1) * 3600 * 1000 + Long.parseLong(departure2) * 60 * 1000;

                            for (StationStage stationStage : stationStages) {
                                item = new StopReference();
                                item.setStationStage(stationStage);
                                item.setTrainReference(trainReference);
                                if(wait!=null){
                                    item.setWait(Integer.parseInt(wait));
                                }else{
                                    item.setWait(0);
                                }
                                item.setArrivalTime(arrival);
                                item.setDepartureTime(departure);
                                beanVnrDao.saveStopReference(item);

                            }
                            status = "Thêm thành công!";
                        } else {
                            status = "Không thành công! Mác tàu đã có lịch trình cho ga này!";
                        }

                    }
                }
            }

        }

        return SUCCESS;
    }

    public String edit() throws Exception {
        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");
        if (stopReferenceId == null || stopReferenceId.isEmpty() || stopReferenceId.equals("")) {
            return SUCCESS;
        } else {
            item = beanVnrDao.getStopReference(Long.parseLong(stopReferenceId));

            if (item != null) {
                    trainReferenceId = item.getTrainReference().getId();
                    trainReference = item.getTrainReference();

                if (format.equals("")) {
                    stationId = item.getStationStage().getStation().getId();
                    String departure3 = String.valueOf(item.getDepartureTime() / 3600000);
                    String departure4 = String.valueOf((item.getDepartureTime() % 3600000) / 60000);
                    if (departure4.length() == 1) {
                        departure4 = "0" + departure4;
                    }
                    setDepartureTime(departure3 + ":" + departure4);
                    String arrival1 = String.valueOf(item.getArrivalTime() / 3600000);
                    String arrival2 = String.valueOf((item.getArrivalTime() % 3600000) / 60000);
                    if (arrival2.length() == 1) {
                        arrival2 = "0" + arrival2;
                    }
                    setArrivalTime(arrival1 + ":" + arrival2);
                    wait= String.valueOf(item.getWait());

                } else if (format.equals("edit")) {
                    status = "";
                    if (stationId == null || arrivalTime == null || departureTime == null || stationId.isEmpty()
                            || stationId.equals("") || arrivalTime.isEmpty() || arrivalTime.equals("")
                            || departureTime.isEmpty() || departureTime.equals("")) {
                        return SUCCESS;
                    } else {

                        station = beanVnrDao.getStation(stationId);
                        if (station != null) {
                            List<StopReference> items = beanVnrDao.getStopReferenceByTrainAndStation(trainReference, station);
                            List<StopReference> itemsAll=beanVnrDao.getStopReferenceListByTrainReference(trainReference);
                            if (items != null || items.size() > 0 ) {
                                String arival1 = arrivalTime.split(":")[0];
                                String arival2 = arrivalTime.split(":")[1];
                                long arrival = Long.parseLong(arival1) * 3600 * 1000 + Long.parseLong(arival2) * 60 * 1000;
                                String departure1 = departureTime.split(":")[0];
                                String departure2 = departureTime.split(":")[1];
                                long departure = Long.parseLong(departure1) * 3600 * 1000 + Long.parseLong(departure2) * 60 * 1000;
                                long diff=departure-items.get(0).getDepartureTime();
                                int index=0;
                                for(int i=0;i<itemsAll.size();i++){
                                    if(itemsAll.get(i).getStationStage().getStation().getId().equals(stationId)){
                                        index=i;
                                        break;
                                    }
                                }
                                for(int i=index;i<itemsAll.size();i++){
                                    StopReference itemStopR=itemsAll.get(i);
                                    if(i==index){
                                        if(wait!=null){
                                            itemsAll.get(i).setWait(Integer.parseInt(wait));
                                        }else{
                                            itemsAll.get(i).setWait(0);
                                        }
                                        itemsAll.get(i).setArrivalTime(arrival);
                                        itemsAll.get(i).setDepartureTime(departure);
                                    }else if(itemStopR.getStationStage().getStation().getId().equals(itemsAll.get(i-1).getStationStage().getStation().getId())){
                                        itemsAll.get(i).setWait(itemsAll.get(i-1).getWait());
                                        itemsAll.get(i).setArrivalTime(itemsAll.get(i-1).getArrivalTime());
                                        itemsAll.get(i).setDepartureTime(itemsAll.get(i-1).getDepartureTime());
                                    }else{
                                        itemsAll.get(i).setDepartureTime(itemsAll.get(i).getDepartureTime()+diff);
                                        itemsAll.get(i).setArrivalTime(itemsAll.get(i).getArrivalTime()+diff);
                                    }
                                }
                                /*for (StopReference stopRef : items) {
                                    if(wait!=null){
                                        stopRef.setWait(Integer.parseInt(wait));
                                    }else{
                                        stopRef.setWait(0);
                                    }
                                    stopRef.setArrivalTime(arrival);
                                    stopRef.setDepartureTime(departure);
                                    beanVnrDao.saveStopReference(stopRef);
                                }*/
                                beanVnrDao.editListStopReferences(itemsAll);
                                status = "Sửa thành công!";
                            } else {
                                status = "Không thành công!";
                            }
                        }
                    }

                }
            }
        }


        return SUCCESS;
    }

    public String delete() throws Exception{
        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");
        if (stopReferenceId == null || stopReferenceId.isEmpty() || stopReferenceId.equals("")) {
            return SUCCESS;
        } else{
           StopReference item1 = beanVnrDao.getStopReference(Long.parseLong(stopReferenceId.trim()));
            if (item1 != null) {
                trainReferenceId = item1.getTrainReference().getId();
                trainReference = item1.getTrainReference();
                stationId = item1.getStationStage().getStation().getId();
                station = beanVnrDao.getStation(stationId);
                if (station != null) {
                    List<StopReference> items1= beanVnrDao.getStopReferenceByTrainAndStation(trainReference, station);
                    if(items1!=null && items1.size()>0){
                         beanVnrDao.deleteListStopReferences(items1);
                    }
                }
            }
        }
        return SUCCESS;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getStopReferenceId() {
        return stopReferenceId;
    }

    public void setStopReferenceId(String stopReferenceId) {
        this.stopReferenceId = stopReferenceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrainReferenceId() {
        return trainReferenceId;
    }

    public void setTrainReferenceId(String trainReferenceId) {
        this.trainReferenceId = trainReferenceId;
    }

    public StopReference getItem() {
        return item;
    }

    public void setItem(StopReference item) {
        this.item = item;
    }

    public TrainReference getTrainReference() {
        return trainReference;
    }

    public void setTrainReference(TrainReference trainReference) {
        this.trainReference = trainReference;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public List<StationStage> getStationStages() {
        return stationStages;
    }

    public void setStationStages(List<StationStage> stationStages) {
        this.stationStages = stationStages;
    }

    public String getWait() {
        return wait;
    }

    public void setWait(String wait) {
        this.wait = wait;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
