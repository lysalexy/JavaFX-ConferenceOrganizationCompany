package com.example.javafxconferenceorganizationcompany.repository;

import java.sql.Connection;

public class LocationRepository {
    private static Connection connection;

    public LocationRepository(Connection con) {
        connection = con;
    }
}
