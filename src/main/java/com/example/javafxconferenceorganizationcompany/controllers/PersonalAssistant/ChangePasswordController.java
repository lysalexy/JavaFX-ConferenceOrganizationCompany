package com.example.javafxconferenceorganizationcompany.controllers.PersonalAssistant;

import com.example.javafxconferenceorganizationcompany.ConferenceOrganizationCompanyApplication;
import com.example.javafxconferenceorganizationcompany.models.User;
import com.example.javafxconferenceorganizationcompany.repository.UserRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangePasswordController implements Initializable {
    UserRepository userRepository;
    Stage stage;
    Integer id;

    @FXML
    private TextField newPassword;

    @FXML
    private Label birthDay;

    @FXML
    private Label login;

    @FXML
    private Label email;

    @FXML
    private Label FIO;
    @FXML
    private Label phoneNumber;


    public void setUserRepository(UserRepository userRep) {
        userRepository = userRep;
    }

    public void setStage(Stage st) {
        stage = st;
    }

    public void setId(Integer i) {
        id = i;
    }

    public void backToMain(){
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("personal-assistant-main-view.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PersonalAssistantMainController controller = fxmlLoader.getController();
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);

    }

    public void setInfo() {
        User user = UserRepository.getUserPersonalInfo(id);

        login.setText(user.getUserLogin());
        FIO.setText(user.getFIO());
        birthDay.setText(user.getBirthDate());
        email.setText(user.getEmail());
        phoneNumber.setText(user.getPhoneNumber());
    }

    public void onClick() {
        String newPass = newPassword.getText();

        UserRepository.changePassword(id, newPass);
        backToMain();
//        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("personal-assistant-main-view.fxml"));
//        Scene newScene = null;
//        try {
//            newScene = new Scene(fxmlLoader.load(), 700, 700);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        PersonalAssistantMainController controller = fxmlLoader.getController();
//        controller.setUserRepository(userRepository);
//        controller.setStage(stage);
//        controller.setId(id);
//        controller.setInfo();
//        stage.setScene(newScene);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
