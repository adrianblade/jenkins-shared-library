import jenkins.model.Jenkins
import org.jenkinsci.plugins.workflow.job.WorkflowJob
import org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition
import org.jenkinsci.plugins.workflow.flow.FlowDefinition
import hudson.plugins.git.GitSCM
import hudson.plugins.git.UserRemoteConfig
import com.cloudbees.hudson.plugins.folder.*

// Bring some values in from ansible using the jenkins_script modules wierd "args" approach (these are not gstrings)
String folderName = "folderName"
String jobName = "jobName"
String jobScript = "jobScript"
String gitRepo = "gitRepo"
String gitRepoName = "gitRepoName"
String credentialsId = "credentialsId"

Jenkins jenkins = Jenkins.instance // saves some typing

// Create the git configuration
UserRemoteConfig userRemoteConfig = new UserRemoteConfig(gitRepo, gitRepoName, null, credentialsId)

branches = null
doGenerateSubmoduleConfigurations = false
submoduleCfg = null
browser = null
gitTool = null
extensions = []
GitSCM scm = new GitSCM([userRemoteConfig], branches, doGenerateSubmoduleConfigurations, submoduleCfg, browser, gitTool, extensions)

// Create the workflow
FlowDefinition flowDefinition = (FlowDefinition) new CpsFlowDefinition("oh my doog", true)

// Check if the job already exists
 Object job = jenkins.createProject(WorkflowJob, jobName)
  

// Add the workflow to the job
job.setDefinition(flowDefinition)

// Set the branch somehow
job.save()