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
public class ComputerListBean {
    
    private Collection cpuList;
    private String url=null;    
    
    public ComputerListBean (String _url) throws Exception {
        url=_url;
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        cpuList = new ArrayList();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
            stmt = conn.createStatement();
            String sql = "SELECT * FROM computers";
            rs = stmt.executeQuery(sql);
            
            while(rs.next()) {
                Statement stmt2 = null;
                ResultSet rs2 = null;
                int cpuPrice = 0;
                
                ComputerBean cb = new ComputerBean();
                
                cb.setID(rs.getInt("id"));
                cb.setName(rs.getString("name"));
                cb.setDescription(rs.getString("description"));
                
                stmt2 = conn.createStatement();
                String sql2 = "SELECT component_id FROM cpu_comp WHERE";
                sql2 += "computer_id = ";
                sql2 += Integer.toString(rs.getInt("id"));
                
                while (rs2.next()) {
                    Statement stmt3 = null;
                    ResultSet rs3 = null;
                    
                    stmt3 = conn.createStatement();
                    String sql3 = "SELECT price FROM components WHERE";
                    sql3 += "id = ";
                    sql3 += Integer.toString(rs2.getInt("id"));
                    
                    while (rs3.next()) {
                        cpuPrice = cpuPrice + rs3.getInt("price");
                    }
                }
                
                cb.setPrice(cpuPrice);
                
                cpuList.add(cb);
            }
            
        } catch(SQLException sqle ){
            throw new Exception(sqle);
        }
    }
}
