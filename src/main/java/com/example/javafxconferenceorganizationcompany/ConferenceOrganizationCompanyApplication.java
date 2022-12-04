package com.example.javafxconferenceorganizationcompany;

import com.example.javafxconferenceorganizationcompany.controllers.AuthenticationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.DriverManager;

import java.io.IOException;
import java.sql.SQLException;

public class ConferenceOrganizationCompanyApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        String url = "jdbc:sqlserver://localhost:1433;user=orange;password=1234;encrypt=false;";
        try {
            var connection = DriverManager.getConnection(url);
            try(var statement = connection.prepareCall("EXEC GET_ALL_ACTIVE_DISHES")) {
                var resultSet = statement.executeQuery();
                while(resultSet.next()) {
                    stage.setTitle(resultSet.getNString(1));
                    System.out.println(resultSet.getNString(1));
                }
            }
            FXMLLoader fxmlLoader = new FXMLLoader(ConferenceOrganizationCompanyApplication.class.getResource("authentication-view.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 700, 400);
            AuthenticationController controller = fxmlLoader.getController();
            controller.setConnection(connection);

            stage.setScene(scene);
            stage.show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        launch();
    }
}