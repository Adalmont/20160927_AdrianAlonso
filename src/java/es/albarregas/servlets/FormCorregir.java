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
public class FormCorregir extends HttpServlet {

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
           if("Limpiar".equals(request.getParameter("limpiar"))){
               out.println(formulario());
               out.println("<p><input type=\"submit\" name=\"enviar\" value=\"Enviar\">");
               out.println("<input type=\"submit\" name=\"limpiar\" value=\"Limpiar\"></p>");
               out.println("</form>");
               out.println("</div>");
           }
           else{
               if ("Volver".equals(request.getParameter("corregir"))){
                   out.println(formularioDatos(request));
                   out.println("<p><input type=\"submit\" name=\"enviar\" value=\"Enviar\">");
                   out.println("<input type=\"submit\" name=\"limpiar\" value=\"Limpiar\"></p>");
                   out.println("</form>");
                   out.println("</div>");
               }
               else{
                   if ("Enviar".equals(request.getParameter("enviar"))){
                       out.println(mostrarDatos(request));
                       out.println(formularioOculto(request));
                       out.println("<a href='index.html'><button type=\"button\">&Iacute;ndice</button></a>");
                       out.println("</form>");
                       out.println("</div>");
                   }
                   else{
                       out.println(formulario());
                       out.println("<p><input type=\"submit\" name=\"enviar\" value=\"Enviar\">");
                       out.println("<input type=\"submit\" name=\"limpiar\" value=\"Limpiar\"></p>");
                       out.println("</form>");
                       out.println("</div>");
                   }
               }
           }
           out.println(pie());
        }
    }
    //el metodo cabecera y pie simplemente sirven para ahorrarse out.prints en el metodo principal
    public String formulario(){
        StringBuilder sb= new StringBuilder();
        sb.append("<div>"
                + "<form action=\"FormCorregir\" method=\"post\">"
                + "<fieldset>"
                + "<legend>Datos</legend>"
                + "<p>Usuario: <input type=\"text\" name=\"Usuario\"></p>"
                + "<p>Contrase&ntilde;a: <input type=\"password\" name=\"contra\"</p>"
                + "<p>Sexo: <input type=\"radio\" name=\"Sexo\" value=\"Hombre\" checked>Hombre"
                + "<input type=\"radio\" name=\"Sexo\" value=\"Mujer\">Mujer</p>"
                + "<p>Aficiones: <input type=\"checkbox\" name=\"Deporte\">Deporte"
                + "<input type=\"checkbox\" name=\"Lectura\">Lectura"
                + "<input type=\"checkbox\" name=\"Cine\">Cine"
                + "<input type=\"checkbox\" name=\"Viajes\">Viajes"
                + "</p>"
                + "</fieldset>");
        return sb.toString();
    }
    public String formularioDatos(HttpServletRequest request){
        StringBuilder sb= new StringBuilder();
        sb.append("<div>"
                + "<form action=\"FormCorregir\" method=\"post\">"
                + "<fieldset>"
                + "<legend>Datos</legend>"
                + "<p>Usuario: <input type=\"text\" name=\"Usuario\" value=\""+request.getParameter("Usuario")+"\"></p>"
                + "<p>Contrase&ntilde;a: <input type=\"password\" name=\"contra\" value=\""+request.getParameter("contra")+"\"></p>"
                + "<p>Sexo: ");
                  if("Hombre".equals(request.getParameter("Sexo"))){
                       sb.append("<p>Sexo: <input type=\"radio\" name=\"Sexo\" value=\"Hombre\" checked>Hombre"
                        +"<input type=\"radio\" name=\"Sexo\" value=\"Mujer\">Mujer</p>");}
                   
                  if("Mujer".equals(request.getParameter("Sexo"))){
                       sb.append("<p>Sexo: <input type=\"radio\" name=\"Sexo\" value=\"Hombre\">Hombre"
                        +"<input type=\"radio\" name=\"Sexo\" value=\"Mujer\" checked>Mujer</p>");}
                sb.append("<p>Aficiones: ");
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
                + "</fieldset>");
        return sb.toString();
    }
    public String mostrarDatos(HttpServletRequest request){
        StringBuilder sb= new StringBuilder();
        Enumeration <String> parametros= request.getParameterNames();
        String parametro;
        sb.append("<div>"
                + "<form action=\"FormCorregir\" methos=\"post\">"
                + "<h2>Datos:</h2>");
        while (parametros.hasMoreElements()){
            parametro= parametros.nextElement();
            if ("on".equals(request.getParameter(parametro))){
                sb.append("<p>"+parametro+"</p>");
            }
            else{
                if("contra".equals(parametro)){
                    sb.append("<p>Contrase&ntilde;a: "+request.getParameter(parametro)+"</p>");
                }
                else{
                if("enviar".equals(parametro)){
                    sb.append("");
                }
                    else{
                        sb.append("<p>"+parametro+": "+request.getParameter(parametro)+"</p>");
                    }
                }
            }
        }
        sb.append("<p>¿Datos Correctos?</p>"
                + "</form>"
                + "</div>");
        return sb.toString();
    }
    public String formularioOculto(HttpServletRequest request){
        StringBuilder sb= new StringBuilder();
        Enumeration <String> parametros = request.getParameterNames();
        String parametro;
        sb.append("<form action=\"FormCorregir\" method=\"post\">");
        while (parametros.hasMoreElements()){
            parametro=parametros.nextElement();
            sb.append("<input type=\"hidden\" name=\""+parametro+"\" value=\""+request.getParameter(parametro)+"\">");
        }
        sb.append("<input type=\"submit\" name=\"corregir\" value=\"Volver\">");
        return sb.toString();
    }
    public String cabecera(){
        StringBuilder sb= new StringBuilder();
        sb.append("<!DOCTYPE html>"
            +"<html>"
            +"<head>"
            +"<title>Formulario Corregible</title>"
            +"<link rel=\"stylesheet\" href=\"css/estcorregir.css\">"
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
