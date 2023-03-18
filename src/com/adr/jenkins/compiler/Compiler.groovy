package com.adr.jenkins.compiler

interface Compiler {
    def build(Map args)
    def runUnitTests(Map args)
    def runIntegrationTests(Map args)
}