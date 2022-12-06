package com.example.javafxconferenceorganizationcompany.controllers.MainPersonalAssistantAndVideographer;

import com.example.javafxconferenceorganizationcompany.ConferenceOrganizationCompanyApplication;
import com.example.javafxconferenceorganizationcompany.controllers.Conference.MainVideographerConferenceController;
import com.example.javafxconferenceorganizationcompany.models.Conference;
import com.example.javafxconferenceorganizationcompany.models.User;
import com.example.javafxconferenceorganizationcompany.repository.CompanyRepository;
import com.example.javafxconferenceorganizationcompany.repository.ConferenceRepository;
import com.example.javafxconferenceorganizationcompany.repository.UserRepository;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import java.sql.Connection;
import java.util.ResourceBundle;

public class PersonalAssistantAndVideographerMainController implements Initializable {

    private Connection connection;
    private UserRepository userRepository;
    private ConferenceRepository conferenceRepository;
    private Stage stage;
    private Integer id;

    private Integer roleId;

    @FXML
    private TableView<Conference> conferences;

    @FXML
    private TableColumn<User,String> startColumn;

    @FXML
    private TableColumn<User,String> finishColumn;

    @FXML
    private TableColumn<User,String> locationColumn;

    @FXML
    private TableColumn<User, String> companyColumn;

    @FXML
    private TableColumn<User, Button> moreColumn;

    @FXML
    private Label email;

    @FXML
    private Label birthDay;

    @FXML
    private Label login;

    @FXML
    private Label phoneNumber;

    @FXML
    private Label FIO;

    public void setConnection(Connection con){
        connection=con;
    }

    public void setUserRepository(UserRepository userRep) {
        userRepository=userRep;
    }

    public void setConferenceRepository (ConferenceRepository conferenceRep){
        conferenceRepository=conferenceRep;
    }

    public void setStage(Stage st) {
        stage = st;
    }

    public void setId(Integer i) {
        id = i;
    }

    public void setRoleId(Integer role){
        roleId=role;
    }

    public void setInfo(){
        User user=UserRepository.getUserPersonalInfo(id);

        login.setText(user.getUserLogin());
        FIO.setText(user.getFIO());
        phoneNumber.setText(user.getPhoneNumber());
        birthDay.setText(user.getBirthDate());
        email.setText(user.getEmail());

        ObservableList<Conference> confs = null;

        if (roleId==2){
            confs=conferenceRepository.getPersonalAssistantConferencesByID(id);
        }
        else{
            System.out.println(roleId);
            confs=conferenceRepository.getVideographerConferencesByID(id);
        }

        for (Conference conf : confs) {

            EventHandler<ActionEvent> handler = null;
            if (roleId == 3) {

                handler = event -> {
                    FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("videographer-conference-view.fxml"));
                    Scene newScene = null;
                    try {
                        newScene = new Scene(fxmlLoader.load(), 700, 700);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    MainVideographerConferenceController controller = fxmlLoader.getController();
                    controller.setConnection(connection);
                    controller.setConferenceRepository(conferenceRepository);
                    controller.setCompanyRepository(new CompanyRepository(connection));
                    controller.setVideographerId(id);
                    controller.setStage(stage);
                    System.out.println(id);
                    controller.setConferenceId(conf.getConferenceId());
                    System.out.println(conf.getConferenceId());
                    controller.setInfo();
                    stage.setScene(newScene);
                };
            } else {

            }

            Button button = conf.getMore();////устанавливаем кнопкам обработчики
            button.setOnAction(handler);
            conf.setMore(button);
        }

        startColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
//        startColumn.setCellValueFactory(cell -> cell.setWarpText(true));

        finishColumn.setCellValueFactory(new PropertyValueFactory<>("finishTime"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("conferenceLocationAddress"));
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        moreColumn.setCellValueFactory(new PropertyValueFactory<>("more"));

        // заполняем таблицу данными
        conferences.setItems(confs);
    }
    public void onClickChangePassword(){
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("personal-assistant-videographer-change-password.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChangePasswordController controller = fxmlLoader.getController();
        controller.setConferenceRepository(conferenceRepository);
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        controller.setRoleId(roleId);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);

    }

    public void onClickChangeLogin(){
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("personal-assistant-videographer-change-login.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChangeLoginController controller = fxmlLoader.getController();
        controller.setConferenceRepository(conferenceRepository);
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        controller.setRoleId(roleId);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);

    }

    public void onClickChangeEmail(){
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("personal-assistant-videographer-change-email.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChangeEmailController controller = fxmlLoader.getController();
        controller.setConferenceRepository(conferenceRepository);
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        controller.setRoleId(roleId);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);

    }
    public void onClickChangePhoneNumber(){
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("personal-assistant-videographer-change-phone.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChangePhoneNumberController controller = fxmlLoader.getController();
        controller.setConferenceRepository(conferenceRepository);
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        controller.setRoleId(roleId);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}

