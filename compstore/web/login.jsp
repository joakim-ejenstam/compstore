<%-- 
    Document   : login
    Created on : 2013-maj-15, 18:05:54
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
        <h1>Login to NoBloat webshop</h1>
        <table border="1">
            <tr>
                <td>
                    Username
                </td>
                <td>  
                    <input size="20" type="text" name="username">
                </td>
            </tr>
            <tr>
                <td>
                    Password
                </td>
                <td>
                    <input size="20" type="text" name="password">
                </td>
            </tr>
        </table>
        <a href="shop?action=loggedin&username=jocklas&password=1377">Login</a>
        <a href="shop">Cancel</a>
        <br>
        Not a user? Register <a href="shop?action=register">here</a>
    </body>
</html>
