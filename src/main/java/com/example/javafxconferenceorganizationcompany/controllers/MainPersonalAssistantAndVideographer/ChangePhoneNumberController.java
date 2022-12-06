package com.example.javafxconferenceorganizationcompany.controllers.MainPersonalAssistantAndVideographer;

import com.example.javafxconferenceorganizationcompany.ConferenceOrganizationCompanyApplication;
import com.example.javafxconferenceorganizationcompany.models.Conference;
import com.example.javafxconferenceorganizationcompany.models.User;
import com.example.javafxconferenceorganizationcompany.repository.ConferenceRepository;
import com.example.javafxconferenceorganizationcompany.repository.UserRepository;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangePhoneNumberController implements Initializable {
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
    private TextField newPhone;

    @FXML
    private Label birthDay;

    @FXML
    private Label login;

    @FXML
    private Label email;

    @FXML
    private Label FIO;

    @FXML
    private Label invalidInput;

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
        birthDay.setText(user.getBirthDate());
        email.setText(user.getEmail());

        ObservableList<Conference> confs = null;

        if (roleId==2){
            confs=conferenceRepository.getPersonalAssistantConferencesByID(id);
        }
        else{
            confs=conferenceRepository.getVideographerConferencesByID(id);
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

    public void backToMain(){
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("personal-assistant-videographer-main-view.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PersonalAssistantAndVideographerMainController controller = fxmlLoader.getController();
        controller.setConferenceRepository(conferenceRepository);
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        controller.setRoleId(roleId);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);

    }
    public void onClick() {
        String newPhoneNumber= newPhone.getText();
        Pattern phone = Pattern.compile("\\+7\\d{10}");
        Matcher match=phone.matcher(newPhoneNumber);
        if (match.matches()) {
            UserRepository.changePhoneNumber(id, newPhoneNumber);
            backToMain();
        }
        else{
            invalidInput.setText("Некорректные данные. Введите номер телефора в формате +79290367459");
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
