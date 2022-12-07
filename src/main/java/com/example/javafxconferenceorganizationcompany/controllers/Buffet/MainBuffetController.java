package com.example.javafxconferenceorganizationcompany.controllers.Buffet;

import com.example.javafxconferenceorganizationcompany.ConferenceOrganizationCompanyApplication;
import com.example.javafxconferenceorganizationcompany.controllers.MainPersonalAssistantAndVideographer.PersonalAssistantAndVideographerMainController;
import com.example.javafxconferenceorganizationcompany.models.BuffetPosition;
import com.example.javafxconferenceorganizationcompany.models.Dish;
import com.example.javafxconferenceorganizationcompany.repository.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class MainBuffetController {
    @FXML
    private TableView buffetPositionsTable;
    @FXML
    private TableColumn dishNamePosColumn;
    @FXML
    private TableColumn amountColumn;
    @FXML
    private TableColumn deleteColumn;
    @FXML
    private Button add;
    @FXML
    private TableView dishesTable;
    @FXML
    private TableColumn dishNameColumn;
    @FXML
    private TableColumn compositionColumn;
    @FXML
    private TableColumn costPerPositionColumn;

    private Integer conferenceId;

    private Integer personalAssistantId;

    private Stage stage;
    private CompanyRepository companyRepository;
    private ConferenceRepository conferenceRepository;

    private Connection connection;


    public void setPersonalAssistantId(Integer personalAssistantId) {
        this.personalAssistantId = personalAssistantId;
    }

    public void setConferenceRepository(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
    }

    public void backToConference() {
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("personal-assistant-videographer-main-view.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PersonalAssistantAndVideographerMainController controller = fxmlLoader.getController();
        controller.setConferenceRepository(conferenceRepository);
        controller.setConnection(connection);
        controller.setUserRepository(new UserRepository(connection));
        controller.setStage(stage);
        controller.setRoleId(2);
        controller.setId(personalAssistantId);
        controller.setInfo();
        stage.setScene(newScene);
    }

    public void backToMain() {
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("personal-assistant-videographer-main-view.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PersonalAssistantAndVideographerMainController controller = fxmlLoader.getController();
        controller.setConferenceRepository(conferenceRepository);
        controller.setConnection(connection);
        controller.setUserRepository(new UserRepository(connection));
        controller.setStage(stage);
        controller.setRoleId(2);
        controller.setId(personalAssistantId);
        controller.setInfo();
        stage.setScene(newScene);
    }

    public void setInfo() {
        BuffetPositionRepository buffet = new BuffetPositionRepository(connection);

        ///собираем фуршет
        ObservableList<BuffetPosition> positions = buffet.getBuffetPositionsByConferenceId(conferenceId);


        for (BuffetPosition pos : positions) {
            //задаём обработчик кнопки удаления
            EventHandler<ActionEvent> handler = event ->
            {
                buffet.deleteBuffetPositionById(pos.getBuffetPositionId());

                FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("buffet-menu.fxml"));
                Scene newScene = null;
                try {
                    newScene = new Scene(fxmlLoader.load(), 700, 700);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                MainBuffetController controller = fxmlLoader.getController();
                controller.setConnection(connection);
                controller.setCompanyRepository(new CompanyRepository(connection));
                controller.setConferenceId(conferenceId);
                controller.setConferenceRepository(conferenceRepository);
                controller.setPersonalAssistantId(personalAssistantId);
                controller.setStage(stage);
                controller.setInfo();
                stage.setScene(newScene);
            };

            Button button = pos.getDelete();////устанавливаем кнопкам обработчики
            button.setOnAction(handler);
            pos.setDelete(button);

        }
        ////задаем таблицу фуршета

        dishNamePosColumn.setCellValueFactory(new PropertyValueFactory<>("dishesName"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("dishesAmount"));
        deleteColumn.setCellValueFactory(new PropertyValueFactory<>("delete"));

        buffetPositionsTable.setItems(positions);

        DishRepository dishRepository = new DishRepository(connection);
        ObservableList<Dish> dishes = dishRepository.getAllActiveDishes();


        dishNameColumn.setCellValueFactory(new PropertyValueFactory<>("dishName"));
        compositionColumn.setCellValueFactory(new PropertyValueFactory<>("productComposition"));
        costPerPositionColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));

        dishesTable.setItems(dishes);
        ///задаём таблицу всех блюд
    }

    public void add() {
        ////переходим на страничку добавления
        FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("buffet-menu-add.fxml"));
        Scene newScene = null;
        try {
            newScene = new Scene(fxmlLoader.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddBuffetController controller = fxmlLoader.getController();
        controller.setConnection(connection);
        controller.setCompanyRepository(new CompanyRepository(connection));
        controller.setConferenceId(conferenceId);
        controller.setConferenceRepository(conferenceRepository);
        controller.setPersonalAssistantId(personalAssistantId);
        controller.setStage(stage);
        controller.setInfo();
        stage.setScene(newScene);
    }
}
