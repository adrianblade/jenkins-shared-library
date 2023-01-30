package com.adr.jenkins

import mocks.PipelineScript
import spock.lang.Specification

class GitShould extends Specification {
    private Git git
    private def script

    void setup() {
        script = new PipelineScript()
        git = new Git(script)
    }

    def 'return true when branch is develop'() {
        given:
        script.env = new Object() {
            String BRANCH_NAME = "develop"
        }

        expect:
        git.isDevelopBranch()
    }
}