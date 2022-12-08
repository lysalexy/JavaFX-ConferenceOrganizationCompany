package com.example.javafxconferenceorganizationcompany.controllers.Staff;

import com.example.javafxconferenceorganizationcompany.ConferenceOrganizationCompanyApplication;
import com.example.javafxconferenceorganizationcompany.controllers.MainAdmin.AdministratorMainController;
import com.example.javafxconferenceorganizationcompany.models.User;
import com.example.javafxconferenceorganizationcompany.repository.UserRepository;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class StaffController {
    @FXML
    private TableView getAllPersonalAssistants;
    @FXML
    private TableColumn loginColumn;
    @FXML
    private TableColumn FIOColumn;
    @FXML
    private TableColumn phoneColumn;
    @FXML
    private TableColumn birthDayColumn;
    @FXML
    private TableColumn emailColumn;
    @FXML
    private TableColumn deleteColumn;
    @FXML
    private TableView getAllVideographers;
    @FXML
    private TableColumn vidLoginColumn;
    @FXML
    private TableColumn vidFIOColumn;
    @FXML
    private TableColumn vidPhoneColumn;
    @FXML
    private TableColumn vidBirthDayColumn;
    @FXML
    private TableColumn vidEmailColumn;
    @FXML
    private TableColumn vidDeleteColumn;
    private UserRepository userRepository;
    private Stage stage;
    private Integer id;
    private Integer roleId;

    private Connection connection;

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

    public void setInfo(){
        ////заполняем таблицу личных помощников
        //заполняем таблицу видеграфов

        ObservableList<User> personalAssistants = userRepository.getAllActivePersonalAssistants();


        for (User pers: personalAssistants) {
            //задаём обработчик кнопки удаления
            EventHandler<ActionEvent> handler = event ->
            {
                userRepository.deactivateUser(pers.getUserId());

                FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("staff.fxml"));
                Scene newScene = null;
                try {
                    newScene = new Scene(fxmlLoader.load(), 700, 700);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                StaffController controller = fxmlLoader.getController();
                controller.setConnection(connection);
                controller.setUserRepository(userRepository);
                controller.setStage(stage);
                System.out.println(roleId);
                controller.setRoleId(roleId);
                controller.setId(id);
                controller.setInfo();
                stage.setScene(newScene);
            };

            Button button = pers.getDelete();////устанавливаем кнопкам обработчики
            button.setOnAction(handler);
            pers.setDelete(button);

        }
        ////задаем таблицу

        loginColumn.setCellValueFactory(new PropertyValueFactory<>("userLogin"));
        FIOColumn.setCellValueFactory(new PropertyValueFactory<>("FIO"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        birthDayColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        deleteColumn.setCellValueFactory(new PropertyValueFactory<>("delete"));

        getAllPersonalAssistants.setItems(personalAssistants);

        ObservableList<User> videographers = userRepository.getAllActiveVideographers();


        for (User vid: videographers) {
            //задаём обработчик кнопки удаления
            EventHandler<ActionEvent> handler = event ->
            {
                userRepository.deactivateUser(vid.getUserId());

                FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("staff.fxml"));
                Scene newScene = null;
                try {
                    newScene = new Scene(fxmlLoader.load(), 700, 700);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                StaffController controller = fxmlLoader.getController();
                controller.setConnection(connection);
                controller.setUserRepository(userRepository);
                controller.setStage(stage);
                System.out.println(roleId);
                controller.setRoleId(roleId);
                controller.setId(id);
                controller.setInfo();
                stage.setScene(newScene);
            };

            Button button = vid.getDelete();////устанавливаем кнопкам обработчики
            button.setOnAction(handler);
            vid.setDelete(button);

        }
        ////задаем таблицу фуршета

        vidLoginColumn.setCellValueFactory(new PropertyValueFactory<>("userLogin"));
        vidFIOColumn.setCellValueFactory(new PropertyValueFactory<>("FIO"));
        vidPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        vidBirthDayColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        vidEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        vidDeleteColumn.setCellValueFactory(new PropertyValueFactory<>("delete"));

        getAllVideographers.setItems(videographers);
    }

    public void backToMain(){
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

    public void addStaff(){
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("add-employee.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddStaffController controller = fxmlLoader.getController();
        controller.setConnection(connection);
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        System.out.println(roleId);
        controller.setRoleId(roleId);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);
    }
}
