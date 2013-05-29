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
    
    public String getXml() {
        
        ComponentBean cob = null;
        Iterator iter = compList.iterator();
        StringBuffer buff = new StringBuffer();
        
        while(iter.hasNext()){
            cob = (ComponentBean)iter.next();
            buff.append("<tr>");
            buff.append("<td>");
            buff.append(cob.getName());
            buff.append("</td>");
            buff.append("<td>");
            buff.append(cob.getType());
            buff.append("</td>");
            buff.append("<td>");
            buff.append(cob.getPrice());
            buff.append("</td>");
            buff.append("<td>");
            buff.append(cob.getQoh());
            buff.append("</td>");
            buff.append("<td>");
            buff.append(cob.getDescription());
            buff.append("</td>");
            buff.append("</tr>");
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
