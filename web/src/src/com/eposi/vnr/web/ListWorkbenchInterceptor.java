package com.eposi.vnr.web;

import com.eposi.common.web.AbstractAction;
import com.eposi.vnr.model.Stage;
import com.eposi.vnr.model.Workbench;
import com.eposi.vnr.persitence.VnrDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.ValueStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TieuTruc on 12/9/2014.
 */
public class ListWorkbenchInterceptor extends AbstractAction implements Interceptor {
    private List<Workbench> workbenches = new ArrayList<Workbench>();

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");

        ValueStack stack = ActionContext.getContext().getValueStack();
        workbenches = beanVnrDao.listWorkbenchs();
        stack.set("ListWorkbenchInterceptor_Workbenchs", workbenches);
        String result = invocation.invoke();
        return result;
    }
    @Override
    public void destroy() {
    }

    @Override
    public void init() {
    }

    public List<Workbench> getWorkbenches() {
        return workbenches;
    }

    public void setWorkbenches(List<Workbench> workbenches) {
        this.workbenches = workbenches;
    }
}
