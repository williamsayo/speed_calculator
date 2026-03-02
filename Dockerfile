FROM maven:latest
LABEL authors="willi"

WORKDIR /app

COPY pom.xml .
COPY . /app

RUN mvn package

cmd ["java","-jar","target/calculator.jar"]