package com.eposi.vnr.model;

import java.io.Serializable;

public class StationStage implements Serializable {
    private static final long serialVersionUID = -8445966050711565851L;

    private String id;
    private Station station;
    private Stage stage;

    private String km; //ly trinh bao nhieu
    private int distance; //khoang cach so voi ga dau trong khu đoạn

//    public StationStage(long id, Station station, Stage stage, String km, int distance) {
//        this.id = id;
//        this.station = station;
//        this.stage = stage;
//        this.km = km;
//        this.distance = distance;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
