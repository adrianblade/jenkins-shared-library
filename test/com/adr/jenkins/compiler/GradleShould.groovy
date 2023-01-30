package com.adr.jenkins.compiler

import mocks.PipelineScript
import spock.lang.Specification

class GradleShould extends Specification {
    private Gradle gradle
    private def script

    void setup() {
        script = Spy(PipelineScript)
        script.sh(script: 'pwd', returnStdout: true) >> 'gradleHome\n'
        gradle = new Gradle(script)
    }

    def 'build and stash the project with proper settings file'() {
        when:
        def tasks = gradle.build(command: 'executeMe', configFileId: 'theRightFile')
        tasks.closure()

        then:
        1 * script.configFile(['fileId': 'theRightFile', 'variable': 'GRADLE_PROPERTIES'])
        1 * script.configFileProvider(['string'], _ as Closure)
        1 * script.sh([script: "executeMe -Dgradle.user.home='gradleHome@tmp'"])
        1 * script.stash([includes: 'buildFolder', name: 'build', allowEmpty: true])
    }

    def 'retrieve the build, then execute and stash the unit test with the proper settings file'() {
        when:
        def tasks = gradle.runUnitTests(command: 'executeMe', configFileId: 'theRightFile')
        tasks.closure()
        tasks.post()

        then:
        1 * script.unstash('build')
        1 * script.configFile(['fileId': 'theRightFile', 'variable': 'GRADLE_PROPERTIES'])
        1 * script.configFileProvider(['string'], _ as Closure)
        1 * script.sh([script: "executeMe -Dgradle.user.home='gradleHome@tmp'"])
        1 * script.stash([includes: 'testFolder', name: 'unit-tests', allowEmpty: true])
        1 * script.junit('testFolder')
    }

    def 'retrieve the build and unit test, then execute and stash the integration test with the proper settings file'() {
        when:
        def tasks = gradle.runIntegrationTests(command: 'executeMe', configFileId: 'theRightFile')
        tasks.closure()
        tasks.post()

        then:
        1 * script.unstash('build')
        0 * script.unstash('unit-tests')
        1 * script.configFile(['fileId': 'theRightFile', 'variable': 'GRADLE_PROPERTIES'])
        1 * script.configFileProvider(['string'], _ as Closure)
        1 * script.sh([script: "executeMe -Dgradle.user.home='gradleHome@tmp'"])
        1 * script.stash([includes: 'integrationFolder', name: 'integration-tests', allowEmpty: true])
        1 * script.junit('integrationFolder')
    }

    def 'integration test stage name can be provided'() {
        when:
        def tasks = gradle.runIntegrationTests(stageName: 'Custom integration tests', command: 'executeMe', configFileId: 'theRightFile')
        tasks.closure()
        tasks.post()

        then:
        tasks.get('stageName')  == 'Custom integration tests'
    }

    def 'integration test stage name has a default if it is not provided'() {
        when:
        def tasks = gradle.runIntegrationTests( command: 'executeMe', configFileId: 'theRightFile')
        tasks.closure()
        tasks.post()

        then:
        tasks.get('stageName')  == 'Integration tests'
    }
}