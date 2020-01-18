#!/bin/bash

cd ./microservices/auth-service/users-specs
mvn -DserverAddress=http://localhost/api-auth clean test
