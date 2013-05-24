/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author Joakim
 */
public class UserBean {
    
    private int id;
    private String name;
    private String address;
    private String mail;
    private String phone;
    
    public UserBean(String uname, String pword, String url) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String query = "SELECT id, Name, Address, Mail, Phone "
                + "FROM customers WHERE "
                + "username = ? AND "
                + "password = ? ";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
            stmt = conn.prepareStatement(query);
            stmt.setString(1, uname);
            stmt.setString(2, pword);
            rs = stmt.executeQuery();
            
            rs.next();
            id = rs.getInt("id");
            name = rs.getString("Name");
            address = rs.getString("Address");
            mail = rs.getString("Mail");
            phone = rs.getString("Phone");
    
        } catch(Exception e) {
            throw new Exception("Connection error", e);
        }
    }
    
    public int getID() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setID(int _id) {
        id = _id;
    }
    
    public void setName(String _name) {
        name = _name;
    }
    
    public void getAddress(Stri)
    
    public String getXml() {
        StringBuffer xmlOut = new StringBuffer();

        xmlOut.append("Namn: ");
        xmlOut.append(name);
        
        return xmlOut.toString();
    }
    
}
