## jenkins-script
jenkins shared library

### Function List
|function|return|params|
|---|:---:|:---|
|`build`|void|void|
|`build.gradle`|void|String options|
|`build.npm`|void|boolean needsInstall|
|`build.yarn`|void|boolean needsInstall|
|`build.pnpm`|void|boolean needsInstall|
|`build.setDockerfile`|void|String projectType|
|`build.getProjectVersion`|String|String projectType|
|`build.image`|void|String imageName, String imageTag, boolean useKaniko|
|`github`|void|void|
|`github.setCommitStatus`|void|String message, String context, String state|
|`k8s`|void|void|
|`k8s.deploy`|void|String deploymentName, String containerName, String namespace, String imageName, String imageTag|

> [!IMPORTANT]
> `github.setCommitStatus` is depends on [GitHub plugins](https://plugins.jenkins.io/github)
