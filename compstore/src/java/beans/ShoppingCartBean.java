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
public class ShoppingCartBean {
    
    private Collection shoppingCart;
    
    public ShoppingCartBean() {
        shoppingCart = new ArrayList();
    }
    
    public void addItem(ComputerBean cb, int quan) {
        Object newItem[] = null;
        ComputerBean tmpBean = null;
        
        if (shoppingCart.isEmpty()) {
            newItem = new Object[2];
            newItem[0] = cb;
            newItem[1] = new Integer(quan);    
            shoppingCart.add(newItem);
        } else {
            Iterator iter = shoppingCart.iterator();
            Object arr[];
	    boolean found = false;
            
            while (iter.hasNext()) {
                arr = (Object[])iter.next();
                
                if (((ComputerBean)arr[0]).getID() == cb.getID()) {
                    Integer antal = (Integer)arr[1];
		    arr[1] = new Integer(antal.intValue()+quan); 
		    found = true;
                }
            }
            
            if(!found){
		newItem = new Object[2];
		newItem[0]=cb;
		newItem[1]=new Integer(quan);    
		shoppingCart.add(newItem);
	    }
        }
    }
    
    public void removeItem(int id, int quan) {
        if(!shoppingCart.isEmpty()){
        
            Iterator iter = shoppingCart.iterator();
            Object arr[] = null;


            while(iter.hasNext()){
                arr = (Object[])iter.next();
                
                if(((ComputerBean)arr[0]).getID() == id) {
                    Integer antal = (Integer)arr[1];

                    if(antal.intValue() <= quan) {
                        iter.remove();
                    } else {
                        arr[1] = new Integer(antal.intValue()-quan);
                    }
                }
            }
        }
    }
    
