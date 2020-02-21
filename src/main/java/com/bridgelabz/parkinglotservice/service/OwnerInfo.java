package com.bridgelabz.parkinglotservice.service;

public class OwnerInfo {

    private boolean parkingStatus;

    public void LotAvailable(boolean status) {
        this.parkingStatus=status;
    }

    public boolean checkStatus() {
        if(parkingStatus)
            return true;
        return false;
    }

}

