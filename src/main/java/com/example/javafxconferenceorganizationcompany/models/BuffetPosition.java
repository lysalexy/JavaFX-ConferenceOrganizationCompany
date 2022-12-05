package com.example.javafxconferenceorganizationcompany.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BuffetPosition {
    private IntegerProperty buffetPositionId;
    private IntegerProperty dishesId;
    private StringProperty dishesName;
    private IntegerProperty conferenceId;
    private IntegerProperty dishesAmount;

    public BuffetPosition(){
        buffetPositionId=new SimpleIntegerProperty();
        dishesId=new SimpleIntegerProperty();
        dishesName=new SimpleStringProperty();
        conferenceId=new SimpleIntegerProperty();
        dishesAmount=new SimpleIntegerProperty();
    }

    public void setBuffetPositionId(int id) {
       buffetPositionId.set(id);
    }
    public void setDishesId(int id) {
        dishesId.set(id);
    }
    public void setDishesName(String name) {
        dishesName.set(name);
    }
    public void setConferenceId(int id) {
        conferenceId.set(id);
    }
    public void setDishesAmount(int amount) {
        dishesAmount.set(amount);
    }

    public int getBuffetPositionId() {
        return buffetPositionId.get();
    }
    public int getConferenceId() {
        return conferenceId.get();
    }
    public int getDishesId() {
        return dishesId.get();
    }
    public String getDishesName() {
        return dishesName.get();
    }
    public int getDishesAmount() {
        return dishesAmount.get();
    }
}
