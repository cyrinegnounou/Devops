FROM openjdk:8
EXPOSE 8091
WORKDIR /DevOps_Pioneers
COPY target/devops-integration.jar /DevOps_Pioneers/
ENTRYPOINT ["java", "-jar", "devops-integration.jar"]
