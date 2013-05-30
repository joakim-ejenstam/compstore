<%-- 
    Document   : manager
    Created on : 2013-maj-29, 10:24:41
    Author     : Joakim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NoBloat: Manager panel</title>
    </head>
    <body>
        <h1>Admin panel</h1>
        <jsp:useBean id="manager" type="beans.ManagerBean" scope="session">
            Error, the bean should have been created in the servlet!
        </jsp:useBean>
          
        <h2>Komponentlista</h2>
        <table>
            <tr bgcolor="yellow">
                <td>
                    Namn
                </td>
                <td>
                    Typ
                </td>
                <td>
                    Pris
                </td>
                <td>
                    Lager saldo
                </td>
            </tr>
            <jsp:getProperty name="manager" property="componentXml"/>
        </table>
        <table>
            <tr>
                <td>
                    <form action="shop?action=addComponent" method="post">
                        <input type="submit" value="Lägg till"/>
                    </form>
                </td>
            </tr>
        </table>
        
        <br>
        <h2>Produktlista</h2>
        <table>
            <tr bgcolor="yellow">
                <td>
                    ID
                </td>
                <td>
                    Namn
                </td>
                <td>
                    Pris
                </td>
            </tr>
            <jsp:getProperty name="manager" property="computerXml"/>
        </table>
        <table>
            <tr>
                <td>
                    <form action="shop?action=addProduct" method="post">
                        <input type="submit" value="Lägg till"/>
                    </form>
                </td>
            </tr>
            <tr>
                <td>
                    <form action="shop" method="post">
                        <input type="submit" value="Avbryt"/>
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>
