FROM jenkins/jenkins:latest

RUN jenkins-plugin-cli --plugins "git job-dsl pipeline-groovy-lib workflow-multibranch workflow-job workflow-aggregator pipeline-stage-view config-file-provider docker-workflow docker-plugin"

USER root
# Install Docker
# prerequisites for docker
RUN apt-get update && apt-get -y install ca-certificates curl gnupg

# docker repos
RUN apt-get update && \
    apt-get -qy full-upgrade && \
    apt-get install -qy curl && \
    apt-get install -qy curl && \
    curl -sSL https://get.docker.com/ | sh

# docker
RUN apt-get -y install docker-ce
RUN usermod -aG docker jenkins

USER jenkins
ENV JENKINS_USER admin
ENV JENKINS_PASS admin

ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

COPY docker/SharedLibraries.groovy /usr/share/jenkins/ref/init.groovy.d/
COPY docker/CreateJobWithSharedLibrary.groovy /usr/share/jenkins/ref/init.groovy.d/
COPY docker/CreateJob.groovy /usr/share/jenkins/ref/init.groovy.d/
COPY docker/CreateJobWithDocker.groovy /usr/share/jenkins/ref/init.groovy.d/

VOLUME /var/jenkins_home

USER jenkins