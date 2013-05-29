/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.ComponentBean;
import beans.ComponentListBean;
import beans.ComputerBean;
import beans.ComputerListBean;
import beans.ManagerBean;
import beans.ShoppingCartBean;
import beans.UserBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Martin
 */
public class ShopServlet extends HttpServlet {
    private static String startpage = null;
    private static String jdbcURL = null;
    private static String detailspage = null;
    private static String errorpage = null;
    private static String loginpage = null;
    private static String registerpage = null;
    private static String checkpage = null;
    private static String thanks = null;
    private static String profilepage = null;
    private static String managerpage = null;
    private static String newcomputer = null;
    
    private ComputerListBean cList = null;
    private ComponentListBean compList = null;
    
    /**
     * Initializer for the servlet.
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        /*Update all jsp strings from config*/
        startpage = config.getInitParameter("SHOW_PAGE");
        jdbcURL = config.getInitParameter("JDBC_URL");
        detailspage = config.getInitParameter("DETAIL_PAGE");
        errorpage = config.getInitParameter("ERROR_PAGE");
        loginpage = config.getInitParameter("LOGIN_PAGE");
        registerpage = config.getInitParameter("REGISTER_PAGE");
        checkpage = config.getInitParameter("CHECKOUT_PAGE");
        thanks = config.getInitParameter("THANK_PAGE");
        profilepage = config.getInitParameter("PROFILE_PAGE");
        managerpage = config.getInitParameter("MANAGER_PAGE");
        newcomputer = config.getInitParameter("NEW_PAGE");
        
        try{
            cList = new ComputerListBean(jdbcURL);
            compList = new ComponentListBean(jdbcURL);
        }
        catch(Exception e) {
            throw new ServletException(e);
        }
        
        ServletContext sc = getServletContext();
        sc.setAttribute("computerList",cList);
                
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sess = request.getSession();
        ShoppingCartBean scb = getCart(request);
        RequestDispatcher rd = null;
        
        //Display the mainpage of the application
        if(request.getParameter("action") == null || 
           request.getParameter("action").equals("show")){	    
            rd = request.getRequestDispatcher(startpage); 
            rd.forward(request,response);
        }
        
        //Show the details page of one product
        else if (request.getParameter("action").equals("details")) {
            if(request.getParameter("cid") != null) {
                
                ComputerBean comp = 
                        cList.getById(
                        Integer.parseInt(request.getParameter("cid")));
                
                request.setAttribute("comp", comp);
            }
            else {
                throw new ServletException("No cid when viewing detail");
            }
            if(request.getAttribute("comp") != null) {
                rd = request.getRequestDispatcher(detailspage);
                rd.forward(request,response);
            } else {
                rd = request.getRequestDispatcher(errorpage);
                rd.forward(request, response);
            }      
        }
        
        // Add items to the cart.
        else if (request.getParameter("action").equals("add")) {
            
            //Check if the computer and quantity is not null
            if(request.getParameter("cid") != null &&
                    request.getParameter("quan") != null) {
                
                //TODO: add things to the shoppingcart later
                ComputerBean cb = cList.getById(
                        Integer.parseInt(request.getParameter("cid")));
                scb.addItem(cb,Integer.parseInt(request.getParameter("quan")));
                
                rd = request.getRequestDispatcher(startpage);
                rd.forward(request, response);
            } else {
                throw new ServletException("No such computer!");
            }      
        }
        
        // Remove item from the cart.
        else if (request.getParameter("action").equals("remove")) {
           
            if(request.getParameter("cid") != null) {
                scb.removeItem(Integer.parseInt(request.getParameter("cid")),1);
                rd = request.getRequestDispatcher(startpage);
                rd.forward(request, response);
            } else {
                throw new ServletException("No item in cart");
            }
            
        }
        
        // Forward user to checkout page
        
        else if (request.getParameter("action").equals("checkout")) {
            if(sess.getAttribute("user") != null) {
                rd = request.getRequestDispatcher(checkpage);
                rd.forward(request, response);
            } else {
                rd = request.getRequestDispatcher(loginpage);
                rd.forward(request, response);
            }
            
        }
        
        // Finalize the customers order
        
        else if (request.getParameter("action").equals("confirm")) {
            try {
                scb.saveOrder(jdbcURL, (UserBean)request.getSession().getAttribute("user"));
            } 
            catch(Exception e){
            }
            rd = request.getRequestDispatcher(thanks);
            rd.forward(request, response);
        }
        
        // Send the user to the login page
        else if (request.getParameter("action").equals("login")) {
            rd = request.getRequestDispatcher(loginpage);
            rd.forward(request,response);
        }
        
        else if (request.getParameter("action").equals("logout")) {
            sess.invalidate();
            rd = request.getRequestDispatcher(thanks);
            rd.forward(request,response);
        }
        
        else if (request.getParameter("action").equals("loggedin")) {
            UserBean _ub = getUser(request);
            rd = request.getRequestDispatcher(startpage);
            rd.forward(request, response);
        }
        
