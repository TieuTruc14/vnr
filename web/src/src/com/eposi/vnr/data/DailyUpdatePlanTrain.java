package com.eposi.vnr.data;

import com.eposi.common.util.FormatUtil;
import com.eposi.common.web.AbstractAction;

import java.util.Date;

/**
 * Created by Tuan on 11/13/2014.
 */
public class DailyUpdatePlanTrain extends AbstractAction {
    private static final long serialVersionUID = 4671823450362359525L;

    public void updatePlanToStop(){
        System.out.println("DailyUpdatePlanTrain.updatePlanToStop:"+ FormatUtil.formatDate(new Date(),""));
    }
}
