//package com.eposi.vnr.web.infrastructure;
//
//
//import com.eposi.common.web.AbstractAction;
//import com.eposi.vnr.model.Segment;
//import com.eposi.vnr.persitence.VnrDao;
//
//import java.util.List;
//
//public class SegmentAction extends AbstractAction {
//    private List<Segment> items;
//    private Segment item;
//    private String segmentId;
//    private String format;
//    private String Status="";
//
//    public String execute() throws Exception{
//        VnrDao beanVnrDao=(VnrDao)this.getBean("beanVnrDao");
//        items=beanVnrDao.getSegments();
//
//        return SUCCESS;
//    }
//
//    public String edit() throws  Exception{
//        VnrDao beanVnrDao=(VnrDao)this.getBean("beanVnrDao");
//        Status="";
//        if (item == null) {
//            if (segmentId == null || segmentId.equals("")) {
//                return null;
//            }else{
//                int id=Integer.parseInt(segmentId);
//                item=beanVnrDao.getSegment(id);
//            }
//        }else{
//            beanVnrDao.saveSegment(item);
//            Status="Sửa thành công!";
//        }
//        return SUCCESS;
//    }
//    public String addnew() throws  Exception{
//        VnrDao beanVnrDao=(VnrDao)this.getBean("beanVnrDao");
//        Status="";
//        if(item!=null){
//            if(beanVnrDao.getSegment(item.getId())==null){
//                beanVnrDao.saveSegment(item);
//                Status="Thêm thành công!";
//            }
//        }
//        return SUCCESS;
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
//    public String getSegmentId() {
//        return segmentId;
//    }
//
//    public void setSegmentId(String segmentId) {
//        this.segmentId = segmentId;
//    }
//
//    public Segment getItem() {
//        return item;
//    }
//
//    public void setItem(Segment item) {
//        this.item = item;
//    }
//
//    public List<Segment> getItems() {
//        return items;
//    }
//
//    public void setItems(List<Segment> items) {
//        this.items = items;
//    }
//
//    public String getStatus() {
//        return Status;
//    }
//
//    public void setStatus(String status) {
//        Status = status;
//    }
//
//}
