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
            buff.append("\" name=\"cid\"/>");
            buff.append("<input type=\"submit\" value=\"Ändra\"/>");
            buff.append("</td>");
            buff.append("</tr>");
            buff.append("</form>");
        }
        
        return buff.toString();
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
}
