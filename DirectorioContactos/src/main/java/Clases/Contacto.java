/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;

/**
 *
 * @author Johan Ordoñez
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

    // -----------------------------------------------------------------
    // Metodos
    // -----------------------------------------------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Contacto getIzq() {
        return izq;
    }

    public void setIzq(Contacto izq) {
        this.izq = izq;
    }

    public Contacto getDer() {
        return der;
    }

    public void setDer(Contacto der) {
        this.der = der;
    }
    
      /**
     * Indica si este nodo es una hoja
     * @return true si este nodo es una hoja y false en caso contrario
     */
    public boolean esHoja( )
    {
        return izq == null && der == null;
    }
    
public void insertar(Contacto nuevo) throws ContactoRepetidoException {
    if (compareTo(nuevo) == 0) {
        throw new ContactoRepetidoException(nuevo.nombre);
    } else if (compareTo(nuevo) > 0) {
        // Agregar el nuevo contacto en el subárbol izquierdo
        if (izq == null) {
            izq = nuevo;
        } else {
            izq.insertar(nuevo);
        }
    } else {
        // Agregar el nuevo contacto en el subárbol derecho
        if (der == null) {
            der = nuevo;
        } else {
            der.insertar(nuevo);
        }
    }
}
    
        /**
     * Retorna el n�mero de contactos que hay en el �rbol que comienza en este nodo utilizando un algoritmo iterativo
     * @return n�mero de contactos en el �rbol que comienza en este nodo
     */
    public int darPesoIterativo( )
    {
        int peso = 0;
        ArrayList pila = new ArrayList( );
        Contacto p = this;
        while( p != null )
        {
            peso++;
            if( p.izq != null )
            {
                // Guarda el sub�rbol derecho en la pila, para recuperarlo m�s tarde
                if( p.der != null )
                    pila.add( p.der );

                // Baja por la izquierda
                p = p.izq;
            }
            else if( p.der != null )
            {
                // Baja por la derecha, puesto que no hay sub�rbol izquierdo
                p = p.der;
            }
            else if( pila.size( ) != 0 )
            {
                // Es una hoja, luego debemos sacar de la pila el �ltimo sub�rbol all� almacenado
                p = ( Contacto )pila.get( 0 );
                pila.remove( 0 );
            }
            else
            {
                p = null;
            }
        }
        return peso;
    }
    
        /**
     * Retorna el n�mero de contactos que hay en el �rbol que comienza en este nodo
     * @return n�mero de contactos en el �rbol que comienza en este nodo
     */
    public int darPeso( )
    {
        int p1 = ( izq == null ) ? 0 : izq.darPeso( );
        int p2 = ( der == null ) ? 0 : der.darPeso( );
        return 1 + p1 + p2;
    }
        
        /**
     * Retorna el contacto que alfab�ticamente corresponde al menor contacto del �rbol que parte de este nodo
     * @return contacto con menor nombre
     */
    public Contacto darMenor( )
    {
        return ( izq == null ) ? this : izq.darMenor( );
    }
    
        /**
     * Retorna el contacto que alfab�ticamente corresponde al mayor contacto del �rbol que parte de este nodo
     * @return contacto con mayor nombre
     */
    public Contacto darMayor( )
    {
        return ( der == null ) ? this : der.darMayor( );
    }
    
public Contacto eliminar(String unNombre) {
    Collator collator = Collator.getInstance(new Locale("es", "ES"));

    // Compara los nombres de manera sensible a la localización

    // Elimina los símbolos y las tildes
    nombre = nombre.replaceAll("[^\\p{ASCII}]", "");

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
            // Si la raíz tiene ambos hijos, encuentra el sucesor en el subárbol derecho
            Contacto sucesor = der.darMenor();
            // Actualiza los datos de la raíz con los del sucesor
            nombre = sucesor.getNombre();
            apellido = sucesor.getApellido();
            celular = sucesor.getCelular();
            direccion = sucesor.getDireccion();
            email = sucesor.getEmail();
            // Elimina el sucesor del subárbol derecho
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
    return this;
}


    
}
