pipeline {
    agent any
    stages {
        stage('git repo & clean') {
            steps {
                bat "rmdir  /s /q TicketBookingServiceJunitTesting"
                bat "git clone https://github.com/varaprsd11/team-service.git"
                bat "mvn clean -f team-service"
            }
        }
        stage('install') {
            steps {
                bat "mvn install -f team-service"
            }
        }
        stage('test') {
            steps {
                bat "mvn test -f team-service"
            }
        }
        stage('package') {
            steps {
                bat "mvn package -f team-service"
            }
        }
    }
}
