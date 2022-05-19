FROM maven:3.6.0-jdk-11-slim AS build
WORKDIR /home/app

COPY pom.xml .
RUN mvn package

COPY . .
RUN mvn package

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /home/app/target/ocr-search*.jar /app/ocr-search.jar

EXPOSE 8187
ENTRYPOINT ["java", "-jar", "/app/ocr-search.jar"]