#!/bin/bash

mvn clean install -f ./microservices/auth-service/spring-server/pom.xml
cp ./microservices/auth-service/spring-server/target/swagger-spring-1.0.0.jar ./microservices/auth-service/images/alpine-java/

mvn clean install -f ./microservices/shop-service/spring-server/pom.xml
cp ./microservices/shop-service/spring-server/target/swagger-spring-1.0.0.jar ./microservices/shop-service/images/alpine-java/

cd ./topology
docker-compose up --build