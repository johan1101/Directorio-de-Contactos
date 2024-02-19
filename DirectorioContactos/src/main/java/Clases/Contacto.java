/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.Serializable;
import java.util.ArrayList;

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
    
    /**
     * Inserta un nuevo contacto al �rbol que comienza en este nodo.
     * @param nuevo el el nuevo contacto que se va a insertar - nuevo != null
     * @throws ContactoRepetidoException se lanza esta excepci�n si el contacto que se quiere agregar ya est� en el directorio
     */
    public void insertar( Contacto nuevo ) throws ContactoRepetidoException
    {
        if( compareTo( nuevo ) == 0 )
            throw new ContactoRepetidoException( nuevo.nombre );

        if( compareTo( nuevo ) > 0 )
        {
            // Debe agregar el nuevo contacto por el sub�rbol izquierdo
            if( izq == null )
                izq = nuevo;
            else
                izq.insertar( nuevo );
        }
        else
        {
            // Debe agregar el nuevo contacto por el sub�rbol derecho
            if( der == null )
                der = nuevo;
            else
                der.insertar( nuevo );
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
}
