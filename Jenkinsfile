pipeline {
    agent any

    tools {
        maven 'Maven@latest'
    }

    environment {
        GIT_REPOSITORY = 'https://github.com/williamsayo/travel_calculator'
        DOCKER_CREDENTIALS = 'docker_cred'
        DOCKER_REPOSITORY = 'williamsayo/travelcalculator'
        DOCKER_TAG = 'latest'
    }

    stages {

        stage('Checkout') {
            steps {
                git "${GIT_REPOSITORY}"
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Publish Report') {
            steps {
                bat 'mvn jacoco:report'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    appImage = docker.build("${DOCKER_REPOSITORY}:${DOCKER_TAG}")
                }
            }
        }

        stage('Publish Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS) {
                        appImage.push()
                    }
                }
            }
        }
    }
}