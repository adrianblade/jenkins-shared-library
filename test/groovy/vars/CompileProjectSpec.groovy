package vars

import com.adr.jenkins.quickstarter.Context
import com.adr.jenkins.quickstarter.IContext
import vars.test_helper.PipelineSpockTestBase
import spock.lang.*

class CompileProjectSpec extends PipelineSpockTestBase {

  def "run successfully"() {
    given:
    def config = [
        compiler: 'gradle',
        gitBranch: 'master'
    ]
    IContext context = new Context(config)
    def configuration = [
            configFileId: 'abcd'
    ]

    when:
    def script = loadScript('vars/compileProject.groovy')
    script.call(context, configuration)

    then:
    printCallStack()
    assertJobStatusSuccess()
  }

}