package com.adr.jenkins.compiler

interface TaskAutomationTool {
    def build(Map args)
    def runUnitTests(Map args)
    def runIntegrationTests(Map args)
}