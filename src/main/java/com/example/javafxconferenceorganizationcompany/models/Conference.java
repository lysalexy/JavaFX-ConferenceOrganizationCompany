package com.example.javafxconferenceorganizationcompany.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

public class Conference {
    private IntegerProperty conferenceId;
    private StringProperty startTime;
    private StringProperty finishTime;
    private IntegerProperty participantsAmount;
    private IntegerProperty budjet;
    private StringProperty conferenceDescription;
    private IntegerProperty conferenceLocationId;
    private StringProperty conferenceLocationAddress;
    private IntegerProperty companyId;
    private StringProperty companyName;
    private StringProperty mainParticipantFIO;
    private StringProperty mainParticipantContactTelephoneNumber;
    private IntegerProperty personalAssistantId;

    private Button more;

    public Conference(){
        conferenceId=new SimpleIntegerProperty();
        startTime=new SimpleStringProperty();
        finishTime=new SimpleStringProperty();
        participantsAmount=new SimpleIntegerProperty();
        budjet=new SimpleIntegerProperty();
        conferenceDescription=new SimpleStringProperty();
        conferenceLocationId=new SimpleIntegerProperty();
        conferenceLocationAddress=new SimpleStringProperty();
        companyId=new SimpleIntegerProperty();
        companyName=new SimpleStringProperty();
        mainParticipantFIO=new SimpleStringProperty();
        mainParticipantContactTelephoneNumber=new SimpleStringProperty();
        personalAssistantId=new SimpleIntegerProperty();
        more=new Button("Дополнительно");
        more.setStyle("-fx-background-color: #FF7F50;-fx-text-fill:#f4f2f2");
    };

    public void setConferenceId(int id) {
       conferenceId.set(id);
    }
    public void setStartTime(String start) {
        startTime.set(start);
    }
    public void setFinishTime(String finish) {
        finishTime.set(finish);
    }
    public void setParticipantsAmount(int amount) {
       participantsAmount.set(amount);
    }
    public void setBudjet(int budj) {
        budjet.set(budj);
    }
    public void setConferenceDescription(String description) {
        conferenceDescription.set(description);
    }
    public void setConferenceLocationId(int id) {
        conferenceLocationId.set(id);
    }
    public void setConferenceLocationAddress(String address) {
        conferenceLocationAddress.set(address);
    }
    public void setCompanyId(int id) {
        companyId.set(id);
    }
    public void setCompanyName(String name) {
       companyName.set(name);
    }
    public void setMainParticipantFIO(String FIO) {
        mainParticipantFIO.set(FIO);
    }
    public void setMainParticipantContactTelephoneNumber(String number) {
        mainParticipantContactTelephoneNumber.set(number);
    }
    public void setPersonalAssistantId(int id) {
        personalAssistantId.set(id);
    }

    public int getConferenceId() {
        return conferenceId.get();
    }
    public String getStartTime() {
        return startTime.get();
    }
    public String getFinishTime() {
        return finishTime.get();
    }
    public int getParticipantsAmount() {
        return participantsAmount.get();
    }
    public int getBudjet() {
        return budjet.get();
    }
    public String getConferenceDescription() {
        return conferenceDescription.get();
    }
    public int getConferenceLocationId() {
        return conferenceLocationId.get();
    }
    public String getConferenceLocationAddress() {
        return conferenceLocationAddress.get();
    }
    public int getCompanyId() {
        return companyId.get();
    }
    public String getCompanyName() {
        return companyName.get();
    }
    public String getMainParticipantFIO() {
        return mainParticipantFIO.get();
    }
    public String getMainParticipantContactTelephoneNumber() {
        return mainParticipantContactTelephoneNumber.get();
    }
    public int getPersonalAssistantId() {
        return personalAssistantId.get();
    }

    public Button getMore() {
        return more;
    }

    public void setMore(Button more) {
        this.more = more;
    }
}
