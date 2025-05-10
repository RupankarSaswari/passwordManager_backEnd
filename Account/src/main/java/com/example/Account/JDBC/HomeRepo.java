package com.example.Account.JDBC;

import com.example.Account.Encryption.Decoder;
import com.example.Account.Home.HomeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class HomeRepo {
    @Autowired
    private LoginRepo loginRepo;
    @Autowired
    private HomeModel homeModel;

    Decoder decoder = new Decoder();

    public List<HomeModel> getAllHomeData(String tableName) {

            String sqlQuery = "SELECT name, username, password FROM " + tableName.toUpperCase() + " WHERE delete = false";

            RowMapper<HomeModel> mapper = (rs, rowNum) -> {

                homeModel.setName(rs.getString("name"));
                homeModel.setUsername(rs.getString("username"));

                // password decoding
                try {
                    homeModel.setPassword(decoder.decrypt(rs.getString("password")));
                } catch (Exception e) {
                    System.out.println(e);
                }
                return homeModel;
            };

            return loginRepo.getTemplate().query(sqlQuery, mapper);
        }

        public List<HomeModel> getAllFavData(String tableName){

            String sqlQuery = "SELECT name, username, password FROM " +
                    tableName.toUpperCase() + " WHERE favorite = true AND delete = false";

            RowMapper<HomeModel> mapper = (rs, rowNum) -> {

                homeModel.setName(rs.getString("name"));
                homeModel.setUsername(rs.getString("username"));

                // Password decoding
                try {
                    homeModel.setPassword(decoder.decrypt(rs.getString("password")));
                } catch (Exception e) {
                    System.out.println("Error decoding password: " + e.getMessage());
                    homeModel.setPassword(null); // Set password to null or handle it accordingly
                }

                return homeModel;
            };

            return loginRepo.getTemplate().query(sqlQuery, mapper);
        }

    public List<HomeModel> getAllDeleteData(String tableName) {

        String sqlQuery = "SELECT name, username, password FROM " + tableName.toUpperCase() + " WHERE delete = true";

        RowMapper<HomeModel> mapper = (rs, rowNum) -> {

            homeModel.setName(rs.getString("name"));
            homeModel.setUsername(rs.getString("username"));

            // password decoding
            try {
                homeModel.setPassword(decoder.decrypt(rs.getString("password")));
            } catch (Exception e) {
                System.out.println(e);
            }
            return homeModel;
        };

        return loginRepo.getTemplate().query(sqlQuery, mapper);
    }

    public boolean saveHomeData(String tableName, String favourite){

        String sqlQuery = "INSERT INTO " +
                tableName.toUpperCase() + " (name, username, password, favorite) VALUES (?, ?, ?, ?)";

        try {

            loginRepo.getTemplate().update(sqlQuery,
                    homeModel.getName(),
                    homeModel.getUsername(),
                    homeModel.getPassword(),
                    favourite);

            return true;
        }catch (Exception e){

            System.out.println("Error inserting data: " + e.getMessage());

            return false;
        }

    }

    public boolean updateColumnValue(String tableName, String username, String platformName, String columnName, Object newValue) {
        String sqlQuery = "UPDATE " + tableName.toUpperCase()+"HOME" + " SET " + columnName + " = ? WHERE username = ? AND name = ?";
        try {
            int rowsAffected = loginRepo.getTemplate().update(sqlQuery, newValue, username, platformName);
            return rowsAffected > 0;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteRowIfMarkedForDeletion(String tableName, String username) {
        String sqlQuery = "DELETE FROM " + tableName.toUpperCase()+"HOME" + " WHERE username = ? AND delete = true";
        try {
            int rowsAffected = loginRepo.getTemplate().update(sqlQuery, username);
            return rowsAffected > 0;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
}
