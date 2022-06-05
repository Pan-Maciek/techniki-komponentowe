FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/ocr/src
COPY pom.xml /home/ocr
RUN mvn -f /home/ocr/pom.xml dependency:resolve
RUN mvn -f /home/ocr/pom.xml clean package -DskipTests=true

FROM fabric8/java-alpine-openjdk11-jre
RUN apk update
RUN apk add --no-cache tesseract-ocr
ENV LC_ALL C
COPY tessdata /home/ocr/tessdata
COPY --from=build /home/ocr/target/ocr-1.0.jar /usr/local/lib/ocr.jar
EXPOSE 8189
ENTRYPOINT ["java","-jar","/usr/local/lib/ocr.jar"]