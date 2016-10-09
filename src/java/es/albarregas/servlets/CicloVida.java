/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Adrian
 */
public class CicloVida extends HttpServlet {
 public void init(ServletConfig config){
        System.out.println("Ejecutando Init");
        
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out= response.getWriter();
        System.out.println("Ejecutando Service");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Ciclo de Vida</title>");      
            out.println("<link rel='stylesheet' href='/20160927_AdrianAlonso/css/estilo.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 id='ciclovida'>Ciclo de Vida</h1>");
            out.println("<a id= 'vuelta' href='index.html'>Regreso</a>");
            out.println("</body>");
            out.println("</html>");
    }
    public void destroy(){
        System.out.println("Ejecutando destroy");
    }

}
