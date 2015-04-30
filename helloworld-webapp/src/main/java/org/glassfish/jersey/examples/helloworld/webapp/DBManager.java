
package org.glassfish.jersey.examples.helloworld.webapp;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ltcn
 */


public class DBManager {
    private String dbURL = "jdbc:mysql://localhost/test?user=root";
    
    
    private Connection conn;
    private Statement stmt;
    
    public DBManager(){
        conn = null;
        stmt = null;
    }
    
    public void open(){
         try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(dbURL);
            stmt = conn.createStatement();
          
        } catch (Exception ex) {
            System.out.println("Connect fail: " + ex.toString());
          
        }
    }
    
    public void close(){
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
            if (conn != null)
            {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }           
        }
        catch (SQLException sqlExcept)
        {
            System.out.println("close fail? :"+ sqlExcept.toString());
        }

    }
    
    public ResultSet qureyExec(String sql) {
        try {
            if(stmt == null){
                stmt = conn.createStatement();
            }
            ResultSet res = stmt.executeQuery(sql);
            return res;
        }
        catch (SQLException sqlExcept){
            System.out.println("qureyExec fail? :" + sqlExcept.toString());
            return null;
        }
    }
    
    public void updateExec(String sql) throws SQLException{

            if(stmt == null){
                stmt = conn.createStatement();
            }
            stmt.executeUpdate(sql);
            
        

    }
    
    
    
}
