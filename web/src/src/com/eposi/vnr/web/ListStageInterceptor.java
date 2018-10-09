package com.eposi.vnr.web;

import com.eposi.common.web.AbstractAction;
import com.eposi.vnr.model.Stage;
import com.eposi.vnr.persitence.VnrDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.ValueStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TieuTruc on 11/25/2014.
 */
public class ListStageInterceptor extends AbstractAction implements Interceptor {
    private List<Stage> stages = new ArrayList<Stage>();

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");

        ValueStack stack = ActionContext.getContext().getValueStack();
        stages = beanVnrDao.getStages();
        stack.set("ListStageInterceptor_stages", stages);
        String result = invocation.invoke();
        return result;
    }
    @Override
    public void destroy() {
    }

    @Override
    public void init() {
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }
}
