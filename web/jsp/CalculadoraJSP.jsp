<%-- 
    Document   : CalculadoraJSP
    Created on : 10-oct-2016, 18:13:29
    Author     : Adrian
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar" %>
<%@page import="java.util.Enumeration" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calculadora</title>
    </head>
    <body>
        <div ip="info">
            <%--En esta seccion se escriben la fecha y el header. para la fecha, uso Calendar.
            para poder escribir el nombre del dia de la semana y del mes uso un switch, ya que
            Calendar los devuelve como int.
            Como conozco el nombre del header que quiero, solo necesito mostrarlo entero--%>
        <%Calendar fecha= Calendar.getInstance();
          int diaSemana=fecha.get(fecha.DAY_OF_WEEK);
          int diaMes=fecha.DAY_OF_MONTH+1;
          int mes=fecha.MONTH+1;
          int anio=fecha.get(fecha.YEAR);
          String diaLetra="";
          String mesLetra="";
          switch(diaSemana){
              case 0: diaLetra= "Domingo";
              case 1: diaLetra= "Lunes";
              case 2: diaLetra= "Martes";
              case 3: diaLetra= "Miercoles";
              case 4: diaLetra= "Jueves";
              case 5: diaLetra= "Viernes";
              case 6: diaLetra= "Sabado";
          }
          switch (mes){
              case 0: mesLetra= "Enero";
              case 1: mesLetra= "Febrero";
              case 2: mesLetra= "Marzo";
              case 3: mesLetra= "Abril";
              case 4: mesLetra= "Mayo";
              case 5: mesLetra= "Junio";
              case 6: mesLetra= "Julio";
              case 7: mesLetra= "Agosto";
              case 8: mesLetra= "Septiembre";
              case 9: mesLetra= "Octubre";
              case 10: mesLetra= "Noviembre";
              case 11: mesLetra= "Diciembre";
          }
          %>
          <p><h3><%=diaLetra%>, <%=diaMes%> de <%=mesLetra%> del <%=anio%></h3></p>
        <%Enumeration<String> cabecera=request.getHeaders("user-agent");
        while(cabecera.hasMoreElements()){
            String cab= cabecera.nextElement();
        %><p><%=cab%>
        <%}%>
        </p>
        </div>
        <div ip="resultado">
            <%--En esta seccion esta la calculadora en si. Al entrar por primera vez o si se pulsa el boton Limpiar,
            se muestra el cuerpo de la calculadora en si. Si se ha pulsado el boton "Operar", se realiza la operacion
            y se muestra el resultado. Si se intenta dividir entre 0, en vez del resultado se muestra un mensaje.
            Si se introducen caracteres que no sean numeros, o no se introduce nada en las cajas de los operandos,
            en vez del resultado se muestra un mensaje indicando que ha habido un error--%>
        <% if("Operar".equals(request.getParameter("enviar"))){
            try{
                double num1= Double.valueOf(request.getParameter("num1"));
                double num2= Double.valueOf(request.getParameter("num2"));
        %><p><h4>Resultado: <%
                if("suma".equals(request.getParameter("cuenta"))){
                    %><%=num1 + num2%><%
                }
                if("resta".equals(request.getParameter("cuenta"))){
                    %><%=num1 - num2%><%
                }
                if("multiplicacion".equals(request.getParameter("cuenta"))){
                    %><%=num1 * num2%><%
                }
                if("division".equals(request.getParameter("cuenta"))){
                    if(num2==0){
                        %> No se puede dividir entre cero<%
                    }
                    else{
                        %><%=num1 / num2%><%
                    }
                } %></h4></p><%
            }catch(NullPointerException | NumberFormatException y){
                %><p><h4>Los valores introducidos no son validos</p></h4><%
            }
            }%></div>
            <div>
                <form action="CalculadoraJSP.jsp" method="post">
                    <p><b>Introduzca los operandos</b>
                    <input type="text" name="num1"> - <input type="text" name="num2"></p>
                    <p><b>Seleccione el tipo de operacion:<b></p>
                    <input type="radio" name="cuenta" value="suma">Suma 
                    <input type="radio" name="cuenta" value="resta">Resta
                    <input type="radio" name="cuenta" value="multiplicacion">Multiplicaci&oacute;n
                    <input type="radio" name="cuenta" value="division">Divisi&oacute;n
                    </br></br>
                    <input type="submit" name="enviar" value="Operar">
                    <input type="submit" name="limpiar" value="Limpiar">
                    <a href="../index.html"><button type="button">&Iacute;ndex</button></a>
                </form>
            </div>
    </body>
</html>
