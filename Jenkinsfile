pipeline {
    agent any

    tools {
        maven "Maven@latest"
    }

    environment {
        GIT_REPOSITORY = "https://github.com/williamsayo/speed_calculator"
        DOCKER_CREDENTIALS = "docker_cred"
        DOCKER_REPOSITORY = "williamsayo/travelcalculator"
        DOCKER_TAG = "latest"
    }
    stages {

        stage('Checkout') {
            steps {
                git ${GIT_REPOSITORY}
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn clean test'
            }
        }

        stage('Code Coverage') {
            steps {
                bat 'mvn jacoco:report'
            }
        }

        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }

        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                        docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                    }
                }
            }
        }

    }

}