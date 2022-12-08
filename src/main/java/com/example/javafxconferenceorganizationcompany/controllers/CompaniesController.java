package com.example.javafxconferenceorganizationcompany.controllers;

import com.example.javafxconferenceorganizationcompany.ConferenceOrganizationCompanyApplication;
import com.example.javafxconferenceorganizationcompany.controllers.MainAdmin.AdministratorMainController;
import com.example.javafxconferenceorganizationcompany.repository.UserRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class CompaniesController {
    @FXML
    private TextField FIO ;
    @FXML
    private TextField companyName;
    @FXML
    private Label invalidLocationName;
    @FXML
    private Label invalidFIO;
    @FXML
    private TextField contactNumber;
    @FXML
    private Label invalidNumber;
    private Connection connection;
    private UserRepository userRepository;
    private Stage stage;
    private Integer id;

    private Integer roleId;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setUserRepository(UserRepository userRep) {
        userRepository = userRep;
    }


    public void setStage(Stage st) {
        stage = st;
    }

    public void setId(Integer i) {
        id = i;
    }

    public void setRoleId(Integer role) {
        roleId = role;
    }

    public void backToMain() {
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("administrator-main.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AdministratorMainController controller = fxmlLoader.getController();
        controller.setConnection(connection);
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        System.out.println(roleId);
        controller.setRoleId(roleId);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);
    }

    public void goToDishes(){
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("base-dishes.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DishesController controller = fxmlLoader.getController();
        controller.setConnection(connection);
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        System.out.println(roleId);
        controller.setRoleId(roleId);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);
    }
    public void goToLocation(){
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("base-locations.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LocationController controller = fxmlLoader.getController();
        controller.setConnection(connection);
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        System.out.println(roleId);
        controller.setRoleId(roleId);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);
    }

    public void setInfo(){
        ////написать
    }
}
