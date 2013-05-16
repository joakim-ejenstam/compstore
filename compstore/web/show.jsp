<%-- 
    Document   : show
    Created on : 2013-maj-15, 09:23:32
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
        <h1>NoBloat computer store</h1>
        <jsp:useBean id="computerList" type="beans.ComputerListBean" scope="application">
            Error, the bean should have been created in the servlet!
        </jsp:useBean>
        
        <jsp:useBean id="shoppingCart" type="beans.ShoppingCartBean" scope="session">
            Error, the bean should have been created in the servlet!
        </jsp:useBean>
        
        <p>Welcome to the NoBloat computer store!<br> The one and only place for reasonable computer purchases.</p>
        
        <table>
            <tr>
                <th>Namn</th>
                <th>Beskrivning</th>
                <th>Pris</th>
            </tr>
            <jsp:getProperty name="computerList" property="xml"/>
        </table>
        
        
        <br>
        <table border="0" cellspacing="1">
            <tr bgcolor="silver">
                <td colspan="4">
                    <strong>Shoppingcart</strong>
                </td>
                <tr bgcolor="silver">
                    <td>Namn</td>
                    <td>Antal</td>
                    <td colspan="2">Ta bort</td>
                </tr>
            </tr>
            <tr>
                <jsp:getProperty name="shoppingCart" property="xml"/>
            </tr>
            <tr>
                <td colspan="2">
                    <a href="shop">Checkout</a>
                </td>
            </tr>
    </table>
        
        
    <a href="shop?action=login">login</a>
    </body>
</html>
