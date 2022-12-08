package com.example.javafxconferenceorganizationcompany.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

public class Location {
    private IntegerProperty locationId;
    private StringProperty locationName;
    private StringProperty locationAddress;
    private  IntegerProperty locationCapacity;
    private IntegerProperty costPerHour;

    private Button delete;

    public Location(){
        locationId=new SimpleIntegerProperty();
        locationName=new SimpleStringProperty();
        locationAddress=new SimpleStringProperty();
        locationCapacity=new SimpleIntegerProperty();
        costPerHour=new SimpleIntegerProperty();
        delete=new Button("Удалить");
        delete.setStyle("-fx-background-color: #FF7F50;-fx-text-fill:#f4f2f2");
    }
    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
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
