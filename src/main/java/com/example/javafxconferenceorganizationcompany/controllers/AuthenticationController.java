package com.example.javafxconferenceorganizationcompany.controllers;

import com.example.javafxconferenceorganizationcompany.ConferenceOrganizationCompanyApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ResourceBundle;

public class AuthenticationController implements Initializable {
    Connection connection;
    Stage stage;

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
                switch (roleId) {////переносим на главную вкладку роли
                    case 1:
                    case 2:
                        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("personal-assistant-main-view.fxml"));
                        Scene newScene = new Scene(fxmlLoader.load(), 700, 400);
                        stage.setScene(newScene);
                    case 3:
                }
            }
            else{
                invalidLoginOrPassword.setText("Неверный логин или пароль");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setConnection(Connection con){
        connection=con;
    }
    public void setStage(Stage st){
        stage=st;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}