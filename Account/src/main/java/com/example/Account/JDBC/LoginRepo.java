package com.example.Account.JDBC;

import com.example.Account.AccountCreation.AccountCreateModel;
import com.example.Account.Encryption.KeyConverter;
import com.example.Account.Login.LoginModelClass;
import com.example.Account.Login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoginRepo {
    @Autowired
    LoginModelClass loginModelClass;
    @Autowired
    AccountCreateModel accountCreateModel;
    private JdbcTemplate template;

    public JdbcTemplate getTemplate() {
        return template;
    }

    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public Boolean saveData() {

        KeyConverter keyConverter = new  KeyConverter();
        String sqlQuery = "INSERT INTO " + accountCreateModel.getUsername().toUpperCase() + "INFO (username, password, secretKey, ivParameterSpec)" +
                " VALUES (?, ?, ?, ?);";
        try {
            template.update(sqlQuery, accountCreateModel.getUsername(),
                    accountCreateModel.getPassword(),
                    keyConverter.secretKeyToString(accountCreateModel.getSecretKey()),
                    keyConverter.ivParameterSpecToString(accountCreateModel.getIvParameterSpec()));
            return true;
        } catch (Exception e){
            System.out.println(e);
            return false;
        }

    }

    public Boolean getData(String username) {

        String sqlQuery = "SELECT * FROM " + username.toUpperCase() + "INFO ;";

        RowMapper<LoginModelClass> mapper = (rs, rowNum) -> {

            loginModelClass.setUsername(rs.getString(1));
            loginModelClass.setPassword(rs.getString(2));
            loginModelClass.setKey(rs.getString(3));
            loginModelClass.setIvParameterSpec(rs.getString(4));

            return loginModelClass;
        };
        try {
            template.query(sqlQuery, mapper);
            return true;

        }catch (Exception e){
            System.out.println(e);
            return false;
        }

    }

    public boolean getTable(String username) {

        String tableName = username + "info";
        System.out.println(tableName);
        String sqlQuery = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = ?;";

        try {
            List<Integer> counts = template.query(sqlQuery, new Object[]{tableName.toUpperCase()}, (rs, rowNum) -> rs.getInt(1));

            if (!counts.isEmpty() && counts.getFirst() > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e+" while finding the table");

        }
        String createTableQuery = "CREATE TABLE " + tableName.toUpperCase() + " ("
                + "username VARCHAR(255), "
                + "password VARCHAR(255), "
                + "secretKey VARCHAR(255), "
                + "ivParameterSpec VARCHAR(255)"
                + ");";
        try {
            template.execute(createTableQuery);
            return false;
        }catch (Exception e){
            System.out.println(e+" while creating login table");
            return true;
        }
    }

    public String dropTable(String username) {

        String sqlQuery = "DROP TABLE IF EXISTS " + username.toUpperCase();
        try {
            template.execute(sqlQuery);
            return "table drop successfully";
        }catch (Exception e){
            System.out.println(e);
            return "table does not deleted for some issue";
        }

    }

    public boolean homeTable(String tableName) {
        String createTableQuery = "CREATE TABLE " + tableName + "Home" + " ("
                + "name VARCHAR(50) NOT NULL, "
                + "username VARCHAR(50) NOT NULL, "
                + "password VARCHAR(50) NOT NULL, "
                + "favorite VARCHAR(10) NOT NULL DEFAULT FALSE, "
                + "delete BOOLEAN NOT NULL DEFAULT FALSE"
                + ");";

        try {
            template.execute(createTableQuery);
            return true;
        } catch (DataAccessException e) {
            // Handle the exception and clean up if needed
            System.out.println("Failed to create table: " + e.getMessage());
            return false;
            // Optionally: Add logic to drop the table if partial creation happened
        }

    }
}