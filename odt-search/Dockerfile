FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/odt-search-1.0-SNAPSHOT.jar /usr/local/lib/odt-search.jar
EXPOSE 8182
ENTRYPOINT ["java","-jar","/usr/local/lib/odt-search.jar"]