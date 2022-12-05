package com.example.javafxconferenceorganizationcompany.repository;

import java.sql.Connection;

public class CompanyRepository {
    private static Connection connection;

    public CompanyRepository(Connection con) {
        connection = con;
    }
}
