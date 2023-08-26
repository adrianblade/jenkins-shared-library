import com.adr.jenkins.quickstarter.IContext
import com.adr.jenkins.stages.DeployStage

def call(IContext context, Map config = [:]) {
    def stage = new DeployStage(this, context, config)
    stage.execute()
}
return this