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
@WebServlet(name = "SvAgregarContacto", urlPatterns = {"/SvAgregarContacto"})
public class SvAgregarContacto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

// Se crean dos instancias de la clase Directorio
Directorio listaContactos = new Directorio();
Directorio listaContactoRepetido = new Directorio();

// Método principal para manejar solicitudes GET
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // Se procesa la solicitud
        processRequest(request, response);
        // Se imprime un mensaje en la consola para indicar que se está ejecutando el método para visualizar un contacto
        System.out.println("Corriendo método para visualizar contacto");

        // Se obtiene el parámetro "codigo" de la solicitud GET, que probablemente sea el ID del contacto que se desea visualizar
        String id = request.getParameter("codigo");

        // Se imprime el ID en la consola (para propósitos de depuración)
        System.out.println(id);

        // Se obtiene el contexto del servlet
        ServletContext context = getServletContext();

        try {
            // Se intenta leer los contactos del archivo utilizando la clase Serializacion y se asigna a la lista de contactos
            listaContactos = Serializacion.leerArchivoContactos(context);
        } catch (ClassNotFoundException ex) {
            // Si ocurre una excepción de ClassNotFoundException al leer los contactos, se registra el error en la consola
            Logger.getLogger(SvAgregarContacto.class.getName()).log(Level.SEVERE, null, ex);
        }

        String datos;

        // Se llama al método Visualizar del objeto listaContactos para obtener los datos del contacto con el ID proporcionado
        datos = listaContactos.Visualizar(listaContactos, Integer.parseInt(id));

        // Se configura el tipo de contenido de la respuesta como "text/html"
        response.setContentType("text/html");
        // Se escribe la información del contacto en el cuerpo de la respuesta
        response.getWriter().write(datos);
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        // Se procesa la solicitud
        processRequest(request, response);
        // Se imprime un mensaje en la consola para indicar que se está ejecutando el método para agregar un contacto
        System.out.println("Corriendo método de agregar contacto");

        // Obtener el contexto del servlet
        ServletContext context = getServletContext();

        try {
            // Se intenta leer los contactos del archivo utilizando la clase Serializacion y se asigna a la lista de contactos repetidos
            listaContactoRepetido = Serializacion.leerArchivoContactos(context);
            // Si la lista de contactos es nula, se inicializa una nueva lista de contactos
            if (listaContactos == null) {
                listaContactos = new Directorio();
            }
        } catch (ClassNotFoundException ex) {
            // Si ocurre una excepción de ClassNotFoundException al leer los contactos, se registra el error en la consola
            Logger.getLogger(SvAgregarContacto.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            // Se intenta leer los contactos del archivo utilizando la clase Serializacion y se asigna a la lista de contactos
            listaContactos = Serializacion.leerArchivoContactos(context);
            // Si la lista de contactos es nula, se inicializa una nueva lista de contactos
            if (listaContactos == null) {
                listaContactos = new Directorio();
            }
        } catch (ClassNotFoundException ex) {
            // Si ocurre una excepción de ClassNotFoundException al leer los contactos, se registra el error en la consola
            Logger.getLogger(SvAgregarContacto.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Se obtienen los parámetros del formulario de la solicitud POST, que incluyen información sobre el nuevo contacto a agregar
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String celular = request.getParameter("celular");
        String direccion = request.getParameter("direccion");
        String email = request.getParameter("correo");

        // Se encuentra el ID mayor entre los contactos existentes y se incrementa en uno para asignarlo al nuevo contacto
        int longitud = listaContactos.encontrarIdMayor();
        longitud = longitud + 1;

        String Repetido = "";

        try {
            // Se intenta agregar el nuevo contacto a la lista de contactos
            listaContactos.agregarContacto(longitud, nombre, apellido, celular, direccion, email);
            // Se verifica si el nuevo contacto es repetido llamando al método contactoRepetido
            Repetido = listaContactoRepetido.contactoRepetido(longitud, nombre, apellido, celular, direccion, email);
        } catch (ContactoRepetidoException ex) {
            // Si se produce una excepción de ContactoRepetidoException, se registra el error en la consola
            Logger.getLogger(SvAgregarContacto.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Se imprime la lista de contactos en la consola (para propósitos de depuración)
        System.out.println(listaContactos);

        // Se escriben los contactos en el archivo utilizando la clase Serializacion
        Serializacion.escribirArchivoContactos(listaContactos, context);

        // Se redirige a la página index.jsp dependiendo de si el contacto es repetido o no
        if (Repetido.equals("si")) {
            // Si el contacto es repetido, se imprime un mensaje en la consola y se establece un atributo de sesión
            System.out.println("El contacto ya existe.");
            HttpSession session = request.getSession();
            session.setAttribute("repetido", "si");
            response.sendRedirect("index.jsp");
        } else {
            // Si el contacto no es repetido, se establece un atributo de sesión y se redirige a index.jsp
            HttpSession session = request.getSession();
            session.setAttribute("repetido", "no");
            response.sendRedirect("index.jsp");
        }
        // Se imprime en la consola si el contacto es repetido o no
        System.out.println(Repetido);
    }
}
