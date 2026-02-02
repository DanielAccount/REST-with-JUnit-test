pipeline {
    agent any

    tools {
        maven 'maven-3.9'
        jdk 'jdk-21'
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
                sh 'mvn clean compile'
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

        stage('Docker Build') {
            steps {
                script {
                    sh "docker build -t testing-demo:${env.BUILD_ID} ."
                }
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging the JAR file...'
                sh 'mvn package -DskipTests'
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