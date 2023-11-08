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
        tage('Static Code Analysis') {
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

         stage('Docker images') {
            steps {
                script {
                    sh 'docker build -t mohamedamine1/saadbguir:backend .'
                    sh 'docker build crud-tuto-front -t mohamedamine1/saadbguir:frontend'
                    //sh 'docker pull mysql:5.7'


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
        stage('Deploy to k8s'){
                    steps{
                        script{
                            kubernetesDeploy (configs: 'deploymentservice.yaml',kubeconfigId: 'k8sconfig')
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
            stage('Push Metrics to Prometheus') {
                steps {
                     echo "Étape 12 : Envoi de métriques à Prometheus"
                    script {
                        sh 'curl http://192.168.1.14:8080/metrics/NfPwF3FXQZ1wURuvEyjtKOFc4u6Eswrz_xSkI9l0CQMl6ig5L1or7YjbS1wMT3kJ'

                }
            }
            }
              stage('Prometheus Metrics Scraping') {
    steps {
        script {
            // Liste des noms de job Prometheus à scraper
            def prometheusJobs = [
                'Nexus',
                'SonarQube',
                'docker',
                'jenkins',
                'prometheus'
            ]

            def prometheusURL = 'http://192.168.1.14:9090' // URL de votre serveur Prometheus

            // Parcourir la liste des jobs et déclencher le scraping pour chaque job
            for (def jobName in prometheusJobs) {
                def prometheusScrapeCommand = "curl -X GET $prometheusURL/api/v1/targets?match[]=$jobName"
                sh script: prometheusScrapeCommand, returnStatus: true
                echo "Metrics scraped for $jobName"
            }
        }
    }
}
  stage('Publish Metrics to Grafana with Prometheus') {
            steps {
                script {
                    def grafanaURL = 'http://192.168.1.14:3000' // URL de votre serveur Grafana
                    def prometheusURL = 'http://192.168.1.14:9090' // URL de votre serveur Prometheus

                    // Create the Grafana dashboard link
                    def createDashboardLinkCommand = "curl -H 'Authorization: Bearer eyJrIjoieVlGTTJ1a2VhR09IeTd6RllwVUF6VFRmNXppWWtBYlMiLCJuIjoiamVua2lucyIsImlkIjoxfQ==' $grafanaURL/api/dashboards/home"
                    sh script: createDashboardLinkCommand, returnStatus: true

                    echo "Metrics published to Grafana with Prometheus"
                }
            }
        }

        stage('Open Grafana Dashboard') {
            steps {
                script {
                    def grafanaDashboardURL = 'http://192.168.1.14:3000/d/sX7FzE4Iz/docker-and-system-monitoring?orgId=1&refresh=5m'

                    // Ouvrir l'URL du tableau de bord Grafana
                    sh "curl -L $grafanaDashboardURL"
                    // Wait for some time for the user to view the dashboard
                    sleep(time: 120, unit: 'SECONDS')
                }
            }
        }
        stage('Display Grafana Dashboard') {
    steps {
        script {
            def grafanaEmbedURL = 'http://192.168.1.14:3000/d/sX7FzE4Iz/docker-and-system-monitoring?from=1699225398330&to=1699311798330&orgId=1'
            def iframe = """
            <iframe width="800" height="600" frameborder="0" src="${grafanaEmbedURL}"></iframe>
            """
            writeFile file: 'grafana_dashboard.html', text: iframe

            step([$class: 'ArtifactArchiver', artifacts: 'grafana_dashboard.html', allowEmptyArchive: true])
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: false,
                keepAll: true,
                reportDir: '', // Set the directory where the HTML file is located
                reportFiles: 'grafana_dashboard.html', // Set the HTML file name
                reportName: 'Grafana Dashboard',
                reportTitles: ''
            ])
        }
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