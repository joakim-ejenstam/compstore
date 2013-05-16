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
    private String url = null;    
    
    public ComputerListBean(String _url) throws Exception {
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
                String cpuParts = "";
                
                ComputerBean cb = new ComputerBean();
                
                cb.setID(rs.getInt("id"));
                cb.setName(rs.getString("name"));
                cb.setDescription(rs.getString("description"));
                
                stmt2 = conn.createStatement();
                String sql2 = "SELECT component_id FROM cpu_comp WHERE ";
                sql2 += "computer_id = ";
                sql2 += Integer.toString(rs.getInt("id"));
                rs2 = stmt2.executeQuery(sql2);
                
                while (rs2.next()) {
                    
                    Statement stmt3 = null;
                    ResultSet rs3 = null;
                    
                    stmt3 = conn.createStatement();
                    String sql3 = "SELECT * FROM components WHERE ";
                    sql3 += "id = ";
                    sql3 += Integer.toString(rs2.getInt("component_id"));
                    sql3 += " ORDER BY type ASC";
                    rs3 = stmt3.executeQuery(sql3);
                    
                    while (rs3.next()) {
                        Statement stmt4 = null;
                        ResultSet rs4 = null;
                        String compType = "";
                        
                        stmt4 = conn.createStatement();
                        String sql4 = "SELECT name FROM component_types WHERE ";
                        sql4 += "id = ";
                        sql4 += Integer.toString(rs3.getInt("type"));
                        rs4 = stmt4.executeQuery(sql4);
                        
                        while (rs4.next()) {
                            cpuParts += rs4.getString("name");
                        }
                        
                        cpuParts += ": ";
                        cpuParts += rs3.getString("name");
                        cpuParts += "<br>";
                        
                        cpuPrice = cpuPrice + rs3.getInt("price");
                    }
                    
                    try {
                        rs3.close();
                    } catch(Exception e) {}
                    try {
                        stmt3.close();
                    } catch(Exception e) {}
                    
                }
                
                cb.setParts(cpuParts);
                cb.setPrice(cpuPrice);
                
                cpuList.add(cb);
                
                try {
                    rs2.close();
                } catch(Exception e) {}
                try {
                    stmt2.close();
                } catch(Exception e) {}
               
            }
            
        } catch(SQLException sqle ){
            throw new Exception(sqle);
        } finally {
 	    try{
                rs.close();
            }
            catch(Exception e) {}
            try{
                stmt.close();
            }
	    catch(Exception e) {}
            try {
                conn.close();
            }
            catch(Exception e){}
        }
    }
    
    java.util.Collection getComputerList() {
        return cpuList;
    }
    
    public String getXml() {
        
        ComputerBean cb=null;
        Iterator iter = cpuList.iterator();
        StringBuffer buff = new StringBuffer();
        
        while(iter.hasNext()){
            cb = (ComputerBean)iter.next();
            buff.append("<tr>");
            buff.append("<td>");
            buff.append(cb.getName());
            buff.append("</td>");
            buff.append("<td>");
            buff.append(cb.getDescription());
            buff.append("</td>");
            buff.append("<td>");
            buff.append(cb.getPrice());
            buff.append("</td>");
            buff.append("<td>");
            buff.append("<a href=\"shop?action=details&cid=");
            buff.append(cb.getID());
            buff.append("\">Details</a>");
            buff.append("</td>");
            buff.append("<td>");
            buff.append("<a href=\"shop?action=add&cid=");
            buff.append(cb.getID());
            buff.append("&quan=1\">Lägg till</a></td>");
            buff.append("</tr>");
        }

        return buff.toString();
    }
    
    public ComputerBean getById(int id) {
	ComputerBean cb = null;
	Iterator iter = cpuList.iterator();
        
	while(iter.hasNext()){
	    cb=(ComputerBean)iter.next();
	    if(cb.getID() == id){
                return cb;
	    }
	}
	return null;
    }
}
