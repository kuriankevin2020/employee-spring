# syntax=docker/dockerfile:1

FROM openjdk:8u312-slim

WORKDIR /app
COPY bin/employee-spring-1.0.0.jar ./

EXPOSE 8080

CMD ["java", "-jar", "employee-spring-1.0.0.jar"]
