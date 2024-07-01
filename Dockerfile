FROM openjdk:17-jdk-alpine as builder

ARG JAR_FILE=target/*.jar
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/sound-good
COPY ${JAR_FILE} application.jar

RUN java -Djarmode=layertools -jar application.jar extract

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./

EXPOSE 8080

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
