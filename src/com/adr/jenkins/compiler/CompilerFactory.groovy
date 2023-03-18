package com.adr.jenkins.compiler

class CompilerFactory {
    static Compiler "for"(def type, def script) {
        switch (type) {
            case 'maven':
                return new Maven(script)
            case 'gradle':
                return new Gradle(script)
        }
    }
}