        else if (request.getParameter("action").equals("profil")) {
            rd = request.getRequestDispatcher(profilepage);
            rd.forward(request, response);
        }
        
        else if (request.getParameter("action").equals("updateProfile")) {
            saveProfile(request);
            rd = request.getRequestDispatcher(startpage);
            rd.forward(request, response);
        }
        
        else if (request.getParameter("action").equals("registerProfile")) {
            if(createProfile(request)) {
                rd = request.getRequestDispatcher(loginpage);
                rd.forward(request, response);
            }
            request.getSession().setAttribute("registerFlag",false);
            rd = request.getRequestDispatcher(registerpage);
            rd.forward(request, response);
        }
        
        else if (request.getParameter("action").equals("register")) {
            request.getSession().setAttribute("registerFlag",true);
            rd = request.getRequestDispatcher(registerpage);
            rd.forward(request, response);
        }
        
        else if (request.getParameter("action").equals("emptyCart")) {
            scb.clear();
            rd = request.getRequestDispatcher(startpage);
            rd.forward(request, response);
        }
        
        else if (request.getParameter("action").equals("manager")) {
            request.getSession().setAttribute("manager", new ManagerBean(jdbcURL,compList,cList));
            rd = request.getRequestDispatcher(managerpage);
            rd.forward(request, response);
        }
        
        else if (request.getParameter("action").equals("updateStock")) {
            ManagerBean mb = (ManagerBean)request.getSession().getAttribute("manager");
            if(mb != null) {
                System.out.println(mb);
                ComponentBean cb = 
                        mb.getComponent(Integer.parseInt(request.getParameter("cid")));
                cb.setPrice(Integer.parseInt(request.getParameter("price")));
                cb.setQoh(Integer.parseInt(request.getParameter("qoh")));
                try { 
                    mb.updateComponent(cb);
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
            rd = request.getRequestDispatcher(managerpage);
            rd.forward(request, response);
        }
        
        else if(request.getParameter("action").equals("addProduct")) {
            if(request.getParameter("cid") != null) {
                request.getSession().setAttribute(
                    "changeComputer", 
                    cList.getById(Integer.parseInt(request.getParameter("cid"))));
            }
            rd = request.getRequestDispatcher(newcomputer);
            rd.forward(request, response);
        }
    }
    
    private ShoppingCartBean getCart(HttpServletRequest request){
        HttpSession se = null;
        se=request.getSession();
        ShoppingCartBean sb =null;
        sb = (ShoppingCartBean)se.getAttribute("shoppingCart");
        if(sb==null){
            sb = new ShoppingCartBean();
            se.setAttribute("shoppingCart",sb);
        }

        return sb;
    }
    
    private UserBean getUser(HttpServletRequest request) {
        HttpSession se = null;
        se=request.getSession();
        UserBean user = null;
        user = (UserBean)se.getAttribute("user");
        
        if(user==null) {
            
            String uname = request.getParameter("username");
            String pword = request.getParameter("password");
            
            try {
                user = new UserBean();
                loadProfile(user,uname,pword);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            
            if(user==null){System.out.println(uname);}
            se.setAttribute("user", user);
        }
        return user;
    }
    
    private void saveProfile(HttpServletRequest request) {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String mail = request.getParameter("mail");
        String phone = request.getParameter("phone");
        
        UserBean ub = (UserBean) request.getSession().getAttribute("user");
        
        ub.setName(name);
        ub.setAddress(address);
        ub.setMail(mail);
        ub.setPhone(phone);
        
        try {
            ub.updateDB(jdbcURL);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadProfile(UserBean user, String uname, String pword) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String query = "SELECT id, Name, Address, Mail, Phone, manager "
                + "FROM customers WHERE "
                + "username = ? AND "
                + "password = ? ";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(jdbcURL);
            
            stmt = conn.prepareStatement(query);
            stmt.setString(1, uname);
            stmt.setString(2, pword);
            rs = stmt.executeQuery();
            
            rs.next();
            user.setID(rs.getInt("id"));
            user.setName(rs.getString("Name"));
            user.setAddress(rs.getString("Address"));
            user.setMail(rs.getString("Mail"));
            user.setPhone(rs.getString("Phone"));
            user.setManager(rs.getInt("manager")); 
    
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private boolean createProfile(HttpServletRequest request) {
        UserBean user = new UserBean();
        String pword = request.getParameter("pword");
        String pword2 = request.getParameter("pword2");
        
        if(pword != null && pword2 != null && pword.equals(pword2)) {
            String uname = request.getParameter("uname");
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String mail = request.getParameter("mail");
            String phone = request.getParameter("phone");
        
            user.setName(name);
            user.setAddress(address);
            user.setMail(mail);
            user.setPhone(phone);
            
            try {
                user.insertDB(jdbcURL,uname,pword);
            } catch(Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
