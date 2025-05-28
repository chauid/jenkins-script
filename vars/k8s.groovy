def call() {
    echo 'k8sTools version: 0.1'
}

def deploy(String deploymentName = 'none', String containerName = 'none', String namespace = 'default', String imageName = 'none', String imageTag = 'latest') {
    if (deploymentName == 'none' || containerName == 'none' || imageName == 'none') {
        error 'Deployment name and image name must be provided.'
        return
    }
    steps.sh """
        kubectl set image deployment/${deploymentName} ${containerName}=${imageName}:${imageTag} -n ${namespace}
        kubectl rollout status deployment/${deploymentName} -n ${namespace}
    """
}
