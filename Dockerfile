FROM openjdk:8

EXPOSE 8089

WORKDIR /DevOps_Pioneers

COPY devops-integration.jar /DevOps_Pioneers/

ENTRYPOINT ["java", "-jar", "devops-integration.jar"]
