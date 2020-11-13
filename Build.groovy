pipeline {
   agent any
        parameters {
        string(defaultValue: "", name: 'Directory');
        string(defaultValue: "", name: 'GIT')
        }
        stages {
            stage('Build'){
                steps{
                   script{
                        echo "${Directory}"
                        echo "${GIT}"
                        dir("${Directory}\\${GIT}"){
                        echo "inizio della build..."
                        sh "mvn clean package"
                        echo "Fine della build"
                       }
                        
                    }
                }
            }
        }
    }
