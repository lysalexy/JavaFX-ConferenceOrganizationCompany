module com.example.javafxconferenceorganizationcompany {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.javafxconferenceorganizationcompany to javafx.fxml;
    exports com.example.javafxconferenceorganizationcompany;
    exports com.example.javafxconferenceorganizationcompany.controllers;
    opens com.example.javafxconferenceorganizationcompany.controllers to javafx.fxml;
    exports com.example.javafxconferenceorganizationcompany.controllers.MainPersonalAssistantAndVideographer;
    opens com.example.javafxconferenceorganizationcompany.controllers.MainPersonalAssistantAndVideographer to javafx.fxml;

    exports com.example.javafxconferenceorganizationcompany.controllers.MainAdmin;
    opens com.example.javafxconferenceorganizationcompany.controllers.MainAdmin to javafx.fxml;

    exports com.example.javafxconferenceorganizationcompany.controllers.Conference;
    opens com.example.javafxconferenceorganizationcompany.controllers.Conference to javafx.fxml;

    opens com.example.javafxconferenceorganizationcompany.models to javafx.fxml;
    exports com.example.javafxconferenceorganizationcompany.models;
}