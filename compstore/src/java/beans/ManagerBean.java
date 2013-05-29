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
    private Collection compList;
    private String url = null;
    
    public ManagerBean(String _url) throws Exception {
        url = _url;
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        compList = new ArrayList();
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
            stmt = conn.createStatement();
            String sql = "SELECT * FROM components";
            rs = stmt.executeQuery(sql);
            
            while(rs.next()) {
                ComponentBean cob = new ComponentBean();
                
                cob.setID(rs.getInt("id"));
                cob.setName(rs.getString("name"));
                cob.setType(rs.getInt("type"));
                cob.setPrice(rs.getInt("price"));
                cob.setQoh(rs.getInt("qoh"));
                cob.setDescription(rs.getString("description"));
                
                compList.add(cob);
            }
            
        } catch (SQLException sqle ){
            throw new Exception(sqle);
        }
    }
    
    java.util.Collection getComponentList() {
        return compList;
    }
}
