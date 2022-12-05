package com.example.javafxconferenceorganizationcompany.repository;

import com.example.javafxconferenceorganizationcompany.models.Conference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConferenceRepository {
    private static Connection connection;

    public ConferenceRepository(Connection con) {
        connection = con;
    }

    public static ObservableList<Conference> getPersonalAssistantConferencesByID(int id) {
        ObservableList<Conference> conferences = FXCollections.observableArrayList();

        String sql = "EXEC GET_PERSONAL_ASSISTANT_CONFERENCES_BY_USER_ID ?";
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);

            request.setInt(1, id);
            ResultSet res = request.executeQuery();
            while (res.next()) {
                Conference conf = new Conference();
                conf.setStartTime(res.getString(1));
                System.out.println(res.getString(1));
                conf.setFinishTime(res.getString(2));
                conf.setConferenceLocationAddress(res.getString(3));
                conf.setCompanyName(res.getString(4));
                conf.setMainParticipantFIO(res.getString(5));
                conf.setMainParticipantContactTelephoneNumber(res.getString(6));

                conferences.add(conf);
            }
            return conferences;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
