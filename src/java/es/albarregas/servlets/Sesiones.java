/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.servlets;

import es.albarregas.beans.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Adrian
 */
public class Sesiones extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Sesiones</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form action=\"Sesiones\" method=\"post\">");
            if (!"Enviar".equals(request.getParameter("enviar"))&&!"Continuar".equals(request.getParameter("continuar"))){
            out.println("<fieldset>");
            out.println("<legend>Usuario 1</legend>");
            out.println("<p>Nombre: <input type=\"text\" name=\"nombre1\"></p>");
            out.println("<p>Identificacion: <input type=\"text\" name=\"id1\"></p>");
            out.println("<p>Sueldo: <input type=\"text\" name=\"sueldo1\"></p>");
            out.println("</fieldset>");
            out.println("<fieldset>");
            out.println("<legend>Usuario 2</legend>");
            out.println("<p>Nombre: <input type=\"text\" name=\"nombre2\"></p>");
            out.println("<p>Identificacion: <input type=\"text\" name=\"id2\"></p>");
            out.println("<p>Sueldo: <input type=\"text\" name=\"sueldo2\"></p>");
            out.println("</fieldset>");
            out.println("<fieldset>");
            out.println("<legend>Usuario 3</legend>");
            out.println("<p>Nombre: <input type=\"text\" name=\"nombre3\"></p>");
            out.println("<p>Identificacion: <input type=\"text\" name=\"id3\"></p>");
            out.println("<p>Sueldo: <input type=\"text\" name=\"sueldo3\"></p>");
            out.println("</fieldset>");
            out.println("<input type=\"submit\" name=\"enviar\" value=\"Enviar\">");
            }else{
            if ("Enviar".equals(request.getParameter("enviar"))){
                ArrayList<Usuario> usuarios= new ArrayList();
                HttpSession sesion = request.getSession();
                Usuario usuario = new Usuario();
                usuario.setId(Integer.parseInt(request.getParameter("id1")));
                usuario.setNombre(request.getParameter("nombre1"));
                usuario.setSueldo(Double.parseDouble(request.getParameter("sueldo1")));
                usuarios.add(usuario);
                usuario.setId(Integer.parseInt(request.getParameter("id2")));
                usuario.setNombre(request.getParameter("nombre2"));
                usuario.setSueldo(Double.parseDouble(request.getParameter("sueldo2")));
                usuarios.add(usuario);
                usuario.setId(Integer.parseInt(request.getParameter("id3")));
                usuario.setNombre(request.getParameter("nombre3"));
                usuario.setSueldo(Double.parseDouble(request.getParameter("sueldo3")));
                usuarios.add(usuario);
                sesion.setAttribute("Usuarios", usuarios);
                out.println("<h1>Datos Enviados</h1>");
                out.println("<input type=\"submit\" name=\"continuar\" value=\"Continuar\">");
                
            }else{
            if("Continuar".equals(request.getParameter("continuar"))){
                HttpSession sesion =request.getSession(true);
                ArrayList<Usuario> usuarios=(ArrayList)sesion.getAttribute("usuarios");
                out.println("<h3>Datos de usuario: </h3>");
                Iterator<Usuario> it= usuarios.iterator();
                int i=0;
                while (it.hasNext()){
                    Usuario usuario = it.next();
                    out.println("<p>Usuario "+ ++i +"</p>");
                    out.println("<ul>");
                    out.println("<li>Nombre: "+ usuario.getNombre()+"</li>");
                    out.println("<li>ID: "+ usuario.getId()+"</li>");
                    out.println("<li>Sueldo: "+ usuario.getSueldo()+"</li>");
                    out.println("</ul>");
                }
                sesion.invalidate();
                out.println("<a href=\"index.html\"><button type=\"button\">&Iacute;ndice</button></a>");
            }}}
            out.println("</form>");
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
