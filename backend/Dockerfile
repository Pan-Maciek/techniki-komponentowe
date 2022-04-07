FROM gradle:7.4.1-jdk11-alpine AS build
WORKDIR /home/gradle/src

COPY build.gradle.kts settings.gradle.kts ./
RUN gradle dependencies --refresh-dependencies -i

COPY . .
RUN gradle build --no-daemon -i

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/backend.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/backend.jar"]