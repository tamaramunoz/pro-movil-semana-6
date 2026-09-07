# Tarea 6: El manejo de datos: Android

Este repositorio contiene la aplicación móvil desarrollada para la Semana 6 de la asignatura **Herramientas de Programación Móvil**.

## Objetivo de la Semana
El objetivo principal de esta unidad fue examinar los procedimientos de manejo de datos en Android, considerando el uso de bases de datos relacionales como SQLite y proveedores de contenidos (Content Providers) para asegurar la persistencia de la información y permitir su intercambio seguro con otras aplicaciones.

## Detalles del Proyecto
* **Lenguaje:** Kotlin.
* **Gestor de Base de Datos:** SQLite utilizando la clase auxiliar `SQLiteOpenHelper` estructurada en paquetes especializados para entidades y acceso a datos.
* **Intercambio de Datos:** Implementación de un Proveedor de Contenidos (*Content Provider*) con su respectiva clase de contrato y definición de URIs para exponer los registros hacia componentes externos.
* **Resultado:** Desarrollo de un sistema de registro de infracciones para la Municipalidad de Pelotillehue. La aplicación permite almacenar de manera persistente el RUT del inspector, nombre del local comercial, dirección y la infracción cometida, generando y mostrando en pantalla un folio único autoincremental, además de posibilitar la modificación de los datos y el listado de las infracciones ingresadas.

## Cómo ejecutar localmente
1. Clonar este repositorio.
2. Abrir la carpeta del proyecto utilizando **Android Studio**.
3. Iniciar un emulador virtual desde el **Device Manager** o conectar un dispositivo móvil físico habilitando la depuración por USB.
4. Presionar el botón **Run** en la barra superior para compilar e instalar la aplicación en el dispositivo.
5. Nota para depuración: Para visualizar el comportamiento de la base de datos y la gestión de URIs, puedes utilizar la herramienta App Inspection o Logcat dentro del entorno de desarrollo.

## Desarrollado por:
- Tamara Muñoz