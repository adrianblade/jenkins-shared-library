package com.adr.jenkins.quickstarter

import com.adr.jenkins.compiler.Compiler

class UnitTestStage extends Stage {

    protected String STAGE_NAME = 'Unit Testing'

    private final Compiler compiler

    UnitTestStage(def script, IContext context, Map config = [:], Compiler compiler) {
        super(script, context, config)
        this.compiler = compiler
    }

    def run() {
        def tasks = compiler.runUnitTests(command: config.command, configFileId: config.configFileId)
        tasks.closure()
    }

}