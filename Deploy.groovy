JBOSS = "${JBOSS}"
JBOSSWAR="${JBOSSWAR}"
GIT="${GIT}"
Ditrectory="${Directory}"
Path="${Directory}\\${GIT}\\target"
pipeline {
    agent any
        stages{
        stage('Deploy application') { 
        steps{
         script{    
             echo "Script che esegue il deploy dell'applicazione del Server Jboss"
                    sh "pwd"
                    dir("${Path}"){
                    echo "Script che esegue lo stop start del Server Jboss"
                    sh '${JBOSS}/jboss-cli.sh -c --command="deploy ${JBOSSWAR} --force"'
                    }
                }
            }
        }
    }
}
