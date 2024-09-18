FROM jelastic/maven:3.9.5-openjdk-22.ea-b17 AS build
WORKDIR /server

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:22-jdk
WORKDIR /server
COPY --from=build /server/target/server-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/server/target/server-0.0.1-SNAPSHOT.jar" ]