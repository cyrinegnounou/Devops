FROM openjdk:8
EXPOSE 8089
WORKDIR /DevOps_Pioneers
ENTRYPOINT ["java", "-jar", "devops-integration.jar"]