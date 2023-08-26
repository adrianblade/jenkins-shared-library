import com.adr.jenkins.quickstarter.IContext
import com.adr.jenkins.stages.DockerBuildAndPushStage

def call(IContext context, Map config = [:]) {
    def stage = new DockerBuildAndPushStage(this, context, config)
    stage.execute()
}
return this