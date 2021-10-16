FROM adoptopenjdk/openjdk11:alpine-jre
COPY target/*.jar smart-hardware-shop.jar
ENTRYPOINT ["java","-jar","/smart-hardware-shop.jar"]
