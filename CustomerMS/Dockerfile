# syntax=docker/dockerfile:1

FROM openjdk:16
ADD target/customer-ms.jar customer-ms.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "customer-ms.jar"]