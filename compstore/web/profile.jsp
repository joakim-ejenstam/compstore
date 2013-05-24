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
        Namn: <jsp:getProperty name="user" property="name"/>
        
        <form action="shop" method="post"><input type="submit" value="Återgå"/></form>
    </body>
</html>
