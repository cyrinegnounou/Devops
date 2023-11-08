pipeline {
    agent any

    tools {
        maven 'M2_HOME'
    }

    stages {
        // Étape 1 : Récupération du code depuis Git
        stage('Git') {
            steps {
                echo "Getting project from Git"
                git branch: 'Reglement', credentialsId: 'git_credentials', url: 'https://github.com/cyrinegnounou/DevOps_Pioneers.git'
            }
        }

        // Étape 2 : Analyse statique du code
        stage('Static Code Analysis') {
            steps {
                echo "Running static code analysis"
                sh 'mvn checkstyle:checkstyle pmd:pmd findbugs:findbugs'
            }
        }

        // Étape 3 : Compilation du projet
        stage('Compiling') {
            steps {
                echo "Compiling the project"
                sh 'mvn compile'
            }
        }

        // Étape 4 : Nettoyage et construction du projet
        stage('Build') {
            steps {
                echo "Cleaning and building the project"
                sh 'mvn clean install'
            }
        }

        // Étape 5 : Tests d'interface utilisateur
        stage('UI Testing') {
            steps {
                echo "Running UI tests"
                sh 'mvn test -Dtest=ReglementServiceImplTest'
                archiveArtifacts artifacts: 'target/surefire-reports/*.xml, target/screenshots/*.png', allowEmptyArchive: true
            }
        }

        // Étape 6 : Tests d'intégration
        stage('Integration Tests') {
            steps {
                echo "Running integration tests"
                sh 'mvn verify -Pintegration-tests'
            }
        }

        // Étape 7 : Tests avec JaCoCo
        stage('Test with JaCoCo') {
            steps {
                echo "Running tests with JaCoCo"
                sh 'mvn clean test jacoco:report'
            }
        }

        // Étape 8 : Tests JUnit/Mockito
        stage('JUnit/Mockito') {
            steps {
                echo "Running JUnit/Mockito tests"
                sh 'mvn test'
                junit '**/target/surefire-reports/TEST-*.xml'
            }
        }

        // Étape 9 : Analyse SonarQube
        stage('SonarQube Analysis') {
            steps {
                echo "Running SonarQube analysis"
                script {
                    withSonarQubeEnv ('SonarQube') {
                        sh 'mvn clean package sonar:sonar -Dsonar.login=admin -Dsonar.password=azerty'
                    }
                }
            }
        }

        // Étape 10 : Porte de qualité
        stage("Quality Gate") {
            steps {
                echo "Quality gate"
                waitForQualityGate abortPipeline: true, credentialsId: 'sonarpass'
            }
        }

        // Étape 11 : Création des images Docker
        stage('Docker images') {
            steps {
                script {
                    sh 'docker build -t mohamedamine1/saadbguir:backend .'
                    sh 'docker build crud-tuto-front -t mohamedamine1/saadbguir:frontend'
                }
            }
        }

        // Étape 12 : Publication des images Docker
        stage('Push image to hub') {
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

        // Étape 13 : Construction et déploiement avec Docker Compose
        stage('Build and Deploy with Docker Compose') {
            steps {
                echo "Building and deploying with Docker Compose"
                script {
                    sh 'docker-compose up -d'
                }
            }
        }

        // Étape 14 : Déploiement vers Kubernetes
        stage('Deploy to k8s') {
            steps {
                script {
                    kubernetesDeploy (configs: 'deploymentservice.yaml', kubeconfigId: 'k8sconfig')
                }
            }
        }

        // Étape 15 : Chargement de l'artefact dans Nexus
        stage('Upload Artifact to Nexus') {
            steps {
                echo "Uploading artifact to Nexus"
                nexusArtifactUploader artifacts: [
                    [
                        artifactId: 'achat',
                        classifier: '',
                        file: 'target/devops-integration.jar',
                        type: 'jar'
                    ]
                ],
                credentialsId: 'nexus',
                groupId: 'tn.esprit.rh',
                nexusUrl: '192.168.1.14:8081',
                nexusVersion: 'nexus3',
                protocol: 'http',
                repository: 'devops-project',
                version: "${env.JOB_NAME} [${env.BUILD_NUMBER}]"
            }
        }

        // Étape 16 : Envoi de métriques à Prometheus
        stage('Push Metrics to Prometheus') {
            steps {
                echo "Pushing metrics to Prometheus"
                script {
                    sh 'curl http://192.168.1.14:8080/metrics/NfPwF3FXQZ1wURuvEyjtKOFc4u6Eswrz_xSkI9l0CQMl6ig5L1or7YjbS1wMT3kJ'
                }
            }
        }

        // Étape 17 : Scraping de métriques Prometheus
        stage('Prometheus Metrics Scraping') {
            steps {
                echo "Scraping Prometheus metrics"
                script {
                    def prometheusJobs = [
                        'Nexus',
                        'SonarQube',
                        'docker',
                        'jenkins',
                        'prometheus'
                    ]

                    def prometheusURL = 'http://192.168.1.14:9090'
                    for (def jobName in prometheusJobs) {
                        def prometheusScrapeCommand = "curl -X GET $prometheusURL/api/v1/targets?match[]=$jobName"
                        sh script: prometheusScrapeCommand, returnStatus: true
                        echo "Metrics scraped for $jobName"
                    }
                }
            }
        }

        // Étape 18 : Publication de métriques à Grafana avec Prometheus
        stage('Publish Metrics to Grafana with Prometheus') {
            steps {
                echo "Publishing metrics to Grafana with Prometheus"
                script {
                    def grafanaURL = 'http://192.168.1.14:3000'
                    def prometheusURL = 'http://192.168.1.14:9090'
                    def createDashboardLinkCommand = "curl -H 'Authorization: Bearer eyJrIjoieVlGTTJ1a2VhR09IeTd6RllwVUF6VFRmNXppWWtBYlMiLCJuIjoiamVua2lucyIsImlkIjoxfQ==' $grafanaURL/api/dashboards/home"
                sh script: createDashboardLinkCommand, returnStatus: true
                echo "Metrics published to Grafana with Prometheus"
            }
        }
    }

    // Étape 19 : Ouvrir le tableau de bord Grafana
    stage('Open Grafana Dashboard') {
        steps {
            script {
                def grafanaDashboardURL = 'http://192.168.1.14:3000/d/sX7FzE4Iz/docker-and-system-monitoring?orgId=1&refresh=5m'
                sh "curl -L $grafanaDashboardURL"
                sleep(time: 120, unit: 'SECONDS')
            }
        }
    }

    // Étape 20 : Afficher le tableau de bord Grafana
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
                    reportDir: '',
                    reportFiles: 'grafana_dashboard.html',
                    reportName: 'Grafana Dashboard',
                    reportTitles: ''
                ])
            }
        }
    }

    // Étape 21 : Notification par e-mail
    stage('Email notification') {
        steps {
            mail bcc: '', body: '''Hi welcome to Jenkins email alert ==> pipeline success
       ''', cc: '', from: '', replyTo: '', subject: 'Jenkins Job', to: 'mohamedamine.hammami@esprit.tn'
        }
    }
}
}

