package com.example.javafxconferenceorganizationcompany.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ResourceBundle;

public class AuthenticationController implements Initializable {
    Connection connection;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;
    @FXML
    private Label invalidLoginOrPassword;

    @FXML
    protected void onEnterClick() throws NoSuchAlgorithmException {

        String sql="SELECT * FROM Users WHERE userLogin= ? AND userHash= ?";

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(password.getText().getBytes(StandardCharsets.UTF_8));
        PreparedStatement request= null;
        try {
            request = connection.prepareStatement(sql);

            request.setString(1, login.getText());
            request.setBytes(2, digest);
            System.out.println(request);
            ResultSet result = request.executeQuery();
            System.out.println(result);
            if (result.next()){
                int userId=result.getInt(1);
                System.out.println(userId);
                int roleId=result.getInt(8);
                login.getScene().getWindow().hide();
                switch (roleId) {////переносим на главную вкладку роли
                    case 1:
                    case 2:
                    case 3:
                }
            }
            else{
                invalidLoginOrPassword.setText("Неверный логин или пароль");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setConnection(Connection con){
        connection=con;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}