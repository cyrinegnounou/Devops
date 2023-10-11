pipeline {
    agent any
    tools {
        maven 'M2_HOME'
    }
    stages {
        stage('build maven') {
            steps {
                checkout scmGit(branches: [[name: '*/Reglement']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/cyrinegnounou/DevOps_Pioneers']])
                sh 'mvn clean install'
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
