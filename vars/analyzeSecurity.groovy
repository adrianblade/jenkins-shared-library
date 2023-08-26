import com.adr.jenkins.quickstarter.IContext
import com.adr.jenkins.stages.SecurityAnalysisStage

def call(IContext context, Map config = [:]) {
    def stage = new SecurityAnalysisStage(this, context, config)
    stage.execute()
}
return this