<%-- 
    Document   : register
    Created on : 2013-maj-16, 10:51:40
    Author     : Joakim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
    <c:if test="${not sessionScope.registerFlag}">
        <p><font color="red">Lösenord matchar inte!</font></p>
    </c:if>
        <h1>Create account</h1>
        <form action="shop?action=registerProfile" method="post">
        <table>
            <tr bgcolor="yellow">
                <td>
                    Användarnamn
                </td>
                <td>
                    <input type="text"
                           value=""
                           size="50"
                           name="uname"/>
                </td>
            </tr>
            <tr bgcolor="yellow">
                <td>
                    Lösenord
                </td>
                <td>
                    <input type="password"
                           value=""
                           size="50"
                           name="pword"/>
                </td>
            </tr>
            <tr bgcolor="yellow">
                <td>
                    Bekräfta lösenord
                </td>
                <td>
                    <input type="password"
                           value=""
                           size="50"
                           name="pword2"/>
                </td>
            </tr>
        </table>
            <br>
        <table>
            <tr bgcolor="yellow">
                <td>
                    Namn
                </td>
                <td>
                    <input type="text" 
                           value=""
                           size="50"
                           name="name"/>
                </td>
            </tr>
            <tr bgcolor="yellow">
                <td>
                    Adress
                </td>
                <td>
                    <input type="text" 
                           value=""
                           size="50"
                           name="address"/>
                </td>
            </tr>
            <tr bgcolor="yellow">
                <td>
                    Mail
                </td>
                <td>
                    <input type="text" 
                           value=""
                           size="50"
                           name="mail"/>
                </td>
            </tr>
            <tr bgcolor="yellow">
                <td>
                    Telefonnummer
                </td>
                <td>
                    <input type="text" 
                           value=""
                           size="50"
                           name="phone"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Registrera"/></form>
                </td>
            </tr>
            <tr>
                <td>
                    <form action="shop" method="GET"><input type="submit" value="Avbryt"/></form>
                </td>
            </tr>
        </table>
    </body>
</html>
