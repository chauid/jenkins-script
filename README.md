## jenkins-script
jenkins shared library

### Function List
|function|return|params|
|---|:---:|:---|
|`build`|void|void|
|`build.gradle`|void|String options|
|`build.setDockerfile`|void|String projectType|
|`build.image`|void|String imageName, String imageTag, boolean useKaniko|
|`k8s`|void|void|
|`k8s.deploy`|void|String deploymentName, String namespace, String imageName, String imageTag, boolean initDeploy|