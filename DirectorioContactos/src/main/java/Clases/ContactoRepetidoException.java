/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Johan Ordoñez
 */
public class ContactoRepetidoException extends Exception {
    
     /**
     * Constructor de la excepci�n
     * @param nombreContacto es el nombre del contacto que se intent� agregar
     */
    public ContactoRepetidoException( String nombreContacto )
    {
        super( "Ya existe un contacto con ese nombre: " + nombreContacto );
    }
}
