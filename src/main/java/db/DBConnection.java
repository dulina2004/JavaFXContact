package db;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    static DBConnection instance;
    List<Customer> conection = new ArrayList<>();
    private DBConnection(){}

    public static DBConnection getInstance() {
        if(null==instance){
            instance = new DBConnection();
        }
        return instance;
    }
    public List<Customer> getDBConnection(){return this.conection;}
}
