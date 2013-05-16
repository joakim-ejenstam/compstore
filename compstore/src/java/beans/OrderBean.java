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
 * @author Martin
 */
public class OrderBean {
    
    private int orderID;
    private int userID;
    private String userName;
    
    public OrderBean() {
    }  
    
    public int getID() {
        return orderID;
    }
    
    public void setID(int _orderID) {
        orderID = _orderID;
    }
    
    public int getUser() {
        return userID;
    }
    
    public void setUser(int _userID) {
        userID = _userID;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String _userName) {
        userName = _userName;
    }
    
}
