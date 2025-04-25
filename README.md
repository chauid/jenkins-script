## jenkins-script
jenkins shared library


### Function List
|function|return|params|
|---|:---:|:---|
|`build`|void|void|
|`build.commitStatus`|void|String message, String state|
|`build.gradle`|void|void|
|`build.docker`|void|String imageName, String imageTag, boolean useKaniko|
|`k8s`|void|void|
|`k8s.deploy`|void|String deploymentName, String namespace, String imageName, String imageTag, boolean initDeploy|