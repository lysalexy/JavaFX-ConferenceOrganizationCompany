package com.example.javafxconferenceorganizationcompany.repository;

import com.example.javafxconferenceorganizationcompany.models.Conference;
import com.example.javafxconferenceorganizationcompany.models.VideoAndPhotoShooting;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VideoAndPhotoShootingRepository {
    private static Connection connection;

    public VideoAndPhotoShootingRepository(Connection con) {
        connection = con;
    }

    public static VideoAndPhotoShooting getVideoAndPhotoShootingByUserIDAndConferenceId(int userId, int conferenceId) {
        String sql = "SELECT * FROM VideoAndPhotoShooting WHERE conferenceId=? AND videographerId=?";
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);
            request.setInt(2, userId);
            request.setInt(1, conferenceId);
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

    public static ObservableList<VideoAndPhotoShooting> getAllVideoAndPhotoShootingsByConferenceId(int conferenceId) {
        ObservableList<VideoAndPhotoShooting> shootings = FXCollections.observableArrayList();

        String sql = "EXEC GET_VIDEOGRAPHER_BY_CONFERENCE_ID ?";
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);

            request.setInt(1, conferenceId);
            ResultSet res = request.executeQuery();
            while (res.next()) {
                VideoAndPhotoShooting shoot = new VideoAndPhotoShooting();

                shoot.setVideoAndPhotoId(res.getInt(1));
                System.out.println(res.getInt(1));
                shoot.setVideographerFIO(res.getString(2));
                shoot.setVideographerPhoneNumber(res.getString(3));
                shoot.setVideoIsRequired(res.getBoolean(4));
                shoot.setPhotoIsRequired(res.getBoolean(5));

                shootings.add(shoot);
            }
            return shootings;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteVideoAndPhotoShootingByShootingId(int id){
        String sql="DELETE FROM VideoAndPhotoShooting WHERE videoAndPhotoId=?";
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);
            request.setInt(1, id);
            request.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
