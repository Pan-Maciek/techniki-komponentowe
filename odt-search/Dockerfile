FROM maven:3.6.0-jdk-11-slim AS build
WORKDIR /home/app

COPY pom.xml .
RUN mvn package

COPY . .
RUN mvn package

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /home/app/target/odt-search*.jar /app/odt-search.jar

EXPOSE 8182
ENTRYPOINT ["java", "-jar", "/app/odt-search.jar"]