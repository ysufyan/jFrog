pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'In Build step: Building...'
                sh """#!/bin/bash
                    set -e
                    ./gradlew build
                    ./gradlew war
                """
            }
        }
        stage('Test') {
            steps {
                echo 'In Test step. Testing...'
            }
        }
        stage('Deploy') {
            steps {
                echo 'In Deploy step. Deploying....'
            }
        }
    }
    
    post {
        success {
         emailext (
          to: 'yasirsufyan@gmail.com ysufyan@yahoo.com',
          subject: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
          body: """SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':
            Check console output at ${env.BUILD_URL}consoleFull """,
          recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider'], 
          [$class: 'CulpritsRecipientProvider']]
        )
    }

    failure {
       emailext (
          to: 'yasirsufyan@gmail.com ysufyan@yahoo.com',
          subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
          body: """FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':
            Check console output at ${env.BUILD_URL}consoleFull """,
          recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider'], 
          [$class: 'CulpritsRecipientProvider']]
        )
    }
    }
    
}