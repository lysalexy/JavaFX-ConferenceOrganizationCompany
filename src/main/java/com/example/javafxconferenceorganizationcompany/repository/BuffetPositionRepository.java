package com.example.javafxconferenceorganizationcompany.repository;

import java.sql.Connection;

public class BuffetPositionRepository {
    private static Connection connection;

    public BuffetPositionRepository(Connection con) {
        connection = con;
    }
}
