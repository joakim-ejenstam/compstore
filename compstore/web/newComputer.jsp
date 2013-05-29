<%-- 
    Document   : newComputer
    Created on : 2013-maj-29, 10:55:54
    Author     : Joakim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager page: Add new Computer</title>
    </head>
    <body>
        <jsp:useBean id="changeComputer" type="beans.ComputerBean" scope="session">
            Error!
        </jsp:useBean>
        <h1>Admin Panel: Add/Edit computer</h1>
        <form action="shop?saveProduct" method="post">
            <input type="submit" value="Spara"/>
        </form>
    </body>
</html>
