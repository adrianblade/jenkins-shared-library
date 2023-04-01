package com.adr.jenkins.quickstarter

import com.adr.jenkins.compiler.Compiler

class CompilerStage extends Stage {

    protected String STAGE_NAME = 'Compile'

    private final Compiler compiler

    CompilerStage(def script, IContext context, Map config = [:], Compiler compiler) {
        super(script, context, config)
        this.compiler = compiler
    }

    def run() {
        def tasks = compiler.build(command: config.command, configFileId: config.configFileId)
        tasks.closure()
    }

}