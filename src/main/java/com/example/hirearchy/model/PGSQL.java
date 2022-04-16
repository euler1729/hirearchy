package com.example.hirearchy.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.UUID;

public abstract class PGSQL {


    static Connection Connect(){
        Connection connection=null;
//        String db_user = "mahmud";
//        String db_password = "mufidul@111";
//        String db_url = "jdbc:postgresql://127.0.0.1:5432/hirearchy";
        String db_url = "jdbc:postgresql://ec2-52-21-136-176.compute-1.amazonaws.com:5432/dflk0e7s9ngvop";
        String db_password = "1ee242de16f2c68eb8554092654d4b4e574a408d37f63747ed5e05d8e52717ce";
        String db_user = "wkprnsntpruzxx";
//        String url = "postgres://wkprnsntpruzxx:1ee242de16f2c68eb8554092654d4b4e574a408d37f63747ed5e05d8e52717ce@ec2-52-21-136-176.compute-1.amazonaws.com:5432/dflk0e7s9ngvop";
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(db_url,db_user,db_password);
            connection.setAutoCommit(false);
        }catch (Exception e){
            System.out.println(e);
            System.exit(0);
        }
        System.out.println("connected!!!");
        return connection;
    }
    protected boolean insert(DB_Operations op) throws SQLException {
        Connection connection = Connect();
        if(connection==null){
            System.out.println("Connection error");
            return false;
        }
        try{
            UUID id = UUID.randomUUID();
            LocalDate date = LocalDate.now();
            String insert = "INSERT INTO users (id,email,hash,name,contact,location,profession,joined) " +
                    "VALUES (?,?,?,?,?,?,?,?);";

            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setObject(1, op.getUuid());
            statement.setString(2, op.getEmail());
            statement.setString(3, op.getPassword());
            statement.setString(4, op.getName());
            statement.setString(5, op.getContact());
            statement.setObject(6, op.getLocation());
            statement.setObject(7, op.getProfession());
            statement.setDate(8, Date.valueOf(op.getJoined()));

            System.out.println(statement);
            statement.executeUpdate();
            connection.commit();
        }catch (Exception exp){
            connection.rollback();
            System.out.println(exp);
            return false;
        }
        return true;
    }
    protected static boolean authenticate(String email, String password) throws SQLException {
        Connection connection = Connect();
        int count = 0;
        try{
            String qry = "SELECT * FROM users WHERE email=\'"+email+"\' AND hash=\'"+password+"\'";
            System.out.println(qry);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(qry);

            while(resultSet.next()) {
                System.out.println(resultSet.getString("email") + " " + resultSet.getString("hash"));
                ++count;
            }
            System.out.println(count);
            statement.close();
        }catch(Exception exp) {
            connection.close();
            System.out.println(exp);
            return false;
        }
        connection.close();
        return count>0;
    }
    protected static ResultSet Query(String qry) throws SQLException{
        Connection connection = Connect();
        ResultSet resultSet = null;
        Statement statement = null;
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(qry);
            statement.close();
        }catch (Exception exp){
            System.out.println(exp);
            connection.rollback();
            connection.close();
            return resultSet;
        }
        connection.rollback();
        connection.close();
        return  resultSet;
    }

}
