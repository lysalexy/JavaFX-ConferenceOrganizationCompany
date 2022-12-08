package com.example.javafxconferenceorganizationcompany.controllers;

import com.example.javafxconferenceorganizationcompany.ConferenceOrganizationCompanyApplication;
import com.example.javafxconferenceorganizationcompany.controllers.MainAdmin.AdministratorMainController;
import com.example.javafxconferenceorganizationcompany.models.Company;
import com.example.javafxconferenceorganizationcompany.models.Dish;
import com.example.javafxconferenceorganizationcompany.repository.CompanyRepository;
import com.example.javafxconferenceorganizationcompany.repository.DishRepository;
import com.example.javafxconferenceorganizationcompany.repository.UserRepository;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompaniesController {
    @FXML
    private TableView companiesTable;
    @FXML
    private TableColumn companyNameColumn;
    @FXML
    private TableColumn FIOColumn;
    @FXML
    private TableColumn phoneColumn;
    @FXML
    private TextField FIO ;
    @FXML
    private TextField companyName;
    @FXML
    private Label invalidCompanyName;
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
    public void add(){
        invalidFIO.setText("");
        invalidNumber.setText("");
        invalidCompanyName.setText("");

        boolean companyNameIsValid=true;
        boolean FIOIsValid=true;
        boolean phoneIsValid=true;

        String companyNameV=companyName.getText();

        CompanyRepository companyRepository=new CompanyRepository(connection);

        if (companyRepository.getCompanyByName(companyNameV).getCompanyId()!=0){
            companyNameIsValid=false;
            invalidCompanyName.setText("Компания с таким названием уже есть");
        }

        String FIOV= FIO.getText();

        Pattern fi=Pattern.compile("([А-ЯË][а-яё]*( |\\z|\\-)){2,5}");
        Matcher matchF = fi.matcher(FIOV);
        if (!matchF.matches()){
            FIOIsValid=false;
            invalidFIO.setText("Некорректное ФИО");
        }

        String newPhoneNumber= contactNumber.getText();
        Pattern phone = Pattern.compile("\\+7\\d{10}");
        Matcher matchP=phone.matcher(newPhoneNumber);
        if (!matchP.matches()) {
            phoneIsValid=false;
            invalidNumber.setText("Формат номера телефона: +7....");
        }

        if (companyNameIsValid&&FIOIsValid&&phoneIsValid){
            companyRepository.addCompany(companyNameV, FIOV,newPhoneNumber);
        }

        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("base-customer-companies.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CompaniesController controller = fxmlLoader.getController();
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
        CompanyRepository companyRepository=new CompanyRepository(connection);
        ObservableList<Company> companies = companyRepository.getAllCompanies();
        ////задаем таблицу
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        FIOColumn.setCellValueFactory(new PropertyValueFactory<>("mainParticipantFIO"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("mainParticipantContactTelephoneNumber"));

        companiesTable.setItems(companies);
    }
}
