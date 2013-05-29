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
public class ComponentBean {
    
    private int id;
    private String name;
    private int type;
    private int price;
    private int qoh;
    private String description;
    
    public ComponentBean() {
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
    
    public int getType() {
        return type;
    }
    
    public void setType(int _type) {
        type = _type;
    }
    
    public int getPrice() {
        return price;
    }
    
    public void setPrice(int _price) {
        price = _price;
    }
    
    public int getQoh() {
        return qoh;
    }
    
    public void setQoh(int _qoh) {
        qoh = _qoh;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String _description) {
        description = _description;
    }
}
