FROM openjdk:8
EXPOSE 8089
WORKDIR /DevOps_Pioneers
COPY target/devops.jar /DevOps_Pioneers/devops.jar
ENTRYPOINT ["java", "-jar", "devops.jar"]