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
        
        <p>Welcome to the NoBloat computer store!<br> The one and only place for reasonable computer purchases.</p>
        
        <table border="5">
            <tr>
                <td>Computer Type</td>
                <td>Motherboard</td>
                <td>CPU</td>
                <td>RAM</td>
                <td>HDD</td>
                <td>Graphics</td>
                <td>Price</td>
            </tr>
            <tr>
                <td>Basic</td>
                <td>Standard</td>
                <td>Singlecore 0,5Mhz</td>
                <td>1MB</td>
                <td>80MB</td>
                <td>N/A</td>
                <td>200$</td>
            </tr>
            <tr>
                <td>Moderate</td>
                <td>Decentshit mkII</td>
                <td>Dualcore 1,5Ghz</td>
                <td>1GB</td>
                <td>160GB 7200RPM</td>
                <td>Standard</td>
                <td>1000$</td>
            </tr>
            <tr>
                <td>Superduper</td>
                <td>Awesome mega 2000</td>
                <td>Petacore 2,7Ghz</td>
                <td>128TB</td>
                <td>Quantum superdrive</td>
                <td>Supergraph 4D</td>
                <td>200kg Urainium</td>
            </tr>
        </table>
        <jsp:getProperty name="computerList" property="xml"/>
        <a href="ShopServlet?action=details">Click motherfucker!</a>
    </body>
</html>
