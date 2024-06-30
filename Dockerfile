# Stage 1: Build stage
FROM openjdk:17-jdk-alpine as builder

# Argument để chỉ định đường dẫn file JAR
ARG JAR_FILE=target/*.jar
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/sound-good
# Copy file JAR vào container
COPY ${JAR_FILE} application.jar

# Extract các lớp từ file JAR
RUN java -Djarmode=layertools -jar application.jar extract

# Stage 2: Run stage
FROM openjdk:17-jdk-alpine

# Tạo thư mục cho ứng dụng
WORKDIR /app

# Copy các lớp từ stage build vào stage run
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./

# Expose port để container có thể truy cập từ bên ngoài
EXPOSE 8080

# Command để chạy ứng dụng
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
