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
public class ManagerBean {
    private ComponentListBean compList;
    private ComputerListBean cpuList;
    private String url = null;
    
    public ManagerBean(String _url, ComponentListBean _compList, ComputerListBean _cpuList){
        compList = _compList;
        cpuList = _cpuList;
        url = _url;
    }
    
    public String getComponentXml() {
        return compList.getManagerxml();
    }
    
    public String getComputerXml() {
        return cpuList.getManagerxml();
    }
    
    public void updateComponent(ComponentBean _cob) throws Exception {
        Connection conn = null;
        
        PreparedStatement stmt = null;
        
        String query = "UPDATE components SET"
                + " price=?,"
                + " qoh=?"
                + " description=?"
                + " WHERE id=?";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, _cob.getPrice());
            stmt.setInt(2, _cob.getQoh());
            stmt.setString(3, _cob.getDescription());
            stmt.setInt(5, _cob.getID());
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
    
    public void deleteComponent(ComponentBean _cob) throws Exception {
        Connection conn = null;
        
        PreparedStatement stmt = null;
        
        String query = "DELETE FROM components WHERE"
                + " id=?";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, _cob.getID());
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
    
    public void insertComponent(ComponentBean _cob) throws Exception {
        Connection conn = null;
        
        PreparedStatement stmt = null;
        
        String query = "INSERT INTO components "
                + "(name, type, price, qoh, description) "
                + "VALUES (?,?,?,?,?)";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
            stmt = conn.prepareStatement(query);
            stmt.setString(1, _cob.getName());
            stmt.setInt(2, _cob.getType());
            stmt.setInt(3, _cob.getPrice());
            stmt.setInt(4, _cob.getQoh());
            stmt.setString(5, _cob.getDescription());
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
}
