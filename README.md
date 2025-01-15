# LiterAlura - Catálogo de Libros

Bienvenido al proyecto **LiterAlura**, un catálogo de libros donde los usuarios pueden buscar libros, registrarlos en una base de datos y consultar información sobre ellos. La aplicación está construida utilizando **Java**, **Spring Boot** y **PostgreSQL**, y consume la API de **Gutendex**, que ofrece más de 70,000 libros gratuitos.

## Funcionalidades

### 1. **Buscar libro por título**
La persona usuaria puede buscar un libro por su título. Si el libro está disponible en la API de Gutendex, se obtiene información sobre el libro (autor, idioma, número de descargas, etc.) y se registra en la base de datos.

### 2. **Listar libros registrados**
Una vez que los libros están en la base de datos, se puede ver un listado con todos ellos.

### 3. **Listar autores registrados**
Muestra los autores que tienen libros en la base de datos y cuántos libros de cada uno están registrados.

### 4. **Listar autores vivos en un año específico**
Permite ver qué autores estaban vivos en un año determinado, por ejemplo, en 1600.

### 5. **Listar libros por idioma**
La persona usuaria puede filtrar los libros por su idioma (ES, EN, FR, PT). La aplicación mostrará los libros registrados en el idioma elegido.

### Manejo de Errores:
- Si no se encuentra un libro al buscar por título, se muestra un mensaje de error.
- No es posible registrar el mismo libro más de una vez en la base de datos.

## Tecnologías Utilizadas

- **Java 17**: Lenguaje de programación.
- **Spring Boot**: Framework para la creación de aplicaciones en Java.
- **Spring Data JPA**: Para la interacción con la base de datos.
- **PostgreSQL**: Base de datos relacional.
- **Gutendex API**: API que proporciona información sobre libros del Proyecto Gutenberg.

## ¿Cómo Funciona?

1. **Búsqueda de libros**: El usuario ingresa el título de un libro. Si existe en la API de Gutendex, se obtiene su información (autor, idioma, número de descargas) y se guarda en la base de datos.
   
2. **Consultas**: Desde la aplicación de consola, puedes listar los libros registrados, los autores, ver qué autores estaban vivos en un año determinado o filtrar los libros por idioma.

3. **Errores y validaciones**: Si un libro ya está registrado, la aplicación no lo agregará de nuevo. Además, se notificará si no se encuentra un libro al buscar por su título.

## ¿Cómo Ejecutarlo?

Para ejecutar el proyecto en tu máquina, sigue estos pasos:

1. **Clona el repositorio**:

   ```bash
   git clone https://github.com/samhdez03/literAlura.git
   ```

2. **Configura la base de datos PostgreSQL**:

   Crea una base de datos en PostgreSQL llamada `literalura` (o usa el nombre que prefieras) y configura las credenciales en el archivo `application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

3. **Ejecuta la aplicación**:

   Si ya tienes Maven instalado, puedes ejecutar el proyecto con el siguiente comando:

   ```bash
   mvn spring-boot:run
   ```

   Esto iniciará la aplicación y podrás interactuar con ella a través de la consola.

## Contribuciones

Si tienes ideas para mejorar la aplicación o deseas agregar nuevas funcionalidades, ¡no dudes en abrir un **pull request**! Algunas ideas para posibles mejoras son:

- Implementar un sistema de paginación para los resultados de búsqueda.
- Mejorar la validación y manejo de errores.
- Agregar la opción de eliminar o actualizar libros en la base de datos.

## Licencia

Este proyecto está bajo la Licencia MIT - consulta el archivo [LICENSE](LICENSE) para más detalles.

## Contacto

Si tienes preguntas o necesitas ayuda, no dudes en contactarnos:
- **LinkedIn**: www.linkedin.com/in/samantha-hernandez-caballero
- **GitHub**: [https://github.com/samhdez03](https://github.com/samhdez03/literAlura.git)

---
