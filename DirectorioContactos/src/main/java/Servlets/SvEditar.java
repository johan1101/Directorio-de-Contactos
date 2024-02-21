/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Clases.Directorio;
import Clases.Serializacion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Johan Ordoñez
 */
@WebServlet(name = "SvEditar", urlPatterns = {"/SvEditar"})
public class SvEditar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    Directorio listaContactos = new Directorio();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
                System.out.println("Corriendo metodo para editar contacto");
        
        String id = request.getParameter("codigo");
        
        System.out.println(id);
        
                // Obtener el contexto del servlet
        ServletContext context = getServletContext();
        
        try {
            listaContactos = Serializacion.leerArchivoContactos(context);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SvAgregarContacto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String datos;
        
        datos = listaContactos.Editar(listaContactos, Integer.parseInt(id));
        
         response.setContentType("text/html");
         response.getWriter().write(datos);

    }
}
