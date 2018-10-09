package com.eposi.vnr.web.scheduling;


import com.eposi.common.web.AbstractAction;
import com.eposi.vnr.model.TrainReference;
import com.eposi.vnr.persitence.VnrDao;

import java.util.List;

public class TrainReferenceIndexAction extends AbstractAction {
    private static final long serialVersionUID = -6910338202466081625L;

    private List<TrainReference> trainReferenceList;

    public String execute() throws Exception {
        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");
        trainReferenceList = beanVnrDao.getTrainReferences();

        return SUCCESS;
    }

    public List<TrainReference> getTrainReferenceList() {
        return trainReferenceList;
    }

}
