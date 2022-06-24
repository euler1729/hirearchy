package com.example.hirearchy.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Locale;
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
        this.email = customer.getEmail().toLowerCase();
        this.profession = customer.getProfession();
        this.location = customer.getLocation();
        this.password = customer.getPassword();
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

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfession(int profession) {
        this.profession = profession;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public void setJoined(LocalDate joined) {
        this.joined = joined;
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

    public DB_Operations auth(String email, String password) throws SQLException {
        ResultSet resultSet = authenticate(email.toLowerCase(),password);
        if(resultSet==null) return  null;
        DB_Operations info = new DB_Operations();
        while(resultSet.next()) {
            info.setName(resultSet.getString("name"));
            info.setEmail(resultSet.getString("email"));
            info.setContact(resultSet.getString("contact"));
            info.setLocation(resultSet.getInt("location"));
            info.setProfession(resultSet.getInt("profession"));
            info.setJoined(resultSet.getDate("joined").toLocalDate());
            if(info.getName()==null || info.getEmail()==null){
                return null;
            }
            System.out.println(info.getName()+" "+info.getContact()+" "+info.getEmail());
        }
//        System.out.println(info.getName()+" "+info.getContact()+" "+info.getEmail());
        return info;
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
