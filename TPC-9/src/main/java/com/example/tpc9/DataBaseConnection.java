package com.example.tpc9;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.CompletionException;

public class DataBaseConnection {
    public Connection databaseLink;

    public Connection getConection(){
        String databaseName = "tpc_9_POO";
        String databaseUser = "root"; //ignorar dados pessoais kk
        String databasePassword = "";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
        }catch (Exception e){
            e.printStackTrace();
        }

        return databaseLink;

    }




}
