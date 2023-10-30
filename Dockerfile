FROM openjdk:11

# Set the working directory
WORKDIR /DevOps_Pioneers

COPY target/devops-integration.jar /DevOps_Pioneers/devops-integration.jar

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "devops-integration.jar"]
#ranou