package com.eposi.vnr.model;

import java.io.Serializable;
import java.util.Date;

/*
  Kế hoạch thực tế
*/
public class Stop implements Serializable {
    private static final long serialVersionUID = 1121277093940220475L;

    private long    id;
    private StationStage stationStage;
    private Train train;
    private long    arrivalTime;       // thoi gian toi ga
    private long    departureTime;     // thoi gian roi ga
    private int wait;              // co phai ga tac nghiep khong
    private String vehicle;//dau may keo tau`
    private boolean status;

    public Stop() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public StationStage getStationStage() {
        return stationStage;
    }

    public void setStationStage(StationStage stationStage) {
        this.stationStage = stationStage;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

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

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
