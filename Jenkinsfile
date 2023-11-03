pipeline {
    agent any
    tools {
        maven 'M2_HOME'
    }
    stages {
        stage('Git') {
            steps {
                echo "Getting project from git"
                git branch: 'Reglement', credentialsId: 'git_credentials', url: 'https://github.com/cyrinegnounou/DevOps_Pioneers.git'
            }
        }
         stage('Static Code Analysis') {
            steps {
                 echo "Étape 2 : Analyse statique du code"
                 sh 'mvn checkstyle:checkstyle pmd:pmd findbugs:findbugs'
            }
        }
        stage('Build ') {
            steps {
                echo "Étape 4 : Nettoyage et construction du projet"
                sh 'mvn clean install'

            }
        }
         stage('Compiling') {
            steps {
                echo "Étape 3 : Compilation du projet"
                sh 'mvn compile'
            }
        }

        stage('UI Testing') {
    steps {
        echo "Étape 5 : Tests d'interface utilisateur"
        // Étape 1 : Télécharger les dépendances du projet, y compris les pilotes de navigateur

        // Étape 2 : Exécuter les tests UI avec Maven et Selenium (ajustez les commandes selon votre projet)
        sh 'mvn test -Dtest=ReglementServiceImplTest'

        // Étape 3 : Collecter les résultats des tests (rapports, captures d'écran, etc.)
        // Vous devrez spécifier où les résultats sont générés
        archiveArtifacts artifacts: 'target/surefire-reports/*.xml, target/screenshots/*.png', allowEmptyArchive: true
    }
}
 stage('Integration Tests') {
            steps {
                echo "Étape 6 : Tests d'intégration"
                sh 'mvn verify -Pintegration-tests'

            }
        }

    stage('Test with JaCoCo') {
        steps{
            echo "Étape 7 : Tests avec JaCoCo"

                // Run your tests with JaCoCo enabled and generate JaCoCo XML reports
                sh 'mvn clean test jacoco:report'
        }
    }

        stage('Collect JaCoCo Coverage') {
            steps{
                 echo "Étape 13 : Collecte de la couverture JaCoCo"
                   jacoco(execPattern: '**/target/jacoco.exec')
    }
        }
  stage('JUnit/Mockito') {
            steps {
                echo "Étape 8 : Tests JUnit/Mockito"
                sh 'mvn test'
                junit '**/target/surefire-reports/TEST-*.xml'
            }
        }

stage('SonarQube Analysis') {
    steps {
        echo "Étape 10 : Analyse SonarQube"
        script {
        withSonarQubeEnv ('SonarQube') {

            sh 'mvn clean package sonar:sonar -Dsonar.login=admin -Dsonar.password=azerty'

        }
        }
    }
}
stage("Quality Gate") {
            steps{
                 echo "Étape 11 : Porte de qualité"


                waitForQualityGate abortPipeline: true, credentialsId: 'sonarpass'
            }
        }






 stage('Push Metrics to Prometheus') {
    steps {
         echo "Étape 12 : Envoi de métriques à Prometheus"
        script {
            sh 'curl http://192.168.1.14:8080/metrics/NfPwF3FXQZ1wURuvEyjtKOFc4u6Eswrz_xSkI9l0CQMl6ig5L1or7YjbS1wMT3kJ'

    }
}
}











         stage('Docker images') {
            steps {
                script {
                    sh 'docker build -t mohamedamine1/saadbguir:backend .'
                    sh 'docker build crud-tuto-front -t mohamedamine1/saadbguir:frontend'
                    sh 'docker pull mysql:5.7'


                }
            }
        }

        stage('push image to hub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'docker-pss', variable: 'dockerpss')]) {
                        sh 'docker login -u mohamedamine1 -p ${dockerpss}'
                        sh 'docker push mohamedamine1/saadbguir:backend'
                        sh 'docker push mohamedamine1/saadbguir:frontend'


                    }
                }
            }
        }
        stage('Build and Deploy with Docker Compose') {
            steps {
                                echo "Étape 14 : Construction et déploiement avec Docker Compose"

                script {
                    sh 'docker-compose up -d' // Utilisation de "docker-compose" au lieu de "docker compose"
                }
            }
        }
        stage('Deploy to k8S'){
        steps{
        script{
        kubernetesDeploy(configs: "deployment.yaml", "service.yaml")

        }
        }
        }


        stage ('upload Artifact to Nexus') {
            steps{
            echo "Étape 15 : Chargement de l'artefact dans Nexus"


         nexusArtifactUploader artifacts: [
          [
          artifactId: 'achat',
          classifier: '',
          file: 'target/devops-integration.jar',
          type: 'jar'
           ]],
          credentialsId: 'nexus',
          groupId: 'tn.esprit.rh',
          nexusUrl: '192.168.1.14:8081',
           nexusVersion: 'nexus3',
           protocol: 'http',
           repository: 'devops-project',
             version: "${env.JOB_NAME} [${env.BUILD_NUMBER}]"

            }



            }



        stage('Email nodification'){
            steps{
                mail bcc: '', body: '''Hi welcome to jenkins email alert ==>pipeline succes
       ''', cc: '', from: '', replyTo: '', subject: 'Jenkins Job', to: 'mohamedamine.hammami@esprit.tn'
            }
        }
    }








}