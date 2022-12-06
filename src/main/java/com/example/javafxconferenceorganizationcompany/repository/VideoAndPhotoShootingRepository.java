package com.example.javafxconferenceorganizationcompany.repository;

import com.example.javafxconferenceorganizationcompany.models.Conference;
import com.example.javafxconferenceorganizationcompany.models.VideoAndPhotoShooting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VideoAndPhotoShootingRepository {
    private static Connection connection;

    public VideoAndPhotoShootingRepository(Connection con) {
        connection = con;
    }

    public static VideoAndPhotoShooting getVideoAndPhotoShootingByUserIDAndConferenceId(int userId, int conferenceId){
        String sql = "SELECT * FROM VideoAndPhotoShooting WHERE conferenceId=? AND videographerId=?";
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);
            request.setInt(2, userId);
            request.setInt(1,conferenceId);
            ResultSet res = request.executeQuery();
            if (res.next()) {
                VideoAndPhotoShooting shooting = new VideoAndPhotoShooting();

                shooting.setPhotoIsRequired(res.getBoolean(3));
                shooting.setVideoIsRequired(res.getBoolean(4));

                return shooting;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new VideoAndPhotoShooting();
    }
}
