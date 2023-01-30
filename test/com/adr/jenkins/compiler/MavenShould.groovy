package com.adr.jenkins.compiler

import mocks.PipelineScript
import spock.lang.Specification

class MavenShould extends Specification {

    private Maven maven
    private def script

    void setup() {
        script = Spy(PipelineScript)
        maven = new Maven(script)
    }

    def 'build and stash the project with proper settings file'() {
        when:
        def tasks = maven.build(command: 'executeMe', configFileId: 'theRightFile')
        tasks.closure()

        then:
        1 * script.configFile(['fileId': 'theRightFile', 'variable': 'MAVEN_GLOBAL_SETTINGS'])
        1 * script.configFileProvider(['string'], _ as Closure)
        1 * script.sh([script: 'executeMe -gs $MAVEN_GLOBAL_SETTINGS'])
        1 * script.stash([includes: 'buildFolder', name: 'build', allowEmpty: true])
    }

    def 'retrieve the build, then execute and stash the unit test with the proper settings file'() {
        when:
        def tasks = maven.runUnitTests(command: 'executeMe', configFileId: 'theRightFile')
        tasks.closure()
        tasks.post()

        then:
        1 * script.unstash('build')
        1 * script.configFile(['fileId': 'theRightFile', 'variable': 'MAVEN_GLOBAL_SETTINGS'])
        1 * script.configFileProvider(['string'], _ as Closure)
        1 * script.sh([script: 'executeMe -gs $MAVEN_GLOBAL_SETTINGS'])
        1 * script.stash([includes: 'testFolder', name: 'unit-tests', allowEmpty: true])
        1 * script.junit('testFolder')
    }

    def 'retrieve the build and unit test, then execute and stash the integration test with the proper settings file'() {
        when:
        def tasks = maven.runIntegrationTests(command: 'executeMe', configFileId: 'theRightFile')
        tasks.closure()
        tasks.post()

        then:
        1 * script.unstash('build')
        0 * script.unstash('unit-tests')
        1 * script.configFile(['fileId': 'theRightFile', 'variable': 'MAVEN_GLOBAL_SETTINGS'])
        1 * script.configFileProvider(['string'], _ as Closure)
        1 * script.sh([script: 'executeMe -gs $MAVEN_GLOBAL_SETTINGS'])
        1 * script.stash([includes: 'integrationFolder', name: 'integration-tests', allowEmpty: true])
        1 * script.junit('integrationFolder')
    }

    def 'integration test stage name can be provided'() {
        when:
        def tasks = maven.runIntegrationTests(stageName: 'Custom integration tests', command: 'executeMe', configFileId: 'theRightFile')
        tasks.closure()
        tasks.post()

        then:
        tasks.get('stageName')  == 'Custom integration tests'
    }

    def 'integration test stage name has a default if it is not provided'() {
        when:
        def tasks = maven.runIntegrationTests( command: 'executeMe', configFileId: 'theRightFile')
        tasks.closure()
        tasks.post()

        then:
        tasks.get('stageName')  == 'Integration tests'
    }
}
