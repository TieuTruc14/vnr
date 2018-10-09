package com.eposi.vnr.model;

import java.io.Serializable;
/*
    Khu đoạn
 */
public class Stage implements Serializable {
    private static final long serialVersionUID = 6057215825309422754L;

    private String    id;
    private String  name;
    private Workbench workbench;
//    private double  distance;
//    private int     countStation;
//    private String  description;

    private int stt;
    private boolean arrangement;//bien quyet dinh lay danh sach stationStage theo chieu tang hay giam dan cua distance

    public Stage() {
    }

    public Workbench getWorkbench() {
        return workbench;
    }

    public void setWorkbench(Workbench workbench) {
        this.workbench = workbench;
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

//    public double getDistance() {
//        return distance;
//    }
//
//    public void setDistance(double distance) {
//        this.distance = distance;
//    }
//
//    public int getCountStation() {
//        return countStation;
//    }
//
//    public void setCountStation(int countStation) {
//        this.countStation = countStation;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }


    public boolean isArrangement() {
        return arrangement;
    }

    public void setArrangement(boolean arrangement) {
        this.arrangement = arrangement;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }
}
