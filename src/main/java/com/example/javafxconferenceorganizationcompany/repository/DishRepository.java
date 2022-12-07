package com.example.javafxconferenceorganizationcompany.repository;

import com.example.javafxconferenceorganizationcompany.models.BuffetPosition;
import com.example.javafxconferenceorganizationcompany.models.Dish;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DishRepository {
    private static Connection connection;

    public DishRepository(Connection con) {
        connection = con;
    }

    public static ObservableList<Dish> getAllActiveDishes(){
        String sql = "EXEC GET_ALL_ACTIVE_DISHES";
        ObservableList<Dish> dishes = FXCollections.observableArrayList();
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);

            ResultSet res = request.executeQuery();
            while (res.next()) {
                Dish dish = new Dish();
                dish.setDishId(res.getInt(1));
                dish.setDishName(res.getNString(2));
                dish.setProductComposition(res.getNString(3));
                dish.setCost(res.getInt(4));

                dishes.add(dish);
            }
            return dishes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
