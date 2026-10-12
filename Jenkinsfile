pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    environment {
        EC2_HOST = '52.27.155.199'
        EC2_USER = 'ec2-user'
    }

    options {
        timeout(time: 20, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
                }
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Deploy') {
            steps {
                withCredentials([sshUserPrivateKey(credentialsId: 'terraform-example-instance', keyFileVariable: 'SSH_KEY')]) {
                    sh '''
                        SSH_OPTS="-i $SSH_KEY -o StrictHostKeyChecking=accept-new"
                        TARGET="$EC2_USER@$EC2_HOST"

                        # Upload under a temp name so the running app's jar isn't overwritten mid-copy
                        scp $SSH_OPTS target/covid-*.jar "$TARGET:/home/$EC2_USER/covid.jar.new"
                        scp $SSH_OPTS deploy/covid.service "$TARGET:/tmp/covid.service"

                        ssh $SSH_OPTS "$TARGET" '
                            set -e
                            if ! command -v java >/dev/null; then
                                sudo dnf install -y java-21-amazon-corretto-headless
                            fi
                            mv ~/covid.jar.new ~/covid.jar
                            sudo mv /tmp/covid.service /etc/systemd/system/covid.service
                            sudo systemctl daemon-reload
                            sudo systemctl enable covid
                            sudo systemctl restart covid

                            # Wait up to 60s for the app to answer
                            for i in $(seq 1 30); do
                                if curl -fs http://localhost:8081/covid >/dev/null; then
                                    echo "App is up"
                                    exit 0
                                fi
                                sleep 2
                            done
                            echo "App did not start, recent logs:"
                            sudo journalctl -u covid -n 50 --no-pager
                            exit 1
                        '
                    '''
                }
            }
        }
    }
}