    public void saveOrder(String _url, UserBean pb) throws SQLException {
        String url=_url;
        ComputerBean cb = null;
        Connection conn = null;
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        ResultSet rs = null;
        
        
        String orderSQL1 = "INSERT INTO orders("
                + "customer_id, finished)"
                + "VALUES(?,?)";
        
        String orderSQL2 = "SELECT LAST_INSERT_ID()";
        
        String orderSQL3 = "INSERT INTO orders_data("
                + "order_id, computer_id, quantity)"
                + "VALUES(?,?,?)";
                
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
            Iterator i = shoppingCart.iterator();
            Object[] buff = null;
            boolean flag = checkQoh(conn);
            System.out.println("Debug: flag= " + flag);
            if(flag) {
            
                System.out.println("Debug: Entered loop");
                
                // Turn off autocommit
                conn.setAutoCommit(false);

                stmt1 = conn.prepareStatement(orderSQL1);
                stmt1.setInt(1, pb.getID());
                stmt1.setInt(2, 0);
                stmt1.execute();

                stmt2 = conn.prepareStatement(orderSQL2);
                rs = stmt2.executeQuery();
                rs.next();

                int orderID = rs.getInt(1);

                Iterator iter = shoppingCart.iterator();
                cb = null;
                Object cmpBuff[];

                stmt3 = conn.prepareStatement(orderSQL3);
                while(iter.hasNext()) {
                    cmpBuff = (Object[])iter.next();
                    cb = (ComputerBean)cmpBuff[0];

                    stmt3.setInt(1, orderID);
                    stmt3.setInt(2, cb.getID());
                    stmt3.setInt(3, ((Integer)cmpBuff[1]).intValue());
                    stmt3.execute();
                    System.out.println("Debug: Added computer " + cb.getName() + " to order DB");
                }

                conn.commit();
                
                conn.setAutoCommit(true);
                removeQoh(conn);
                
                System.out.println("Debug: Committed, clearing cart");
                shoppingCart.clear();
            } else {
                throw new Exception("Out of stock");
            }
            
        } catch(Exception e) {
            try {
                conn.rollback(); // The transaction failed
            } catch(Exception re) {}
            e.printStackTrace();
            throw new SQLException("Error saving order", e);
        }
        finally {
            try {
                rs.close();
            } catch(Exception e) {}
            try {
                stmt1.close();
            } catch(Exception e) {}
            try {
                stmt2.close();
            } catch(Exception e) {}
            try {
                stmt3.close();
            } catch(Exception e) {}
            try {
                conn.close();
            } catch(Exception e) {}
        }
    }
    
    public String getXml() {
        StringBuffer buff = new StringBuffer();
        
        Iterator iter = shoppingCart.iterator();
        Object objBuff[] = null;
        
        while(iter.hasNext()){
            objBuff =(Object[])iter.next();
            buff.append("<tr><td>");
            buff.append(((ComputerBean)objBuff[0]).getXml2());
            buff.append("</td><td>");
            buff.append(((Integer)objBuff[1]).intValue());
            buff.append("<td><a href=\"shop?action=remove&cid=");
            buff.append(((ComputerBean)objBuff[0]).getID());
            buff.append("\">Ta bort</a>");
            buff.append("</td></tr>");
        }
        return buff.toString();
    }
    
    public boolean checkQoh(Connection conn) throws Exception {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Boolean greenLight = true;
        
        String query = "SELECT component_id FROM cpu_comp WHERE "
                + "computer_id = ?";
       
        String query2 = "SELECT id, qoh FROM components";
        
        HashMap components = new HashMap();
        Iterator iter = shoppingCart.iterator();
        Object objBuff[] = null;
        try {
            while (iter.hasNext()) {
                objBuff =(Object[])iter.next();

                int mapValue = 0;

                stmt = conn.prepareStatement(query);
                stmt.setInt(1, ((ComputerBean)objBuff[0]).getID());
                rs = stmt.executeQuery();

                while(rs.next()) {

                    if (components.get(rs.getInt("component_id")) != null) {
                        mapValue = (Integer)components.get(rs.getInt("component_id"));

                        components.put(rs.getInt("component_id"), mapValue+(Integer)objBuff[1]);
                    } else {
                        components.put(rs.getInt("component_id"), (Integer)objBuff[1]);
                    }
                }
            }

            rs = null;
            Statement s = conn.createStatement();
            rs = s.executeQuery(query2);
            
            while(rs.next()) {
                if(components.get(rs.getInt("id"))!= null) {
                    if (((Integer)components.get(rs.getInt("id"))) > rs.getInt("qoh")) {
                        greenLight = false;
                        break;
                    }
                }
            }
        } catch(Exception e) {
            throw new Exception("SQL error checking quantity", e);
        }
        finally {
            try{
                rs.close();
            }catch(Exception e){}
            try{
                stmt.close();
            }catch(Exception e){}
        }
        
        return greenLight;
    }
    
    public void removeQoh(Connection conn) throws Exception {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        
        String query = "SELECT component_id FROM cpu_comp WHERE "
                + "computer_id = ?";
       
        String query2 = "UPDATE components SET qoh = qoh - ? WHERE id = ?";
        
        Iterator iter = shoppingCart.iterator();
        Object objBuff[] = null;
        
        try {
            while (iter.hasNext()) {
                objBuff =(Object[])iter.next();

                stmt = conn.prepareStatement(query);
                stmt.setInt(1, ((ComputerBean)objBuff[0]).getID());
                rs = stmt.executeQuery();

                while(rs.next()) {
                    stmt2 = conn.prepareStatement(query2);
                    stmt2.setInt(1, (Integer)objBuff[1]);
                    stmt2.setInt(2, rs.getInt("component_id"));
                    stmt2.execute();
                }
            }    
        } catch(Exception e) {
            throw new Exception("SQL error removing QOH on component", e);
        }
        finally {
            try{
                rs.close();
            }catch(Exception e){}
            try{
                stmt.close();
            }catch(Exception e){}
        }
    }
    
    public void clear() {
        shoppingCart.clear();
    }
}
