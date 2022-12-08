package com.example.javafxconferenceorganizationcompany.controllers.MainAdmin;

import com.example.javafxconferenceorganizationcompany.ConferenceOrganizationCompanyApplication;
import com.example.javafxconferenceorganizationcompany.controllers.CompaniesController;
import com.example.javafxconferenceorganizationcompany.controllers.Conference.AddConferenceController;
import com.example.javafxconferenceorganizationcompany.controllers.Conference.AddVideoAndPhotoWithTablesController;
import com.example.javafxconferenceorganizationcompany.controllers.Conference.MainPersonalAssistantConferenceWithVideoController;
import com.example.javafxconferenceorganizationcompany.controllers.LocationController;
import com.example.javafxconferenceorganizationcompany.controllers.Staff.AddStaffController;
import com.example.javafxconferenceorganizationcompany.controllers.Staff.StaffController;
import com.example.javafxconferenceorganizationcompany.models.Conference;
import com.example.javafxconferenceorganizationcompany.models.User;
import com.example.javafxconferenceorganizationcompany.repository.CompanyRepository;
import com.example.javafxconferenceorganizationcompany.repository.UserRepository;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

public class AdministratorMainController implements Initializable {
    @FXML
    private TextField finish;
    @FXML
    private RadioButton locations;
    @FXML
    private TextField start;
    @FXML
    private RadioButton personalAssistants;
    @FXML
    private RadioButton videographers;
    @FXML
    private Label invalidDate;
    private Connection connection;
    private UserRepository userRepository;
    private Stage stage;
    private Integer id;

    private Integer roleId;

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

    public void setInfo() {
        User user = UserRepository.getUserPersonalInfo(id);

        login.setText(user.getUserLogin());
        FIO.setText(user.getFIO());
        phoneNumber.setText(user.getPhoneNumber());
        birthDay.setText(user.getBirthDate());
        email.setText(user.getEmail());

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                ///парсим время и если всё ок то идем на другую страничку
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                    LocalDateTime startD = LocalDateTime.parse(start.getText(), formatter);
                    LocalDateTime finishD = LocalDateTime.parse(finish.getText(), formatter);

                    if (startD.isBefore(finishD)) {

                        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("administrator-main-get-locations.fxml"));
                        Scene newScene = null;
                        try {
                            newScene = new Scene(fxmlLoader.load(), 700, 700);
                        } catch (IOException r) {
                            throw new RuntimeException(r);
                        }
                        GetFreeLocations controller = fxmlLoader.getController();
                        controller.setConnection(connection);
                        controller.setConnection(connection);
                        controller.setUserRepository(userRepository);
                        controller.setStage(stage);
                        System.out.println(roleId);
                        controller.setRoleId(roleId);
                        controller.setId(id);
                        controller.setInfo();
                        controller.setStartAndFinish(start.getText(), finish.getText());
                        stage.setScene(newScene);
                    }
                    else {
                        invalidDate.setText("Время до должно быть раньше времени после");
                    }
                } catch (DateTimeParseException n) {
                    invalidDate.setText("Формат даты и времени yyyy-MM-dd HH:mm:ss.S");
                }
            }
        };

        locations.setOnAction(event);

        EventHandler<ActionEvent> eventAss = e -> {
            ///парсим время и если всё ок то идем на другую страничку
            try {
                System.out.println(start.getText());
                System.out.println(finish.getText());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                LocalDateTime startD = LocalDateTime.parse(start.getText(), formatter);
                LocalDateTime finishD = LocalDateTime.parse(finish.getText(), formatter);

                if (startD.isBefore(finishD)) {

                    FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("administrator-main-get-personal-assistants.fxml"));
                    Scene newScene = null;
                    try {
                        newScene = new Scene(fxmlLoader.load(), 700, 700);
                    } catch (IOException r) {
                        throw new RuntimeException(r);
                    }
                    GetFreePersonalAssistants controller = fxmlLoader.getController();
                    controller.setConnection(connection);
                    controller.setConnection(connection);
                    controller.setUserRepository(userRepository);
                    controller.setStage(stage);
                    System.out.println(roleId);
                    controller.setRoleId(roleId);
                    controller.setId(id);
                    controller.setStartAndFinish(start.getText(), finish.getText());
                    controller.setInfo();
                    stage.setScene(newScene);
                }
                else {
                    invalidDate.setText("Время до должно быть раньше времени после");
                }
            } catch (DateTimeParseException n) {
                invalidDate.setText("Формат даты и времени yyyy-MM-dd HH:mm:ss.S");
            }
        };

        personalAssistants.setOnAction(eventAss);

        EventHandler<ActionEvent> eventAVid = e -> {
            ///парсим время и если всё ок то идем на другую страничку
            try {
                System.out.println(start.getText());
                System.out.println(finish.getText());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                LocalDateTime startD = LocalDateTime.parse(start.getText(), formatter);
                LocalDateTime finishD = LocalDateTime.parse(finish.getText(), formatter);

                if (startD.isBefore(finishD)) {
                    FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("administrator-main-get-videographers.fxml"));
                    Scene newScene = null;
                    try {
                        newScene = new Scene(fxmlLoader.load(), 700, 700);
                    } catch (IOException r) {
                        throw new RuntimeException(r);
                    }
                    GetFreeVideographers controller = fxmlLoader.getController();
                    controller.setConnection(connection);
                    controller.setConnection(connection);
                    controller.setUserRepository(userRepository);
                    controller.setStage(stage);
                    System.out.println(roleId);
                    controller.setRoleId(roleId);
                    controller.setId(id);
                    controller.setStartAndFinish(start.getText(), finish.getText());
                    controller.setInfo();
                    stage.setScene(newScene);
                }
                else {
                    invalidDate.setText("Время до должно быть раньше времени после");
                }
            } catch (DateTimeParseException n) {
                invalidDate.setText("Формат даты и времени yyyy-MM-dd HH:mm:ss.S");
            }
        };

       videographers.setOnAction(eventAVid);

    }

    public void onClickChangePassword() {
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("administrator-main-change-password.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChangePasswordController controller = fxmlLoader.getController();
        controller.setConnection(connection);
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        controller.setRoleId(roleId);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);

    }

    public void onClickChangeLogin() {
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("administrator-main-change-login.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChangeLoginController controller = fxmlLoader.getController();
        controller.setConnection(connection);
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        controller.setRoleId(roleId);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);

    }

    public void onClickChangeEmail() {
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("administrator-main-change-email.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChangeEmailController controller = fxmlLoader.getController();
        controller.setConnection(connection);
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        controller.setRoleId(roleId);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);

    }

    public void onClickChangePhoneNumber() {
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("administrator-main-change-phone.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChangePhoneNumberController controller = fxmlLoader.getController();
        controller.setConnection(connection);
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        controller.setRoleId(roleId);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);
    }

    public void toStaff() {
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
    }

    public void addStaff() {
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

    public void goToBase() {
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

    public void addConference() {
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("add-conference.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddConferenceController controller = fxmlLoader.getController();
        controller.setConnection(connection);
        controller.setUserRepository(userRepository);
        controller.setStage(stage);
        System.out.println(roleId);
        controller.setRoleId(roleId);
        controller.setId(id);
        controller.setInfo();
        stage.setScene(newScene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}


