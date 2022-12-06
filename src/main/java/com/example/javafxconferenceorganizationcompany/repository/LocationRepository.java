package com.example.javafxconferenceorganizationcompany.repository;

import com.example.javafxconferenceorganizationcompany.models.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationRepository {
    private static Connection connection;

    public LocationRepository(Connection con) {
        connection = con;
    }

    public Location getLocationById(int id){
        Location location = new Location();
        String sql="SELECT * FROM Locations WHERE locationId=?";
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);
            request.setInt(1, id);
            ResultSet result = request.executeQuery();
            if (result.next()) {
                location.setLocationId(result.getInt(1));
                location.setLocationName(result.getString(2));
                location.setLocationAddress(result.getString(3));
                location.setLocationCapacity(result.getInt(4));
                location.setCostPerHour(result.getInt(5));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return location;
    }
}
