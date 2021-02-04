JBOSS = "${JBOSS}"
JBOSSWAR="${JBOSSWAR}"
GIT="${GIT}"
Folder = "${Folder}"
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
                    dir("${Folder}"){
                    sh "cp ${Path}/${JBOSSWAR} ${PathCondiviso}"
                    }
                }
            }
        }
    }
}
