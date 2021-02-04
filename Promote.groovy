JBOSS = "${JBOSS}"
JBOSSWAR="${JBOSSWAR}"
GIT="${GIT}"
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
                    dir("${JENKINS_HOME}"){
                    sh "mkdir -p Build"
                    sh "cp ${Path}/${JBOSSWAR} ${PathCondiviso}"
                    }
                }
            }
        }
    }
}
