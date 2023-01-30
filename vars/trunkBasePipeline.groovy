def call() {

    pipeline {
        options {
            disableConcurrentBuilds()
            timestamps()
            ansiColor('xterm')
            buildDiscarder(logRotator(numToKeepStr: '10'))
            skipDefaultCheckout(true)
        }
        agent any

        stages {
            stage('Checkout') {
                steps {
                    deleteDir()
                    script {
                        this.sh 'echo hello'
                    }
                }
            }
        }
    }

}