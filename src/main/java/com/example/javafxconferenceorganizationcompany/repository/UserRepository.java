package com.example.javafxconferenceorganizationcompany.repository;

import com.example.javafxconferenceorganizationcompany.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private static Connection connection;

    public UserRepository(Connection con) {
        connection = con;
    }

    public static User getUserPersonalInfo(Integer id) {
        User user = new User();
        String sql = "EXEC GET_USER_BY_ID ?";
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);
            request.setInt(1, id);
            ResultSet result = request.executeQuery();
            if (result.next()) {
                user.setUserLogin(result.getNString(1));
                user.setFIO(result.getNString(2));
                user.setPhoneNumber(result.getString(3));
                user.setBirthDate(result.getDate(4).toString());
                user.setEmail(result.getString(5));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public static void changePhoneNumber(Integer id, String newPhoneNumber) {
        String sql = "CHANGE_USER_PHONE_NUMBER_BY_ITS_ID ?,?";
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);
            request.setInt(1, id);
            request.setString(2, newPhoneNumber);
            request.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
