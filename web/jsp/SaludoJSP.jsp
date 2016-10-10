<%-- 
    Document   : SaludoJSP
    Created on : 10-oct-2016, 17:29:20
    Author     : Adrian
--%>

<%@page language="java"  contentType="text/html; charset=ISO‐8859‐1" pageEncoding="UTF-8"%>
<%@page import="java.time.LocalTime" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO‐8859‐1">
        <title>Saludo</title>
    </head>
    <body>
        <%
            String genero = request.getParameter("sexo").equals("Hombre") ? "señor":"señora";
            int hora = LocalTime.now().getHour();
            String saludo="";
            if(hora>=8 && hora<18){
                saludo = "buenos dias";
            }
            if (hora >=18 && hora<22){
                saludo= "buenas tardes";
            }
            if (hora >=22 || hora>=0 && hora<8){
                saludo = "buenas noches";
            }
        %>
    <p><h3>Saludos y <%=saludo%>, <%=genero%> <%=request.getParameter("nombre")%></h3></p>
    <p><a href="../index.html"><button type="button">Index</button></a></p>
        
    </body>
</html>
