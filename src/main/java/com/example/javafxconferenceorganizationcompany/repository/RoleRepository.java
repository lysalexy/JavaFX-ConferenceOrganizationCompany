package com.example.javafxconferenceorganizationcompany.repository;

import com.example.javafxconferenceorganizationcompany.models.Role;
import com.example.javafxconferenceorganizationcompany.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRepository {
    private static Connection connection;

    public RoleRepository(Connection con) {
        connection = con;
    }

    public ObservableList<Role> getRoles() {
        ObservableList<Role> roles = FXCollections.observableArrayList();

        String sql="SELECT * FROM Roles";

        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);

            ResultSet res = request.executeQuery();
            while (res.next()) {
               Role role = new Role();

               role.setRoleId(res.getInt(1));
               role.setRoleName(res.getString(2));

                roles.add(role);
            }
            return roles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Role getRoleByRoleName(String roleName){
        Role role = new Role();

        String sql = "EXEC GET_ROLE_ID_BY_ITS_NAME ?";

        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);
            request.setString(1, roleName);
            ResultSet result = request.executeQuery();
            if (result.next()) {
                role.setRoleId(result.getInt(1));
                role.setRoleName(result.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return role;
    }

}
