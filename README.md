# Vehicle Service
## Tecnologías y dependencias
* Java 17
* Spring Boot 3
* Gradle
* PostgreSQL 15
* Flyway
* WebClient
* Lombok
* Mapstruct
## Versionamiento
Los commits en este repositorio siguen las convenciones de: [Convetional Commits](https://www.conventionalcommits.org/en/v1.0.0/).
___
## Instrucciones de ejecuión
Después de ejecutar la aplicación, se 

### BD local
Para ejecutar esta aplicación se necesita una base de datos PostgreSQL 15 y crear la base de datos: 
> create database vehicles;

Especificar los datos para la conexión en el archivo: **application-local.properties**.

Ejecutar el proyecto: `./gradlew bootRun`

---
### BD en contenedor Docker
Para ejecutar un contenedor de Docker con postgresql 15 se puede ejecutar el siguiente comando:

`docker run --name postgres_15.4 -e POSTGRES_PASSWORD=password -e POSTGRES_USER=postgres -e POSTGRES_DB=vehicles -p 5432:5432 -d postgres:15.4`

Ejecutar el proyecto: `./gradlew bootRun`
