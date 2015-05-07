
package org.glassfish.jersey.examples.helloworld.webapp;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ltcn
 */


public class CoustomerDBAgent {
    
    private static DBManager db;
    private String tableName = "customer";
    
    public CoustomerDBAgent(){
        if(db == null){
            db = new DBManager();
            db.open();
        }
    }
    public void close(){
        if(db != null){
            db.close();
            db = null;
        }
    }
    
    public boolean ifdbinit() {
        ResultSet res = db.qureyExec("select * from " +  tableName);
        if(res != null){
            try {
                res.close();
            } catch (SQLException sqlExcept) {

            }
            return true;
        }else{
            return false;
        }
    }
    
    public void initdb(){
        String sql = "CREATE TABLE "+ tableName + " ( "
                + "id INT NOT NULL AUTO_INCREMENT,"
                + "email CHAR(30) NOT NULL UNIQUE, "
                + "password CHAR(30),"
                + "PRIMARY KEY (id)"
                + ")";
        try {
            db.updateExec(sql);
        } catch (SQLException sqlExcept) {
           System.out.println("initDB fail? :" + sqlExcept.getMessage());
        }
    }
    
    public String insertData(String inputemail, String inputpassword){
        String sql = "INSERT INTO " + tableName + " "
                + "(email, password) VALUES ( '"
                + inputemail + "', '" + inputpassword + "' )";
        try {
            db.updateExec(sql);
            return "OK";
        } catch (SQLException sqlE) {
            return sqlE.getMessage();
        }
    }
    
    public String changePassword(String inputemail, String inputpassword){
        String sql = "UPDATE " + tableName + " SET  "
                + "password='" + inputpassword + "' where email='"
                + inputemail + "'";
        try {
            db.updateExec(sql);
            return "OK";
        } catch (SQLException sqlE) {
            return sqlE.getMessage();
        }
    }
    
    public String deleteCustomer(String inputemail){
        String sql = "DELETE FROM " + tableName + " where email='"
                + inputemail + "'";
        try {
            db.updateExec(sql);
            return "OK";
        } catch (SQLException sqlE) {
            return sqlE.getMessage();
        }
    }
    
    public MyList<NewCustomer> getData(){
        String sql  = "SELECT * from " + tableName ;
        NewCustomer[] storagespace = new NewCustomer[64];
        MyList<NewCustomer> reslist = new MyList<NewCustomer>(storagespace,64);
        try {
            ResultSet results = db.qureyExec(sql);
            if (results == null) {
                return null;
            }
            while (results.next()) {
                int id = results.getInt(1);
                String email = results.getString(2);
                String password = results.getString(3);
                reslist.add(new NewCustomer(email, password));
            }
            results.close();
            return reslist;
        } catch (SQLException sqlE) {
            return null;
        }
    }
}
