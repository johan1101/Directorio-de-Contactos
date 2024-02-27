/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.Serializable;
import java.text.Collator;
import java.util.Locale;

/**
 *
 * @author Johan Ordoñez - Esneyder - Alejandro
 */
public class Contacto implements Comparable, Serializable {

    /**
     * Id del contacto
     */
    private int id;

    /**
     * Nombre del contacto
     */
    private String nombre;

    /**
     * Apellido del contacto
     */
    private String apellido;

    /**
     * Email del contacto
     */
    private String email;

    /**
     * Dirección del contacto
     */
    private String direccion;

    /**
     * Celular del contacto
     */
    private String celular;

    /**
     * Subarbol izquierdo del contacto
     */
    private Contacto izq;

    /**
     * Subarbol derecho del contacto
     */
    private Contacto der;

    /**
     * Crea un contacto con toda la informaci�n b�sica
     *
     * @param elId id de la persona a contactar - id != null
     * @param elNombre nombre de la persona a contactar - nombre != null
     * @param elApellido apellido de la persona a contactar - apellido != null
     * @param elEmail correo electr�nico de contacto
     * @param laDireccion direcci�n de contacto
     * @param elCelular celular del contacto
     */
    public Contacto(int id, String nombre, String apellido, String celular, String direccion, String email, Contacto izq, Contacto der) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.direccion = direccion;
        this.celular = celular;
        this.izq = null;
        this.der = null;
    }

    // -----------------------------------------------------------------
    // M�todo interfaz Comparable
    // -----------------------------------------------------------------
    /**
     * Compara este contacto con otro
     *
     * @param o es el otro contacto con el que se compara
     * @return -1 si este contacto es menor al otro, 0 si son iguales y 1 si
     * este contacto es mayor al otro
     */
    public int compareTo(Object o) {
        Contacto otro = (Contacto) o;
        return nombre.compareToIgnoreCase(otro.nombre);
    }

    public int compareToC(Object o) {
        Contacto otro = (Contacto) o;
        return nombre.compareToIgnoreCase(otro.nombre);
    }

