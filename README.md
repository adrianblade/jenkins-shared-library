<div align="center">
  <h1>🦝 Rocket: Jenkins Shared Library 🦝</h1>
  <strong>Shared libraries for your Jenkins</strong>
</div>
<br>

## 🚀 Installation

## 🐳 Try it in Docker

```bash
    ~ docker run -it -e JAVA_OPTS="-Djenkins.install.runSetupWizard=false" -p 8080:8080 jenkins/jenkins:latest
    ~ docker build -t jenkins:test . && docker run -it -v /var/run/docker.sock:/var/run/docker.sock -p 8080:8080 jenkins:test

    docker build -t jenkins:test . && docker run \
      --name jenkins \
      --restart=on-failure \
      --detach \
      --network jenkins \
      --env DOCKER_HOST=tcp://docker:2376 \
      --env DOCKER_CERT_PATH=/certs/client \
      --env DOCKER_TLS_VERIFY=1 \
      --publish 8080:8080 \
      --publish 50000:50000 \
      jenkins:test
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
