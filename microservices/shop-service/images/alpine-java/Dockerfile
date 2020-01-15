FROM anapsix/alpine-java
MAINTAINER Christoph Rouff soit Rueff <christoph.rouffsoitrueff@heig-vd.ch>
RUN apk add --no-cache bash
COPY ./wait_for_it.sh /opt/
COPY ./swagger-spring-1.0.0.jar /opt/shop-api.jar
WORKDIR /opt
RUN chmod +x wait_for_it.sh
CMD ["java","-jar","shop-api.jar"]
