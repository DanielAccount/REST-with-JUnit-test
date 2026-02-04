pipeline {
    agent any

    tools {
        maven 'maven3.9'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }

        stage('Compile') {
            steps {
                echo 'Compiling the application...'
                sh 'mvn compile'
            }
        }

        stage('Unit Test') {
            steps {
                echo 'Running JUnit Tests...'
                // If tests fail, the pipeline stops here
                sh 'mvn test'
            }
            post {
                always {
                    // This archives the JUnit reports so Jenkins can display graphs
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

          stage('Package') {
            steps {
                echo 'Packaging the JAR file...'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    sh "docker build -t testing-demo:${env.BUILD_ID} ."
                }
            }
        }

        stage('Deploy') {
              //Deploy on docker container
            steps {
                echo 'Packaging the JAR file...'
                sh '''
                    docker stop calculator-container || true 
                    docker rm calculator-container || true
                    docker run -d --name calculator-container -p 5090:8080 testing-demo:${env.BUILD_ID}
                '''
            }
        }

      
    }

    post {
        success {
            echo 'Build and Tests Passed Successfully!'
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
        failure {
            echo 'Pipeline Failed. Check the logs and JUnit reports.'
        }
    }
}
