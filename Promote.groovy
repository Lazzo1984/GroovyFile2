JBOSS = "${JBOSS}"
JBOSSWAR="${JBOSSWAR}"
GIT="${GIT}"
Cartella="${Cartella}"
Ditrectory="${Directory}"
Path="${Directory}/${GIT}/target"
PathCondiviso="../../shared_directory"
pipeline {
    agent any
        stages{
        stage('Deploy application') { 
        steps{
         script{  
                    sh "pwd"
                    dir("${Cartella}"){
                    sh "mkdir -p ../../Build"
                    sh "cp ${Path}/${JBOSSWAR} ${PathCondiviso}"
                    }
                }
            }
        }
    }
}
