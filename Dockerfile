FROM openjdk:8
EXPOSE 8089
WORKDIR /DevOps_Pioneers
COPY DevOps_Pioneers/target/devops-integration.jar /DevOps_Pioneers/
ENTRYPOINT ["java", "jar", "devops-integration.jar" ]



