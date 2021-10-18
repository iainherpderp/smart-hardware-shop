FROM adoptopenjdk/openjdk11:alpine-jre
COPY target/api-0.0.1-SNAPSHOT.jar smart-hardware-shop.jar
ENTRYPOINT ["java","-jar","/smart-hardware-shop.jar"]
