package com.eposi.vnr.web.infrastructure;

import com.eposi.common.web.AbstractAction;
import com.eposi.vnr.model.Stage;
import com.eposi.vnr.persitence.VnrDao;

import java.util.List;

/**
 * Created by TieuTruc on 11/18/2014.
 */
public class StageAction extends AbstractAction {
    private List<Stage> items;
    private Stage item;
    private String stageId;
    private String format;
    private String Status = "";

    public String execute() throws Exception {
        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");
        items = beanVnrDao.getStages();

        return SUCCESS;
    }

    public String edit() throws Exception {
        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");
        Status = "";
        if (item == null) {
            if (stageId == null || stageId.equals("")) {
                return null;
            } else {
                item = beanVnrDao.getStage(stageId);
            }
        } else {
            beanVnrDao.saveStage(item);
            Status = "Sửa thành công!";
        }
        return SUCCESS;
    }

    public String addnew() throws Exception {
        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");
        Status = "";
        if (item != null) {
            if (beanVnrDao.getStage(item.getId()) == null) {
                beanVnrDao.saveStage(item);
                Status = "Thêm thành công!";
            }
        }
        return SUCCESS;
    }

    public List<Stage> getItems() {
        return items;
    }

    public void setItems(List<Stage> items) {
        this.items = items;
    }

    public Stage getItem() {
        return item;
    }

    public void setItem(Stage item) {
        this.item = item;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
