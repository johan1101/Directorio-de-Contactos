/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Clases.Contacto;
import java.io.IOException;
import java.io.Serializable;
import javax.servlet.ServletContext;

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
    
    public void agregarContacto(int id, String nombre, String apellido, String celular, String direccion, String email) throws ContactoRepetidoException {
    Contacto nuevoContacto = new Contacto(id, nombre, apellido, celular, direccion, email, null, null);
    if (contactoRaiz == null) {
        contactoRaiz = nuevoContacto;
    } else {
       contactoRaiz.insertar(nuevoContacto);
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

    public String mostrarArbol(Directorio listaContacto, ServletContext context) throws IOException, ClassNotFoundException {
        StringBuilder resultado = new StringBuilder();
        if (listaContacto != null && listaContacto.contactoRaiz != null) {
            // Recorre el árbol binario en orden (inorden)
            resultado.append(recorrerArbol(listaContacto.contactoRaiz, context));
        }
        return resultado.toString();
    }

    private String recorrerArbol(Contacto raiz, ServletContext context) throws IOException, ClassNotFoundException {
        StringBuilder resultado = new StringBuilder();
        if (raiz != null) {
            // Recorre el subárbol izquierdo
            resultado.append(recorrerArbol(raiz.getIzq(), context));

            // Agrega los detalles del contacto actual
            resultado.append("<tr>");
            resultado.append("<td>" + raiz.getId() + "</td>");
            resultado.append("<td>" + raiz.getNombre() + "</td>");
            resultado.append("<td>" + raiz.getApellido() + "</td>");
            resultado.append("<td>" + raiz.getCelular() + "</td>");
            resultado.append("<td>" + raiz.getDireccion() + "</td>");
            resultado.append("<td>" + raiz.getEmail() + "</td>");
            resultado.append("<td><a href='#' type='button' class='btn btn-outline-primary' onclick='visualizar(" + raiz.getId() + ")'><i class='fa-solid fa-eye'></i></a>");
            resultado.append("<a href='#' type='button' class='btn btn-outline-success' data-bs-toggle='modal' data-bs-target='#editar' onclick=''><i class='fa-solid fa-pen-clip'></i></a>");
resultado.append("<a href='#' type='button' class='btn btn-outline-danger' data-bs-toggle='modal' data-bs-target='#eliminar' onclick='modalEliminar(\"" + raiz.getNombre() + "\")'><i class='fa-solid fa-trash'></i></a></td>");

            resultado.append("</tr>");

            // Recorre el subárbol derecho
            resultado.append(recorrerArbol(raiz.getDer(), context));
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

    private int contadorUsuarios; // Variable para contar usuarios

    // Constructor y otros métodos de la clase
    // Método para recorrer el árbol binario y contar usuarios
    public int contarUsuarios() {
        contadorUsuarios = 0; // Inicializar el contador
        contarUsuarios(contactoRaiz); // Llamar al método auxiliar para iniciar el recorrido
        return contadorUsuarios; // Devolver el total de usuarios encontrados
    }

    // Método auxiliar recursivo para recorrer el árbol binario y contar usuarios
    private void contarUsuarios(Contacto raiz) {
        if (raiz != null) {
            // Si el nodo actual no es nulo, es un usuario
            contadorUsuarios++;
            raiz.setId(contadorUsuarios);// Aumentar el contador de usuarios
            // Recorrer recursivamente el subárbol izquierdo
            contarUsuarios(raiz.getIzq());
            // Recorrer recursivamente el subárbol derecho
            contarUsuarios(raiz.getDer());
        }
    }
    
        public void eliminarContacto( String nombre )
    {
        contactoRaiz = contactoRaiz.eliminar( nombre );
        numContactos--;
    }

//    public boolean eliminar(int id) {
//        Contacto auxiliar = contactoRaiz;
//        Contacto padre = contactoRaiz;
//        boolean esHijoIzq = true;
//        while (auxiliar.getId() != id) {
//            padre = auxiliar;
//            if (id < auxiliar.getId()) {
//                esHijoIzq = true;
//                auxiliar = auxiliar.getIzq();
//            } else {
//                esHijoIzq = false;
//                auxiliar = auxiliar.getDer();
//            }
//            if (auxiliar == null) {
//                return false;
//            }
//        }
//        if (auxiliar.getIzq() == null && auxiliar.getDer() == null) {
//            if (auxiliar == contactoRaiz) {
//                contactoRaiz = null;
//            } else if (esHijoIzq) {
//                padre.setDer(null);
//            } else {
//                padre.setDer(null);
//            }
//        } else if (auxiliar.getDer() == null) {
//            if (auxiliar == contactoRaiz) {
//                contactoRaiz = auxiliar.getIzq();
//            } else if (esHijoIzq) {
//                padre.setIzq(auxiliar.getIzq());
//            } else {
//                padre.setDer(auxiliar.getIzq());
//            }
//        } else if (auxiliar.getIzq() == null) {
//            if (auxiliar == contactoRaiz) {
//                contactoRaiz = auxiliar.getDer();
//            } else if (esHijoIzq) {
//                padre.setIzq(auxiliar.getDer());
//            } else {
//                padre.setDer(auxiliar.getIzq());
//            }
//        } else {
//            Contacto reemplazo = obtenerNodoReemplazo(auxiliar);
//            if (auxiliar == contactoRaiz) {
//                contactoRaiz = reemplazo;
//            } else if (esHijoIzq) {
//                padre.setIzq(reemplazo);
//            } else {
//                padre.setDer(reemplazo);
//            }
//            reemplazo.setIzq(auxiliar.getIzq());
//        }
//        return true;
//    }

    public Contacto obtenerNodoReemplazo(Contacto nodoReemp) {
        Contacto reemplazarPadre = nodoReemp;
        Contacto reemplazo = nodoReemp;
        Contacto auxiliar = nodoReemp.getDer();
        while (auxiliar != null) {
            reemplazarPadre = reemplazo;
            reemplazo = auxiliar;
            auxiliar = auxiliar.getIzq();
        }
        if (reemplazo != nodoReemp.getDer()) {
            reemplazarPadre.setIzq(reemplazo.getDer());
            reemplazo.setDer(nodoReemp.getDer());
        }
        return reemplazo;
    }
}
