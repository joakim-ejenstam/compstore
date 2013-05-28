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
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String _address) {
        address = _address;
    }
    
    public String getMail() {
        return mail;
    }
    
    public void setMail(String _mail) {
        mail = _mail;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String _phone) {
        phone = _phone;
    }
    
    public String getXml() {
        StringBuffer xmlOut = new StringBuffer();

        xmlOut.append("Namn: ");
        xmlOut.append(name);
        
        return xmlOut.toString();
    }
 
    public void updateDB(String _url) throws Exception {
        String url = _url;
        
        Connection conn = null;
        
        PreparedStatement stmt = null;
        
        String query = "UPDATE customers SET"
                + " name=?,"
                + " address=?,"
                + " mail=?,"
                + " phone=?"
                + " WHERE id=?";
        
        System.out.println("Debug, update query: " + query);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
            stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, mail);
            stmt.setString(4, phone);
            stmt.setInt(5, id);
            stmt.execute();
            
        } catch(Exception e) {
            throw new Exception("Could not update",e);
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                throw new Exception("Could not close connection",e);
            }
        }
    }
    
    public void insertDB(String _url, String uname, String pword) throws Exception {
        String url = _url;
        
        Connection conn = null;
        
        PreparedStatement stmt = null;
        
        String query = "INSERT INTO customers"
                + " (username, password,"
                + " name, address, mail, phone)"
                + " VALUES (?,?,?,?,?,?)";
        
        System.out.println("Debug, update query: " + query);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
            stmt = conn.prepareStatement(query);
            stmt.setString(1, uname);
            stmt.setString(2, pword);
            stmt.setString(3, name);
            stmt.setString(4, address);
            stmt.setString(5, mail);
            stmt.setString(6, phone);
            stmt.execute();
            
        } catch(Exception e) {
            throw new Exception("Could not insert",e);
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                throw new Exception("Could not close connection",e);
            }
        }
    }
}
