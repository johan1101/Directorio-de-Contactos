
## Directorio de contactos

Este es un proyecto de aplicación web desarrollado en Java que permite gestionar un directorio de contactos utilizando un árbol binario para mantener la organización de los mismos.
## Descripción

El proyecto consta de varias clases principales:

- Directorio: Esta clase representa el árbol binario que contiene los contactos. Proporciona métodos para agregar, eliminar, buscar y mostrar los contactos de manera ordenada.

- Contacto: Esta clase define la estructura de un contacto, incluyendo sus atributos como nombre, apellido, teléfono, dirección y correo electrónico. También contiene métodos para acceder y modificar estos atributos.

- Serialización: Clase encargada de la persistencia de datos, permitiendo guardar y cargar los contactos desde y hacia archivos.

El proyecto también incluye dos servlets:

- SvAgregarContacto: Servlet utilizado para agregar nuevos contactos al directorio. Gestiona la lógica de negocio relacionada con la creación de contactos y la interacción con la interfaz de usuario.

- SvEliminar: Servlet encargado de eliminar contactos del directorio. Maneja la lógica asociada a la eliminación de contactos, manteniendo la integridad del árbol binario.

## Funcionalidades principales

- Agregar Contacto: Permite al usuario agregar un nuevo contacto al directorio, asegurando que se cumplan ciertos requisitos como el formato correcto del correo electrónico y el número de teléfono.

- Eliminar Contacto: Permite al usuario eliminar un contacto existente del directorio, manteniendo la organización del árbol binario.

- Visualizar Contacto: Proporciona la capacidad de ver los detalles de un contacto específico, mostrando su nombre, apellido, teléfono, dirección y correo electrónico.

## Integrantes

- Johan Ordoñez
- Esneyder Ibarra
- Alejandro Portilla
