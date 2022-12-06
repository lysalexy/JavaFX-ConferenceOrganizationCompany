package com.example.javafxconferenceorganizationcompany.repository;

import com.example.javafxconferenceorganizationcompany.models.Company;
import com.example.javafxconferenceorganizationcompany.models.VideoAndPhotoShooting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
