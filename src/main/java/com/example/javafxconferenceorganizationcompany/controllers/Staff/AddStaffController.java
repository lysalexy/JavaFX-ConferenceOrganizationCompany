package com.example.javafxconferenceorganizationcompany.controllers.Staff;

import com.example.javafxconferenceorganizationcompany.ConferenceOrganizationCompanyApplication;
import com.example.javafxconferenceorganizationcompany.controllers.MainAdmin.AdministratorMainController;
import com.example.javafxconferenceorganizationcompany.models.Role;
import com.example.javafxconferenceorganizationcompany.repository.RoleRepository;
import com.example.javafxconferenceorganizationcompany.repository.UserRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddStaffController {
    @FXML
    private TextField email;
    @FXML
    private  DatePicker birthDate;
    @FXML
    private TextField FIO;
    @FXML
    private Button add;
    @FXML
    private TextField login;
    @FXML
    private TextField password;
    @FXML
    private ComboBox<String> role;
    @FXML
    private Label invalidLogin;
    @FXML
    private Label employeeSuccessfullyAdded;
    @FXML
    private Label invalidFIO;
    @FXML
    private TextField phoneNumber;
    @FXML
    private Label invalidPhoneNumber;
    @FXML
    private Label invalidEmail;
    @FXML
    private Label invalidPassword;
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

    public void add(){
        boolean loginIsValid=true;
        boolean passwordIsValid=true;
        boolean FIOIsValid=true;
        boolean phoneNumberIsValid=true;
        boolean emailIsValid=true;

        String loginV = login.getText();

        String loginIsAvailable=userRepository.loginIsAvailable(loginV);

        if(!loginIsAvailable.equals("")){
            loginIsValid=false;
            invalidLogin.setText(loginIsAvailable);
        }

        String passwordV=password.getText();

        if(passwordV.equals("")){
            passwordIsValid=false;
            invalidPassword.setText("Пустая строка не может быть паролем");
        }
        ////хешируем пароль
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] digest = md.digest(passwordV.getBytes(StandardCharsets.UTF_8));

        String FIOV= FIO.getText();

        Pattern fi=Pattern.compile("([А-ЯË][а-яё]*( |\\z|\\-)){2,5}");
        Matcher matchF = fi.matcher(FIOV);
        if (!matchF.matches()){
            FIOIsValid=false;
            invalidFIO.setText("Некорретное ФИО");
        }

        String newPhoneNumber= phoneNumber.getText();
        Pattern phone = Pattern.compile("\\+7\\d{10}");
        Matcher matchP=phone.matcher(newPhoneNumber);
        if (!matchP.matches()) {
            phoneNumberIsValid=false;
            invalidPhoneNumber.setText("Формат номера телефона: +7....");
        }

        LocalDate birthDateV = birthDate.getValue();

        String emailV= email.getText();
        Pattern em = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$");
        Matcher matchE=em.matcher(emailV);
        if (!matchE.matches()) {
            emailIsValid=false;
            invalidEmail.setText("Некорреткный адрес электронной почты");
        }
        //roleId

       try {
           String roleV = role.getValue();

           Role role= new RoleRepository(connection).getRoleByRoleName(roleV);

           int roleId = role.getRoleId();

           if (loginIsValid&&passwordIsValid&&FIOIsValid&&phoneNumberIsValid&&emailIsValid){
               userRepository.addNewUser(loginV,digest,FIOV,newPhoneNumber,birthDateV,emailV,roleId);

               employeeSuccessfullyAdded.setText("Новый сотрудник успешно добавлен");
           }
       }
        catch (NullPointerException e){
            FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("add-employee.fxml"));
            Scene newScene = null;
            try {
                newScene = new Scene(fxmlLoader.load(), 700, 700);
            } catch (IOException n) {
                throw new RuntimeException(n);
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



        ////считываем поля
        ///прогоняем их через валидацию
        ///если все поля валидны, то выполняем добавление
    }

    public void setInfo(){
        ObservableList<Role> roles= new RoleRepository(connection).getRoles();

        ObservableList<String> roleNames = FXCollections.observableArrayList();
        for (Role ro : roles) {
            roleNames.add(ro.getRoleName());
        }

        role.setItems(roleNames);
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
}
