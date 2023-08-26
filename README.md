<div align="center">
  <h1>🦝 Rocket: Jenkins Shared Library 🦝</h1>
  <strong>Shared libraries for your Jenkins</strong>
</div>
<br>

## 🚀 Installation

## 🐳 Try it in Docker

```bash
    ~ docker run -it -e JAVA_OPTS="-Djenkins.install.runSetupWizard=false" -p 8080:8080 jenkins/jenkins:latest
    ~ docker build -t jenkins:test . && docker run -it -u root -v /var/run/docker.sock:/var/run/docker.sock -p 8080:8080 jenkins:test

    ~ docker build -t jenkins:test . && docker run -it -u root -v /var/run/docker.sock:/var/run/docker.sock -p 8080:8080 jenkins:test
    
```

https://github.com/adrianblade/jenkins-shared-library.git

## Testing on jenkins

Global Shared Libraries
There are several places where Shared Libraries can be defined, depending on the use-case. Manage Jenkins » System » Global Pipeline Libraries as many libraries as necessary can be configured.

## 💻 Usage

```bash
    ~ ./gradlew build
```

```bash
├── 📁 examples
├── 📁 src
├── 📁 var
├── 📁 test
```

## 🤝 Contributing


https://github.com/cloudogu/ces-build-lib/blob/6fc99fcf7aa3f1993fcf8784c3fd0391b62d6b76/src/com/cloudogu/ces/cesbuildlib/Git.groovy

https://github.com/opendevstack/ods-jenkins-shared-library/tree/master/src/org/ods
