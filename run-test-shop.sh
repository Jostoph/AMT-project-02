#!/bin/bash

cd ./microservices/shop-service/shop-specs
mvn -DserverAddress=http://localhost/api-shop clean test
