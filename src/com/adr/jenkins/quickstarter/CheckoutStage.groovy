package com.adr.jenkins.quickstarter

class CheckoutStage extends Stage {

    protected String STAGE_NAME = 'Checkout'

    CheckoutStage(def script, IContext context, Map config = [:]) {
        super(script, context, config)
    }

    def run() {
        script.checkout script.scm
    }

}