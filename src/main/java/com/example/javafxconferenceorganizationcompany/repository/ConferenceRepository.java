package com.example.javafxconferenceorganizationcompany.repository;

import java.sql.Connection;

public class ConferenceRepository {
    private static Connection connection;

    public ConferenceRepository(Connection con) {
        connection = con;
    }
}
