package com.example.javafxconferenceorganizationcompany.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

public class Dish {
    private IntegerProperty dishId;
    private StringProperty dishName;
    private StringProperty productComposition;
    private IntegerProperty cost;

    private Button delete;

    public Dish() {
        dishId = new SimpleIntegerProperty();
        dishName = new SimpleStringProperty();
        productComposition = new SimpleStringProperty();
        cost = new SimpleIntegerProperty();
        delete=new Button("Удалить");
        delete.setStyle("-fx-background-color: #FF7F50;-fx-textFill:#f4f2f2");
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
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

    public String getProductComposition() {
        return productComposition.get();
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

