package com.example.hirearchy.model;
import java.sql.*;
public class PGSQL {
    static String name = "mahmud";
    static String password = "mufidul@111";
    static Connection connection = null;
    static Statement statement = null;
    public static void Connect(){
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://127.0.0.1:5432/hirearchy";
            connection = DriverManager.getConnection(url,name,password);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
//            String insert = "INSERT INTO login (id,email,hash) VALUES('28f721ad-2665-4a2a-b4c5-76ced2acb466','mahmud1@gmail.com','afadfasf')";
//            statement.executeUpdate(insert);
            String query = "SELECT * FROM login;";
            ResultSet qry =  statement.executeQuery(query);
            while(qry.next()){
                System.out.println(qry.getString("id")
                        +" "+qry.getString("email")
                        +" "+qry.getString("hash"));
            }
            statement.close();
            connection.commit();
            connection.close();
        }catch (Exception e){
            System.out.println(e);
            System.exit(0);
        }
        System.out.println("connected!!!");
    }
}
