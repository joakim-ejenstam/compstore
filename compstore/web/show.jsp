<%-- 
    Document   : show
    Created on : 2013-maj-15, 09:23:32
    Author     : Joakim
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NoBloat Computer Store</title>
    </head>
    <body>
        <h1>NoBloat computer store</h1>
        <jsp:useBean id="computerList" type="beans.ComputerListBean" scope="application">
            Error, the bean should have been created in the servlet!
        </jsp:useBean>
        
        <jsp:useBean id="shoppingCart" type="beans.ShoppingCartBean" scope="session">
            Error, the bean should have been created in the servlet!
        </jsp:useBean>
        
        <c:if test="${sessionScope.user != null}"> 
        <jsp:useBean id="user" type="beans.UserBean" scope="session">
            Error, the bean should have been created in the servlet!
        </jsp:useBean>
            
        <jsp:getProperty name="user" property="xml"/>
        </c:if>
        
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
            <tr>
                <td>
                    <strong>Shoppingcart</strong>
                </td>
                <tr bgcolor="silver">
                    <td>Namn</td>
                    <td>Antal</td>
                </tr>
            </tr>
            <tr>
                <jsp:getProperty name="shoppingCart" property="xml"/>
            </tr>
            <tr>
                <td colspan="2">
                    <a href="shop?action=checkout">Till kassan</a>
                </td>
                <td>
                    <a href="shop?action=emptyCart">Töm</a>
                </td>
            </tr>
        </table>
        
    
    <br>
    <br>
    <c:if test="${sessionScope.user != null}">
        <table>
            <tr>
                <td>
                    <form action="shop?action=profil" method="post">
                        <input type="submit" value="Min profil"/>
                    </form>
                </td>
                <td>
                    <form action="shop?action=logout" method="post">
                        <input type="submit" value="Logga ut"/>
                    </form>
                </td>
            </tr>
        </table>             
    </c:if>
    <c:if test="${sessionScope.user == null}">
    <form action="shop?action=login" method="post">
        <input type="submit" value="Logga in"/>
    </form>
    </c:if>
    <br>
    <br>
    <strong>Om du vill lägga stora ordrar, <a href="http://www.youtube.com/watch?v=dQw4w9WgXcQ" target="_blank">ring oss!</a></strong>
    </body>
</html>
