package com.eposi.vnr.web.infrastructure;

import com.eposi.common.web.AbstractAction;
import com.eposi.vnr.model.StationStage;
import com.eposi.vnr.persitence.VnrDao;

import java.util.List;

/**
 * Created by TieuTruc on 11/25/2014.
 */
public class StationStageAction extends AbstractAction {
    private List<StationStage> items;
    private StationStage item;
    private String id;
    private String Status="";

    public String execute() throws Exception {
        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");
        items = beanVnrDao.listStationStage();

        return SUCCESS;
    }

    public String edit() throws Exception {
        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");
        Status = "";
        if (item == null) {
            if (id == null || id.equals("")) {
                return null;
            } else {
//                long idSt = Long.parseLong(id);
                item = beanVnrDao.getStationStage(id);
            }
        } else {
            beanVnrDao.saveStationStage(item);
            Status = "Sửa thành công!";
        }
        return SUCCESS;
    }
    public String add() throws Exception {
        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");
        Status = "";
        if(item!=null) {
            /*if (beanVnrDao.getStationStage(item.getId()) == null) {
                beanVnrDao.saveStationStage(item);
                Status = "Thêm thành công!";
            }*/
        }
        return SUCCESS;
    }


    public List<StationStage> getItems() {
        return items;
    }

    public void setItems(List<StationStage> items) {
        this.items = items;
    }

    public StationStage getItem() {
        return item;
    }

    public void setItem(StationStage item) {
        this.item = item;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
