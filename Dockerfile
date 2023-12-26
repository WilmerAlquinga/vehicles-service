FROM openjdk:17-jdk

WORKDIR /app

ENV SPRING_PROFILES_ACTIVE=docker

COPY build/libs/vehicles-1.0.0.jar /app/app.jar

EXPOSE 8080 5005

CMD ["java", "-jar", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "app.jar"]
