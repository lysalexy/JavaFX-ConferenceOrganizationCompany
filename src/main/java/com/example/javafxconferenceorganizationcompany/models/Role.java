package com.example.javafxconferenceorganizationcompany.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Role {
    private IntegerProperty roleId;
    private StringProperty roleName;

    public Role(){
        roleId=new SimpleIntegerProperty();
        roleName=new SimpleStringProperty();
    }

    public void setRoleId(int id) {
        roleId.set(id);
    }
    public void setRoleName(String name) {
        roleName.set(name);
    }

    public int getRoleId() {
        return roleId.get();
    }
    public String getRoleName() {
        return roleName.get();
    }
}
