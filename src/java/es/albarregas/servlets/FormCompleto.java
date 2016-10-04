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
public class FormCompleto extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Formulario Completo</title>"); 
            out.println("<link rel='stylesheet' href='/20160927/CSS/Estilos.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id='servfor'>");
            out.println("<h3>Datos Personales</h3>");
            out.println("<ul>"
                    + "<li>Usuario: "+request.getParameter("usuario")+"</li>"+
                    "<li>Contraseña: "+request.getParameter("contrasena")+"</li>"
                    + "<li>Edad: "+request.getParameter("edad")+"</li></ul>");
            out.println("<h3>Datos Varios</h3>");
            out.println("<ul>"
                    + "<li>Estado civil: "+request.getParameter("estado")+"</li>"
                    +"<li>Aficciones: <ul>");
            String[] valores = request.getParameterValues("aficciones");
            for (int i=0;i<valores.length;i++){
                out.println("<li>"+valores[i]+"</li>");
            }
            out.println("</ul></li>");
            out.println("<li>Comentarios: "+request.getParameter("comentario")+"</li></ul>");
            out.println("<h3>Medios Tecnologicos</h3>");
            out.println("<ul>"
                    + "<li>Internet: "+request.getParameter("internet")+"</li>"
                    + "<li>Sistema Operativo: "+request.getParameter("sistema")+"</li></ul>");
            out.println("</div>");
            out.println("<a id= 'vuelta' href='/20160927_AdrianAlonso/index.html'>Regreso</a>");
            out.println("</body>");
            out.println("</html>");
        }
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
