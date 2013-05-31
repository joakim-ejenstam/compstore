<%-- 
    Document   : newComponent
    Created on : 2013-maj-31, 00:24:02
    Author     : Martin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager page: Add new Component</title>
    </head>
    <body>
    <h1>Admin Panel: Add/Edit component</h1>
    
    <jsp:useBean id="components" type="beans.ComponentListBean" scope="session"/>
    
    <form action="shop?action=updateComponent" method="post">
        
        <c:if test="${sessionScope.changeComponent != null}">
            <jsp:useBean id="changeComponent" type="beans.ComponentBean" scope="session">
                Error!
            </jsp:useBean>
                <table>
                    <tr>
                        <td>Namn</td>
                        <td>
                            <input type="text" 
                                   name="name" 
                                   value=${changeComponent.name} 
                                   size="50"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Typ</td>
                        <td>
                            ${components.dropdownxml}
                        </td>
                    </tr>
                    <tr>
                        <td>Pris</td>
                        <td>
                            <input type="text" 
                                   name="price" 
                                   value=${changeComponent.price} 
                                   size="50"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Antal i lager</td>
                        <td>
                            <input type="text" 
                                   name="qoh" 
                                   value=${changeComponent.qoh} 
                                   size="50"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Beskrivning</td>
                        <td>
                            <input type="text" 
                                   name="description" 
                                   value=${changeComponent.description} 
                                   size="50"/>
                        </td>
                    </tr>
                </table>

        </c:if>
        <c:if test="${sessionScope.changeComponent == null}">
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
                    <td>Typ</td>
                    <td>
                        ${components.dropdownxml}
                    </td>
                </tr>
                <tr>
                    <td>Pris</td>
                    <td>
                        <input type="text" 
                               name="price" 
                               value="" 
                               size="50"/>
                    </td>
                </tr>
                <tr>
                    <td>Antal i lager</td>
                    <td>
                        <input type="text" 
                               name="qoh" 
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
        <input type="submit" value="Spara"/>
    </form>
    </body>
</html>
