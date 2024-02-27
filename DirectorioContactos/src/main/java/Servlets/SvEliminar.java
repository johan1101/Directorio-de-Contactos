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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Johan Ordoñez - Esneyder - Alejandro
 */
@WebServlet(name = "SvEliminar", urlPatterns = {"/SvEliminar"})
public class SvEliminar extends HttpServlet {

    Directorio listaContactos = new Directorio();

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

     // Se imprime un mensaje en la consola para indicar que se está ejecutando el método para eliminar un contacto
     System.out.println("Corriendo método para eliminar un contacto");

     // Se obtiene el parámetro "id" del formulario de la solicitud POST, que contiene el ID del contacto a eliminar
     String id = request.getParameter("id");
     // Si el ID no es nulo, se eliminan los símbolos y las tildes del ID para un procesamiento limpio
     if (id != null) {
         id = id.replaceAll("[^\\p{ASCII}]", "");
     }

     // Se imprime el ID del contacto a eliminar en la consola (para propósitos de depuración)
     System.out.println(id);

     // Obtener el contexto del servlet
     ServletContext context = getServletContext();

     try {
         // Se intenta leer los contactos del archivo utilizando la clase Serializacion y se asigna a la lista de contactos
         listaContactos = Serializacion.leerArchivoContactos(context);
     } catch (ClassNotFoundException ex) {
         // Si ocurre una excepción de ClassNotFoundException al leer los contactos, se registra el error en la consola
         Logger.getLogger(SvEliminar.class.getName()).log(Level.SEVERE, null, ex);
     }

     // Se elimina el contacto con el ID especificado de la lista de contactos
     listaContactos.eliminarContacto(id);

     // Se escriben los contactos actualizados en el archivo utilizando la clase Serializacion
     Serializacion.escribirArchivoContactos(listaContactos, context);

     // Se obtiene la sesión y se invalida para eliminar todas las variables de sesión
     HttpSession session = request.getSession(false);
     if (session != null) {
         session.invalidate();
     }

     // Se redirige a la página index.jsp después de eliminar el contacto
     response.sendRedirect("index.jsp");
    }

}
