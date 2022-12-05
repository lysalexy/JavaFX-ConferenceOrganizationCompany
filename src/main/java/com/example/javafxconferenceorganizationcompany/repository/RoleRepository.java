package com.example.javafxconferenceorganizationcompany.repository;

import java.sql.Connection;

public class RoleRepository {
    private static Connection connection;

    public RoleRepository(Connection con) {
        connection = con;
    }
}
