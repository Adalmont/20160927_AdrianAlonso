/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Adrian
 */
public class ContadorVisitasSesion extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /*Se inicia una sesion. Si es creada por primera vez, y por lo tanto no tiene el atributo "valor",
            este se inicializa, usando Integer en vez de int ya que se requiere un objeto.
            Si por otro lado, si que hay un atributo "valor", lo que indica que es una sesion existente,
            se añade 1 al valor del atributo.*/
            HttpSession sesion= request.getSession(true);
            if (sesion.getAttribute("valor")==null){
                Integer valor= 1;
                sesion.setAttribute("valor", valor);
            }
            else{
                Integer nuevoValor= (Integer)sesion.getAttribute("valor")+1;
                sesion.setAttribute("valor", nuevoValor);
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Contador de visitas con sesion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form action=\"ContadorVisitasSesion\" method=\"post\">");
            /*Si se marca la casilla de "invalidar sesion", la sesion se invalida y el contador se pone a 0.*/
            if ("on".equals(request.getParameter("limpiar"))){
                sesion.invalidate();
                out.println("<p><h3>Numero de visitas: 0</p></h3>");
            }
            else{
                out.println("<p><h3>Numero de visitas: "+sesion.getAttribute("valor")+"</h3></p>"); 
            }
            out.println("<input type=\"checkbox\" name=\"limpiar\">Invalidar Sesion");
            out.println("<input type=\"submit\" value=\"Recargar\">");
            out.println("<a href=\"index.html\"><button type=\"button\">&Iacute;ndice</button></a>");
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
