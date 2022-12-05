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

public class ChangeEmailController implements Initializable {
    UserRepository userRepository;
    Stage stage;
    Integer id;

    @FXML
    private TextField newEmail;

    @FXML
    private Label birthDay;

    @FXML
    private Label login;

    @FXML
    private Label phoneNumber;

    @FXML
    private Label FIO;

    @FXML
    private Label invalidInput;

    public void setUserRepository(UserRepository userRep) {
        userRepository=userRep;
    }

    public void setStage(Stage st) {
        stage = st;
    }

    public void setId(Integer i) {
        id = i;
    }

    public void setInfo(){
        User user=UserRepository.getUserPersonalInfo(id);

        login.setText(user.getUserLogin());
        FIO.setText(user.getFIO());
        birthDay.setText(user.getBirthDate());
        phoneNumber.setText(user.getPhoneNumber());
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
    public void onClick() {
        String email= newEmail.getText();
        Pattern em = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$");
        Matcher match=em.matcher(email);
        if (match.matches()) {
            UserRepository.changeEmail(id, email);
            backToMain();
//            FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("personal-assistant-main-view.fxml"));
//            Scene newScene = null;
//            try {
//                newScene = new Scene(fxmlLoader.load(), 700, 700);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            PersonalAssistantMainController controller = fxmlLoader.getController();
//            controller.setUserRepository(userRepository);
//            controller.setStage(stage);
//            controller.setId(id);
//            controller.setInfo();
//            stage.setScene(newScene);
        }
        else{
            invalidInput.setText("Некорректные данные. Введите настоящий адрес электронной почты");
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
