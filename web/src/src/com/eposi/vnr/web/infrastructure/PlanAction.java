//package com.eposi.vnr.web.infrastructure;
//
//import com.eposi.common.web.AbstractAction;
//import com.eposi.vnr.model.Plan;
//import com.eposi.vnr.model.Station;
////import com.eposi.vnr.model.Train;
//import com.eposi.vnr.persitence.VnrDao;
//
//import java.util.List;
//
///**
//* Created by TieuTruc on 11/15/2014.
//*/
//public class PlanAction extends AbstractAction {
//
//    private String format="html";
//    private Plan item;
//    private List<Plan> items;
//    private String planId;
//    private String trainId;
////    private Train train;
//    private List<Station> stations;
//
//    public String execute() throws  Exception{
//        VnrDao beanVnrDao=(VnrDao)this.getBean("beanVnrDao");
//        if(trainId!=null){
//            if(trainId.isEmpty() || trainId.equals("")){
//                return SUCCESS;
//            }
//            train=beanVnrDao.getTrainByName(trainId);
//            if(train!=null){
//               /* items=beanVnrDao.findPlanByTrain(train);*/
//            }
//        }
//        return SUCCESS;
//    }
//
//    public String edit() throws  Exception{
//        VnrDao beanVnrDao=(VnrDao)this.getBean("beanVnrDao");
//        stations=beanVnrDao.getStations();
//        if (item == null) {
//            if (planId == null || planId.equals("")) {
//                return null;
//            }else{
//                long id=Long.parseLong(planId);
//                item=beanVnrDao.getPlan(id);
//            }
//        }else{
//            beanVnrDao.savePlan(item);
//        }
//        return SUCCESS;
//    }
//
//    public String addnew() throws  Exception{
//        VnrDao beanVnrDao=(VnrDao)this.getBean("beanVnrDao");
//        stations=beanVnrDao.getStations();
//        if (trainId==null||trainId.equals("")){
//            return SUCCESS;
//        }
//        train=beanVnrDao.getTrainByName(trainId);
//        if(item!=null){
//            if(beanVnrDao.getPlan(item.getId())==null){
//                if(train!=null){
//                   /* item.setTrain(train);*/
//                    beanVnrDao.savePlan(item);
//                }
//            }
//        }
//        return SUCCESS;
//    }
//
//    public String getFormat() {
//        return format;
//    }
//
//    public void setFormat(String format) {
//        this.format = format;
//    }
//
//    public Plan getItem() {
//        return item;
//    }
//
//    public void setItem(Plan item) {
//        this.item = item;
//    }
//
//    public List<Plan> getItems() {
//        return items;
//    }
//
//    public void setItems(List<Plan> items) {
//        this.items = items;
//    }
//
//    public String getPlanId() {
//        return planId;
//    }
//
//    public void setPlanId(String planId) {
//        this.planId = planId;
//    }
//
//    public String getTrainId() {
//        return trainId;
//    }
//
//    public void setTrainId(String trainId) {
//        this.trainId = trainId;
//    }
//
//    public Train getTrain() {
//        return train;
//    }
//
//    public void setTrain(Train train) {
//        this.train = train;
//    }
//    public List<Station> getStations() {
//        return stations;
//    }
//
//    public void setStations(List<Station> stations) {
//        this.stations = stations;
//    }
//}
