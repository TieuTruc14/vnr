package com.eposi.vnr.web;

import com.eposi.common.web.AbstractAction;
import com.eposi.vnr.model.TrainReference;
import com.eposi.vnr.persitence.VnrDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.ValueStack;

import java.util.ArrayList;
import java.util.List;

public class ListTrainReferenceInterceptor extends AbstractAction implements Interceptor  {
    private static final long serialVersionUID = 4217534406377160833L;

    private List<TrainReference> trainReferences = new ArrayList<TrainReference>();

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");

        ValueStack stack = ActionContext.getContext().getValueStack();
        trainReferences = beanVnrDao.getTrainReferences();
        stack.set("ListTrainReferenceInterceptor_trainReferences", trainReferences);
        String result = invocation.invoke();
        return result;
    }
    @Override
    public void destroy() {
    }

    @Override
    public void init() {
    }

    public List<TrainReference> getTrainReferences() {
        return trainReferences;
    }

    public void setTrainReferences(List<TrainReference> trainReferences) {
        this.trainReferences = trainReferences;
    }
}
