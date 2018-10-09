package com.eposi.vnr.web;

import com.eposi.common.web.AbstractAction;
import com.eposi.vnr.model.Station;
import com.eposi.vnr.persitence.VnrDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.ValueStack;
import java.util.ArrayList;
import java.util.List;

public class ListStationInterceptor extends AbstractAction implements Interceptor  {
    private static final long serialVersionUID = 6411097849152470770L;

    private List<Station> stations = new ArrayList<Station>();

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        VnrDao beanVnrDao = (VnrDao) this.getBean("beanVnrDao");

        ValueStack stack = ActionContext.getContext().getValueStack();
        stations = beanVnrDao.getStations();
        stack.set("ListStationInterceptor_stations", stations);
        String result = invocation.invoke();
        return result;
    }
    @Override
    public void destroy() {
    }

    @Override
    public void init() {
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
