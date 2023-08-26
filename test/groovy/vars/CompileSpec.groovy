package vars

import com.adr.jenkins.quickstarter.Context
import com.adr.jenkins.quickstarter.IContext
import vars.test_helper.PipelineSpockTestBase
import spock.lang.*

class CompileSpec extends PipelineSpockTestBase {

  def "run successfully"() {
    given:
    def config = [
        compiler: 'gradle',
        gitBranch: 'master'
    ]
    IContext context = new Context(config)
    def configuration = [
            configFileId: 'abcd',
            command: './gradlew build'
    ]

    when:
    def script = loadScript('vars/compile.groovy')
    script.call(context, configuration)

    then:
    printCallStack()
    assertJobStatusSuccess()
  }

}