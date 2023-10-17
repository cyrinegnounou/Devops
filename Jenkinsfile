pipeline {
    agent any
    tools {
        // Assuming 'M2_HOME' is a valid tool in your Jenkins configuration
        maven 'M2_HOME'
    }
    stages {
        stage('Git') {
            steps {
                echo "Getting project from git"
                git branch: 'Reglement', credentialsId: 'git_credentials', url: 'https://github.com/cyrinegnounou/DevOps_Pioneers'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Compiling') {
            steps {
                sh 'mvn compile'
            }
        }

        stage('SonarQube') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=azerty'
            }
        }
        stage('junit & mockito') {
            steps {
                sh 'mvn test'
                junit '**/target/surefire-reports/TEST-*.xml'
            }
        }
        stage('build docker image') {
            steps {
                script {
                    sh 'docker build -t mohamedamine1/devops-integration .'
                }
            }
        }
        stage('push image to hub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'docker-pss', variable: 'dockerpss')]) {
                        sh 'docker login -u mohamedamine1 -p ${dockerpss}'
                        sh 'docker push mohamedamine1/devops-integration'
                    }
                }
            }
        }


    }
}
