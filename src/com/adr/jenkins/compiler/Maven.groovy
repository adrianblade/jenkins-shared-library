package com.adr.jenkins.compiler

class Maven implements Serializable, Compiler {

    private final def script

    Maven(def script) {
        this.script = script
    }

    def build(Map args) {
        [
            stageName: 'Build',
            closure  : {
                runWithTemplateSetup(command: args.command, configFileId: args.configFileId)
                script.stash includes: script.outputFolders.build, name: 'build', allowEmpty: true
            },
            whenBranch: args.whenBranch
        ]
    }

    def runUnitTests(Map args) {
        [
            stageName: 'Unit tests',
            closure  : {
                script.unstash 'build'
                runWithTemplateSetup(command: args.command, configFileId: args.configFileId)
                script.stash includes: script.outputFolders.test, name: 'unit-tests', allowEmpty: true
            },
            whenBranch: args.whenBranch,
            post     : { script.junit script.outputFolders.test }
        ]
    }

    def runIntegrationTests(Map args) {
        [
            stageName : args.stageName ?: 'Integration tests',
            closure   : {
                script.unstash 'build'
                runWithTemplateSetup(command: args.command, configFileId: args.configFileId)
                script.stash includes: script.outputFolders.integration, name: 'integration-tests', allowEmpty: true
            },
            whenBranch: args.whenBranch,
            post      : { script.junit script.outputFolders.integration }
        ]
    }

    def runWithTemplateSetup(Map args) {
        script.configFileProvider(
                [
                        script.configFile(fileId: args.configFileId, variable: 'MAVEN_GLOBAL_SETTINGS')
                ]
        ) {
            script.sh(script: "${args.command.trim()} -gs \$MAVEN_GLOBAL_SETTINGS")
        }
    }
}