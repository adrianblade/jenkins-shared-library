package mocks;

class PipelineScript {

    def env = [
            BRANCH_NAME      : 'BRANCH_NAME',
            BUILD_URL        : 'BUILD_URL',
            BUILD_NUMBER     : 'BUILD_NUMBER',
            NEXUS_CREDENTIALS: 'NEXUS_USER:NEXUS_PASSWORD'
    ]

    def currentBuild = [
            currentResult: 'DEFAULT_CURRENT_RESULT'
    ]

    def outputFolders = [
            build      : 'buildFolder',
            test       : 'testFolder',
            integration: 'integrationFolder'
    ]

    void stash(Map args) {
    }

    void unstash(String id) {
    }

    void junit(String id) {
    }

    String sh(Map args) {
        return 'DEFAULT_SH_RETURN_VALUE'
    }

    void error(String message) {
        println message
    }

    void slackSend(Map args) {}

    void sshagent(Map args, Closure closure) {
        closure()
    }

    Map httpRequest(Map args) {
        def emptyMap = [:]
        emptyMap
    }

    Map readJSON(Map args) {
        def emptyMap = [:]
        emptyMap
    }

    /**
     * PROTIP: Always pass 'parameters' to force-return a Map, makes life easier.
     * Returns String if no 'parameters' argument is passed.
     * Returns Map if 'parameters' argument is passed.
     * Throws org.jenkinsci.plugins.workflow.steps.FlowInterruptedException when user rejects or when interrupted.
     */
    Map input(Map args) throws Exception {}

    void milestone(int number) {}

    void echo(String) {}

    void withNPM(Map args, Closure closure) {
        closure()
    }

    void configFileProvider(List args, Closure closure) {
        closure()
    }

    def configFile(Map args) {
        "string"
    }
}