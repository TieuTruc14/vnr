package com.eposi.vnr.model;

import java.io.Serializable;

public class Station implements Serializable {
    private static final long serialVersionUID = 5591955055206189186L;

    private String id;
    private String name;
    private Workbench workbench;

//    private String km; //ly trinh bao nhieu
//    private int distance; //khoang cach so voi ga dau trong khu đoạn

    public Station() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Workbench getWorkbench() {
        return workbench;
    }

    public void setWorkbench(Workbench workbench) {
        this.workbench = workbench;
    }

//    public String getKm() {
//        return km;
//    }
//
//    public void setKm(String km) {
//        this.km = km;
//    }
//
//    public int getDistance() {
//        return distance;
//    }
//
//    public void setDistance(int distance) {
//        this.distance = distance;
//    }
}
