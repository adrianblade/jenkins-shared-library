package com.adr.jenkins.compiler

import mocks.PipelineScript
import spock.lang.Specification

class CompilerFactoryShould extends Specification {

    private def script

    void setup() {
        script = Spy(PipelineScript)
    }

    def 'returns maven tool when type provided is maven'() {
        when:
        def tool = CompilerFactory.for('maven', script)

        then:
        tool.getClass() == Maven
    }

    def 'returns gradle tool when type provided is gradle'() {
        when:
        def tool = CompilerFactory.for('gradle', script)

        then:
        tool.getClass() == Gradle
    }
}