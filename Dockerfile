FROM openjdk:8
EXPOSE 8089
WORKDIR /DevOps_Pioneers
COPY target/devops.jar /DevOps_Pioneers/devops-integration.jar
ENTRYPOINT ["java", "-jar", "devops-integration.jar"]