<%-- 
    Document   : newComputer
    Created on : 2013-maj-29, 10:55:54
    Author     : Joakim
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager page: Add new Computer</title>
    </head>
    <body>
    <h1>Admin Panel: Add/Edit computer</h1>
    <c:if test="${sessionScope.changeComputer != null}">
        <jsp:useBean id="changeComputer" type="beans.ComputerBean" scope="session">
            Error!
        </jsp:useBean>
            <form action="shop?updateComputer" method="post">
            <table>
                <tr>
                    <td>Namn</td>
                    <td>
                        <input type="text" 
                               name="name"
                               value=${changeComputer.name}
                               size="50"/>
                    </td>
                </tr>
            </table>
            </form>
    </c:if>
    <c:if test="${sessionScope.changeComputer == null}">
        <form action="shop?updateComputer" method="post">
            <table>
                <tr>
                    <td>Namn</td>
                    <td>
                        <input type="text" 
                               name="name"
                               value=""
                               size="50"/>
                    </td>
                </tr>
                <tr>
                    <td>Beskrivning</td>
                    <td>
                        <input type="text" 
                               name="description"
                               value=""
                               size="50"/>
                    </td>
                </tr>
            </table>
            </form>
    </c:if>
        
    <form action="shop?saveProduct" method="post">
        <input type="submit" value="Spara"/>
    </form>
        
        
    </body>
</html>
