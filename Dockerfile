FROM openjdk:8
EXPOSE 8089
WORKDIR /DevOps_Pioneers
ADD target/devops.jar /DevOps_Pioneers/
ENTRYPOINT ["java", "-jar", "devops.jar"]
