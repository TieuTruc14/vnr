//package com.eposi.vnr.web.infrastructure;
//
//import com.eposi.common.util.FormatUtil;
//import com.eposi.common.web.AbstractAction;
//import com.eposi.vnr.model.Label;
//import com.eposi.vnr.model.Plan;
//import com.eposi.vnr.model.Stop;
//import com.eposi.vnr.model.Train;
//import com.eposi.vnr.persitence.VnrDao;
//import org.apache.commons.lang3.time.DateUtils;
//
//import java.util.Calendar;
//import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
//
//public class TrainAction extends AbstractAction {
//    private String format="html";
//    private Train item;
//    private List<Train> items;
//    private List<Stop>  stops;
//    private String trainId;
//    private String strBeginTime;
//    private String messageOk  = "";
//    private String messageErr = "";
//
//    public String execute()throws  Exception{
//        VnrDao beanVnrDao=(VnrDao)this.getBean("beanVnrDao");
//        items=beanVnrDao.getTrains();
//
//        if(trainId!=null){
//            if(!trainId.isEmpty()||!trainId.equals("")){
//                item=beanVnrDao.getTrain(Long.parseLong(trainId));
//                stops =beanVnrDao.findStopByTrain(item);
//            }
//        }
//
//        return SUCCESS;
//    }
//
//    public String edit() throws  Exception{
//        VnrDao beanVnrDao=(VnrDao)this.getBean("beanVnrDao");
//        messageOk="";
//        if (item == null) {
//            if (trainId == null || trainId.equals("")) {
//                return null;
//            }else{
//                long id=Long.parseLong(trainId);
//                item=beanVnrDao.getTrain(id);
//                messageOk="Sửa thành công!";
//            }
//        }else{
//            if(strBeginTime!=null) {
//                Date beginTime = FormatUtil.parseDate(strBeginTime, "yyyy/MM/dd");
//                item.setBeginTime(beginTime);
//                messageOk="Sửa thành công!";
//            }
//
//            beanVnrDao.saveTrain(item);
//        }
//        return SUCCESS;
//    }
//
//    public String addnew() throws  Exception{
//        VnrDao beanVnrDao=(VnrDao)this.getBean("beanVnrDao");
//        messageOk ="";
//        messageErr="";
//        if(item!=null){
//            if(beanVnrDao.getTrain(item.getId())==null){
//                if(strBeginTime!=null){
//                    Date beginTime = FormatUtil.parseDate(strBeginTime, "yyyy/MM/dd HH:mm:ss");
//                    item.setBeginTime(beginTime);
//                    Label label = item.getTrainReference();
//                    if(label!=null){
//                        List<Plan> lstPlan = beanVnrDao.findStopReferenceByTrainReference(label);
//                        if(lstPlan!=null){
//                            if(lstPlan.size()>0){
//                                Date startDate = DateUtils.truncate(item.getBeginTime(), Calendar.DATE);
//                                long diffTime  = beginTime.getTime()-startDate.getTime()- label.getDepartureTime();
//                                Train newTrain = beanVnrDao.saveTrain(item);
//                                List<Stop> lstStop = buildStops(lstPlan, newTrain, diffTime);
//                                beanVnrDao.saveOrUpdateMany(lstStop);
//                                messageOk="Thêm mới thành công!";
//                            }else{
//                                messageErr="Mác tàu được chọn chưa có kế hoạch chạy tàu";
//                            }
//                        }else{
//                            messageErr="Mác tàu được chọn chưa có kế hoạch chạy tàu";
//                        }
//                    }
//                }
//            }
//        }
//        return SUCCESS;
//    }
//
//    private List<Stop> buildStops(List<Plan> lstPlan, Train train, long diffTime){
//        List<Stop> lstStop = new LinkedList<Stop>();
//        Date startDate = DateUtils.truncate(train.getBeginTime(), Calendar.DATE);
//
//        for(Plan plan :lstPlan){
//            long arrivalTime   = startDate.getTime()+plan.getArrivalTime()+diffTime;
//            long departureTime = startDate.getTime()+plan.getDepartureTime()+diffTime;
//            Stop stop = new Stop(plan.getStage(), train, plan.getStation(), new Date(arrivalTime), new Date(departureTime), plan.isWait(), plan.getOrderStation(), false);
//            lstStop.add(stop);
//        }
//        return lstStop;
//    }
//
//
//    public String getFormat() {
//        return format;
//    }
//
//    public void setFormat(String format) {
//        this.format = format;
//    }
//
//    public Train getItem() {
//        return item;
//    }
//
//    public void setItem(Train item) {
//        this.item = item;
//    }
//
//    public List<Train> getItems() {
//        return items;
//    }
//
//    public void setItems(List<Train> items) {
//        this.items = items;
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
//    public String getStrBeginTime() {
//        return strBeginTime;
//    }
//
//    public void setStrBeginTime(String strBeginTime) {
//        this.strBeginTime = strBeginTime;
//    }
//
//    public String getMessageOk() {
//        return messageOk;
//    }
//
//    public void setMessageOk(String messageOk) {
//        this.messageOk = messageOk;
//    }
//
//    public String getMessageErr() {
//        return messageErr;
//    }
//
//    public void setMessageErr(String messageErr) {
//        this.messageErr = messageErr;
//    }
//
//    public List<Stop> getStops() {
//        return stops;
//    }
//
//    public void setStops(List<Stop> stops) {
//        this.stops = stops;
//    }
//}
