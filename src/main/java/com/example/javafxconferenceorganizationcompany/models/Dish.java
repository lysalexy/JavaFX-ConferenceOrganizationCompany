package com.example.javafxconferenceorganizationcompany.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Dish {
    private IntegerProperty dishId;
    private StringProperty dishName;
    private StringProperty productComposition;
    private IntegerProperty cost;

    public Dish() {
        dishId = new SimpleIntegerProperty();
        dishName = new SimpleStringProperty();
        productComposition = new SimpleStringProperty();
        cost = new SimpleIntegerProperty();
    }

    public void setDishId(int id) {
        dishId.set(id);
    }
    public void setDishName(String name) {
       dishName.set(name);
    }
    public void setProductComposition(String composition) {
        productComposition.set(composition);
    }
    public void setCost(int costV) {
        cost.set(costV);
    }

    public int getDishId() {
        return dishId.get();
    }
    public String getDishName() {
        return dishName.get();
    }
    public int getCost() {
        return cost.get();
    }
}

