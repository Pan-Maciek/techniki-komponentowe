FROM mozilla/sbt:8u292_1.5.7 AS build
WORKDIR /home/app
COPY src /home/app/src
COPY build.sbt /home/app
COPY project/plugins.sbt /home/app/project/plugins.sbt
RUN sbt clean assembly


FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/scala-2.13/pdf-search-assembly-0.1.jar /usr/local/lib/pdf-search.jar
EXPOSE 8183
ENTRYPOINT ["java", "-jar", "/usr/local/lib/pdf-search.jar"]