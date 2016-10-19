<%-- 
    Document   : carritoLibros
    Created on : 18-oct-2016, 16:44:05
    Author     : Adrian
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="es.albarregas.beans.Libros" %>
<%@page import="java.util.ArrayList" %>
<%@page import="javax.servlet.http.HttpSession" %>
<%@page import="java.util.Iterator" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../css/estLibreria.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="cabecera">
        <h1>Seleccione los libros y la cantidad que desea comprar</h1>
        <img src="../img/libros.png" alt="icon">
        </div>
        <%--En este fragmento del codigo se realizan las compras y las comprobaciones de errores.
        Lo primero que comprueba es si se ha pulsado, o no, el boton de finalizar compra.--%>
        <%if (!"Finalizar compra".equals(request.getParameter("fin"))){
            /*si no se ha pulsado finalizar la compra, se comprueba si ha pulsado incluir en la cesta.
            Si no se ha pulsado, porque es la primera vez que se entra en la pagina, se escribe la lista
            de libros y la cantidad. Si se ha pulsado, se comprueba si se ha seleccionado un libro y si
            se ha introducido una cantidad valida. si no, salta error.*/
            if("Incluir en la cesta".equals(request.getParameter("cesta"))){
                try{ 
                if (request.getParameter("libro")==null){
                    %><p><h3>Error: debe seleccionar un libro</h3></p><%
                }
                else
                if(Integer.parseInt(request.getParameter("cantidad"))<=0){
                    %><p><h3>Error: No se pueden introducir ni 0 ni valores negativos en la cantidad</h3></p><%
                }
                else{
                    /*aqui se carga un arraylist con el atributo libros de la sesion. si el resultado es null
                    porque es la primera vez que se ejecuta, se crea. Si no, se busca si el libro introducido
                    ya esta en la lista. Si esta se aumenta la cantidad y si no se añade.*/
                    HttpSession sesion = request.getSession(true);
                    ArrayList<Libros> listLibros = (ArrayList) sesion.getAttribute("Libros");
                    Libros libro = new Libros();
                    libro.setTitulo(request.getParameter("libro"));
                    libro.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
                    if(listLibros==null){
                        listLibros= new ArrayList();
                        listLibros.add(libro);
                        sesion.setAttribute("Libros", listLibros);
                    }
                    else{
                        boolean enLista=false;
                        for (int i=0;i<listLibros.size();i++){
                            if (libro.getTitulo().equals(listLibros.get(i).getTitulo())){
                                int cantidad= libro.getCantidad()+listLibros.get(i).getCantidad();
                                listLibros.get(i).setCantidad(cantidad);
                                enLista=true;
                            }
                        }
                        if (enLista==false){
                            listLibros.add(libro);
                        }
                        sesion.setAttribute("Libros", listLibros);
                    }
                    %><p><h3>Compra añadida a la cesta</h3></p><%
                }
                }catch(NumberFormatException e){
                %><p><h3>Error: la cantidad introducida no es valida</h3></p><%
                }
    }%>
    <div id="menu">
        <form action="carritoLibros.jsp" method="post">
        <select name="libro" size="7">
            <option value="El Hobbit">El Hobbit</option>
            <option value="El Quijote">El Quijote</option>
            <option value="El Conde Dracula">El Conde Dracula</option>
            <option value="Tirante el Blanco">Tirante el Blanco</option>
            <option value="La familia de Pascual Duarte">La familia de Pascual Duarte</option>
            <option value="Hamlet">Hamlet</option>
            <option value="La Divina Comedia">La Divina Comedia</option>
        </select>
        <p>Cantidad: <input type="text" name="cantidad"></p>
        <input type="submit" name="cesta" value="Incluir en la cesta">
        <input type="reset" value="Limpiar">
        <input type="submit" name="fin" value="Finalizar compra">
        </form>
    </div>
        <% }else{
        %><div id="final">
            <h4>Detalles del pedido:</h4>
            <%--Al pulsar "Finalizar compra" se comprueba si el atributo de la sesion es nulo(no se ha comprado nada)
            o no. Si es nulo, se muestra un mensaje y se termina la aplicacion. Si no, se muestran los libros comprados
            y la cantidad y se termina la aplicacion--%>
            <%
            HttpSession sesion= request.getSession(true);
            ArrayList<Libros> carrito = (ArrayList) sesion.getAttribute("Libros");
            if(carrito!=null){
                %><table id="tabla">
                <tr>
                    <th>Libro</th>
                    <th>Cantidad</th>
                </tr>
                <%
            Iterator<Libros> it = carrito.iterator();
            while(it.hasNext()){
                Libros libro = it.next();
            %><tr>
                <td><%=libro.getTitulo()%></td>
                <td><%=libro.getCantidad()%></td>
              </tr>
            <%
            }
            %></table>
            <%}else{
                    %><p><h3>No se ha comprado ningun libro</h3></p><%
                    } sesion.invalidate();%>
              <a href="../index.html"><button type="button">&Iacute;ndice</button></a>
              </div><%
        }%>
        
    </body>
</html>
