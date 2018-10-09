package com.eposi.vnr.web.infrastructure;

import com.eposi.common.web.AbstractAction;
import com.eposi.vnr.model.Stage;
import com.eposi.vnr.model.StationStage;
import com.eposi.vnr.persitence.VnrDao;

import java.util.List;

public class StageDetailAction extends AbstractAction {
    private static final long serialVersionUID = -2067464833220011516L;

    private String id;
    private Stage stageItem;

    private List<StationStage> stationStageList;

    public String execute() throws Exception {
        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");

        stageItem = beanVnrDao.getStage(id);

        if (stageItem != null) {
            stationStageList  = beanVnrDao.getStationStageListByStage(stageItem);
        }

        return SUCCESS;
    }

    public List<StationStage> getStationStageList() {
        return stationStageList;
    }

    public void setStationStageList(List<StationStage> stationStageList) {
        this.stationStageList = stationStageList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Stage getStageItem() {
        return stageItem;
    }

    public void setStageItem(Stage stageItem) {
        this.stageItem = stageItem;
    }
}
