JBOSS = "${JBOSS}"
JBOSSWAR="${JBOSSWAR}"
GIT="${GIT}"
Folder = "${Folder}"
Ditrectory="${Directory}"
Path="${Directory}\\${GIT}\\target"
PathCondiviso="../../../Build"
Build="${Build}"
pipeline {
    agent any
        stages{
        stage('Deploy application') { 
        steps{
         script{  
                    sh "pwd"
                    dir("${Folder}"){
                    sh "mkdir -p ${Build}"
                    sh "cp ${Path}/${JBOSSWAR} ${PathCondiviso}"
                    }
                }
            }
        }
    }
}
