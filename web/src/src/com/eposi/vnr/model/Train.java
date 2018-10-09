package com.eposi.vnr.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Đoàn tàu
 */
public class Train implements Serializable {
    private static final long serialVersionUID = -2805782152222414465L;

    private long   id;
    private TrainReference  trainReference;
    private Date   beginTime;   // ngay gio  xuat phat (roi ga)
    private boolean status;//trang thai dong bo chua

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TrainReference getTrainReference() {
        return trainReference;
    }

    public void setTrainReference(TrainReference trainReference) {
        this.trainReference = trainReference;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
