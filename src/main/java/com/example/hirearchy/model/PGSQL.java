package com.example.hirearchy.model;

import com.example.hirearchy.controller.DB_Operations;

import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

import static com.example.hirearchy.controller.RegisterAndLoginPageController.professionArr;

public abstract class PGSQL {

    public static Connection connection=null;

    protected static void Connect(){

//        String db_user = "mahmud";
//        String db_password = "***************";
//        String db_url = "jdbc:postgresql://127.0.0.1:5432/hirearchy";
        String db_url = "jdbc:postgresql://ec2-52-21-136-176.compute-1.amazonaws.com:5432/dflk0e7s9ngvop";
        String db_password = "1ee242de16f2c68eb8554092654d4b4e574a408d37f63747ed5e05d8e52717ce";
        String db_user = "wkprnsntpruzxx";
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(db_url,db_user,db_password);
            connection.setAutoCommit(false);
        }catch (Exception e){
            System.out.println(e);
            System.exit(0);
        }
        System.out.println("connected!!!");
    }
    protected boolean insert(DB_Operations op) throws SQLException {
        if(connection==null) Connect();

        try{
            UUID id = UUID.randomUUID();
            LocalDate date = LocalDate.now();
            String insert = "INSERT INTO user_info (id,email,hash,name,contact,location,profession,joined) " +
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
            exp.getStackTrace();
            return false;
        }
        return true;
    }
    protected static ResultSet authenticate(String email, String password) throws SQLException {
        if(connection==null) Connect();
        ResultSet resultSet;
        try{
            String qry = "SELECT * FROM user_info WHERE email=\'"+email+"\' AND hash=\'"+password+"\'";
            System.out.println(qry);
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(qry);
        }catch(Exception exp) {
            System.out.println(Arrays.toString(exp.getStackTrace()));
            System.out.println("PGSQL line 74");
            return null;
        }
        return resultSet;
    }
    protected static ResultSet Query(String qry) throws SQLException{
        if(connection==null) Connect();
        ResultSet resultSet = null;
        Statement statement = null;
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(qry);
            return resultSet;
        }catch (Exception exp){
            exp.getStackTrace();
            assert statement != null;
            statement.close();
            return null;
        }
    }
    protected static boolean Update(String update) throws SQLException {
        if(connection==null)Connect();
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(update);
            connection.commit();
        }catch (SQLException e){
            connection.rollback();
            e.getStackTrace();
            return false;
        }
        return true;
    }

    protected static void Insert_history(DB_Operations customer,DB_Operations worker ) throws SQLException {
        if(connection==null)Connect();
        try{
            LocalDate date = LocalDate.now();
            String insert = "INSERT INTO history (customer_name, worker_name, worker_profession, date, customer_email, worker_email) " +
                    "VALUES (?,?,?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setObject(1,customer.getName());
            statement.setObject(2,worker.getName());
            statement.setObject(3,professionArr[worker.getProfession()]);
            statement.setObject(4,date);
            statement.setObject(5,customer.getEmail());
            statement.setObject(6,worker.getEmail());
            statement.executeUpdate();
             connection.commit();
        }catch(SQLException e){
            connection.rollback();
            e.getStackTrace();
        }
    }
    public void Insert_history22(String customerEmail, String workerEmail) throws SQLException {
        if(connection==null) Connect();
        try{
            LocalDate date = LocalDate.now();
            String insrt = "INSERT INTO history (customer_email, worker_email,date) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(insrt);

            statement.setObject(1,customerEmail);
            statement.setObject(2,workerEmail);
            statement.setObject(3,date);
            statement.executeUpdate();
            connection.commit();
        }catch (SQLException e){
            connection.rollback();
            System.out.println(e.getMessage());
            System.out.println("error at PGQL line 140");
        }
    }
}
