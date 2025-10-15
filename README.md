
# 🛡️ Auth-REST-API: Gestión de Usuarios y Roles (JWT)

Una API REST robusta construida con Spring Boot 3 para la gestión de usuarios, roles y autenticación mediante JSON Web Tokens (JWT). La aplicación utiliza Spring Security y Spring Data JPA para asegurar los endpoints y manejar la persistencia de datos.

## 🚀 Tecnologías
- Spring Boot 3
- Spring Security
- Spring Data JPA
- H2 Database
- Java JWT (auth0)
- Lombok
- Java 17

## 💻 Requisitos previos
Antes de ejecutar el programa, asegurate de tener instalado:

    1. Java Development Kit (JDK) 17 o superior
    2. Apache Maven 3.x (para la gestión de dependecias)

## ▶️ Ejecución y Puesta en Marcha

Sigue estos pasos para levantar la API en tu entorno local:

1. Clonar el Repositorio:
    ```
    bash
    git clone
    cd auth-rest-api```

2. Ejecutar la aplicación:
    ```
    mvn spring-boot:run
    ```
3. Estado

La API se iniciará en el puerto por defecto de Spring Boot: http://localhost:8080

## 🔑 Endpoints de Autenticación y Administración

Esta API expone los siguientes endpoints. Nota: Todos los endpoints protegidos requieren enviar el JWT en el encabezado ```Authorization: Bearer <token>```

| Categoría | Método | Endpoint | Descripción | Seguridad Requerida |
| :--- | :--- | :--- | :--- | :--- |
| **Auth** | `POST` | `/api/auth/register` | Registro de un nuevo usuario. | Pública |
| **Auth** | `POST` | `/api/auth/login` | Obtiene un JWT válido con credenciales. | Pública |
| **Users** | `GET` | `/api/users/me` | Retorna el perfil del usuario autenticado. | Autenticado |
| **Admin** | `GET` | `/api/admin/users` | Lista todos los usuarios del sistema. | Rol **ADMIN** |

## 🧪 Notas de Desarrollo
