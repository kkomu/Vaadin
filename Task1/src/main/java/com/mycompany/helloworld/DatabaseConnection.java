/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.helloworld;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author opiframe
 */
public class DatabaseConnection {
    
    private JDBCConnectionPool pool;
    
    public void connectToDB(){
        
        try {
            pool = new SimpleJDBCConnectionPool("com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/person","root","root",1,100);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public JDBCConnectionPool getConnection(){
        return pool;
    }
    
}
