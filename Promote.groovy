JBOSS = "${JBOSS}"
JBOSSWAR="${JBOSSWAR}"
GIT="${GIT}"
Cartella="${Cartella}"
Ditrectory="${Directory}"
Path="${Directory}\\${GIT}\\target"
PathCondiviso="../../../Build"
pipeline {
    agent any
        stages{
        stage('Deploy application') { 
        steps{
         script{  
                    sh "pwd"
                    dir("${Cartella}"){
                    sh "cp ${Path}//${JBOSSWAR} ${PathCondiviso}"
                    }
                }
            }
        }
    }
}
