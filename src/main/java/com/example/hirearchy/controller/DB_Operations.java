package com.example.hirearchy.controller;

import com.example.hirearchy.model.PGSQL;
import com.example.hirearchy.model.Person;
import com.example.hirearchy.model.Worker;

import java.math.BigInteger;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import static com.example.hirearchy.controller.RegisterAndLoginPageController.professionMap;

public class DB_Operations extends PGSQL {
    //Variables
    BigInteger id;
    UUID uuid;
    String name;
    String email;
    String contact;
    String password;
    int profession;
    int location;
    LocalDate joined;
    Date date;
    double monthly_rate;
    double hourly_rate;



    //Constructors
    public DB_Operations(){
        this.uuid = null;
        this.name = null;
        this.contact = null;
        this.email = null;
        this.profession = -1;
        this.location = -1;
        this.password = null;
        this.joined = null;
    }
    private<T extends Person> DB_Operations(T user) {
        this.uuid = UUID.randomUUID();
        this.name = user.getName();
        this.contact = user.getContact_no();
        this.email = user.getEmail().toLowerCase();
        this.profession = user.getProfession();
        this.location = user.getLocation();
        this.password = user.getPassword();
        this.joined = LocalDate.now();
    }

    //Getters and Setters
    public void setId(BigInteger id){this.id=id;}
    public BigInteger getId(){return this.id;}

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getMonthly_rate() {
        return monthly_rate;
    }

    public void setMonthly_rate(double monthly_rate) {
        this.monthly_rate = monthly_rate;
    }

    public double getHourly_rate() {
        return hourly_rate;
    }

    public void setHourly_rate(double hourly_rate) {
        this.hourly_rate = hourly_rate;
    }

    //Registering new User
    public <T extends Person> boolean insertRecord(T customer){
        try{
            DB_Operations op = new DB_Operations(customer);
            return insert(op);
        }catch (Exception exp){
            System.out.println(exp);
            return false;
        }
    }
    //Authentication while Logging-in
    public DB_Operations auth(String email, String password) throws SQLException {

        ResultSet resultSet = authenticate(email.toLowerCase(),password);
        if(resultSet==null) {
            return null;
        }

        DB_Operations info = new DB_Operations();

        while(resultSet.next()) {
            info.setName(resultSet.getString("name"));
            info.setEmail(resultSet.getString("email"));
            info.setContact(resultSet.getString("contact"));
            info.setLocation(resultSet.getInt("location"));
            info.setProfession(resultSet.getInt("profession"));
            info.setJoined(resultSet.getDate("joined").toLocalDate());
            info.setUuid(resultSet.getObject("id",java.util.UUID.class));
            if(info.getName()==null || info.getEmail()==null||info.getContact()==null){
                return null;
            }
//            System.out.println(info.getName()+" "+info.getContact()+" "+info.getEmail());
        }
//        System.out.println(info.getName()+" "+info.getContact()+" "+info.getEmail());
        return info;
    }
    //Query Methods
    public ResultSet search(int profession, int location){
        String qry = "SELECT name, profession, location, contact FROM user_info WHERE " +
                    "profession="+profession+" OR location="+location;
        System.out.println(qry);
        try{
            return Query(qry);
        }catch (Exception exp){
            exp.getStackTrace();
            return null;
        }
    }
    public ResultSet search(int profession){
        String qry = "SELECT name, profession, location, contact FROM user_info WHERE " +
                "profession="+profession;
        System.out.println(qry);
        try{
            return Query(qry);
        }catch (Exception exp){
            System.out.println(exp);
            return null;
        }
    }
    public ArrayList<Worker> search_custom(int profession, int location){
        ArrayList<Worker> worker = new ArrayList<>();
        String qry = "SELECT * FROM user_info WHERE " +
                "profession="+profession+" AND location="+location;
        System.out.println(qry);
        try{
            ResultSet resultSet = Query(qry);
            if(resultSet==null) return null;
            while (resultSet.next()){
                Worker info = new Worker(
                        resultSet.getString("name"),
                        resultSet.getString("contact"),
                        resultSet.getString("email"),
                        resultSet.getInt("profession"),
                        "",
                        resultSet.getInt("location"),
                        resultSet.getInt("monthly_rate"),
                        resultSet.getInt("hourly_rate")
                );
                worker.add(info);
            }
        }catch (Exception exp){
            System.out.println(exp);
            return null;
        }
        for(Worker w:worker){
            System.out.println(w.getName());
        }
        return worker;
    }
    //DB Update Methods
    public String update_user_info(String email,String db_col, String value){
        String upd = "UPDATE user_info SET " +
                db_col + "=" + value + " WHERE email=" + email;
        StringBuilder record = null;
        try {
            if (Update(upd)) {
                String qry = "SELECT " + db_col + "FROM user_info WHERE email=" + email;
                ResultSet resultSet = Query(qry);
                record = new StringBuilder();
                while (true) {
                    assert resultSet != null;
                    if (!resultSet.next()) break;
                    if (db_col.equals("profession")) {
                        record.append(resultSet.getInt("profession"));
                    } else if (db_col.equals("location")) {
                        record.append(resultSet.getInt("profession"));
                    } else record.append(resultSet.getString(db_col));
                }
            }
        } catch (SQLException e) {
            e.getStackTrace();
            return "";
        }
        assert record != null;
        return record.toString();
    }
    public<T extends Person> void insert_history(T custom, Worker wrkr){
        DB_Operations customer = new DB_Operations();
        customer.setName(custom.getName());
        customer.setEmail(custom.getEmail());
        DB_Operations worker = new DB_Operations();
        worker.setName(wrkr.getName());
        worker.setEmail(wrkr.getEmail());
        worker.setProfession(wrkr.getProfession());
        try{
            Insert_history(customer,worker);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<DB_Operations> get_worker_history(String email){
        String qry = "SELECT customer_name,customer_email,date FROM history WHERE worker_email="+email;
        System.out.println(qry);
        ArrayList<DB_Operations> list = new ArrayList<>();
        try{
            ResultSet resultSet = Query(qry);
            if(resultSet==null) return null;
            while (resultSet.next()){
                DB_Operations info = new DB_Operations();
                info.setId(BigInteger.valueOf(resultSet.getInt("id")));
                info.setName(resultSet.getString("customer_name"));
                info.setEmail(resultSet.getString("customer_email"));
                info.setDate(resultSet.getDate("date"));
                list.add(info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<DB_Operations> get_customer_history(String email){
        String qry = "SELECT worker_name,worker_email,worker_profession,date FROM history WHERE customer_email="+email;
        System.out.println(qry);
        ArrayList<DB_Operations> list = new ArrayList<>();
        try{
            ResultSet resultSet = Query(qry);
            if(resultSet==null) return null;
            while (resultSet.next()){
                DB_Operations info = new DB_Operations();
                info.setId(BigInteger.valueOf(resultSet.getInt("id")));
                info.setName(resultSet.getString("worker_name"));
                info.setEmail(resultSet.getString("worker_email"));
                info.setProfession(professionMap.get(resultSet.getString("profession")));
                info.setDate(resultSet.getDate("date"));
                list.add(info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
//    public void accept(String )
    public void Insert_history2(String customerEmail, String workerEmail){
        try{
            Insert_history22(customerEmail,workerEmail);
            System.out.println(customerEmail+" "+workerEmail);
        } catch (SQLException e) {
            System.out.println("error at DB_OP line 322");
            e.printStackTrace();
        }
    }
    public static void db_connect(){
        Connect();
    }
}
