FROM maven:3.9.6-eclipse-temurin-21-alpine AS development
WORKDIR /home/app

COPY ./pom.xml /home/app/pom.xml
RUN mvn clean install

EXPOSE 8080
ENTRYPOINT ["mvn","spring-boot:run"]