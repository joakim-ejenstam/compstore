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
    
    private int id;
    private String name;
    private String description;
    private int price;
    private String parts;
    
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
    
    public void setPrice(int _price) {
        price = _price;
    }
    
    public String getParts() {
        return parts;
    }
    
    public void setParts(String _parts) {
        parts = _parts;
    }
    
    public String getXml() {
        StringBuffer xmlOut = new StringBuffer();

         xmlOut.append("Namn: ");
         xmlOut.append(name);
         xmlOut.append("<br>Beskrivning: ");
         xmlOut.append(description);
         xmlOut.append("<br><br>Delar: <br>");
         xmlOut.append(parts);
         xmlOut.append("<br>Pris: ");
         xmlOut.append(price);

         return xmlOut.toString();
    }
    
    public String getXml2() {
        StringBuffer xmlOut = new StringBuffer();

         xmlOut.append(name);

         return xmlOut.toString();
    }
}
