package com.example.javafxconferenceorganizationcompany.controllers.Conference;

import com.example.javafxconferenceorganizationcompany.ConferenceOrganizationCompanyApplication;
import com.example.javafxconferenceorganizationcompany.controllers.MainPersonalAssistantAndVideographer.PersonalAssistantAndVideographerMainController;
import com.example.javafxconferenceorganizationcompany.models.*;
import com.example.javafxconferenceorganizationcompany.repository.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class MainVideographerConferenceController {
    @FXML
    private Label FIO;
    @FXML
    private Label amount;
    @FXML
    private Label locationAddress;
    @FXML
    private Label phoneNumber;
    @FXML
    private CheckBox photoIsRequired;
    @FXML
    private CheckBox videoIsRequired;
    @FXML
    private Label personalAssistantPhone;
    @FXML
    private Label personalAssistant;
    @FXML
    private Label companyName;
    @FXML
    private Label start;
    @FXML
    private Label finish;

    private Integer conferenceId;

    private Integer videographerId;

    private Stage stage;
    private CompanyRepository companyRepository;
    private ConferenceRepository conferenceRepository;

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setConferenceRepository(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
    }

    public void setVideographerId(Integer id) {
        this.videographerId=id;
    }

    public void setInfo() {
        Conference conference = conferenceRepository.getConferenceById(conferenceId);

        start.setText(conference.getStartTime());
        finish.setText(conference.getFinishTime());
        amount.setText(String.valueOf(conference.getParticipantsAmount()));
        System.out.println(conference.getParticipantsAmount());

        int locationId=conference.getConferenceLocationId();
        int companyId = conference.getCompanyId();

        Company company = companyRepository.getCompanyByItsId(companyId);
        companyName.setText(company.getCompanyName());
        FIO.setText(company.getMainParticipantFIO());
        phoneNumber.setText(company.getMainParticipantContactTelephoneNumber());

        UserRepository userRepository = new UserRepository(connection);
        User user = userRepository.getPersonalAssistantByConferenceId(conferenceId);
        personalAssistant.setText(user.getFIO());
        personalAssistantPhone.setText(user.getPhoneNumber());

        LocationRepository locationRepository=new LocationRepository(connection);
        Location location=locationRepository.getLocationById(locationId);

        locationAddress.setText(location.getLocationAddress());

        VideoAndPhotoShootingRepository videoAndPhotoShootingRepository= new VideoAndPhotoShootingRepository(connection);
        VideoAndPhotoShooting vid= videoAndPhotoShootingRepository.getVideoAndPhotoShootingByUserIDAndConferenceId(videographerId, conferenceId);

        videoIsRequired.setSelected(vid.isVideoIsRequired());
        System.out.println(vid.isVideoIsRequired());
        videoIsRequired.setDisable(true);
        videoIsRequired.setStyle("-fx-opacity: 1");

        photoIsRequired.setSelected(vid.isPhotoIsRequired());
        System.out.println(vid.isPhotoIsRequired());
        photoIsRequired.setDisable(true);
        photoIsRequired.setStyle("-fx-opacity: 1");
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
        controller.setRoleId(3);
        controller.setId(videographerId);
        controller.setInfo();
        stage.setScene(newScene);

    }
}
