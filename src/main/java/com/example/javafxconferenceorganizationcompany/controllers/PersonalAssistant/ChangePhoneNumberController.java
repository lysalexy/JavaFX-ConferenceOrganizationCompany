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

public class ChangePhoneNumberController implements Initializable {
    UserRepository userRepository;
    Stage stage;
    Integer id;

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
        email.setText(user.getEmail());
    }

    public void onClick() {
        ////проверка на дурака "Некорректные данные. Введите номер телефора в формате +79290367459"
        UserRepository.changePhoneNumber(id,newPhone.getText());
        System.out.println(newPhone.getText());

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
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
