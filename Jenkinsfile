pipeline {
    agent any
    environment {
        GIT_CREDENTIALS = credentials('jenkins-pipeline') // Sử dụng ID của credential
        EC2_SSH_KEY = credentials('ec2-ssh-key') // ID của SSH Key Pair
    }
    tools {
            jdk 'JDK11'
            maven 'Maven'
    }
    stages {
        stage('Checkout') {
            steps {
                // Sử dụng thông tin xác thực để truy cập repository
                git url: 'https://github.com/xuanvi125/math-service.git', branch: 'main', credentialsId: 'jenkins-pipeline'
            }
        }
        stage('Build') {
            steps {
                // Compile và chạy các bài test
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Test') {
            steps {
                // Chạy test và tạo báo cáo JaCoCo
                sh 'mvn test jacoco:report'
            }
            post {
                always {
                    // Lưu lại báo cáo test kết quả
                    junit '**/target/surefire-reports/*.xml'
                    // Lưu lại báo cáo JaCoCo
                    jacoco execPattern: '**/target/jacoco.exec', classPattern: '**/target/classes', sourcePattern: '**/src/main/java'
                }
            }
        }
         stage('Deploy') {
            steps {
                // Sử dụng SSH Agent với credential key.pem
                sshagent(['ec2-ssh-key']) { // Thay 'your-credential-id' bằng ID của credential trong Jenkins
                    script {
                        // Sao chép file đến remote server
                        sh '''
                            echo "Copying file to remote server..."
                            scp -o StrictHostKeyChecking=no /var/jenkins_home/workspace/hello-world-pipeline/target/demo-0.0.1-SNAPSHOT.jar ec2-user@54.237.165.127:/home/ec2-user/
                            # SSH vào remote server và chạy file JAR
                            ssh -o StrictHostKeyChecking=no ec2-user@54.237.165.127 '
                            PID=$(sudo lsof -t -i:7070)
                            if [ -n "$PID" ]; then
                                echo "Killing process on port 7070 (PID: $PID)"
                                sudo kill -9 $PID
                            else
                                echo "No process running on port 7070"
                            fi
                            # run jar file
                            nohup java -jar /home/ec2-user/demo-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &
                            exit 0'
                        
                        '''
                    }
                }
            }
        }
    }
    post {
        always {
            // Luôn luôn dọn dẹp workspace sau khi pipeline chạy xong
            cleanWs()
        }
    }
}
