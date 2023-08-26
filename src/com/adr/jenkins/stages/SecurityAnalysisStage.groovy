package com.adr.jenkins.stages

import com.adr.jenkins.quickstarter.IContext
import com.adr.jenkins.quickstarter.Stage

class SecurityAnalysisStage extends Stage {

    protected String STAGE_NAME = 'Security Analysis'

    SecurityAnalysisStage(def script, IContext context, Map config = [:]) {
        super(script, context, config)
    }

    def run() {}
}