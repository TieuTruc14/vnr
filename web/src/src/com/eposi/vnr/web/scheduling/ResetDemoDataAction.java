package com.eposi.vnr.web.scheduling;

import com.eposi.common.web.AbstractAction;
import com.eposi.vnr.data.SampleDataDemo;

public class ResetDemoDataAction extends AbstractAction {
    private static final long serialVersionUID = 4107366480906024233L;

    public String execute() throws Exception {
        SampleDataDemo beanSampleDataDemo = (SampleDataDemo) this.getBean("beanSampleDataDemo");
        beanSampleDataDemo.init();

        return SUCCESS;
    }
}
