package com.example.javafxconferenceorganizationcompany.models;

import javafx.beans.property.*;
import javafx.scene.control.Button;

public class VideoAndPhotoShooting {
    private IntegerProperty videoAndPhotoId;
    private IntegerProperty videographerId;
    private StringProperty videographerFIO;
    private StringProperty videographerPhoneNumber;
    private BooleanProperty photoIsRequired;
    private BooleanProperty videoIsRequired;
    private IntegerProperty conferenceId;

    private Button delete;

    public VideoAndPhotoShooting(){
        videoAndPhotoId=new SimpleIntegerProperty();
        videographerId=new SimpleIntegerProperty();
        videographerFIO=new SimpleStringProperty();
        videographerPhoneNumber=new SimpleStringProperty();
        photoIsRequired=new SimpleBooleanProperty();
        videoIsRequired=new SimpleBooleanProperty();
        conferenceId=new SimpleIntegerProperty();
        delete=new Button("Удалить");
        delete.setStyle("-fx-background-color: #FF7F50;-fx-text-fill:#f4f2f2");
    }

    public void setVideoAndPhotoId(int id) {
        videoAndPhotoId.set(id);
    }
    public void setVideographerId(int id) {
       videographerId.set(id);
    }
    public void setVideographerFIO(String FIO) {
        videographerFIO.set(FIO);
    }
    public void setVideographerPhoneNumber(String number) {
        videographerPhoneNumber.set(number);
    }
    public void setPhotoIsRequired(boolean isRequired) {
        photoIsRequired.set(isRequired);
    }
    public void setVideoIsRequired(boolean isRequired) {
        videoIsRequired.set(isRequired);
    }
    public void setConferenceId(int id) {
        conferenceId.set(id);
    }

    public void setDelete(Button delete){
        this.delete=delete;
    }

    public int getVideoAndPhotoId() {
        return videoAndPhotoId.get();
    }
    public int getVideographerId() {
        return videographerId.get();
    }
    public String getVideographerFIO() {
        return videographerFIO.get();
    }
    public String getVideographerPhoneNumber() {
        return videographerPhoneNumber.get();
    }
    public boolean isVideoIsRequired() {
        return videoIsRequired.get();
    }
    public boolean isPhotoIsRequired() {
        return photoIsRequired.get();
    }
    public int getConferenceId() {
        return conferenceId.get();
    }

    public Button getDelete() {
        return delete;
    }
}
