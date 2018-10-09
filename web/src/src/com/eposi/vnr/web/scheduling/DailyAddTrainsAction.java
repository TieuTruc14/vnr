package com.eposi.vnr.web.scheduling;

import com.eposi.common.web.AbstractAction;
import com.eposi.vnr.model.TrainReference;
import com.eposi.vnr.persitence.VnrDao;

import java.util.List;

public class DailyAddTrainsAction extends AbstractAction {
    private static final long serialVersionUID = -6819074110844495803L;


    public String execute() throws Exception {

        return SUCCESS;
    }

    public List<TrainReference> getTrainReferenceList() {
        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");
        List<TrainReference> trainReferenceList = beanVnrDao.getTrainReferences();

        return trainReferenceList;
    }
}
