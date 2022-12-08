package com.example.javafxconferenceorganizationcompany.controllers.Conference;

import com.example.javafxconferenceorganizationcompany.ConferenceOrganizationCompanyApplication;
import com.example.javafxconferenceorganizationcompany.controllers.MainAdmin.AdministratorMainController;
import com.example.javafxconferenceorganizationcompany.models.Company;
import com.example.javafxconferenceorganizationcompany.models.Location;
import com.example.javafxconferenceorganizationcompany.models.User;
import com.example.javafxconferenceorganizationcompany.repository.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddConferenceController {

    @FXML
    private  TextField description;
    @FXML
    private TextField start;
    @FXML
    private TextField finish;
    @FXML
    private Button add;
    @FXML
    private TextField amount;
    @FXML
    private ComboBox customerCompany;
    @FXML
    private ComboBox personalAssistantFIO;
    @FXML
    private ComboBox personalAssistantBirthDay;
    @FXML
    private Button getBirthDay;
    @FXML
    private Label invalidStart;
    @FXML
    private Label invalidAmount;
    @FXML
    private Label conferenceSuccessfullyAdded;
    @FXML
    private Label invalidFIO;
    @FXML
    private TextField budget;
    @FXML
    private Label invalidBudget;
    @FXML
    private Label invalidFinish;
    @FXML
    private Button getFree;
    @FXML
    private ComboBox loc;
    private Connection connection;
    private UserRepository userRepository;
    private Stage stage;
    private Integer id;

    private Integer roleId;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setUserRepository(UserRepository userRep) {
        userRepository=userRep;
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

    public void enterDateAndAmount(){
        invalidStart.setText("");
        invalidFinish.setText("");
        invalidBudget.setText("");
        invalidAmount.setText("");
        boolean startIsValid=true;
        boolean finishIsValid=true;

        String startV=start.getText();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            LocalDateTime startD = LocalDateTime.parse(startV, formatter);
        }
        catch (DateTimeParseException e){
            startIsValid=false;
            invalidStart.setText("Формат даты и времени yyyy-MM-dd HH:mm:ss.S");
        }
        String finishV=finish.getText();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            LocalDateTime startD = LocalDateTime.parse(finishV, formatter);
        }
        catch (DateTimeParseException e){
            finishIsValid=false;
            invalidFinish.setText("Формат даты и времени yyyy-MM-dd HH:mm:ss.S");
        }
        ///проверяем даты на корректность

        try {
            int am = Integer.parseInt(amount.getText());
            if (am <= 0) {
                throw new NumberFormatException();
            }

            if (startIsValid&&finishIsValid)
            {
                /////заполняем чекбоксы
                ObservableList<Location> locations= new LocationRepository(connection).getAllFreeLocations(startV,finishV,am);

                ObservableList<String> locationNames = FXCollections.observableArrayList();
                for (Location loc : locations) {
                    locationNames.add(loc.getLocationName());
                }
                loc.setItems(locationNames);

                ObservableList<Company> companies= new CompanyRepository(connection).getAllCompanies();

                ObservableList<String> companyNames = FXCollections.observableArrayList();
                for (Company comp: companies) {
                    companyNames.add(comp.getCompanyName());
                }
                customerCompany.setItems(companyNames);

                ObservableList<User> assistants= userRepository.getFreePersonalAssistants(startV, finishV);

                ObservableList<String> assistantsFIO = FXCollections.observableArrayList();
                for (User ass: assistants) {
                    assistantsFIO.add(ass.getFIO());
                }
               personalAssistantFIO.setItems(assistantsFIO);

            }
        } catch (NumberFormatException e) {
            invalidAmount.setText("Должно быть целым положительным числом");
        }
        ///проверяем число на корректность
    }

    public void enterFIO(){
        invalidStart.setText("");
        invalidFinish.setText("");
        invalidBudget.setText("");
        invalidAmount.setText("");
        ObservableList<String> birthDayByFIO = userRepository.getUserBirthDayByFIO((String) personalAssistantFIO.getValue());

        personalAssistantBirthDay.setItems(birthDayByFIO);
        ////заполняем дату
    }

    public void setInfo(){
        ObservableList<Location> locations= new LocationRepository(connection).getAllLocations();

        ObservableList<String> locationNames = FXCollections.observableArrayList();
        for (Location loc : locations) {
            locationNames.add(loc.getLocationName());
        }
        loc.setItems(locationNames);

        ObservableList<Company> companies= new CompanyRepository(connection).getAllCompanies();

        ObservableList<String> companyNames = FXCollections.observableArrayList();
        for (Company comp: companies) {
            companyNames.add(comp.getCompanyName());
        }
        customerCompany.setItems(companyNames);

        ObservableList<User> assistants= userRepository.getAllActivePersonalAssistants();

        ObservableList<String> assistantsFIO = FXCollections.observableArrayList();
        for (User ass: assistants) {
            assistantsFIO.add(ass.getFIO());
        }
        personalAssistantFIO.setItems(assistantsFIO);

        ObservableList<String> birthDay= FXCollections.observableArrayList();

        birthDay.add("empty");

        personalAssistantBirthDay.setItems(birthDay);

    }

    public void add(){
        ////все собираем, проверяем, пишем, и если что-то не ок, то не добавляем
        invalidStart.setText("");
        invalidFinish.setText("");
        invalidBudget.setText("");
        invalidAmount.setText("");
        invalidFIO.setText("");

        boolean startIsValid=true;
        boolean finishIsValid=true;

        String startV=start.getText();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            LocalDateTime startD = LocalDateTime.parse(startV, formatter);
        }
        catch (DateTimeParseException e){
            startIsValid=false;
            invalidStart.setText("Формат даты и времени yyyy-MM-dd HH:mm:ss.S");
        }
        String finishV=finish.getText();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            LocalDateTime startD = LocalDateTime.parse(finishV, formatter);
        }
        catch (DateTimeParseException e){
            finishIsValid=false;
            invalidFinish.setText("Формат даты и времени yyyy-MM-dd HH:mm:ss.S");
        }
        ///проверяем даты на корректность

        try {
            int am = Integer.parseInt(amount.getText());
            if (am <= 0) {
                throw new NumberFormatException();
            }

            try {
                double budgetV= Double.parseDouble(budget.getText());
                if (budgetV<=0){
                    throw new NumberFormatException();
                }
                if (startIsValid && finishIsValid) {
                    Location locat= new LocationRepository(connection).getLocationByName((String) loc.getValue());
                    int locationId=locat.getLocationId();

                    Company comp = new CompanyRepository(connection).getCompanyByName((String) customerCompany.getValue());
                    int companyId=comp.getCompanyId();

                    User personalAssistant = userRepository.getUserByHisFIOAndBirthDay((String) personalAssistantFIO.getValue(), (String) personalAssistantBirthDay.getValue());
                    int persId=personalAssistant.getUserId();

                    String descriptionV=description.getText();

                    new ConferenceRepository(connection).addConference(startV,finishV,am,budgetV,descriptionV,locationId,companyId,persId);
                    conferenceSuccessfullyAdded.setText("Конференция успешно создана");

                    ////добавление конференции, вывод в лейбл
                }
            }
            catch (NumberFormatException e){
                invalidBudget.setText("Должен быть положительным числом");
            }
        } catch (NumberFormatException e) {
            invalidAmount.setText("Должно быть целым положительным числом");
        }
    }
}
