pipeline {
    agent any
    environment {
        GIT_CREDENTIALS = credentials('github-account') // Sử dụng ID của credential
        EC2_SSH_KEY = credentials('ec2-ssh-key') // ID của SSH Key Pair
    }
    tools {
            jdk 'JDK21'
            maven 'Maven'
    }
    stages {
        stage('Checkout') {
            steps {
                // Sử dụng thông tin xác thực để truy cập repository
                git url: 'https://github.com/xuanvi125/calculator.git', branch: 'main', credentialsId: 'github-account'
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
                            scp -o StrictHostKeyChecking=no /var/jenkins_home/workspace/calculator-cicd/target/calculator-0.0.1-SNAPSHOT.jar ec2-user@100.29.13.93:/home/ec2-user/
                            # SSH vào remote server và chạy file JAR
                            ssh -o StrictHostKeyChecking=no ec2-user@100.29.13.93 '
                            PID=$(sudo lsof -t -i:7070)
                            if [ -n "$PID" ]; then
                                echo "Killing process on port 7070 (PID: $PID)"
                                sudo kill -9 $PID
                            else
                                echo "No process running on port 7070"
                            fi
                            # run jar file
                            nohup java -jar /home/ec2-user/calculator-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &
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
