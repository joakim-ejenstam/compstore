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
                ComputerBean cb = new ComputerBean();
                
                cb.setID(rs.getInt("id"));
                cb.setName(rs.getString("name"));
                cb.setDescription(rs.getString("description"));
                
                //cb.setParts(getComputerParts(cb));
                //cb.setPrice(getComputerPrice(cb));
                
                cpuList.add(cb);
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
        
        ComputerBean cb = null;
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
            buff.append(Integer.toString(getComputerPrice(cb)));
            buff.append("</td>");
            buff.append("<td>");
            buff.append("<a href=\"shop?action=details&cid=");
            buff.append(cb.getID());
            buff.append("\">Detaljvy</a>");
            buff.append("</td>");
            buff.append("<td>");
            buff.append("<a href=\"shop?action=add&cid=");
            buff.append(cb.getID());
            buff.append("&quan=1\">Lägg till</a></td>");
            buff.append("</tr>");
        }

        return buff.toString();
    }
    
    public String getManagerxml() {
        
        ComputerBean cb = null;
        Iterator iter = cpuList.iterator();
        StringBuffer buff = new StringBuffer();
        
        while(iter.hasNext()){
            cb = (ComputerBean)iter.next();
            buff.append("<form action=\"shop?action=addProduct\" method=\"post\">");
            buff.append("<tr>");
            buff.append("<td>");
            buff.append(cb.getID());
            buff.append("</td>");
            buff.append("<td>");
            buff.append(cb.getName());
            buff.append("</td>");
            buff.append("<td>");
            buff.append(Integer.toString(getComputerPrice(cb)));
            buff.append("</td>");
            buff.append("<td>");
            buff.append("<input type=\"submit\" value=\"Ändra\"/>");
            buff.append("<input type=\"hidden\" name=\"cid\" value=\"");
            buff.append(cb.getID());
            buff.append("\"/>");
            buff.append("</td>");
            buff.append("</tr>");
            buff.append("</form>");
        }

        return buff.toString();
    }
    
    public ComputerBean getById(int id) throws Exception {
	ComputerBean cb = null;
	Iterator iter = cpuList.iterator();
        
	while(iter.hasNext()){
	    cb=(ComputerBean)iter.next();
            
            cb.setPrice(getComputerPrice(cb));
            cb.setParts(getComputerParts(cb));
            
	    if(cb.getID() == id){
                return cb;
	    }
	}
	return null;
    }
    
    public int getComputerPrice(ComputerBean cb) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        int price = 0;
        
        String query = "SELECT SUM(price) AS price FROM components WHERE id IN "
                + "(SELECT component_id FROM cpu_comp WHERE computer_id=?)";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, cb.getID());
            rs = stmt.executeQuery();
            
            rs.next();
            
            price = rs.getInt("price");
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return price;
    }
    
    public String getComputerParts(ComputerBean cb) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        
        String parts = "";
        
        String query = "SELECT name, type FROM components WHERE id IN "
                + "(SELECT component_id FROM cpu_comp WHERE computer_id=?)";
        
        String query2 = "SELECT name FROM component_types WHERE id=?";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, cb.getID());
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                stmt2 = conn.prepareStatement(query2);
                stmt2.setInt(1, rs.getInt("type"));
                
                rs2 = stmt2.executeQuery();
                rs2.next();
                parts += rs2.getString("name"); 
                parts += ": ";
                parts += rs.getString("name");
                parts += "<br />";
            }
        } catch(Exception e) {
            throw new Exception("Could not get parts",e);
        } finally {
            try {
                rs.close();
                rs2.close();
                stmt.close();
                stmt2.close();
                conn.close();
            } catch (Exception e) {
                throw new Exception("Could not close connection",e);
            }
        }
        
        return parts;
    }
    
    public void updateComputer(ComputerBean cb) {
        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        
        String updateQuery = "UPDATE computers SET name=?, description=? "
                + "WHERE id=?";
        
        String deleteQuery = "DELETE FROM cpu_comp WHERE computer_id=?";
        
        String insertQuery = "INSERT INTO cpu_comp (computer_id, component_id) "
                + "VALUES (?,?)";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
            stmt = conn.prepareStatement(updateQuery);
            stmt.setString(1, cb.getName());
            stmt.setString(2, cb.getDescription());
            stmt.setInt(3, cb.getID());
            stmt.execute();
            
            stmt = conn.prepareStatement(deleteQuery);
            stmt.setInt(1, cb.getID());
            stmt.execute();
            
            String[] splitArray = cb.getParts().split(":");
            
            for(String s : splitArray) {
                stmt3 = conn.prepareStatement(insertQuery);
                stmt3.setInt(1, cb.getID());
                stmt3.setInt(2, Integer.parseInt(s));
                stmt3.execute();
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                stmt2.close();
                stmt3.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void insertComputer(ComputerBean cb) {
        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        ResultSet rs = null;
        
        String insertQuery1 = "INSERT INTO computers (name, description) "
                + "VALUES (?,?)";
        
        String selectQuery = "SELECT LAST_INSERT_ID() AS cid";
        
        String insertQuery2 = "INSERT INTO cpu_comp (computer_id, component_id)"
                + "VALUES (?,?)";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
            conn.setAutoCommit(false);
            
            stmt = conn.prepareStatement(insertQuery1);
            stmt.setString(1, cb.getName());
            stmt.setString(2, cb.getDescription());
            stmt.execute();
            
            stmt2 = conn.prepareStatement(selectQuery);
            rs = stmt2.executeQuery();
            
            conn.commit();
            
            rs.next();
            int cid = rs.getInt("cid");
            
            conn.setAutoCommit(true);
            
            String[] splitArray = cb.getParts().split(":");
            
            for(String s : splitArray) {
                stmt3 = conn.prepareStatement(insertQuery2);
                stmt3.setInt(1, cid);
                stmt3.setInt(2, Integer.parseInt(s));
                stmt3.execute();
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                stmt2.close();
                stmt3.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
