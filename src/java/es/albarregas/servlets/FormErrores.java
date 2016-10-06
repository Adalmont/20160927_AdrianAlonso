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
public class FormErrores extends HttpServlet {

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
            //para controlar si se produce algun error uso variables booleanas
            boolean errornombre= false;
            boolean errorusuario=false;
            boolean errorcontra=false;
            boolean errorfecha=false;
            if (request.getParameter("Nombre")==null||"".equals(request.getParameter("Nombre"))){
                errornombre=true;
            }
            if (request.getParameter("Usuario")==null||"".equals(request.getParameter("Usuario"))){
                errorusuario=true;
            }
            if (request.getParameter("Contrasena")==null || "".equals(request.getParameter("Contrasena"))){
                errorcontra=true;
            }
            errorfecha=comprobarFecha(request.getParameter("Dia"), request.getParameter("Mes"),request.getParameter("Anio"));
            //si alguno de los errores esta activos se muestra cual y se vuelve a imprimir el formulario
            if (errornombre== true || errorusuario== true|| errorcontra==true|| errorfecha==true){
                out.println("<div id=\"errores\"");
                if (errornombre==true){
                    out.println("<p><h1>Error en el Nombre!</h1></p>");
                }
                if (errorusuario==true){
                    out.println("<p><h1>Error en el Usuario!</h1></p>");
                }
                if (errorcontra==true){
                    out.println("<p><h1>Error en la Contrase&ntilde;a!!</h1></p>");
                }
                if (errorfecha==true){
                    out.println("<p><h1>Error en la Fecha!!</h1></p>");
                }
                out.println("</div>");
                out.println(formulario(request));
                out.println("<div>");
                
            }
            //si no hay ningun error se procede a mostrar los datos
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
                                            out.println("<p>"+parametro+": "+request.getParameter(parametro)+"</p>");}
                                        }
                                }
                    
                }
                out.println("</div>");
                out.println("<a href='/20160927_AdrianAlonso/index.html'><button type=\"button\">&Iacute;ndice</button></a>");
                
            }
            out.println(pie());
        }
    }
    //este metodo comprueba que la fecha introducida sea valida, y devuelve un true si no lo es para activar el error
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
           sb.append("<form action=\"FormErrores\">"
            +"<h3>Informaci&oacute;n personal</h3>"
            +"<p>*Nombre: <input type=\"text\" name=\"Nombre\" value=\""+request.getParameter("Nombre")+"\"></p>"
            +"<p>Apellidos: <input type=\"text\" name=\"Apellidos\" value=\""+request.getParameter("Apellidos")+"\"></p>");
                   if("Hombre".equals(request.getParameter("Sexo"))){
            sb.append("<p>Sexo: <input type=\"radio\" name=\"Sexo\" value=\"Hombre\" checked>Hombre"
                +"<input type=\"radio\" name=\"Sexo\" value=\"Mujer\">Mujer</p>");}
                   
                   if("Mujer".equals(request.getParameter("Sexo"))){
                       sb.append("<p>Sexo: <input type=\"radio\" name=\"Sexo\" value=\"Hombre\">Hombre"
                +"<input type=\"radio\" name=\"Sexo\" value=\"Mujer\" checked>Mujer</p>");}
                   
           sb.append("<p> Fecha de nacimiento: <input type=\"number\" name=\"Dia\" min=\"1\" max=\"31\" value=\""+request.getParameter("Dia")+"\">/"
                +"<input type=\"number\" name=\"Mes\" min=\"1\" max=\"12\" value=\""+request.getParameter("Mes")+"\">/"
                +"<input type=\"number\" name=\"Anio\" min=\"1900\" max=\"2016\" value=\""+request.getParameter("Anio")+"\"></p>"
            +"<br/>"
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
            +"<input type=\"submit\" value=\"Enviar\">"
            +"<a href='/20160927_AdrianAlonso/html/htmlformconerrores.html'><button type=\"button\">Limpiar</button></a>"
            +"</form>");
           return sb.toString();
    }
    //el metodo cabecera y pie simplemente sirven para ahorrarse out.prints en el metodo principal
    public String cabecera(){
        StringBuilder sb= new StringBuilder();
        sb.append("<!DOCTYPE html>"
            +"<html>"
            +"<head>"
            +"<title>Formulario con errores</title>"
            +"<link rel=\"stylesheet\" href=\"/20160927_AdrianAlonso/css/estformerr.css\">"
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
