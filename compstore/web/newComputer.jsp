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
    
    <jsp:useBean id="components" type="beans.ComponentListBean" scope="session"/>
    
    <form action="shop?updateProduct" method="post">
        
        <c:if test="${sessionScope.changeComputer != null}">
            <jsp:useBean id="changeComputer" type="beans.ComputerBean" scope="session">
                Error!
            </jsp:useBean>
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
                    <tr>
                        <td>Beskrivning</td>
                        <td>
                            <input type="text"
                                   name="description"
                                   value=${changeComputer.description}
                        </td>
                    </tr>
                </table>

        </c:if>
        <c:if test="${sessionScope.changeComputer == null}">
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
        </c:if>
        ${components.checkboxxml}   
        <input type="submit" value="Spara"/>
    </form>
    </body>
</html>
