package com.adr.jenkins.quickstarter

import com.cloudbees.groovy.cps.NonCPS

class Context implements IContext {

    private final Map config

    Context(Map config) {
        this.config = config
    }

    @NonCPS
    String getJobName() {
        config.jobName
    }

    @NonCPS
    String getBuildNumber() {
        config.buildNumber
    }

    @NonCPS
    String getBuildUrl() {
        config.buildUrl
    }

    @NonCPS
    String getBuildTime() {
        config.buildTime
    }

    @NonCPS
    String getDockerRegistry() {
        config.dockerRegistry
    }

    @NonCPS
    String getProjectId() {
        config.projectId
    }

    @NonCPS
    String getComponentId() {
        config.componentId
    }

    @NonCPS
    String getCompiler() {
        config.compiler
    }

    @NonCPS
    String getGitBranch() {
        config.gitBranch
    }
}