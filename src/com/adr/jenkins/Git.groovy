package com.adr.jenkins

class Git implements Serializable {

    private final def script

    Git(def script) {
        this.script = script
    }

    boolean isDevelopBranch() {
        script.env.BRANCH_NAME == 'develop'
    }
}