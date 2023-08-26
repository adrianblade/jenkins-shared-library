package com.adr.jenkins.quickstarter

import com.adr.jenkins.util.Logger
import com.adr.jenkins.util.ILogger

class Pipeline implements Serializable {

    private final def script
    private final Map config
    private final ILogger logger

    Pipeline(def script, Map config) {
        this.script = script
        this.config = config
        this.logger = new Logger(script, false)
    }

    @SuppressWarnings(['AbcMetric', 'UnnecessaryObjectReferences'])
    def execute(Closure block) {
        // build params
        //checkRequiredBuildParams()
        config.imageTag = script.env.IMAGE_TAG ?: 'latest'
        config.gitRef = script.env.GIT_REF ?: 'main'
        config.agentImageTag = script.env.AGENT_IMAGE_TAG ?: config.imageTag
        config.sharedLibraryRef = script.env.SHARED_LIBRARY_REF ?: config.imageTag
        //config.projectId = script.env.PROJECT_ID.toLowerCase()
        //config.componentId = script.env.COMPONENT_ID.toLowerCase()
        config.gitUrlHttp = script.env.GIT_URL_HTTP ?: config.gitUrlHttp
        config.packageName = script.env.PACKAGE_NAME
        config.group = script.env.GROUP_ID
        config.openShiftProject = "${config.projectId}-cd"

        // config options
        if (!config.sourceDir) {
            // Extract folder name from e.g. "be-golang-plain/Jenkinsfile"
            //def jenkinsfilePath = script.currentBuild?.rawBuild?.parent?.definition?.scriptPath.toString()
            def jenkinsfilePath = "be-golang-plain/Jenkinsfile"
            def jenkinsfilePathParts = jenkinsfilePath.split('/')
            if (jenkinsfilePathParts.size() >= 2) {
                config.sourceDir = jenkinsfilePathParts[-2]
            } else {
                script.error "Config option 'sourceDir' is required but not given!"
            }
        }
        if (!config.image && !config.imageStreamTag && !config.podContainers) {
            script.error "One of 'image', 'imageStreamTag' or 'podContainers' is required but not given!"
        }
        if (!config.targetDir) {
            // Use componentId as some build tools (e.g. sbt) need to work in a directory
            // having the same name as the application they target.
            config.targetDir = config.componentId
        }
        if (!config.podVolumes) {
            config.podVolumes = []
        }
        /*if (!config.annotations) {
            config.annotations = [
                script.podAnnotation(
                    key: 'cluster-autoscaler.kubernetes.io/safe-to-evict', value: 'false'
                    )
                ]
        }*/

        // vars from jenkins master
        script.node {
            config.jobName = script.env.JOB_NAME
            config.buildNumber = script.env.BUILD_NUMBER
            config.buildUrl = script.env.BUILD_URL
            config.buildTime = new Date()
            config.dockerRegistry = script.env.DOCKER_REGISTRY ?: 'docker-registry.default.svc:5000'
        }

        onAgentNode(config) { context ->
            new CheckoutStage(script, context, config).execute()
            
            // Execute user-defined stages.
            block(context)
        }
    }

    private checkRequiredBuildParams() {
        def requiredParams = ['PROJECT_ID', 'COMPONENT_ID', 'GIT_URL_HTTP']
        for (def i = 0; i < requiredParams.size(); i++) {
            def param = requiredParams[i]
            if (!script.env[param]) {
                script.error "Build param '${param}' is required but not given!"
            }
        }
    }

    private onAgentNode(Map config, Closure block) {
        if (!config.podContainers) {
            if (!config.containsKey('alwaysPullImage')) {
                config.alwaysPullImage = true
            }
            if (!config.podServiceAccount) {
                config.podServiceAccount = 'jenkins'
            }
            if (!config.resourceRequestMemory) {
                config.resourceRequestMemory = '512Mi'
            }
            if (!config.resourceLimitMemory) {
                config.resourceLimitMemory = '1Gi'
            }
            if (!config.resourceRequestCpu) {
                config.resourceRequestCpu = '100m'
            }
            if (!config.resourceLimitCpu) {
                config.resourceLimitCpu = '1'
            }
            if (!config.image) {
                config.image = "${config.dockerRegistry}/${config.imageStreamTag}"
            }
            /*config.podContainers = [
                script.containerTemplate(
                    name: 'jnlp',
                    image: config.image,
                    workingDir: '/tmp',
                    alwaysPullImage: config.alwaysPullImage,
                    resourceRequestMemory: config.resourceRequestMemory,
                    resourceLimitMemory: config.resourceLimitMemory,
                    resourceRequestCpu: config.resourceRequestCpu,
                    resourceLimitCpu: config.resourceLimitCpu,
                    args: ''
                )
            ]*/
        }

        def podLabel = "qs-${UUID.randomUUID()}"

        /*script.podTemplate(
            label: podLabel,
            cloud: 'kubernetes',
            containers: config.podContainers,
            volumes: config.podVolumes,
            serviceAccount: config.podServiceAccount,
            annotations: config.annotations,
        ) {
            script.node(podLabel) {
                IContext context = new Context(config)
                block(context)
            }
        }*/

        //DELETE
        debugLog("Running in docker image ${config.imageStreamTag}")
        
        script.node() {
                IContext context = new Context(config)
                block(context)
        }
    }

    private def debugLog(String msg) {
        return script.echo("DEBUG: ${msg}")
        
    }

}