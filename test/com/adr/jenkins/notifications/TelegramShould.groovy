package com.adr.jenkins.notifications

import mocks.PipelineScript
import spock.lang.Specification

class TelegramShould extends Specification {
    private Telegram telegram
    private def script

    void setup() {
        script = new PipelineScript()
        telegram = new Telegram(script, "", "")
    }

    def 'notify to telegram'() {
        given:
        script.env = new Object() {
            String BRANCH_NAME = "develop"
            String JOB_NAME = "job_name"
            String BUILD_URL = "build_url"
        }

        expect:
        telegram.sendStatusOk()
    }
}