package com.eposi.vnr.model;

import java.io.Serializable;

/**
 * Kế hoạch cho Đoàn tàu Mẫu (không phải kế hoạch thực)
 */
public class StopReference implements Serializable {
    private static final long serialVersionUID = -7085653432109043001L;

    private long    id;

//    private Station station;
//    private Stage   stage;
    private StationStage stationStage;
    private TrainReference trainReference;
    private long    arrivalTime;       // thoi gian toi ga
    private long    departureTime;     // thoi gian roi ga
    private int wait;              // co phai ga tac nghiep khong
//    private int     orderStation;

    public StopReference() {
    }

//    public StopReference(Station station, Stage stage, TrainReference trainReference, long arrivalTime, long departureTime, boolean wait, boolean locked) {
//        this.station = station;
//        this.stage   = stage;
//        this.trainReference = trainReference;
//        this.arrivalTime   = arrivalTime;
//        this.departureTime = departureTime;
//        this.wait = wait;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public TrainReference getTrainReference() {
        return trainReference;
    }

    public void setTrainReference(TrainReference trainReference) {
        this.trainReference = trainReference;
    }

//    public Station getStation() {
//        return station;
//    }
//
//    public void setStation(Station station) {
//        this.station = station;
//    }

//    public Stage getStage() {
//        return stage;
//    }
//
//    public void setStage(Stage stage) {
//        this.stage = stage;
//    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public long getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(long departureTime) {
        this.departureTime = departureTime;
    }


//    public int getOrderStation() {
//        return orderStation;
//    }
//
//    public void setOrderStation(int orderStation) {
//        this.orderStation = orderStation;
//    }

    public StationStage getStationStage() {
        return stationStage;
    }

    public void setStationStage(StationStage stationStage) {
        this.stationStage = stationStage;
    }
}
