import com.adr.jenkins.compiler.CompilerFactory
import com.adr.jenkins.quickstarter.IContext
import com.adr.jenkins.quickstarter.CompilerStage

def call(IContext context, Map config = [:]) {
    def tool = CompilerFactory.for(context.compiler, this)
    def stage = new CompilerStage(this, context, config, tool)
    stage.execute()
}
return this