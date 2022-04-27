package com.example.hirearchy.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class DB_Operations extends PGSQL{
    //Variables
    UUID uuid;
    String name;
    String email;
    String contact;
    String password;
    int profession;
    int location;
    LocalDate joined;

    //Constructors
    public DB_Operations(){

    }
    private<T extends Person> DB_Operations(T customer) {
        this.uuid = UUID.randomUUID();
        this.name = customer.getName();
        this.contact = customer.getContact_no();
        this.email = customer.getEmail();
        this.profession = customer.getProfession();
        this.location = customer.getLocation();
        this.password = customer.getPassword();
        this.joined = LocalDate.now();
    }
    private DB_Operations(Worker worker) {
        this.uuid = UUID.randomUUID();
        this.name = worker.getName();
        this.contact = worker.getContact_no();
        this.email = worker.getEmail();
        this.profession = worker.getProfession();
        this.location = worker.getLocation();
        this.password = worker.getPassword();
        this.joined = LocalDate.now();
    }

    //Getters and Setters
    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }

    public String getPassword() {
        return password;
    }

    public int getProfession() {
        return profession;
    }

    public int getLocation() {
        return location;
    }

    public LocalDate getJoined() {
        return joined;
    }

    public <T extends Person> boolean insertRecord(T customer){
        try{
            DB_Operations op = new DB_Operations(customer);
            return insert(op);
        }catch (Exception exp){
            System.out.println(exp);
            return false;
        }
    }

    public static boolean auth(String email, String password) throws SQLException {
        return authenticate(email, password);
    }
    public static ResultSet search(String profession, String location){
        String qry = "SELECT name, profession, location, contact FROM users WHERE " +
                    "profession="+profession+" AND location="+location;
        System.out.println(qry);
        try{
            return Query(qry);
        }catch (Exception exp){
            System.out.println(exp);
            return null;
        }
    }
    public static ResultSet search(String profession){
        String qry = "SELECT name, profession, location, contact FROM users WHERE " +
                "profession="+profession;
        System.out.println(qry);
        try{
            return Query(qry);
        }catch (Exception exp){
            System.out.println(exp);
            return null;
        }
    }
    public static void db_connect(){
        Connect();
    }
}
