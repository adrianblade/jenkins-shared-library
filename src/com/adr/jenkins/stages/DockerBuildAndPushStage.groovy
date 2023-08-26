package com.adr.jenkins.stages

import com.adr.jenkins.quickstarter.IContext
import com.adr.jenkins.quickstarter.Stage

class DockerBuildAndPushStage extends Stage {

    protected String STAGE_NAME = 'DockerBuildAndPush'

    DockerBuildAndPushStage(def script, IContext context, Map config = [:]) {
        super(script, context, config)
    }

    def run() {}
}