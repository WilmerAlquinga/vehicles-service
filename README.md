# Vehicle Service
## NOTAS
* El backend devuelve con páginado el listado de vehiculos pero en el front aun no se envian los datos para eso, si no se envian se esta tomando por defecto la primera página y 20 elementos
* El formato en el que se debe enviar la fecha es: yyyy-MM-dd, este endpoint aun no se está consumiendo en el front
* El SQL de la base esta en la carpeta *src/main/java/resources* en el archivo **V1.0__AddInitialSchema.sql**, se ejecuta automáticamente al iniciar la app.
## Versionamiento
Los commits en este repositorio siguen las convenciones de: [Convetional Commits](https://www.conventionalcommits.org/en/v1.0.0/).
___
## Instrucciones de ejecuión

### BD local
Para ejecutar esta aplicación se necesita una base de datos PostgreSQL 15 y crear la base de datos con el comando: 
> create database vehicles;

Especificar los datos para la conexión en el archivo: **application-local.properties**.

### BD en contenedor Docker
Para ejecutar un contenedor de Docker con postgresql 15 se puede ejecutar el siguiente comando:

`cd vehicles-resources` y luego:
`docker-compose --env-file .env up -d`

### Ejecución del proyecto:

Ejecutar el comando: `./gradlew bootRun`

### Ejecución del proyecto en Docker (test remote debug):

Ejecutar los comandos: 

`./gradlew build`

`docker build -t vehicles-service:0.0.1 .`

`docker run -p 8080:8080 -p 5005:5005 --network vehicles-net --name vehicles-ws vehicles-service:0.0.1`
