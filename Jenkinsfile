def DEPLOY_APPLICATION = params.getOrDefault("deploy_application", "")
pipeline {
    agent any
    parameters {
        booleanParam(defaultValue: DEPLOY_APPLICATION, description: '', name: 'deploy_application');

    }

    environment {
        ANSIBLE_GIT_URL=''
        PROJECT_GIT_URL=''
        GIT_CREDENTIAL='git'
        JAR_LOCATION='${WORKSPACE}/git-code/target'
    }

    stages {
        stage('Getting project code from GIT') {
            steps {
                dir('git-code') {
                    git branch: '${gitBranch}',
                        credentialsId: "${env.GIT_CREDENTIAL}",
                        url: "${env.PROJECT_GIT_URL}"
                }
            }
        }

        stage ('Compile Code') {
            steps {
                dir('git-code') {
                    sh 'mvn -B -DskipTests clean package'
                }
            }
        }

        stage ('Getting ansible code from GIT') {
            steps {
                dir('ansible-code') {
                    git branch: 'main',
                        changelog: false,
                        credentialsId: "${env.GIT_CREDENTIAL}",
                        poll: false,
                        url: "${env.ANSIBLE_GIT_URL}"
                }
            }
        }

        stage ('Running ansible') {
            steps {
                dir('ansible-code') {
                    ansiblePlaybook credentialsId: 'JenkinsId',
                        disableHostKeyChecking: true,
                        installation: 'ansible',
                        inventory: 'inventory/servers.txt',
                        playbook: 'playbook.yml',
                        extras: "--extra-vars \"configure_apache=${CONFIGURE_APACHE}
                        domain_name=${DOMAIN_NAME}
                        server_ip=${SERVER_IP} server_port=${SERVER_PORT}
                        configure_ssl=${CONFIGURE_SSL}
                        configure_jdk=${CONFIGURE_JDK}
                        deploy_application=${DEPLOY_APPLICATION}
                        package_file_location=${env.JAR_LOCATION}\""
                }
            }
        }
    }
}