/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Adrian
 */
public class FormErrorActualizado extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(cabecera());
            boolean error= false;
            boolean [] errores= {false, false, false, false};
            //primero se comprueban cuales errores se han cometido (o no)
            if (request.getParameter("Nombre")==null||"".equals(request.getParameter("Nombre"))){
                errores[0]=true;
            }
            if (request.getParameter("Usuario")==null||"".equals(request.getParameter("Usuario"))){
                errores[1]=true;
            }
            if (request.getParameter("Contrasena")==null || "".equals(request.getParameter("Contrasena"))){
                errores[2]=true;
            }
            errores[3]=comprobarFecha(request.getParameter("Dia"), request.getParameter("Mes"),request.getParameter("Anio"));
            for (int i=0;i<errores.length;i++){
                if (errores[i]==true){
                    error=true;
                }
            }
            /*si se comete algun error, se lleva al usuario a una pantalla de error, y despues se le 
            devuelve al formulario, mostrandole que errores a cometido. si todo es correcto, se le muestran
            los datos introducidos y se le da la posibilidad de corregir alguno o de volver al indice*/
            if("Volver".equals(request.getParameter("corregir"))){
                out.println("<div id=\"errores\">");
                if(errores[0]==true){
                    out.println("<h1>Error en el nombre</h1>");
                }
                if(errores[1]==true){
                    out.println("<h1>Error en el usuario</h1>");
                }
                if(errores[2]==true){
                    out.println("<h1>Error en la contrase&ntilde;a</h1>");
                }
                if(errores[3]==true){
                    out.println("<h1>Error en la fecha</h1>");
                }
                out.println("</div>");
                out.println("<div>");
                out.println(formulario(request));
                out.println("</div>");
            }
            else{
            if (error==true){
                out.println("<h1>Error en los datos</h1>");
                out.println(formOculto(request));
                out.println("</form>");
            }
            else{
                Enumeration<String> parametros= request.getParameterNames();
                out.println("<div>");
                while(parametros.hasMoreElements()){
                    String parametro= parametros.nextElement();
                        if ("Dia".equals(parametro)){
                            out.println("<p>Fecha de Nacimiento: "+ request.getParameter(parametro));
                        }
                        else
                            if ("Mes".equals(parametro)){
                                out.println("/"+request.getParameter(parametro));
                            }
                                else
                                if ("Anio".equals(parametro)){
                                    out.println("/"+request.getParameter(parametro)+"</p>");
                                }
                                else{
                                    if ("Nombre".equals(parametro)){
                                        out.println("<h2>Datos Personales: </h2>");
                                    }
                                    if("Usuario".equals(parametro)){
                                        out.println("<h2>Datos de Usuario: </h2>");
                                    }
                                    if("Contrasena".equals(parametro)){
                                        out.println("<p>Contrase&ntilde;a: "+ request.getParameter(parametro)+"</p>");
                                        out.println("<h2>Aficiones:</h2>");
                                    }
                                    else
                                        if("on".equals(request.getParameter(parametro))){
                                            out.println("<p>"+parametro+"</p>");
                                        }
                                        else{
                                            if (request.getParameter(parametro)==null||"".equals(request.getParameter(parametro))){
                                            out.println("<p>"+parametro+": no proporcionado</p>");
                                            }
                                            else{
                                                if ("Envio".equals(parametro)){
                                                    out.println("");
                                                }
                                                else{
                                                    out.println("<p>"+parametro+": "+request.getParameter(parametro)+"</p>");}
                                            }
                                        }
                                }
                    
                }
                out.println("</div>");
                out.println(formOculto(request));
                out.println("<a href='index.html'><button type=\"button\">&Iacute;ndice</button></a>");
                out.println("</form>");
            }
            }
            out.println(pie());
        }
    }
    //metodo para comprobar la validez de la fecha introducida
    public boolean comprobarFecha(String dia, String mes, String anio){
        int dian = Integer.parseInt(dia);
        int mesn= Integer.parseInt(mes);
        int anion= Integer.parseInt(anio);
        boolean error=false;
        if (dian==0||mesn==0||anion==0){
        error=true;
        }
        if (mesn==4||mesn==6||mesn==9||mesn==11 && dian>30){
            error= true;
        }
        if (mesn==2 && anion%4!=0 && dian>28){
            error= true;
        }
        if (mesn==2 && anion%4==0 && dian>29){
            error=true;
        }

        return error;
    }
    //este metodo imprime el formulario original conservando la informacion ya dada
    public String formulario(HttpServletRequest request){
        StringBuilder sb= new StringBuilder();
           sb.append("<form action=\"FormErrorActualizado\" method=\"post\">"
            +"<h3>Informaci&oacute;n personal</h3>"
            +"<p>*Nombre: <input type=\"text\" name=\"Nombre\" value=\""+request.getParameter("Nombre")+"\"></p>"
            +"<p>Apellidos: <input type=\"text\" name=\"Apellidos\" value=\""+request.getParameter("Apellidos")+"\"></p>");
                   if("Hombre".equals(request.getParameter("Sexo"))){
            sb.append("<p>Sexo: <input type=\"radio\" name=\"Sexo\" value=\"Hombre\" checked>Hombre"
                +"<input type=\"radio\" name=\"Sexo\" value=\"Mujer\">Mujer</p>");}
                   
                   if("Mujer".equals(request.getParameter("Sexo"))){
                       sb.append("<p>Sexo: <input type=\"radio\" name=\"Sexo\" value=\"Hombre\">Hombre"
                +"<input type=\"radio\" name=\"Sexo\" value=\"Mujer\" checked>Mujer</p>");}
                   
           sb.append("<p>Fecha de nacimiento: <select name=\"Dia\">");
                    for (int i=1;i<=31;i++){
                        if (i==Integer.parseInt(request.getParameter("Dia"))){
                           sb.append("<option value=\""+i+"\" selected>"+i+"</option>");
                        }
                        else{
                           sb.append("<option value=\""+i+"\">"+i+"</option>");
                        }
                    }
            sb.append("</select>"
            + "/<select name=\"Mes\">");
                    for (int i=1;i<=12;i++){
                        if (i==Integer.parseInt(request.getParameter("Mes"))){
                           sb.append("<option value=\""+i+"\" selected>"+i+"</option>");
                        }
                        else{
                           sb.append("<option value=\""+i+"\">"+i+"</option>");
                        }
                    }
            sb.append("</select>"
            + "/<select name=\"Anio\">");
                    for (int i=1951;i<=1996;i++){
                        if (i==Integer.parseInt(request.getParameter("Anio"))){
                           sb.append("<option value=\""+i+"\" selected>"+i+"</option>");
                        }
                        else{
                           sb.append("<option value=\""+i+"\">"+i+"</option>");
                        }
                    }
            sb.append("</select></p><br/>"
            +"<h3> Datos de Acceso</h3>"
            +"<p>*Usuario: <input type=\"text\" name=\"Usuario\" value=\""+request.getParameter("Usuario")+"\"></p>"
            +"<p>*Contrase&ntilde;a: <input type=\"password\" name=\"Contrasena\" value=\""+request.getParameter("Contrasena")+"\"></p>"
            +"<br/>"
            +"<h3>Aficiones</h3>"
            +"<p>");
           if ("on".equals(request.getParameter("Deporte"))){
                sb.append("<input type=\"checkbox\" name=\"Deporte\" checked>Deporte");}
           else{
               sb.append("<input type=\"checkbox\" name=\"Deporte\">Deporte");
           }
           if("on".equals(request.getParameter("Lectura"))){
                sb.append("<input type=\"checkbox\" name=\"Lectura\" checked>Lectura");}
           else{
               sb.append("<input type=\"checkbox\" name=\"Lectura\">Lectura");}
           if("on".equals(request.getParameter("Cine"))){
                sb.append("<input type=\"checkbox\" name=\"Cine\" checked>Cine");}
           else{
               sb.append("<input type=\"checkbox\" name=\"Cine\">Cine");}
           if("on".equals(request.getParameter("Viajes"))){
                sb.append("<input type=\"checkbox\" name=\"Viajes\" checked>Viajes");}
           else{
               sb.append("<input type=\"checkbox\" name=\"Viajes\">Viajes");
           }
            sb.append("</p>"
            +"<input type=\"submit\" name=\"Envio\" value=\"Enviar\">"
            +"<a href='html/htmlformerroresactualizado.html'><button type=\"button\">Limpiar</button></a>"
            +"</form>");
           return sb.toString();
    }
    //este metodo crea un formulario oculto para almacenar la informacion
    public String formOculto(HttpServletRequest request){
        StringBuilder sb= new StringBuilder();
        Enumeration <String> parametros = request.getParameterNames();
        String parametro;
        sb.append("<form action=\"FormErrorActualizado\" method=\"post\">");
        while (parametros.hasMoreElements()){
            parametro=parametros.nextElement();
            sb.append("<input type=\"hidden\" name=\""+parametro+"\" value=\""+request.getParameter(parametro)+"\">");
        }
        sb.append("<input type=\"submit\" name=\"corregir\" value=\"Volver\">");
        return sb.toString();
    }
    //el metodo cabecera y pie simplemente sirven para ahorrarse out.prints en el metodo principal
    public String cabecera(){
        StringBuilder sb= new StringBuilder();
        sb.append("<!DOCTYPE html>"
            +"<html>"
            +"<head>"
            +"<title>Formulario con Errores Actualizado</title>"
            +"<link rel=\"stylesheet\" href=\"css/estformacterr.css\">"
            +"</head>"
            +"<body>");
        return sb.toString();
    }
    
        public String pie(){
        StringBuilder sb= new StringBuilder();
        sb.append("</body>"
                + "</html>");
        return sb.toString();
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
