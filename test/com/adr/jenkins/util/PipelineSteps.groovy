package com.adr.jenkins.util

import groovy.util.logging.Slf4j
import com.adr.jenkins.util.IPipelineSteps


@Slf4j
class PipelineSteps implements IPipelineSteps {

    private Map currentBuild = [:]
    private Map env = [:]

    PipelineSteps() {
        env.WORKSPACE = System.getProperty("java.io.tmpdir")
    }

    void archiveArtifacts(String artifacts) {
    }

    void archiveArtifacts(Map args) {
    }

    def checkout(Map config) {
        return [:]
    }

    void dir(String path, Closure block) {
        block()
    }

    void error(String message) {
    }

    void echo(String message) {
        log.info(message)
    }

    def getCurrentBuild() {
        return currentBuild
    }

    Map getEnv() {
        return env
    }

    void junit(String path) {
    }

    void junit(Map config) {
    }

    def load(String path) {
        return [:]
    }

    def sh(def args) {
        return ""
    }

    void stage(String name, Closure block) {
        block()
    }

    void stash(String name) {
    }

    void stash(Map config) {
    }

    void unstash(String name) {
    }

    @Override
    def fileExists(String file) {
        return null
    }

    @Override
    def readFile(String file, String encoding) {
        return null
    }

    @Override
    def readFile(Map args) {
        log.debug("readFile file: ${env.WORKSPACE}/${args.file}")
        try {
            return new File("${env.WORKSPACE}/${args.file}")?.text
        } catch(Exception exception){
            return ""
        }
    }

    @Override
    def writeFile(String file, String text, String encoding) {
        return null
    }

    @Override
    def writeFile(Map args) {
        return null
    }

    @Override
    def readJSON(Map args) {
        log.debug("readJSON file: ${args.text}")
    }

    @Override
    def readYaml(Map args) {
        
    }

    

    @Override
    def writeYaml(Map args) {
        return null
    }

    @Override
    def writeJSON(Map args) {
        return null
    }

    @Override
    def timeout(Map args, Closure block) {
        return null
    }

    @Override
    def deleteDir() {
        return null
    }

    def sleep(int seconds) {
        return null
    }

    @Override
    def withEnv(java.util.List env, groovy.lang.Closure block) {
      block()
    }

    @Override
    def unstable(String message) {
    }

    def usernamePassword(Map credentialsData) {
    }

    def sshUserPrivateKey(Map credentialsData) {
    }

    def string(Map credentialsData) {
    }

    def withCredentials(List credentialsList, Closure block) {
      block()
    }

    def get(def key) {
      return currentBuild.get(key)
    }

    def put(def key, def value) {
      currentBuild.put(key, value)
    }

    def unwrap() {
      return [:]
    }

    def node (String name, Closure block) {
      block ()
    }

    def emailext(Map args) {

    }

    def findFiles (Map args) {
        return []
    }

}