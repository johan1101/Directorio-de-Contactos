/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Clases.ContactoRepetidoException;
import Clases.Directorio;
import Clases.Serializacion;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Johan Ordoñez
 */
@WebServlet(name = "SvAgregarContacto", urlPatterns = {"/SvAgregarContacto"})
public class SvAgregarContacto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    Directorio listaContactos = new Directorio();
    Directorio listaContactoRepetido = new Directorio();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        System.out.println("Corriendo metodo para visualizar contacto");

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

        datos = listaContactos.Visualizar(listaContactos, Integer.parseInt(id));

        response.setContentType("text/html");
        response.getWriter().write(datos);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        System.out.println("Corriendo metodo de agregar contacto");

        // Obtener el contexto del servlet
        ServletContext context = getServletContext();

        try {
            listaContactoRepetido = Serializacion.leerArchivoContactos(context);
            if (listaContactos == null) {
                listaContactos = new Directorio();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SvAgregarContacto.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            listaContactos = Serializacion.leerArchivoContactos(context);
            if (listaContactos == null) {
                listaContactos = new Directorio();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SvAgregarContacto.class.getName()).log(Level.SEVERE, null, ex);
        }

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String celular = request.getParameter("celular");
        String direccion = request.getParameter("direccion");
        String email = request.getParameter("correo");

        int longitud = listaContactos.encontrarIdMayor();

        longitud = longitud + 1;

        String Repetido = "";

        try {
            listaContactos.agregarContacto(longitud, nombre, apellido, celular, direccion, email);
            Repetido = listaContactoRepetido.contactoRepetido(longitud, nombre, apellido, celular, direccion, email);
        } catch (ContactoRepetidoException ex) {
            Logger.getLogger(SvAgregarContacto.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(listaContactos);

        Serializacion.escribirArchivoContactos(listaContactos, context);

        if (Repetido.equals("si")) {
            // Si el contacto es repetido, puedes hacer algo aquí, por ejemplo:
            System.out.println("El contacto ya existe.");
            // Establecer el atributo de solicitud

            // Establecer un valor en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("repetido", "si");

            response.sendRedirect("index.jsp");
            // Luego puedes enviar un parámetro, por ejemplo, a través de una redirección

        } else {
            // Establecer un valor en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("repetido", "no");

            response.sendRedirect("index.jsp");
        }
        System.out.println(Repetido);

    }
}
