package com.example.hirearchy.model;
import com.example.hirearchy.controller.Person;
//import javafx.beans.value.ObservableFloatValue;

import java.sql.*;
import java.time.LocalDate;
import java.util.UUID;

public class PGSQL {
    static String name = "mahmud";
    static String password = "mufidul@111";
    static String url = "jdbc:postgresql://127.0.0.1:5432/hirearchy";

    //    static Connection connection = null;
//    static Statement statement = null;
    public static Connection Connect(){
        Connection connection=null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,name,password);
            connection.setAutoCommit(false);
//            statement = connection.createStatement();
//            String insert = "INSERT INTO login (id,email,hash) VALUES('28f721ad-2665-4a2a-b4c5-76ced2acb466','mahmud1@gmail.com','afadfasf')";
//            statement.executeUpdate(insert);
//            String query = "SELECT * FROM login;";
//            ResultSet qry =  statement.executeQuery(query);
//            while(qry.next()){
//                System.out.println(qry.getString("id")
//                        +" "+qry.getString("email")
//                        +" "+qry.getString("hash"));
//            }
//            statement.close();
//            connection.commit();
//            connection.close();
        }catch (Exception e){
            System.out.println(e);
            System.exit(0);
        }
        System.out.println("connected!!!");
        return connection;
    }


    public static void insertRecord(Person user) throws SQLException {
        Connection connection = Connect();
        if(connection==null){
            System.out.println("Connection error");
            return;
        }
        try{
            UUID id = UUID.randomUUID();
            LocalDate date = LocalDate.now();
            String insert = "INSERT INTO users (id,email,hash,name,contact,location,profession,joined) " +
                            "VALUES (?,?,?,?,?,?,?,?);";

            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setObject(1, id);
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getName());
            statement.setString(5, user.getContact_no());
            statement.setObject(6, user.getLocation());
            statement.setObject(7, user.getProfession());
            statement.setDate(8, Date.valueOf(date));

            System.out.println(statement);
            statement.executeUpdate();
            connection.commit();
        }catch (Exception exp){
            System.out.println(exp);
        }
    }
    void login(String email, String password){

    }
}
