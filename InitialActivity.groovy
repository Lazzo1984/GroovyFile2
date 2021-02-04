def folder = "${GIT}"
def Directory = "${Directory}"
def JBOSS = "${JBOSS}"
def JBOSSWAR = "${JBOSSWAR}"
def Folder = "${Folder}"
pipeline {
    agent any
        stages{
        stage('Create Folder') { 
        steps{
            script{    
             echo "Check se la Cartella ${folder} esiste----------------------"
             dir("${Directory}"){
                try{
                    sh "pwd"
                    sh "pwd"
                    sh "rm -rf ${folder}"
                    } catch(err) {
                    echo "---Se la Cartella ${folder} non esiste proseguo------------"
                    echo err.getMessage()
                   }
                  echo "---Creazione della Cartella ---"
                   sh "mkdir ${folder}"
                }
            }
        }
    }
        stage('Download branch from Git'){
            steps{
                script{
                   dir("${Directory}/${folder}") {
                    echo "Download del branch selezionato da Git"
                    git branch:'${GIT}',url:'https://github.com/Lazzo1984/DockerMavenProject.git'
                    echo "Fine Download da Git"
                    }
                }
            }
        }
        stage('Richiamo Job Build'){    
            steps{
                script{
                    build job: 'GroovyBuild',
                        parameters:
                        [[$class:'StringParameterValue',name:'Directory',value:String.valueOf(Directory)],
                        [$class:'StringParameterValue',name:'GIT',value:String.valueOf(GIT)]]
                        wait: true
                    }
                }
            }
          stage('Richiamo Job Deploy'){    
            steps{
                script{
                    build job: 'GroovyDeploy',
                        parameters:
                        [[$class:'StringParameterValue',name:'Directory',value:String.valueOf(Directory)],
                        [$class:'StringParameterValue',name:'GIT',value:String.valueOf(GIT)],
                        [$class:'StringParameterValue',name:'JBOSS',value:String.valueOf(JBOSS)],
                        [$class:'StringParameterValue',name:'JBOSSWAR',value:String.valueOf(JBOSSWAR)]]
                        wait: true
                    }
                }
            }
        stage('Richiamo Job RestartJboss'){    
            steps{
                script{
                    build job: 'GroovyRestart',
                        parameters:
                        [[$class:'StringParameterValue',name:'JBOSS',value:String.valueOf(JBOSS)]]
                        wait: true
                    }
                }
            }
         stage('Richiamo Job Promote'){    
            steps{
                script{
                    build job: 'GroovyPromote',
                        parameters:
                        [[$class:'StringParameterValue',name:'Directory',value:String.valueOf(Directory)],
                        [$class:'StringParameterValue',name:'GIT',value:String.valueOf(GIT)],
                        [$class:'StringParameterValue',name:'JBOSS',value:String.valueOf(JBOSS)],
                        [$class:'StringParameterValue',name:'JBOSSWAR',value:String.valueOf(JBOSSWAR)],
                         [$class:'StringParameterValue',name:'Folder',value:String.valueOf(Folder)]]
                        wait: true
                    }
                }
            }
            
        }
        post {
        failure {
            mail to: 'stefano.lazzati1984@gmail.com',
            subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
            body: "Build failed: ${env.BUILD_URL}"
        }
        success {
            script{
            if (currentBuild.result == 'SUCCESS') {
                mail to: 'stefano.lazzati1984@gmail.com',
                subject: "Pipeline Success: ${currentBuild.fullDisplayName}",
                body: "Build Success: ${env.BUILD_URL}"     
                }
            }
        }

    }
}
