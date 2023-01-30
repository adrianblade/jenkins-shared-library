package com.adr.jenkins.compiler

class TaskAutomationFactory {
    static TaskAutomationTool "for"(def type, def script) {
        switch (type) {
            case 'maven':
                return new Maven(script)
            case 'gradle':
                return new Gradle(script)
        }
    }
}