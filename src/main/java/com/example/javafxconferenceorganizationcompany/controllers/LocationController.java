package com.example.javafxconferenceorganizationcompany.controllers;

import com.example.javafxconferenceorganizationcompany.ConferenceOrganizationCompanyApplication;
import com.example.javafxconferenceorganizationcompany.controllers.MainAdmin.AdministratorMainController;
import com.example.javafxconferenceorganizationcompany.controllers.Staff.StaffController;
import com.example.javafxconferenceorganizationcompany.models.Location;
import com.example.javafxconferenceorganizationcompany.models.User;
import com.example.javafxconferenceorganizationcompany.repository.LocationRepository;
import com.example.javafxconferenceorganizationcompany.repository.UserRepository;
import javafx.beans.property.Property;
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

public class LocationController {
    @FXML
    private TableView locationTable;
    @FXML
    private TableColumn locationNameColumn;
    @FXML
    private TableColumn locationAddressColumn;
    @FXML
    private TableColumn locationCapacityColumn;
    @FXML
    private TableColumn costColumn;
    @FXML
    private TableColumn deleteColumn;
    @FXML
    private TextField locationName;
    @FXML
    private Button buffetPositions;
    @FXML
    private Button customerCompanies;
    @FXML
    private Label invalidLocationName;
    @FXML
    private TextField locationAddress;
    @FXML
    private Label invalidAddress;
    @FXML
    private TextField locationCapacity;
    @FXML
    private Label invalidCapacity;
    @FXML
    private TextField locationCost;
    @FXML
    private Label invalidCost;
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

    public void setInfo(){
        LocationRepository locationRepository=new LocationRepository(connection);
        ObservableList<Location> locations = locationRepository.getAllLocations();


        for (Location loc: locations) {
            //задаём обработчик кнопки удаления
            EventHandler<ActionEvent> handler = event ->
            {
                locationRepository.deleteLocation(loc.getLocationId());

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
            };

            Button button = loc.getDelete();////устанавливаем кнопкам обработчики
            button.setOnAction(handler);
            loc.setDelete(button);

        }
        ////задаем таблицу
        locationNameColumn.setCellValueFactory(new PropertyValueFactory<>("locationName"));
        locationAddressColumn.setCellValueFactory(new PropertyValueFactory<>("locationAddress"));
        locationCapacityColumn.setCellValueFactory(new PropertyValueFactory<>("locationCapacity"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("costPerHour"));

        deleteColumn.setCellValueFactory(new PropertyValueFactory<>("delete"));

        locationTable.setItems(locations);
    }
}
