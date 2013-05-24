<%-- 
    Document   : checkout
    Created on : 2013-maj-24, 09:40:09
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
        <jsp:useBean id="shoppingCart" type="beans.ShoppingCartBean" scope="session">
            Error, the bean should have been created in the servlet!
        </jsp:useBean>
            
        <h1>Checkout</h1>
        
        <table border="0" cellspacing="1">
            <tr>
                <td>
                    <strong>Din order</strong>
                </td>
                <tr bgcolor="silver">
                    <td>Namn</td>
                    <td>Antal</td>
                </tr>
            </tr>
            <tr>
                <jsp:getProperty name="shoppingCart" property="xml"/>
            </tr>
        </table>
        
        <a href="shop">Fortsätt handla</a>
        <a href="shop?action=confirm">Bekräfta order</a>
    </body>
</html>
