FROM openjdk:8
EXPOSE 8091
WORKDIR /DevOps_Pioneers
ADD target/devops-integration.jar /DevOps_Pioneers/
ENTRYPOINT ["java" , "-jar","/devops-integration.jar"]

# This is a comment in a Dockerfiles
# This is a comment in a Dockerfiles
#Push successful D:\: Pushed 1 commit to origin/facture
# This is a comment in a Dockerfiles
# This is a comment in a Dockerfileshf
#jbjd
