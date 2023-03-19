FROM jenkins/jenkins:lts-jdk11

RUN jenkins-plugin-cli --plugins "git job-dsl kubernetes pipeline-groovy-lib saml ssh-slaves workflow-multibranch"

ENV JENKINS_USER admin
ENV JENKINS_PASS admin

ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

COPY docker/SharedLibraries.groovy /usr/share/jenkins/ref/init.groovy.d/
COPY docker/CreateJob.groovy /usr/share/jenkins/ref/init.groovy.d/

VOLUME /var/jenkins_home

USER jenkins