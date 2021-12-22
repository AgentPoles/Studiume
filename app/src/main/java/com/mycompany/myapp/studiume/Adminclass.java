package com.mycompany.myapp.studiume;

import android.arch.persistence.room.Entity;

public class Adminclass {
    private int id;
    private String adminName;
    private String currentPin;

    public Adminclass(){

    }

    public Adminclass(String adminName, String currentPin){
        this.adminName = adminName;
        this.currentPin = currentPin;
    }

    public Adminclass(int id, String adminName, String currentPin){
        this.id = id;
        this.adminName = adminName;
        this.currentPin = currentPin;
    }

    public int getId() {
        return id;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getCurrentPin() {
        return currentPin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setCurrentPin(String currentPin) {
        this.currentPin = currentPin;
    }
}
