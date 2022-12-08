package com.example.javafxconferenceorganizationcompany.repository;

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

    public static int getDishByDishName(String dishName){
        int out=0;
        String sql = "EXEC GET_DISH_ID_BY_ITS_NAME ?";
        PreparedStatement request=null;
        try{
            request=connection.prepareStatement(sql);
            request.setString(1,dishName);
            ///request.setInt(2,out);
            ResultSet res =request.executeQuery();
            if(res.next()){
                out=res.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return out;
    }

    public void deactivateDish(int dishId){
        String sql = "EXEC DEACTIVATE_DISH_BY_ITS_ID ?";
        try {
            PreparedStatement request = connection.prepareStatement(sql);
            request.setInt(1,dishId);
            request.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
