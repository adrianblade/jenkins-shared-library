FROM jenkins/jenkins:latest

RUN jenkins-plugin-cli --plugins "git job-dsl pipeline-groovy-lib workflow-multibranch workflow-job workflow-aggregator pipeline-stage-view config-file-provider"

ENV JENKINS_USER admin
ENV JENKINS_PASS admin

ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

COPY docker/SharedLibraries.groovy /usr/share/jenkins/ref/init.groovy.d/
COPY docker/CreateJobWithSharedLibrary.groovy /usr/share/jenkins/ref/init.groovy.d/
COPY docker/CreateJob.groovy /usr/share/jenkins/ref/init.groovy.d/

VOLUME /var/jenkins_home

USER jenkins