/**
     * Obtiene el identificador del contacto.
     *
     * @return el identificador del contacto
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del contacto.
     *
     * @param id el identificador a establecer
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del contacto.
     *
     * @return el nombre del contacto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del contacto.
     *
     * @param nombre el nombre a establecer
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido del contacto.
     *
     * @return el apellido del contacto
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido del contacto.
     *
     * @param apellido el apellido a establecer
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene el correo electrónico del contacto.
     *
     * @return el correo electrónico del contacto
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del contacto.
     *
     * @param email el correo electrónico a establecer
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la dirección del contacto.
     *
     * @return la dirección del contacto
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del contacto.
     *
     * @param direccion la dirección a establecer
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene el número de celular del contacto.
     *
     * @return el número de celular del contacto
     */
    public String getCelular() {
        return celular;
    }

    /**
     * Establece el número de celular del contacto.
     *
     * @param celular el número de celular a establecer
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * Obtiene el hijo izquierdo del contacto en el árbol.
     *
     * @return el hijo izquierdo del contacto
     */
    public Contacto getIzq() {
        return izq;
    }

    /**
     * Establece el hijo izquierdo del contacto en el árbol.
     *
     * @param izq el hijo izquierdo a establecer
     */
    public void setIzq(Contacto izq) {
        this.izq = izq;
    }

    /**
     * Obtiene el hijo derecho del contacto en el árbol.
     *
     * @return el hijo derecho del contacto
     */
    public Contacto getDer() {
        return der;
    }

    /**
     * Establece el hijo derecho del contacto en el árbol.
     *
     * @param der el hijo derecho a establecer
     */
    public void setDer(Contacto der) {
        this.der = der;
    }

    /**
     * Indica si este nodo es una hoja
     *
     * @return true si este nodo es una hoja y false en caso contrario
     */
    public boolean esHoja() {
        return izq == null && der == null;
    }

    /**
     * Inserta un nuevo contacto en el árbol de contactos.
     *
     * @param nuevo el nuevo contacto a insertar
     * @throws ContactoRepetidoException si se intenta insertar un contacto que
     * ya existe en el árbol
     */
    public void insertar(Contacto nuevo) throws ContactoRepetidoException {
        // Compara el nuevo contacto con el contacto actual
        if (compareTo(nuevo) == 0) {
            // Si el nuevo contacto es igual al contacto actual, no se hace nada
        } else if (compareTo(nuevo) > 0) {
            // Si el nuevo contacto es mayor que el contacto actual,
            // se agrega en el subárbol izquierdo
            if (izq == null) {
                // Si el subárbol izquierdo es nulo, se asigna el nuevo contacto como hijo izquierdo
                izq = nuevo;
            } else {
                // Si el subárbol izquierdo no es nulo, se llama recursivamente al método en el subárbol izquierdo
                izq.insertar(nuevo);
            }
        } else {
            // Si el nuevo contacto es menor que el contacto actual,
            // se agrega en el subárbol derecho
            if (der == null) {
                // Si el subárbol derecho es nulo, se asigna el nuevo contacto como hijo derecho
                der = nuevo;
            } else {
                // Si el subárbol derecho no es nulo, se llama recursivamente al método en el subárbol derecho
                der.insertar(nuevo);
            }
        }
    }

    /**
     * Busca un contacto en el árbol para verificar que no este repetido..
     *
     * @param nuevo el nuevo contacto a buscar
     * @return "si" si el contacto ya existe en el árbol, "no" si no se
     * encuentra en el árbol
     * @throws ContactoRepetidoException si ocurre un error durante la búsqueda
     */
    public String contactoR(Contacto nuevo) throws ContactoRepetidoException {
        // Compara el nuevo contacto con el contacto actual
        int comparacion = compareToC(nuevo);

        // Evalúa el resultado de la comparación
        if (comparacion == 0) {
            // Si el resultado es 0, significa que el contacto ya existe en el árbol
            return "si";
        } else if (comparacion > 0 && izq != null) {
            // Si la comparación es mayor que cero y el subárbol izquierdo no es nulo,
            // se llama recursivamente al método en el subárbol izquierdo
            return izq.contactoR(nuevo);
        } else if (comparacion < 0 && der != null) {
            // Si la comparación es menor que cero y el subárbol derecho no es nulo,
            // se llama recursivamente al método en el subárbol derecho
            return der.contactoR(nuevo);
        } else {
            // Si no se encontró el contacto en el árbol, se devuelve "no"
            return "no";
        }
    }

    /**
     * Retorna el contacto que alfab�ticamente corresponde al menor contacto del
     * �rbol que parte de este nodo
     *
     * @return contacto con menor nombre
     */
    public Contacto darMenor() {
        return (izq == null) ? this : izq.darMenor();
    }

    /**
     * Retorna el contacto que alfab�ticamente corresponde al mayor contacto del
     * �rbol que parte de este nodo
     *
     * @return contacto con mayor nombre
     */
    public Contacto darMayor() {
        return (der == null) ? this : der.darMayor();
    }

    /**
     * Elimina un contacto del árbol de contactos.
     *
     * @param unNombre el nombre del contacto a eliminar
     * @return el nodo que se convierte en la nueva raíz del subárbol, después
     * de realizar la eliminación
     */
    public Contacto eliminar(String unNombre) {
        // Crea un comparador de cadenas sensible a la localización
        Collator collator = Collator.getInstance(new Locale("es", "ES"));

        // Compara los nombres de manera sensible a la localización
        // Elimina los símbolos y las tildes del nombre actual
        nombre = nombre.replaceAll("[^\\p{ASCII}]", "");

        // Compara el nombre actual con el nombre a eliminar
        int comparacion = collator.compare(nombre, unNombre);

        if (comparacion == 0) {
            // Si la raíz coincide con el nombre a eliminar
            if (izq == null) {
                // Si el hijo izquierdo es nulo, devuelve el hijo derecho como nueva raíz
                return der;
            } else if (der == null) {
                // Si el hijo derecho es nulo, devuelve el hijo izquierdo como nueva raíz
                return izq;
            } else {
                // Si la raíz tiene ambos hijos
                // Encuentra el sucesor en el subárbol derecho
                Contacto sucesor = der.darMenor();
                // Actualiza los datos de la raíz con los del sucesor
                nombre = sucesor.getNombre();
                apellido = sucesor.getApellido();
                celular = sucesor.getCelular();
                direccion = sucesor.getDireccion();
                email = sucesor.getEmail();
                // Elimina el sucesor del subárbol derecho y lo asigna como nuevo hijo derecho
                der = der.eliminar(sucesor.getNombre());
                return this;
            }
        } else if (comparacion > 0) {
            // Si el nombre a eliminar es menor que el nombre de la raíz, busca en el subárbol izquierdo
            if (izq != null) {
                izq = izq.eliminar(unNombre);
            }
        } else {
            // Si el nombre a eliminar es mayor que el nombre de la raíz, busca en el subárbol derecho
            if (der != null) {
                der = der.eliminar(unNombre);
            }
        }
        // Retorna el nodo actual como raíz del subárbol
        return this;
    }
}
