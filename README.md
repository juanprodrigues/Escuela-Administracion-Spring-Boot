# Escuela-Administracion-Spring-Boot
_Este trabajo contiene características importantes, como inicios de sección, validación y registro, envió de correos, relaciones Bidireccionales, Encriptación de contraseñas y asignaciones de roles. Para este último vamos a simular que la aplicación maneja 3 roles, ADMIN, el cual tienen privilegios de CRUD a los demás roles , el rol PROFESOR, donde sus privilegios de administrar enditades como Materias y Estudiantes, y por ultimo tenemos el rol de ESTUDIANTE, el cual solo puede inscribirse a materias_

 _(Al final de esta presentación se observan capturas)_
 
### Pre-requisitos 📋
 
_Clonar repositorio en su computadora._
 
 
```
$ git clone https://github.com/juanprodrigues/Escuela-Administracion-Spring-Boot.git
 
```
 
### Instalación 🔧
 
_Para ejecutar el proyecto se necesitan las siguientes tecnologías._
 
```
Spring Tool Suite 4
Java 8
Maven 3.8.6
MySQL Workbench 8.0
```
 
 
 
## Despliegue 📦
_Antes de desplegar la aplicación se tiene que configurar el archivo application.properties que se encuentra en:_
```
src\main\resources
```
_Se tiene que crear la base de datos y completa con sus credenciales de User y Password de su MySQL , además de una cuenta de correo GMAIL para enviar correos de bienvenida a los usuarios que se registran._
 
_Se corre el proyecto y se puede visualizar el puerto 8080 como se muestra a continuación._
 
```
http://localhost:8080/
```

_Al inicial se puede crear un usuario con privilegios de ESTUDIANTE o se puede manipular desde la base de datos y cambiar el rol a ADMIN O PROFESOR._
 
## Construido con 🛠️
 
_Menciona las herramientas que utilizaste para crear tu proyecto._
 
```
Spring Tool Suite 4
Java 8
Maven 3.8.6
MySQL Workbench 8.0
```
 
 
 
* [Spring Tool Suite 4](https://spring.io/tools) - El framework web usado
* [Java 8 ](https://www.oracle.com/ar/java/technologies/javase/javase8-archive-downloads.html) - Lenguaje principal
* [Maven ](https://maven.apache.org/) - Manejador de dependencias
* [MySQL ](https://dev.mysql.com/downloads/workbench/) - Usado para generar administrar la bases de datos
 
 
## Mejoras para el futuro
_Una posible mejora es que agregar mas consultas(query) y trabajar con imágenes, es decir que cada usuario tenga un foto como avatar._
 
 
 Cualquier sugerencia o recomendación se pueden comunicar al siguiente correo juanprodrigues.33@gmail.com
 
 
## Gracias por llegar hasta el final 🎁
 
* Comenta a otros sobre este proyecto 📢
* Invita una cerveza 🍺 o un café ☕ a alguien de tu equipo.
 
 
 
---
⌨️ con ❤️ por [Juan Pablo Rodriguez](https://github.com/juanprodrigues) 😊
 
 
## Algunas capturas del proyecto
_En la primer figura podemos observar Login donde el user y password las podemos extraer de la base de datos o crear un usuario nuevo como se observa en la segunda imagen._
 
_En la tercer imagen vemos un usuario logueado con permisos de administrador y a vemos que funcionalidades tiene su privilegio._
 
_En la imagen 4 y 5, vemos un usuario logueado  como profesor y una función que comparte con un ADMIN que es crear Materias. Y el imagen 6 vemos un lista que los alumno que están logueados _

_Con respecto al rol de Usuario solo podemos ver como puede dar de alta en las materias que quiere, y no tienen los privilegio que poseen ADMIN y PROFESOR._
 
_Por último tenemos un tabla en la cual podemos visualizar todos los datos y además él tenemos el privilegio de dar de baja un elemento y condicionar la vista que se vio anteriormente._

![alt login](https://i.ibb.co/Wz05rb4/login.png)
![alt registrar](https://i.ibb.co/4sKFSdF/register.png)
![alt admin](https://i.ibb.co/khrPnXt/index-Admin.png)
![alt crearMateria](https://i.ibb.co/3chpKQR/crear-Materia.png)
![alt indexProfesor](https://i.ibb.co/ZmCfYzy/index-Profesor.png)
![alt indexStuden](https://i.ibb.co/brj5Fp7/incionE.png)
![alt listMateria](https://i.ibb.co/nf6K8qg/list-Materias.png)

