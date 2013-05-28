<%-- 
    Document   : profile
    Created on : May 24, 2013, 6:45:35 PM
    Author     : Joakim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="user" type="beans.UserBean" scope="session">
            Error, the bean should have been created in the servlet!
        </jsp:useBean>
        <h1>Hantera profil</h1>
        
        <form action="shop?action=updateProfile" method="post">
        <table>
            <tr>
                <td>
                    Namn
                </td>
                <td>
                    <input type="text" 
                           value="<jsp:getProperty name="user" property="name"/>"
                           size="50"
                           name="name"/>
                </td>
            </tr>
            <tr>
                <td>
                    Adress
                </td>
                <td>
                    <input type="text" 
                           value="<jsp:getProperty name="user" property="address"/>"
                           size="50"
                           name="address"/>
                </td>
            </tr>
            <tr>
                <td>
                    Mail
                </td>
                <td>
                    <input type="text" 
                           value="<jsp:getProperty name="user" property="mail"/>"
                           size="50"
                           name="mail"/>
                </td>
            </tr>
            <tr>
                <td>
                    Telefonnummer
                </td>
                <td>
                    <input type="text" 
                           value="<jsp:getProperty name="user" property="phone"/>"
                           size="50"
                           name="phone"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="reset" value="Återställ"/>
                </td>
                <td>
                    <input type="submit" value="Spara"/>
                </td>
            </tr>
        </table>
        </form>
    </body>
</html>
