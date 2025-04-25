package com.example

class BuildUtils implements Serializable {
    def steps

    BuildUtils(steps) {
        this.steps = steps
    }

    def setCommitStatus(String repositoryUrl, String message, String state) {
        step([
            $class: "GitHubCommitStatusSetter",
            reposSource: [$class: "ManuallyEnteredRepositorySource", url: "${repositoryUrl}"],
            contextSource: [$class: "ManuallyEnteredCommitContextSource", context: "ci/jenkins/build-status"],
            errorHandlers: [[$class: "ChangingBuildStatusErrorHandler", result: "UNSTABLE"]],
            statusResultSource: [
                $class: "ConditionalStatusResultSource",
                results: [[$class: "AnyBuildResult", message: message, state: state]]
            ]
        ])
    }

    def gradleBuild(String projectDir) {
        steps.sh "cd ${projectDir}"
        steps.sh 'chmod +x ./gradlew'
        steps.sh './gradlew clean build'
    }

    def dockerBuild(String projectDir, String imageName, String imageTag, boolean useKaniko = false) {
        steps.sh "cd ${projectDir}"
        if (useKaniko) {
            steps.sh "docker build -t ${imageName}:${imageTag} ."
            steps.sh "docker push ${imageName}:${imageTag}"
        }
        else {
            container('kaniko') {
                steps.sh "/kaniko/executor --context . --dockerfile Dockerfile --destination ${IMAGE_NAME}:${TAG}"
            }
        }
    }
}