## jenkins-script
jenkins shared library


### Usage
|function|return|params|
|---|:---:|:---|
|`build`|void|void|
|`build.gradle`|void|void|
|`build.docker`|void|String imageName, String imageTag, boolean useKaniko|
|`k8s`|void|void|
|`k8s.deploy`|void|String deploymentName, String namespace, String imageName, String imageTag, boolean initDeploy|