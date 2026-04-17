# Sistema de Gestión de Biblioteca - API REST

Este proyecto es una **API RESTful** desarrollada con **Java** y **Spring Boot** para la gestión integral de una biblioteca. Permite administrar de manera digital el catálogo de libros, autores, categorías, usuarios y el ciclo completo de préstamos y devoluciones, garantizando la integridad de los datos y el control de stock.

Forma parte del proyecto final del curso de Desarrollo de Componentes del Negocio.

## Características
- **Seguridad robusta**: Autenticación y autorización basada en **JWT (JSON Web Tokens)**.
- **Validación de Datos**: Uso de `Jakarta Validation` para asegurar la calidad de la información recibida.
- **Mapeo Eficiente**: Implementación de **MapStruct** para la conversión limpia entre Entidades y DTOs.
- **Persistencia Relacional**: Almacenamiento de datos en **PostgreSQL** mediante Spring Data JPA.
- **Documentación Interactiva**: Documentación completa de endpoints integrada con **Swagger/OpenAPI**.
- **Manejo de Excepciones**: Gestión centralizada de errores para respuestas consistentes en la API.

## Tecnologías Utilizadas
* **Java 21**
* **Spring Boot 3.5.x**
* **PostgreSQL** (Base de datos relacional)
* **Maven** (Gestión de dependencias)
* **Lombok** (Reducción de código boilerplate)
* **MapStruct** (Mapeo de objetos)
* **Spring Security & JWT** (Seguridad)
* **Springdoc OpenAPI** (Swagger UI)

## Estructura del Proyecto
```text
src/main/java/gestion/biblioteca/
├── config/      # Configuraciones de Seguridad, JWT y Swagger
├── controller/  # Endpoints de la API REST
├── dto/         # Objetos de Transferencia de Datos (Request/Response)
├── entity/      # Entidades de persistencia (JPA)
├── exception/   # Manejo global de excepciones
├── mapper/      # Mapeo entre entidades y DTOs (MapStruct)
├── repository/  # Interfaces de repositorio (Spring Data JPA)
├── security/    # Componentes de seguridad (Filtros, EntryPoints)
└── service/     # Lógica de negocio e implementaciones
```

## Configuración e Instalación

1.  **Base de Datos**:
    *   Crear una base de datos en PostgreSQL llamada `BD_BIBLIOTECA`. Crea una schema llamado `bd_gestionbiblioteca`.
    *   Asegurarse de que el puerto y las credenciales coincidan con `application.yaml`.

2.  **Importar el Proyecto**:
    *   Importar como proyecto Maven en su IDE preferido (IntelliJ IDEA recomendado).

3.  **Actualizar dependencias**:
    ```bash
    mvn clean install
    ```

4.  **Ejecutar la aplicación**:
    *   Localizar la clase `BibliotecaApplication.java` y ejecutarla.
    *   La aplicación se iniciará en el puerto `8070` con el prefijo `/api/`.

## Documentación de la API (Swagger)

Una vez iniciada la aplicación, puede acceder a la documentación interactiva en:
[http://localhost:8070/api/swagger-ui/index.html](http://localhost:8070/api/swagger-ui/index.html)

## Endpoints Principales

| Método | Endpoint | Descripción |
|:---:|:---:|:---:|
| **POST** | `/api/auth/login` | Iniciar sesión y obtener Token JWT |
| **POST** | `/api/auth/register` | Registro público de nuevos usuarios |
| **GET** | `/api/libros` | Listar catálogo de libros activos |
| **POST** | `/api/libros` | Registrar un nuevo libro en el catálogo |
| **GET** | `/api/prestamos` | Listar todos los préstamos registrados |
| **POST** | `/api/prestamos` | Registrar un nuevo préstamo de libro |
| **PATCH** | `/api/prestamos/{id}/devolver` | Procesar devolución y actualizar stock |
| **GET** | `/api/usuarios` | Listar usuarios registrados (Admin) |
