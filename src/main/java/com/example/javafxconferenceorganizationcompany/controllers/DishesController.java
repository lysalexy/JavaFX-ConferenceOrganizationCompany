package com.example.javafxconferenceorganizationcompany.controllers;

import com.example.javafxconferenceorganizationcompany.ConferenceOrganizationCompanyApplication;
import com.example.javafxconferenceorganizationcompany.controllers.MainAdmin.AdministratorMainController;
import com.example.javafxconferenceorganizationcompany.models.Dish;
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

public class DishesController {
    @FXML
    private TextField dishName;
    @FXML
    private Button companies;
    @FXML
    private TableView dishTable;
    @FXML
    private TableColumn dishNameColumn;
    @FXML
    private TableColumn dishCompositionColumn;
    @FXML
    private TableColumn dishCostColumn;
    @FXML
    private TableColumn delete;
    @FXML
    private Label invalidName;
    @FXML
    private TextField composition;
    @FXML
    private Label invalidComposition;
    @FXML
    private TextField dishCost;
    @FXML
    private Label invalidCost;
    @FXML
    private Button locations;
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

    public void add() {
        invalidComposition.setText("");
        invalidCost.setText("");
        invalidName.setText("");

        boolean nameIsValid = true;

        String dishNameV = dishName.getText();

        DishRepository dishRepository = new DishRepository(connection);

        if (dishRepository.getDishByDishName(dishNameV) != 0) {
            nameIsValid = false;
            invalidName.setText("Блюдо с таким названием уже есть");
        }

        String compositionV = composition.getText();

        String costPerPos = dishCost.getText();

        try {
            double cost = Double.parseDouble(costPerPos);

            if (nameIsValid) {
                new DishRepository(connection).addDish(dishNameV,compositionV,cost);

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
        } catch (NumberFormatException e) {
            invalidCost.setText("Необходимо ввести неотрицательное число");
        }
    }

    public void setInfo() {
        DishRepository dishRepository = new DishRepository(connection);
        ObservableList<Dish> dishes = dishRepository.getAllActiveDishes();


        for (Dish dish : dishes) {
            //задаём обработчик кнопки удаления
            EventHandler<ActionEvent> handler = event ->
            {
                dishRepository.deactivateDish(dish.getDishId());

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
            };

            Button button = dish.getDelete();////устанавливаем кнопкам обработчики
            button.setOnAction(handler);
            dish.setDelete(button);

        }
        ////задаем таблицу
        dishNameColumn.setCellValueFactory(new PropertyValueFactory<>("dishName"));
        dishCompositionColumn.setCellValueFactory(new PropertyValueFactory<>("productComposition"));
        dishCostColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));

        delete.setCellValueFactory(new PropertyValueFactory<>("delete"));

        dishTable.setItems(dishes);
    }
    public void goToLocations(){
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

    public void goToCompanies(){
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
}
