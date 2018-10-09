package com.eposi.vnr.web.infrastructure;

import com.eposi.common.web.AbstractAction;
import com.eposi.vnr.model.Station;
import com.eposi.vnr.persitence.VnrDao;

import java.util.List;

public class StationAction extends AbstractAction {
    private List<Station> items;
    private Station item;
    private String stationId;
    private String format;
    private String Status = "";

    public String execute() throws Exception {
        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");
        items = beanVnrDao.getStations();

        return SUCCESS;
    }

    public String edit() throws Exception {
        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");
        Status = "";
        if (item == null) {
            if (stationId == null || stationId.equals("")) {
                return null;
            } else {
                item = beanVnrDao.getStation(stationId);
            }
        } else {
            beanVnrDao.saveStation(item);
            Status = "Sửa thành công!";
        }
        return SUCCESS;
    }

    public String addnew() throws Exception {
        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");
        Status = "";
        if (item != null) {
            if (beanVnrDao.getStation(item.getId()) == null) {
                beanVnrDao.saveStation(item);
                Status = "Thêm thành công!";
            }
        }
        return SUCCESS;
    }

    public List<Station> getItems() {
        return items;
    }

    public void setItems(List<Station> items) {
        this.items = items;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Station getItem() {
        return item;
    }

    public void setItem(Station item) {
        this.item = item;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
