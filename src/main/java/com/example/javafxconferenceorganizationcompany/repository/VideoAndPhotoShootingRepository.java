package com.example.javafxconferenceorganizationcompany.repository;

import java.sql.Connection;

public class VideoAndPhotoShootingRepository {
    private static Connection connection;

    public VideoAndPhotoShootingRepository(Connection con) {
        connection = con;
    }
}
