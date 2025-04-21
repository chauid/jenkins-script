package com.example

class BuildUtils implements Serializable {
    def steps

    BuildUtils(steps) {
        this.steps = steps
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