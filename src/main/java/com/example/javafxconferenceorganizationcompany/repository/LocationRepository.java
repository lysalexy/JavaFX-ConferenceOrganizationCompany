package com.example.javafxconferenceorganizationcompany.repository;

import com.example.javafxconferenceorganizationcompany.models.Company;
import com.example.javafxconferenceorganizationcompany.models.Location;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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


    public static ObservableList<Location> getAllLocations(){
        ObservableList<Location> locations = FXCollections.observableArrayList();

        String sql="EXEC GET_ALL_LOCATIONS";

        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);

            ResultSet res = request.executeQuery();
            while (res.next()) {
                Location loc = new Location();

                loc.setLocationId(res.getInt(1));
                loc.setLocationName(res.getString(2));
                loc.setLocationAddress(res.getString(3));
                loc.setLocationCapacity(res.getInt(4));
                loc.setCostPerHour(res.getInt(5));

                locations.add(loc);
            }
            return locations;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<Location> getAllFreeLocations(String start, String finish, int amount){
        ObservableList<Location> locations = FXCollections.observableArrayList();

        String sql="EXEC GET_FREE_LOCATIONS ?,?,?";

        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            LocalDateTime startD=LocalDateTime.parse(start,formatter);
            LocalDateTime finishD=LocalDateTime.parse(finish,formatter);

            request.setTimestamp(1, Timestamp.valueOf(startD));
            request.setTimestamp(2, Timestamp.valueOf(finishD));
            request.setInt(3,amount);

            ResultSet res = request.executeQuery();
            while (res.next()) {
                Location loc = new Location();

               loc.setLocationName(res.getString(1));
               loc.setLocationAddress(res.getString(2));
               loc.setCostPerHour(res.getInt(3));

                locations.add(loc);
            }
            return locations;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Location getLocationByName(String name){
        Location location = new Location();
        String sql="SELECT * FROM Locations WHERE locationName=?";
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);
            request.setString(1, name);
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

    public Location getLocationByAddress(String address){
        Location location = new Location();
        String sql="SELECT * FROM Locations WHERE locationName=?";
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);
            request.setString(1, address);
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

    public void addLocation(String locationName, String locationAddress, int capacity, double cost){
        String sql = "EXEC ADD_LOCATION ?,?,?,?";
        try {
            PreparedStatement request = connection.prepareStatement(sql);
            request.setString(1,locationName);
            request.setString(2,locationAddress);
            request.setInt(3,capacity);
            request.setDouble(4,cost);
            request.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteLocation(int locationId){
        String sql = "EXEC DEACTIVATE_LOCATION_BY_ITS_ID ?";
        try {
            PreparedStatement request = connection.prepareStatement(sql);
            request.setInt(1,locationId);
            request.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
