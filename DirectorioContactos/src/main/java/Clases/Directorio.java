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
 * @author Johan Ordoñez - Esneyder - Alejandro
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
     * Obtiene el contacto raíz del árbol.
     *
     * @return el contacto raíz del árbol
     */
    public Contacto getContactoRaiz() {
        return contactoRaiz;
    }

    /**
     * Establece el contacto raíz del árbol.
     *
     * @param contactoRaiz el contacto raíz a establecer
     */
    public void setContactoRaiz(Contacto contactoRaiz) {
        this.contactoRaiz = contactoRaiz;
    }
    
    

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
     * Agrega un nuevo contacto al árbol.
     *
     * @param id        el ID del contacto
     * @param nombre    el nombre del contacto
     * @param apellido  el apellido del contacto
     * @param celular   el número de celular del contacto
     * @param direccion la dirección del contacto
     * @param email     el correo electrónico del contacto
     * @throws ContactoRepetidoException si el contacto ya existe en el árbol
     */
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
     * Verifica si un contacto ya existe en el árbol.
     *
     * @param id        el ID del contacto
     * @param nombre    el nombre del contacto
     * @param apellido  el apellido del contacto
     * @param celular   el número de celular del contacto
     * @param direccion la dirección del contacto
     * @param email     el correo electrónico del contacto
     * @return "si" si el contacto está repetido, "no" si no está repetido
     * @throws ContactoRepetidoException si ocurre un error al verificar la repetición del contacto
     */
    public String contactoRepetido(int id, String nombre, String apellido, String celular, String direccion, String email) throws ContactoRepetidoException {
        Contacto nuevoContacto = new Contacto(id, nombre, apellido, celular, direccion, email, null, null);
        if (contactoRaiz == null) {
            return "no";
        } else {
            return contactoRaiz.contactoR(nuevoContacto);
        }
    }

    /**
     * Muestra el árbol de contactos en forma de tabla HTML.
     *
     * @param listaContacto el directorio de contactos
     * @param context       el contexto del servlet
     * @return una cadena que representa la tabla HTML del árbol de contactos
     * @throws IOException            si ocurre un error de entrada o salida
     * @throws ClassNotFoundException si no se encuentra la clase durante la deserialización
     */
    public String mostrarArbol(Directorio listaContacto, ServletContext context) throws IOException, ClassNotFoundException {
        StringBuilder resultado = new StringBuilder();
        if (listaContacto != null && listaContacto.contactoRaiz != null) {
            // Recorre el árbol binario en orden (inorden)
            resultado.append(recorrerArbol(listaContacto.contactoRaiz, context));
        }
        return resultado.toString();
    }

    
    /**
     * Recorre el árbol de contactos en orden (inorden) y genera una tabla HTML con los detalles de cada contacto.
     *
     * @param raiz    el nodo raíz del subárbol a recorrer
     * @param context el contexto del servlet
     * @return una cadena que representa una tabla HTML con los detalles de cada contacto
     * @throws IOException            si ocurre un error de entrada o salida
     * @throws ClassNotFoundException si no se encuentra la clase durante la deserialización
     */
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
            resultado.append("<a href='#' type='button' class='btn btn-outline-danger' data-bs-toggle='modal' data-bs-target='#eliminar' onclick='modalEliminar(\"" + raiz.getNombre() + "\")'><i class='fa-solid fa-trash'></i></a></td>");

            resultado.append("</tr>");

            // Recorre el subárbol derecho
            resultado.append(recorrerArbol(raiz.getDer(), context));
        }
        return resultado.toString();
    }

    /**
     * Recorre el árbol de contactos para visualizar un contacto específico por su ID.
     *
     * @param listaContacto el directorio de contactos
     * @param id            el ID del contacto a visualizar
     * @return una cadena que representa los detalles del contacto solicitado
     */
    public String Visualizar(Directorio listaContacto, int id) {
        StringBuilder resultado = new StringBuilder();
        if (listaContacto != null && listaContacto.contactoRaiz != null) {
            // Recorre el árbol binario en orden (inorden)
            resultado.append(recorrerArbolVisualizar(listaContacto.contactoRaiz, id));
        }
        return resultado.toString();
    }

     /**
     * Recorre el árbol de contactos para visualizar un contacto específico por su ID, generando una vista con los detalles del contacto.
     *
     * @param raiz el nodo raíz del subárbol a recorrer
     * @param id   el ID del contacto a visualizar
     * @return una cadena que representa una vista HTML con los detalles del contacto solicitado
     */
    private String recorrerArbolVisualizar(Contacto raiz, int id) {
        StringBuilder resultado = new StringBuilder();
        if (raiz != null) {
            // Recorre el subárbol izquierdo
            resultado.append(recorrerArbolVisualizar(raiz.getIzq(), id));

            // Agrega los detalles del contacto actual si coincide con el ID proporcionado
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

    /**
     * Recorre el árbol binario de contactos y cuenta la cantidad de usuarios.
     *
     * @return el número total de usuarios encontrados en el árbol
     */
    public int contarUsuarios() {
        contadorUsuarios = 0; // Inicializar el contador
        contarUsuarios(contactoRaiz); // Llamar al método auxiliar para iniciar el recorrido
        return contadorUsuarios; // Devolver el total de usuarios encontrados
    }

    /**
     * Método auxiliar recursivo para recorrer el árbol binario y contar usuarios.
     *
     * @param raiz el nodo raíz del subárbol a recorrer
     */
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

    /**
     * Elimina un contacto del árbol binario por su nombre y actualiza el número total de contactos.
     *
     * @param nombre el nombre del contacto a eliminar
     */
    public void eliminarContacto(String nombre) {
        contactoRaiz = contactoRaiz.eliminar(nombre);
        numContactos--;
    }

     /**
     * Obtiene el nodo de reemplazo para eliminar un nodo del árbol binario.
     *
     * @param nodoReemp el nodo que se eliminará
     * @return el nodo de reemplazo para el nodo que se eliminará
     */
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

    /**
     * Encuentra el ID más grande presente en el árbol binario de contactos.
     *
     * @return el ID más grande encontrado, o 0 si el árbol está vacío
     */
    public int encontrarIdMayor() {
        if (contactoRaiz == null) {
            // Si el árbol está vacío, retorna un valor que indique que no hay ningún ID
            return 0;
        } else {
            // Llama a un método auxiliar para encontrar el ID mayor comenzando desde la raíz
            return encontrarIdMayor(contactoRaiz, contactoRaiz.getId());
        }
    }

    /**
     * Método auxiliar recursivo para encontrar el ID más grande en el árbol binario de contactos.
     *
     * @param nodo el nodo actual que se está evaluando
     * @param maximoActual el valor máximo actual encontrado hasta este punto
     * @return el ID más grande encontrado
     */
    private int encontrarIdMayor(Contacto nodo, int maximoActual) {
        if (nodo != null) {
            // Verifica si el ID de este nodo es mayor que el máximo actual
            if (nodo.getId() > maximoActual) {
                maximoActual = nodo.getId();
            }
            // Llama recursivamente a la función para los hijos izquierdos y derechos
            maximoActual = encontrarIdMayor(nodo.getIzq(), maximoActual);
            maximoActual = encontrarIdMayor(nodo.getDer(), maximoActual);
        }
        return maximoActual;
    }
}
