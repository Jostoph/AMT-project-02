#!/bin/bash

cd ./microservices/shop-service/users-specs
mvn -DserverAddress=http://localhost/api-shop clean test
