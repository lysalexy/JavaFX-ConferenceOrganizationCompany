package com.example.javafxconferenceorganizationcompany.repository;

import com.example.javafxconferenceorganizationcompany.models.BuffetPosition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuffetPositionRepository {
    private static Connection connection;

    public BuffetPositionRepository(Connection con) {
        connection = con;
    }

    public static ObservableList<BuffetPosition> getBuffetPositionsByConferenceId(int conferenceId) {
        String sql = "EXEC GET_BUFFET_POSITIONS_BY_CONFERENCE_ID ?";
        ObservableList<BuffetPosition> positions = FXCollections.observableArrayList();
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);

            request.setInt(1, conferenceId);
            ResultSet res = request.executeQuery();
            while (res.next()) {
                BuffetPosition position = new BuffetPosition();
                position.setBuffetPositionId(res.getInt(1));
                position.setDishesName(res.getNString(2));
                position.setDishesAmount(res.getInt(3));

                positions.add(position);
            }
            return positions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteBuffetPositionById(int id){
        String sql = "EXEC DELETE_BUFFET_POSITION ?";
        try {
            PreparedStatement request = connection.prepareStatement(sql);
            request.setInt(1,id);
            request.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addBuffetPosition(int dishId, int conferenceId, int amount){
        String sql = "EXEC ADD_BUFFET_POSTIONS ?,?,?";
        try {
            PreparedStatement request = connection.prepareStatement(sql);
            request.setInt(1,dishId);
            request.setInt(2,conferenceId);
            request.setInt(3,amount);
            request.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
