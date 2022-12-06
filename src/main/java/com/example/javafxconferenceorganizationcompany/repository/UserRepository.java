package com.example.javafxconferenceorganizationcompany.repository;

import com.example.javafxconferenceorganizationcompany.models.User;
import kotlin.text.Regex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static void changeEmail(Integer id, String newEmail) {
        String sql = "CHANGE_USER_EMAIL_BY_ITS_ID ?,?";
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);
            request.setInt(1, id);
            request.setString(2, newEmail);
            request.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String changeLogin(Integer id, String newLogin) {
        String sqlLogin = "SELECT * FROM Users WHERE userLogin=?";
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sqlLogin);

            request.setString(1,newLogin);
            ResultSet res = request.executeQuery();
            if (res.next()) {
                return "Данный логин уже занят. Выберите другой.";
            } else {
                String sql = "CHANGE_USER_LOGIN_BY_ITS_ID ?,?";
                PreparedStatement req = null;

                req = connection.prepareStatement(sql);
                req.setInt(1, id);
                req.setString(2, newLogin);
                req.executeUpdate();
                return "";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void changePassword(Integer id, String newPassword) {
        String sql = "CHANGE_USER_HASH_BY_ITS_ID ?,?";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] digest = md.digest(newPassword.getBytes(StandardCharsets.UTF_8));
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);
            request.setInt(1, id);
            request.setBytes(2, digest);
            request.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static  User getPersonalAssistantByConferenceId(int conferenceId){
        User user = new User();
        String sql="EXEC GET_PERSONAL_ASSISTANT_BY_CONFERENCE_ID ?";
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);
            request.setInt(1, conferenceId);
            ResultSet result = request.executeQuery();
            if (result.next()) {
                user.setFIO(result.getNString(1));
                user.setPhoneNumber(result.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;


    }
}
