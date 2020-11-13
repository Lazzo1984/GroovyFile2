pipeline {
    agent any
        parameters {
        string(defaultValue: "", name: 'JBOSS');
        }
        stages{
            stage('Stop&Start Server Jboss') { 
                steps{
                    script{    
                        echo "Script che esegue lo stop start del Server Jboss"
                        sh "pwd"
                        //sh "cd ../../../../opt/jboss/wildfly/bin"
                        dir("${JBOSS}"){
                            sh "pwd"
                            echo "Script che esegue lo stop start del Server Jboss"
                            sh "./jboss-cli.sh --connect --command=:reload"
                        }
                    }
                }
            }
        }
    }