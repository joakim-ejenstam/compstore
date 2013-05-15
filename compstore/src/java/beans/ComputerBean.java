/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.*;
import java.sql.*;
import java.io.*;

/**
 *
 * @author Martin
 */
public class ComputerBean {
    
    //describes a computer
    
    private String url=null;
    
    public ComputerBean (String _url) throws Exception {
        url=_url;
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
            
        } catch(SQLException sqle ){
            throw new Exception(sqle);
        }
    }
    

    
    private int id;
    private String name;
    private String description;
    private int price;
    
    public ComputerBean() {
    }
    
    public int getID() {
        return id;
    }
    
    public void setID(int _id) {
        id = _id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String _name) {
        name = _name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String _description) {
        description = _description;
    }
    
    public int getPrice() {
        return price;
    }
}
