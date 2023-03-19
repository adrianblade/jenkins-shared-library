<div align="center">
  <h1>🦝 Rocket: Jenkins Shared Library 🦝</h1>
  <strong>Shared libraries for your Jenkins</strong>
</div>
<br>

## 🚀 Installation

## 🐳 Try it in Docker

```bash
    ~ docker run -it -e JAVA_OPTS="-Djenkins.install.runSetupWizard=false" -p 8080:8080 jenkins/jenkins:latest
    ~ docker build -t jenkins:test . && docker run -it -p 8080:8080 jenkins:test
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
