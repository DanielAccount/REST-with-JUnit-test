pipeline {
    agent any

    environment {
        // Change these to match your actual Docker Hub username and App name
        DOCKER_HUB_USER = 'danielshipping123'
        APP_NAME        = 'my-calculator'
        IMAGE_TAG       = "1.0.${env.BUILD_ID}"
        DOCKER_CREDS_ID = 'docker-hub-creds'
    }

    tools {
        maven 'maven3.9'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Package') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    // Log in to Docker Hub using Jenkins Credentials
                    withCredentials([usernamePassword(credentialsId: "${DOCKER_CREDS_ID}", passwordVariable: 'DOCKER_PASS', usernameVariable: 'DOCKER_USER')]) {
                        sh "echo \$DOCKER_PASS | docker login -u \$DOCKER_USER --password-stdin"

                        // Build the image with the Docker Hub prefix
                        sh "docker build -t ${DOCKER_HUB_USER}/${APP_NAME}:${IMAGE_TAG} ."
                        sh "docker build -t ${DOCKER_HUB_USER}/${APP_NAME}:latest ."

                        // Push both the specific build version and 'latest'
                        sh "docker push ${DOCKER_HUB_USER}/${APP_NAME}:${IMAGE_TAG}"
                        sh "docker push ${DOCKER_HUB_USER}/${APP_NAME}:latest"
                    }
                }
            }
        }

        stage('Kubernetes Deploy') {
            steps {
                script {
                    echo 'Applying Kubernetes Manifests...'

                    // Option A: If your YAMLs are in the project root
                    sh "kubectl apply -f deployment.yaml"
                    sh "kubectl apply -f services.yaml"

                    // Force Kubernetes to pull the new image even if the tag name is 'latest'
                    sh "kubectl rollout restart deployment/calculator-deployment"
                }
            }
        }
    }

    post {
        success {
            echo "Successfully deployed ${APP_NAME} to Kubernetes!"
        }
        failure {
            echo 'Pipeline failed. Check Docker credentials or Kubernetes connectivity.'
        }
    }
}