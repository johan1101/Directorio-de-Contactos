/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Clases.Contacto;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Johan Ordoñez
 */
public class Directorio implements Serializable {

    //------------------------------------------------------
    // Atributos
    //------------------------------------------------------
    /**
     * Raiz del arbol de contactos presentes en el directorio
     */
    private Contacto contactoRaiz;

    /**
     * Numero de contactos presentes en el directorio
     */
    private int numContactos;

    /**
     * Crea el directorio sin ningún contacto
     */
    public Directorio() {
        contactoRaiz = null;
        numContactos = 0;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
    /**
     * Agrega un contacto al directorio <br>
     * <b>post: </b>El contacto ha sido agregado al directorio.
     *
     * @param nom nombre del contacto - nom != null
     * @param tel tel�fono del contacto
     * @param dir direcci�n del contacto
     * @param email direcci�n electr�nica del contacto
     * @throws ContactoRepetidoException cuando ya existe un contacto con ese
     * nombre
     */
    public void agregarContacto(int id, String nombre, String apellido, String celular, String direccion, String email) throws ContactoRepetidoException {
        Contacto c = new Contacto(id, nombre, apellido, celular, direccion, email, null, null);
        if (contactoRaiz == null) {
            contactoRaiz = c;
        } else {
            contactoRaiz.insertar(c);
        }
        numContactos++;
    }

    /**
     * Retorna el n�mero de contactos que est�n en el directorio
     *
     * @return n�mero de contactos presentes en el �rbol
     */
    public int darPeso() {
        return contactoRaiz == null ? 0 : contactoRaiz.darPeso();
    }

    public String mostrarArbol(Directorio listaContacto) {
        StringBuilder resultado = new StringBuilder();
        if (listaContacto != null && listaContacto.contactoRaiz != null) {
            // Recorre el árbol binario en orden (inorden)
            resultado.append(recorrerArbol(listaContacto.contactoRaiz));
        }
        return resultado.toString();
    }

    private String recorrerArbol(Contacto raiz) {
        StringBuilder resultado = new StringBuilder();
        if (raiz != null) {
            // Recorre el subárbol izquierdo
            resultado.append(recorrerArbol(raiz.getIzq()));

            // Agrega los detalles del contacto actual
            resultado.append("<tr>");
            resultado.append("<td>" + raiz.getId() + "</td>");
            resultado.append("<td>" + raiz.getNombre() + "</td>");
            resultado.append("<td>" + raiz.getApellido() + "</td>");
            resultado.append("<td>" + raiz.getCelular() + "</td>");
            resultado.append("<td>" + raiz.getDireccion() + "</td>");
            resultado.append("<td>" + raiz.getEmail() + "</td>");
            resultado.append("<td><a href='#' type='button' class='btn btn-outline-primary' onclick='visualizar(" + raiz.getId() + ")' data-nombre=" + raiz.getId() + "><i class='fa-solid fa-eye'></i></a>");
            resultado.append("<a href='#' type='button' class='btn btn-outline-success' data-bs-toggle='modal' data-bs-target='#editar' data-nombre=" + raiz.getId() + "><i class='fa-solid fa-pen-clip'></i></a>");
            resultado.append("<a href='#' type='button' class='btn btn-outline-danger' data-bs-toggle='modal' data-bs-target='#eliminar' data-nombre=" + raiz.getId() + "><i class='fa-solid fa-trash'</i></a></td>");

            resultado.append("</tr>");

            // Recorre el subárbol derecho
            resultado.append(recorrerArbol(raiz.getDer()));
        }
        return resultado.toString();
    }

    public String Visualizar(Directorio listaContacto, int id) {
        StringBuilder resultado = new StringBuilder();
        if (listaContacto != null && listaContacto.contactoRaiz != null) {
            // Recorre el árbol binario en orden (inorden)
            resultado.append(recorrerArbolVisualizar(listaContacto.contactoRaiz, id));
        }
        return resultado.toString();
    }

    private String recorrerArbolVisualizar(Contacto raiz, int id) {
        StringBuilder resultado = new StringBuilder();
        if (raiz != null) {
            // Recorre el subárbol izquierdo
            resultado.append(recorrerArbolVisualizar(raiz.getIzq(), id));

            // Agrega los detalles del contacto actual
            if (raiz.getId() == id) {

                resultado.append("<h2>Información del contacto</h2>");
                resultado.append("<hr>");
                resultado.append("<div class=\"row\">");
                resultado.append("<div class=\"col\">");
                resultado.append("<div class=\"form-element\">");
                resultado.append("<label class='titu' for=\"nombre\">Nombre</label>");
                resultado.append("<p class='dato'>" + raiz.getNombre() + "</p>");
                resultado.append("<hr class='linea'>");
                resultado.append("</div>");
                resultado.append("</div>");
                resultado.append("<div class=\"col\">");
                resultado.append("<div class=\"form-element\">");
                resultado.append("<label class='titu' for=\"apellido\">Apellido</label>");
                resultado.append("<p class='dato'>" + raiz.getApellido() + "</p>");
                resultado.append("<hr class='linea'>");
                resultado.append("</div>");
                resultado.append("</div>");
                resultado.append("</div>");
                resultado.append("<div class=\"row\">");
                resultado.append("<div class=\"col\">");
                resultado.append("<div class=\"form-element\">");
                resultado.append("<label class='titu' for=\"celular\">Celular</label>");
                resultado.append("<p class='dato'>" + raiz.getCelular() + "</p>");
                resultado.append("<hr class='linea'>");
                resultado.append("</div>");
                resultado.append("</div>");
                resultado.append("<div class=\"col\">");
                resultado.append("<div class=\"form-element\">");
                resultado.append("<label class='titu' for=\"direccion\">Dirección</label>");
                resultado.append("<p class='dato'>" + raiz.getDireccion() + "</p>");
                resultado.append("<hr class='linea'>");
                resultado.append("</div>");
                resultado.append("</div>");
                resultado.append("</div>");
                resultado.append("<div class=\"row\">");
                resultado.append("<div class=\"col\">");
                resultado.append("<div class=\"form-element\">");
                resultado.append("<label class='titu' for=\"correo\">Correo electrónico</label>");
                resultado.append("<p class='dato'>" + raiz.getEmail() + "</p>");
                resultado.append("<hr class='linea'>");
                resultado.append("</div>");
                resultado.append("</div>");
                resultado.append("</div>");
                resultado.append("<div class=\"row\">");
                resultado.append("<div class=\"col\">");
                resultado.append("</div>");
                resultado.append("</div>");
                resultado.append("</div>");

            }

            // Recorre el subárbol derecho
            resultado.append(recorrerArbolVisualizar(raiz.getDer(), id)); 
        }
        return resultado.toString();
    }
}
