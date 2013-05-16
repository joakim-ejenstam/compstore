<%-- 
    Document   : details
    Created on : 2013-maj-15, 13:10:41
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
        <h1>Computer details</h1>
        <jsp:useBean id="comp" type="beans.ComputerBean" scope="request"/>
        <jsp:getProperty name="comp" property="xml"/>
        <br>
        <table border="1">
            <tr>
                <td><a href="shop?action=show">Back to store</a></td>
                <td><a href="shop?action=add&cid=1&quan=10000">Add to cart</a></td>            
            </tr>
        </table>
    </body>
</html>
