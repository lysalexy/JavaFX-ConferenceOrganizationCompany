package com.example.javafxconferenceorganizationcompany.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Location {
    private IntegerProperty locationId;
    private StringProperty locationName;
    private StringProperty locationAddress;
    private  IntegerProperty locationCapacity;
    private IntegerProperty costPerHour;

    public Location(){
        locationId=new SimpleIntegerProperty();
        locationName=new SimpleStringProperty();
        locationAddress=new SimpleStringProperty();
        locationCapacity=new SimpleIntegerProperty();
        costPerHour=new SimpleIntegerProperty();
    }

    public void setLocationId(int id) {
       locationId.set(id);
    }
    public void setLocationName(String name){
        locationName.set(name);
    }
    public void setLocationAddress(String address){
        locationAddress.set(address);
    }
    public void setLocationCapacity(int capacity) {
        locationCapacity.set(capacity);
    }
    public void setCostPerHour(int cost) {
        costPerHour.set(cost);
    }

    public int getLocationId() {
        return locationId.get();
    }
    public String getLocationName() {
        return locationName.get();
    }
    public String getLocationAddress() {
        return locationAddress.get();
    }
    public int getLocationCapacity() {
        return locationCapacity.get();
    }
    public int getCostPerHour() {
        return costPerHour.get();
    }
}
