package com.eposi.vnr.web.scheduling;

import com.eposi.common.web.AbstractAction;
import com.eposi.vnr.model.Stop;
import com.eposi.vnr.model.StopReference;
import com.eposi.vnr.model.Train;
import com.eposi.vnr.model.TrainReference;
import com.eposi.vnr.persitence.VnrDao;

import java.util.List;
import java.util.*;

public class TrainReferenceDetailAction extends AbstractAction {
    private static final long serialVersionUID = 4180388684310854810L;

    private String id;
    private TrainReference item;
    private List<StopReference> stopReferenceList;
    private String departureTime;
    private String format="";
    private String status="";

    public String execute() throws Exception {
        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");
        item = beanVnrDao.getTrainReference(id);

        if (item != null) {
            stopReferenceList = beanVnrDao.getStopReferenceListByTrainReference(item);
            Map<String, String> mapStations = new HashMap<String, String>();

            List<StopReference> result = new ArrayList<StopReference>();
            for (StopReference stopRef : stopReferenceList) {
                String KEY = stopRef.getStationStage().getStation().getId();
                if (mapStations.get(KEY) == null) {
                    result.add(stopRef); // Them doi tuong vao danh sach
                    mapStations.put(KEY, KEY);
                }
            }
            stopReferenceList=result;
        }

        return SUCCESS;
    }

    public String add() throws Exception{
        VnrDao beanVnrDao=(VnrDao)this.getBean("beanVnrDao");
        status="";
        if(item!=null){
            if(beanVnrDao.getTrainReference(item.getId())==null){
                item.setName(item.getId());
                String departure3=departureTime.split(":")[0];
               String departure4=departureTime.split(":")[1];
               long departure=Long.parseLong(departure3)*3600*1000+Long.parseLong(departure4)*60*1000;
               item.setDepartureTime(departure);
                beanVnrDao.saveTrainReference(item);
                status="Thêm thành công!";
            }
        }

        return SUCCESS;
    }

    public String edit() throws Exception{
        VnrDao beanVnrDao=(VnrDao)this.getBean("beanVnrDao");
        status="";
        if(format.equals("edit")){
                if (item != null) {
                    if(beanVnrDao.getTrainReference(item.getId())!=null) {
                        item.setName(item.getId());
                        String departure3 = departureTime.split(":")[0];
                        String departure4 = departureTime.split(":")[1];
                        long departure = Long.parseLong(departure3) * 3600 * 1000 + Long.parseLong(departure4) * 60 * 1000;
                        item.setDepartureTime(departure);
                        beanVnrDao.saveTrainReference(item);
                        status="Sửa thành công!";
                        format="";
                    }
                }

        }else{
            if(id!=null) {
                item = beanVnrDao.getTrainReference(id);
                if(item!=null){
                    String departure3= String.valueOf(item.getDepartureTime()/3600000);
                    String departure4=String.valueOf((item.getDepartureTime()%3600000)/60000);
                    if(departure4.length()==1){
                        departure4 ="0"+departure4;
                    }
                    setDepartureTime(departure3+":"+departure4);
                }
            }
        }

        return SUCCESS;
    }
    public String delete() throws Exception{
        VnrDao beanVnrDao=(VnrDao)this.getBean("beanVnrDao");
        status="";
            if(id!=null) {
                item = beanVnrDao.getTrainReference(id);
                if(item!=null){
                    List<Train> trains=beanVnrDao.findTrainsByTrainReference(item);
                    if(trains!=null && trains.size()>0){
                        List<Stop> stops=beanVnrDao.findStopByTrainList(trains);
                        beanVnrDao.deleteStops(stops);
                        beanVnrDao.deleteTrains(trains);
                    }
                    List<StopReference> stopres=beanVnrDao.getStopReferenceListByTrainReference(item);
                    if(stopres!=null && stopres.size()>0){
                        beanVnrDao.deleteListStopReferences(stopres);
                    }
                    beanVnrDao.deleteTrainReference(item);
                }
            }

        return SUCCESS;
    }

    public List<StopReference> getStopReferenceList() {
        return stopReferenceList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TrainReference getItem() {
        return item;
    }

    public void setItem(TrainReference item) {
        this.item = item;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setStopReferenceList(List<StopReference> stopReferenceList) {
        this.stopReferenceList = stopReferenceList;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
