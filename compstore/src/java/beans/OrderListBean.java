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
public class OrderListBean {
   
   private Collection newOrderList;
   private Collection oldOrderList;
   private String url = null;
    
   public OrderListBean(String _url) throws Exception {
        url = _url;
        
        Connection conn = null;
        
        Statement stmtNew = null;
        ResultSet rsNew = null;
        newOrderList = new ArrayList();
        
        Statement stmtOld = null;
        ResultSet rsOld = null;
        oldOrderList = new ArrayList();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);

            stmtNew = conn.createStatement();
            String sqlNew = "SELECT * FROM orders WHERE finished = 0";
            rsNew = stmtNew.executeQuery(sqlNew);

            while (rsNew.next()) {
                Statement stmtNew2 = null;
                ResultSet rsNew2 = null;
                
                OrderBean obNew = new OrderBean();
                
                obNew.setID(rsNew.getInt("order_id"));
                obNew.setUser(rsNew.getInt("customer_id"));
                
                stmtNew2 = conn.createStatement();
                String sqlNew2 = "SELECT Name FROM customers WHERE id = ";
                sqlNew2 += Integer.toString(rsNew.getInt("customer_id"));
                rsNew2 = stmtNew.executeQuery(sqlNew2);
                
                while (rsNew2.next()) {
                    obNew.setUserName(rsNew2.getString("Name"));
                }
                
                newOrderList.add(obNew);
                
                try {
                    rsNew2.close();
                } catch(Exception e) {}
                try {
                    stmtNew2.close();
                } catch(Exception e) {}
            } 
            
            stmtOld = conn.createStatement();
            String sqlOld = "SELECT * FROM orders WHERE finished = 1";
            rsOld = stmtOld.executeQuery(sqlOld);
            
            while (rsOld.next()) {
                Statement stmtOld2 = null;
                ResultSet rsOld2 = null;
                
                OrderBean obOld = new OrderBean();
                
                obOld.setID(rsOld.getInt("order_id"));
                obOld.setUser(rsOld.getInt("customer_id"));
                
                stmtOld2 = conn.createStatement();
                String sqlOld2 = "SELECT Name FROM customers WHERE id = ";
                sqlOld2 += Integer.toString(rsNew.getInt("customer_id"));
                rsOld2 = stmtNew.executeQuery(sqlOld2);
                
                while (rsOld2.next()) {
                    obOld.setUserName(rsOld2.getString("Name"));
                }
                
                oldOrderList.add(obOld);
                
                try {
                    rsOld2.close();
                } catch(Exception e) {}
                try {
                    stmtOld2.close();
                } catch(Exception e) {}
            } 
            
        } catch(SQLException sqle ){
            throw new Exception(sqle);
        } finally {
 	    try{
                rsNew.close();
                rsOld.close();
            }
            catch(Exception e) {}
            try{
                stmtNew.close();
                stmtOld.close();
            }
	    catch(Exception e) {}
            try {
                conn.close();
            }
            catch(Exception e){}
        }
    }
   
   java.util.Collection getNewOrderList() {
        return newOrderList;
   }
   
   java.util.Collection getOldOrderList() {
        return oldOrderList;
   }
   
   public String getXmlNew() {
        OrderBean ob=null;
        Iterator iter = newOrderList.iterator();
        StringBuffer buff = new StringBuffer();
                
        while(iter.hasNext()){
            ob = (OrderBean)iter.next();
            buff.append("<tr>");
            buff.append("<td>");
            buff.append(ob.getID());
            buff.append("</td>");
            buff.append("<td>");
            buff.append(ob.getUserName());
            buff.append("</td>");
            buff.append("</tr>");
        }

        return buff.toString();
   }
   
   public String getXmlOld() {
        OrderBean ob=null;
        Iterator iter = oldOrderList.iterator();
        StringBuffer buff = new StringBuffer();
                
        while(iter.hasNext()){
            ob = (OrderBean)iter.next();
            buff.append("<tr>");
            buff.append("<td>");
            buff.append(ob.getID());
            buff.append("</td>");
            buff.append("<td>");
            buff.append(ob.getUserName());
            buff.append("</td>");
            buff.append("</tr>");
        }

        return buff.toString();
   }
}
