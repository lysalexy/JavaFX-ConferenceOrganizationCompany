package com.example.javafxconferenceorganizationcompany.repository;

import com.example.javafxconferenceorganizationcompany.models.User;
import com.example.javafxconferenceorganizationcompany.models.VideoAndPhotoShooting;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kotlin.text.Regex;

import javax.swing.text.DateFormatter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public static ObservableList<User> getFreeVideographers(String start, String finish){
        ObservableList<User> freeVideographers = FXCollections.observableArrayList();

        String sql = "EXEC GET_FREE_VIDEOGRAPHERS ?,?";
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            LocalDateTime startD=LocalDateTime.parse(start,formatter);
            LocalDateTime finishD=LocalDateTime.parse(finish,formatter);

            request.setTimestamp(1, Timestamp.valueOf(startD));
            request.setTimestamp(2, Timestamp.valueOf(finishD));
            ResultSet res = request.executeQuery();
            while (res.next()) {
                User videogr = new User();

                videogr.setFIO(res.getString(1));
                videogr.setBirthDate(res.getString(2));

                freeVideographers.add(videogr);
            }
            return freeVideographers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<User> getFreePersonalAssistants(String start, String finish){
        ObservableList<User> freePersonalAssistants = FXCollections.observableArrayList();

        String sql = "EXEC GET_FREE_PERSONAL_ASSISTANTS ?,?";
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            LocalDateTime startD=LocalDateTime.parse(start,formatter);
            LocalDateTime finishD=LocalDateTime.parse(finish,formatter);

            request.setTimestamp(1, Timestamp.valueOf(startD));
            request.setTimestamp(2, Timestamp.valueOf(finishD));
            ResultSet res = request.executeQuery();
            while (res.next()) {
                User pers = new User();

                pers.setFIO(res.getString(1));
                pers.setBirthDate(res.getString(2));

                freePersonalAssistants.add(pers);
            }
            return freePersonalAssistants;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static User getVideographerByFIO(String FIO){
        User user = new User();
        String sql="SELECT TOP 1 * FROM Users WHERE roleId=3 AND FIO=?";
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);
            request.setString(1, FIO);
            ResultSet result = request.executeQuery();
            if (result.next()) {
                user.setUserId(result.getInt(1));
                user.setUserLogin(result.getNString(2));
                user.setFIO(result.getNString(4));
                user.setPhoneNumber(result.getString(5));
                user.setBirthDate(result.getDate(6).toString());
                user.setEmail(result.getString(7));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public ObservableList<User> getAllActivePersonalAssistants(){
        ObservableList<User> activeAssistants = FXCollections.observableArrayList();

        String sql = "SELECT * FROM GET_ALL_ACTIVE_PERSONAL_ASSISTANTS";
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);

            ResultSet res = request.executeQuery();
            while (res.next()) {
                User pers = new User();

                pers.setUserId(res.getInt(1));
                pers.setUserLogin(res.getString(2));
                pers.setFIO(res.getString(3));
                pers.setPhoneNumber(res.getString(4));
                pers.setBirthDate(res.getString(5));
                pers.setEmail(res.getString(6));

                activeAssistants.add(pers);
            }
            return activeAssistants;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<User> getAllActiveVideographers(){
        ObservableList<User> activeVideographers = FXCollections.observableArrayList();

        String sql = "SELECT * FROM GET_ALL_ACTIVE_VIDEOGRAPHERS";
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);

            ResultSet res = request.executeQuery();
            while (res.next()) {
                User vid = new User();

                vid.setUserId(res.getInt(1));
                vid.setUserLogin(res.getString(2));
                vid.setFIO(res.getString(3));
                vid.setPhoneNumber(res.getString(4));
                vid.setBirthDate(res.getString(5));
                vid.setEmail(res.getString(6));

                activeVideographers.add(vid);
            }
            return activeVideographers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deactivateUser(int id){
        String sql="EXEC DEACTIVATE_USER_BY_ITS_ID ?";
        try {
            PreparedStatement request = connection.prepareStatement(sql);
            request.setInt(1,id);
            request.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String loginIsAvailable(String login){
        String sqlLogin = "SELECT * FROM Users WHERE userLogin=?";
        PreparedStatement request = null;
        try {
        request = connection.prepareStatement(sqlLogin);

        request.setString(1, login);
        ResultSet res = request.executeQuery();
        if (res.next()) {
        return "Данный логин уже занят. Выберите другой.";
        }
        else
            return "";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addNewUser(String userLogin,  byte[] userHash, String FIO, String phone, LocalDate birthDay,String email, int roleId){
        String sql="EXEC ADD_USER ?,?,?,?,?,?,?";
        try {
            PreparedStatement request = connection.prepareStatement(sql);
            request.setString(1,userLogin);
            request.setBytes(2,userHash);
            request.setString(3,FIO);
            request.setString(4,phone);
            request.setDate(5, Date.valueOf(birthDay));
            request.setString(6,email);
            request.setInt(7,roleId);
            request.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  ObservableList<String> getUserBirthDayByFIO(String FIO){
        ObservableList<String> birthDays= FXCollections.observableArrayList();;
        String sql="SELECT  * FROM Users WHERE  FIO=?";
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);
            request.setString(1, FIO);
            ResultSet result = request.executeQuery();
            while (result.next()) {
                String birthDay=result.getDate(6).toString();

                birthDays.add(birthDay);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return birthDays;
    }

    public User getUserByHisFIOAndBirthDay(String FIO, String birthDay){
        User user = new User();
        String sql="EXEC GET_PERSONAL_ASSISTANT_ID_BY_FIO_AND_BIRTHDATE ?,?";
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthDayD=LocalDate.parse(birthDay,formatter);

            request.setString(1, FIO);
            request.setDate(2, Date.valueOf(birthDayD));

            ResultSet result = request.executeQuery();
            if (result.next()) {
               user.setUserId(result.getInt(1));
                user.setUserLogin(result.getString(2));
                user.setFIO(result.getString(3));
                user.setPhoneNumber(result.getString(4));
                user.setBirthDate(result.getString(5));
                user.setEmail(result.getString(6));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
