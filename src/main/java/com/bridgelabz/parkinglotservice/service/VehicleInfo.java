package com.bridgelabz.parkinglotservice.service;


import com.bridgelabz.parkinglotservice.enumeration.CHECKFORPARK;
import com.bridgelabz.parkinglotservice.enumeration.VehicleData;

import java.time.LocalTime;

public class VehicleInfo {

    public CHECKFORPARK checkForPark;
    public VehicleData colour;
    public VehicleData carType;
    public String plateNo;
    public String driverName;
    public LocalTime time;


    public VehicleInfo(String driverName, CHECKFORPARK checkforpark, VehicleData carType, String plateNo, VehicleData color, LocalTime time) {
        this.driverName=driverName;
        this.checkForPark=checkforpark;
        this.carType=carType;
        this.plateNo=plateNo;
        this.colour=color;
        this.time=time;
    }


    public CHECKFORPARK getCheckForPark() {
        return checkForPark;
    }

    @Override
    public String toString() {
        return "VehicleInfo{" +
                "CHECKFORPARK=" + checkForPark +
                ", colour=" + colour +
                ", carType=" + carType +
                ", plateNo='" + plateNo + '\'' +
                ", driverName='" + driverName + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
