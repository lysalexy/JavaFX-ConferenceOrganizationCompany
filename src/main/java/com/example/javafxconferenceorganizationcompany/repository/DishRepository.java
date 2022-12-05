package com.example.javafxconferenceorganizationcompany.repository;

import java.sql.Connection;

public class DishRepository {
    private static Connection connection;

    public DishRepository(Connection con) {
        connection = con;
    }
}
