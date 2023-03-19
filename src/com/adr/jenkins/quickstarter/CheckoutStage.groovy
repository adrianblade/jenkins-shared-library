package com.adr.jenkins.quickstarter

class CheckoutStage extends Stage {

    protected String STAGE_NAME = 'Checkout'

    CheckoutStage(def script, IContext context, Map config = [:]) {
        super(script, context, config)
    }

    def run() {
        script.sh "echo ${config}"
        script.sh "echo ${context}"
        script.checkout([
            $class: 'GitSCM', 
            branches: [[name: '*/master']], 
            doGenerateSubmoduleConfigurations: false, 
            extensions: [[$class: 'CleanCheckout']], 
            submoduleCfg: [], 
            userRemoteConfigs: [[url: config.gitUrlHttp]]
        ])
    }

}