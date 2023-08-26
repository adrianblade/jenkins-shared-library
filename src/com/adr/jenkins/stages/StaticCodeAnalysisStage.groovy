package com.adr.jenkins.stages

import com.adr.jenkins.quickstarter.IContext
import com.adr.jenkins.quickstarter.Stage

class StaticAnalysisStage extends Stage {

    protected String STAGE_NAME = 'Static Analysis Code'

    StaticAnalysisStage(def script, IContext context, Map config = [:]) {
        super(script, context, config)
    }

    def run() {}
}