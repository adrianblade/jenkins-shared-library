package com.adr.jenkins.notifications

import groovy.json.JsonOutput

class Telegram implements Serializable {

    private final def script
    private final String telegramTokenCredentialId
    private final String telegramChatIdCredentialId

    Telegram(def script, String telegramTokenCredentialId, String telegramChatIdCredentialId) {
        this.script = script
        this.telegramTokenCredentialId = telegramTokenCredentialId
        this.telegramChatIdCredentialId = telegramChatIdCredentialId
    }

    void sendStatusFail() {
        sendStatus('Fail')
    }

    void sendStatusOk() {
        sendStatus('OK')
    }

    void sendStatus(def status) {
        def icon = ''
        if (status.toLowerCase() == 'ok') {
            icon = '🎉'
        }
        if (status.toLowerCase() == 'fail') {
            icon = '🌧'
        }

        //  Params:        ${JsonOutput.toJson(params ? params : [])}
        def message = """-------------------------------------
        ${icon} Jenkins build: *${status.toUpperCase()}!*
        -------------------------------------
        Repository:    ${script.env.JOB_NAME}
        Branch:        ${script.env.BRANCH_NAME}
        *Commit Msg:*
        ...TODO:

        [Job Log here](${script.env.BUILD_URL}/consoleText)
        --------------------------------------"""

        send([message: message])
    }

    void send(Map args) {
        try {
        sh """
            curl -s -X POST https://api.telegram.org/bot${telegramTokenCredentialId}/sendMessage \
            -d chat_id=${telegramChatIdCredentialId} \
            -d parse_mode="${args.parseMode ? args.parseMode : 'Markdown'}" \
            -d text="${args.message ? args.message : 'Telegram bot is alive!'}"
        """
        } catch (Exception ex) {
        
        }
    }

}