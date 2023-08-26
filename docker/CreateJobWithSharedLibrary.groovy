import jenkins.model.Jenkins
import org.jenkinsci.plugins.workflow.job.WorkflowJob
import org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition
import org.jenkinsci.plugins.workflow.flow.FlowDefinition


Jenkins jenkins = Jenkins.instance // saves some typing

// Create the workflow
FlowDefinition flowDefinition = (FlowDefinition) new CpsFlowDefinition("""
@Library('adrianblade-jenkins')

def baseImage = env.BASE_IMAGE ?: 'gradle'
def gitRef = env.GIT_REF ?: 'main'
def imageTag = env.IMAGE_TAG ?: 'latest'

quickstarterPipeline(
  imageStreamTag: "gradle:latest",
  compiler: "gradle",
  gitUrlHttp: "https://github.com/adrianblade/sample-java.git"
) { context ->

  stage('Write something') {
      sh "echo hi!"
  }

  config = [command: "./gradlew build", configFileId: null]
  compile(context, config)

  config = [command: "./gradlew test", configFileId: null]
  unitTest(context, config)

  analyzeCode(context, [])
  analyzeSecurity(context, [])
  buildAndPush(context, [])
  deploy(context, [])

}
""", true)

// Check if the job already exists
 Object job = jenkins.createProject(WorkflowJob, "shared-lib")
  

// Add the workflow to the job
job.setDefinition(flowDefinition)

// Set the branch somehow
job.save()