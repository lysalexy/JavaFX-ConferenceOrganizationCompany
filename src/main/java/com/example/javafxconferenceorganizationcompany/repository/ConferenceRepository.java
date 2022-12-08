package com.example.javafxconferenceorganizationcompany.repository;

import com.example.javafxconferenceorganizationcompany.models.Conference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConferenceRepository {
    private static Connection connection;

    public ConferenceRepository(Connection con) {
        connection = con;
    }

    static ObservableList<Conference> getConferencesById(String sql, int id) {
        ObservableList<Conference> conferences = FXCollections.observableArrayList();
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);

            request.setInt(1, id);
            ResultSet res = request.executeQuery();
            while (res.next()) {
                Conference conf = new Conference();
                conf.setConferenceId(res.getInt(1));
                conf.setStartTime(res.getString(2));
                ///System.out.println(res.getString(1));
                conf.setFinishTime(res.getString(3));
                conf.setConferenceLocationAddress(res.getString(4));
                conf.setCompanyName(res.getString(5));
                conf.setMainParticipantFIO(res.getString(6));
                conf.setMainParticipantContactTelephoneNumber(res.getString(7));

                conferences.add(conf);
            }
            return conferences;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static ObservableList<Conference> getPersonalAssistantConferencesByID(int id) {
        return getConferencesById("EXEC GET_PERSONAL_ASSISTANT_CONFERENCES_BY_USER_ID ?", id);
    }

    public static ObservableList<Conference> getVideographerConferencesByID(int id) {
        return getConferencesById("EXEC GET_VIDEOGRAPHER_CONFERENCES_BY_USER_ID ?", id);

    }

    public static Conference getConferenceById(int id) {
        String sql = "SELECT * FROM Conferences WHERE conferenceId=?";
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);
            request.setInt(1,id);
            System.out.println(id);
            ResultSet res = request.executeQuery();
            if (res.next()) {
                Conference conf = new Conference();

                conf.setStartTime(res.getString(2));
                conf.setFinishTime(res.getString(3));
                conf.setParticipantsAmount(res.getInt(4));
                conf.setBudjet(res.getInt(5));
                conf.setConferenceDescription(res.getNString(6));
                conf.setConferenceLocationId(res.getInt(7));
                conf.setCompanyId(res.getInt(8));
                conf.setPersonalAssistantId(res.getInt(9));

                return conf;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new Conference();
    }

    public void addConference(String start, String finish, int participantsAmount,
                              double budget, String description, int locationId, int companyId, int personalAssistantId){
        String sql = "EXEC ADD_CONFERENCE ?,?,?,?,?,?,?,?";
        try {
            PreparedStatement request = connection.prepareStatement(sql);

            request = connection.prepareStatement(sql);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            LocalDateTime startD=LocalDateTime.parse(start,formatter);
            LocalDateTime finishD=LocalDateTime.parse(finish,formatter);

            request.setTimestamp(1, Timestamp.valueOf(startD));
            request.setTimestamp(2, Timestamp.valueOf(finishD));
            request.setInt(3, participantsAmount);
            request.setDouble(4, budget);
            request.setString(5, description);
            request.setInt(6,locationId);
            request.setInt(7,companyId);
            request.setInt(8,personalAssistantId);

            request.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
