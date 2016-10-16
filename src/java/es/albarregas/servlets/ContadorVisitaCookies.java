/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Adrian
 */
public class ContadorVisitaCookies extends HttpServlet {

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
            //Primero se crea un array con todas las cookies que halla y una nueva cookie nula
            Cookie[] cookies = request.getCookies();
            Cookie cookie = null;
            /*si el array no es nulo (es decir, ya habia cookies en el navegador) se busca si alguna es la cookie
            de este servlet. Si es asi, se asigna a la variable cookie. si no, se crea*/
             if (cookies!=null){
                for (int i=0;i<cookies.length;i++){
                    if (cookies[i].getName().equals("Contador")){
                        cookie=cookies[i];
                        break;
                    }
                }
            }
            if (cookies==null){
                cookie = new Cookie("Contador", "0");
                cookie.setMaxAge(604800);
            }
            /*Si se pulsa el boton de Limpiar, el valor del contador vuelve a 0, y se escribira la info de la cookie.
            si se pulsa recargar, se recarga la pagina y se le añade 1 al contador de visitas*/
            if ("Limpiar".equals(request.getParameter("limpiar"))){
                cookie.setValue("0");
            }

            else{
            if ("Recargar".equals(request.getParameter("recargar"))){
                int valor=1;
                valor = Integer.parseInt(cookie.getValue())+1;
                String nuevoValor= String.valueOf(valor);
                cookie.setValue(nuevoValor);
            }}
            response.addCookie(cookie);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Contador de Visitas con Cookies</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form action=\"ContadorVisitaCookies\" method=\"post\">");
            out.println("<p>Numero de veces que has visitado esta pagina: "+cookie.getValue()+"</p>");
            if ("Limpiar".equals(request.getParameter("limpiar"))){
                out.println("Datos de la cookie: ");
                out.println("<ul>");
                out.println("<li>Nombre: "+cookie.getName()+"</li>");
                out.println("<li>Comentario: "+cookie.getComment()+"</li>");
                out.println("<li>Dominio: "+cookie.getDomain()+"</li>");
                out.println("<li>Edad: "+cookie.getMaxAge()+"</li>");
                out.println("<li>Clase: "+cookie.getClass()+"</li>");
                out.println("<li>Camino: "+cookie.getPath()+"</li>");
                out.println("<li>Seguro: "+cookie.getSecure()+"</li>");
                out.println("<li>Version: "+cookie.getVersion()+"</li>");
                out.println("</ul>");
            }
            out.println("<input type=\"submit\" name=\"recargar\" value=\"Recargar\">");
            out.println("<input type=\"submit\" name=\"limpiar\" value=\"Limpiar\">");
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
