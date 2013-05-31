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
public class ComponentListBean {
    
    private Collection compList;
    private String url = null; 
    
    public ComponentListBean(String _url) throws Exception {
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
            
            stmt = null;
            rs = null;
            
        } catch (SQLException sqle ){
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
    
    java.util.Collection getComponentList() {
        return compList;
    }
    
    public String getManagerxml() {
        
        ComponentBean cob = null;
        Iterator iter = compList.iterator();
        StringBuffer buff = new StringBuffer();
        
        while(iter.hasNext()){
            cob = (ComponentBean)iter.next();
            buff.append("<form action=\"shop?action=updateStock\" method=\"post\">");
            buff.append("<tr>");
            buff.append("<td>");
            buff.append(cob.getName());
            buff.append("</td>");
            buff.append("<td>");
            buff.append(cob.getType());
            buff.append("</td>");
            buff.append("<td>");
            buff.append("<input type=\"text\" value=\"");
            buff.append(cob.getPrice());
            buff.append("\" size=\"5\" name=\"price\"/>");
            buff.append("</td>");
            buff.append("<td>");
            buff.append("<input type=\"text\" value=\"");
            buff.append(cob.getQoh());
            buff.append("\"size=\"5\" name=\"qoh\"/>");
            buff.append("</td>");
            buff.append("<td>");
            buff.append("<input type=\"hidden\" value=\"");
            buff.append(cob.getID());
            buff.append("\" name=\"compid\"/>");
            buff.append("<input type=\"submit\" value=\"Ändra pris/antal\"/>");
            buff.append("</td>");
            buff.append("</form>");
            buff.append("<td>");
            buff.append("<form action=\"shop?action=addComponent\" method=\"post\">");
            buff.append("<input type=\"submit\" value=\"Ändra komponent\"/>");
            buff.append("<input type=\"hidden\" name=\"compid\" value=\"");
            buff.append(cob.getID());
            buff.append("\"/></form>");
            buff.append("</td>");
            buff.append("</form>");
            buff.append("<td>");
            buff.append("<form action=\"shop?action=removeComponent\" method=\"post\">");
            buff.append("<input type=\"submit\" value=\"Ta bort\"/>");
            buff.append("<input type=\"hidden\" name=\"compid\" value=\"");
            buff.append(cob.getID());
            buff.append("\"/></form>");
            buff.append("</td>");
            buff.append("</tr>");
        }
        
        return buff.toString();
    }
    
    public String getCheckboxxml() {
        ComponentBean cob = null;
        Iterator iter = compList.iterator();
        StringBuffer buff = new StringBuffer();
        
        while(iter.hasNext()) {
            cob = (ComponentBean)iter.next();
            buff.append("<input type=\"checkbox\" name=\"type\" value=\"");
            buff.append(Integer.toString(cob.getID()));
            buff.append("\" /> ");
            buff.append(cob.getName());
            buff.append("<br />");
        }
        
        return buff.toString();
    }
    
    public String getDropdownxml() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String returnString = "<select name=\"type\">";
        returnString += "<option value=0>Välj typ</option>";
        
        String query = "SELECT * FROM component_types ORDER BY name ASC";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                returnString += "<option value=\"";
                returnString += Integer.toString(rs.getInt("id"));
                returnString += "\">";
                returnString += rs.getString("name");
                returnString += "</option>";
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        returnString += "</select>";
        
        return returnString;
    }
    
    public ComponentBean getById(int id) {
	ComponentBean cob = null;
	Iterator iter = compList.iterator();
        
	while(iter.hasNext()){
	    cob=(ComponentBean)iter.next();
	    if(cob.getID() == id){
                return cob;
	    }
	}
	return null;
    }
    
    public void removeById(int id) throws Exception {
        ComponentBean cob = null;
        Iterator iter = compList.iterator();
        while(iter.hasNext()) {
            cob = (ComponentBean)iter.next();
            if(cob.getID() == id){
                compList.remove(cob);
            }
        }
    }
    
    public void updateComponent(ComponentBean cob) {
        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        
        String updateQuery = "UPDATE components SET "
                + "name=?, "
                + "type=?, "
                + "price=?, "
                + "qoh=?, "
                + "description=? "
                + "WHERE id=?";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
            stmt = conn.prepareStatement(updateQuery);
            stmt.setString(1, cob.getName());
            stmt.setInt(2, cob.getType());
            stmt.setInt(3, cob.getPrice());
            stmt.setInt(4, cob.getQoh());
            stmt.setString(5, cob.getDescription());
            stmt.setInt(6, cob.getID());
            stmt.execute();
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void insertComponent(ComponentBean cob) {
        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        ResultSet rs = null;
        
        int compid;
        
        String insertQuery = "INSERT INTO components (name, "
                + "type, "
                + "price, "
                + "qoh, "
                + "description) "
                + "VALUES (?,?,?,?,?)";
        
        String selectQuery = "SELECT LAST_INSERT_ID() AS compid";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
            conn.setAutoCommit(false);
            
            stmt = conn.prepareStatement(insertQuery);
            stmt.setString(1, cob.getName());
            stmt.setInt(2, cob.getType());
            stmt.setInt(3, cob.getPrice());
            stmt.setInt(4, cob.getQoh());
            stmt.setString(5, cob.getDescription());
            stmt.execute();
            
            stmt2 = conn.prepareStatement(selectQuery);
            rs = stmt2.executeQuery();
            
            conn.commit();
            
            rs.next();
            compid = rs.getInt("compid");
            
            conn.setAutoCommit(true);
            
            cob.setID(compid);
            
            compList.add(cob);

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                stmt2.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void deleteComponent(ComponentBean cob) {
        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        
        String deleteQuery1 = "DELETE FROM components WHERE id=?";
        
        String deleteQuery2 = "DELETE FROM cpu_comp WHERE component_id=?";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
            stmt = conn.prepareStatement(deleteQuery1);
            stmt.setInt(1, cob.getID());
            stmt.execute();
            
            stmt2 = conn.prepareStatement(deleteQuery2);
            stmt2.setInt(1, cob.getID());
            stmt2.execute();
            
            removeById(cob.getID());

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                stmt2.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
