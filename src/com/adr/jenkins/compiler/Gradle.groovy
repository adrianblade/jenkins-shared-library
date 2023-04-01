package com.adr.jenkins.compiler

class Gradle implements Serializable, Compiler {

    private final def script

    Gradle(def script) {
        this.script = script
    }

    def build(Map args) {
        [
            stageName : 'Build',
            closure   : {
                runWithTemplateSetup(command: args.command, configFileId: args.configFileId)
                script.stash includes: 'buildFolder', name: 'build', allowEmpty: true
            },
            whenBranch: args.whenBranch
        ]
    }

    def runUnitTests(Map args) {
        [
            stageName : 'Unit tests',
            closure   : {
                script.unstash 'build'
                runWithTemplateSetup(command: args.command, configFileId: args.configFileId)
                script.stash includes: 'testFolder', name: 'unit-tests', allowEmpty: true
            },
            whenBranch: args.whenBranch,
            post      : { script.junit script.outputFolders.test }
        ]
    }

    def runIntegrationTests(Map args) {
        [
            stageName : args.stageName ?: 'Integration tests',
            closure   : {
                script.unstash 'build'
                runWithTemplateSetup(command: args.command, configFileId: args.configFileId)
                script.stash includes: 'integrationFolder', name: 'integration-tests', allowEmpty: true
            },
            whenBranch: args.whenBranch,
            post      : { script.junit script.outputFolders.integration }
        ]
    }

    def runWithTemplateSetup(Map args) {
        if (args.configFileId != null) {
            script.configFileProvider([
                script.configFile(fileId: args.configFileId, variable: 'GRADLE_PROPERTIES')
            ]) {
                runCommand(args)
            }
        } else {
            runCommand(args)
        }
    }

    private def runCommand(Map args) {
        def gradleHome = gradleHome()
        script.sh(script: "mv ${gradleHome}@tmp/config* ${gradleHome}@tmp/gradle.properties")
        script.sh(script: "${args.command.trim()} ${gradleHomeParameter(gradleHome)}")
    }

    private def gradleHome(){
        this.script.sh(script: 'pwd', returnStdout: true).trim()
    }

    private static String gradleHomeParameter(def gradleHome) {
        "-Dgradle.user.home='${gradleHome}@tmp'"
    }
}