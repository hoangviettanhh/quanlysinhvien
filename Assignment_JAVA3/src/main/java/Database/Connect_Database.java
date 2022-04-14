/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Laptop
 */
public class Connect_Database {
    public static Connection _intance = null;
    
    private Connect_Database(){
       
   }
    public static Connection getIntance() throws Exception {
//        if (_intance == null) {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             _intance = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Assignment Java3;user=sa;password=admin");
        
        return _intance;
    }
}
