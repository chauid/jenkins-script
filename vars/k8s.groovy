def call() {
    echo 'k8sTools version: 0.1'
}

def deploy(String deploymentName = 'none', String namespace = 'default', String imageName = 'none', String imageTag = 'latest', boolean initDeploy = false) {
    if (deploymentName == 'none' || imageName == 'none') {
        error 'Deployment name and image name must be provided.'
    }
    if (initDeploy) {
        steps.sh """
            kubectl apply -f ${env.WORKSPACE}/k8s/${deploymentName}.yaml -n ${namespace}
        """
    }
    else {
        steps.sh """
            kubectl set image deployment/${deploymentName} ${deploymentName}=${imageName}:${imageTag} -n ${namespace}
            kubectl rollout status deployment/${deploymentName} -n ${namespace}
        """
    }
}
