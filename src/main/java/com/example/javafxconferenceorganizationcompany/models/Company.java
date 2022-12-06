package com.example.javafxconferenceorganizationcompany.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Company {
    private IntegerProperty companyId;
    private StringProperty companyName;
    private StringProperty mainParticipantFIO;
    private StringProperty mainParticipantContactTelephoneNumber;

    public Company(){
        companyId=new SimpleIntegerProperty();
        companyName=new SimpleStringProperty();
        mainParticipantFIO=new SimpleStringProperty();
        mainParticipantContactTelephoneNumber=new SimpleStringProperty();
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
}
