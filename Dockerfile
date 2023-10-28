FROM openjdk:8
EXPOSE 8089
WORKDIR /DevOps_Pioneers
COPY target/devops-integration.jar/
ENTRYPOINT ["java", "jar", "devops-integration.jar" ]