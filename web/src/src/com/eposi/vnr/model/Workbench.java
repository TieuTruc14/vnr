package com.eposi.vnr.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by TieuTruc on 12/9/2014.
 */
public class Workbench implements Serializable {

    private int id;
    private String name;
    private Date timeLock;//khoa' thoi gian chinh ke hoach

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTimeLock() {
        return timeLock;
    }

    public void setTimeLock(Date timeLock) {
        this.timeLock = timeLock;
    }
}
