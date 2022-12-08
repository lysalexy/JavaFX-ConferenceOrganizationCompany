package com.example.javafxconferenceorganizationcompany.repository;

import com.example.javafxconferenceorganizationcompany.models.Company;
import com.example.javafxconferenceorganizationcompany.models.Location;
import com.example.javafxconferenceorganizationcompany.models.Role;
import com.example.javafxconferenceorganizationcompany.models.VideoAndPhotoShooting;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CompanyRepository {
    private static Connection connection;

    public CompanyRepository(Connection con) {
        connection = con;
    }

    public static Company getCompanyByItsId(int id){
        String sql = "EXEC GET_COMPANY_BY_ID ?";
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);
            request.setInt(1, id);
            ResultSet res = request.executeQuery();
            if (res.next()) {
                Company company = new Company();

                company.setCompanyName(res.getString(1));
                company.setMainParticipantFIO(res.getString(2));
                company.setMainParticipantContactTelephoneNumber(res.getString(3));

                return company;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new Company();
    }

    public static ObservableList<Company> getAllCompanies(){
        ObservableList<Company> companies = FXCollections.observableArrayList();

        String sql="EXEC GET_ALL_COMPANIES";

        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);

            ResultSet res = request.executeQuery();
            while (res.next()) {
                Company comp = new Company();

                comp.setCompanyName(res.getString(1));
                comp.setMainParticipantFIO(res.getString(2));
                comp.setMainParticipantContactTelephoneNumber(res.getString(3));

                companies.add(comp);
            }
            return companies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Company getCompanyByName(String name){
        Company company = new Company();
        String sql="SELECT * FROM Companies WHERE companyName=?";
        PreparedStatement request = null;
        try {
            request = connection.prepareStatement(sql);
            request.setString(1, name);
            ResultSet result = request.executeQuery();
            if (result.next()) {
                company.setCompanyId(result.getInt(1));
                company.setCompanyName(result.getString(2));
                company.setMainParticipantFIO(result.getString(3));
                company.setMainParticipantContactTelephoneNumber(result.getString(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return company;
    }
}
