package com.example

class Buildutils implements Serializable {
    def steps

    Buildutils(steps) {
        this.steps = steps
    }

    def gradleBuild(String projectDir, String options = 'BUILD') {
        steps.sh "cd ${projectDir}"
        steps.sh 'chmod +x ./gradlew'
        switch(options) {
            case 'BUILD':
                steps.sh './gradlew clean build'
                break
            case 'NO_TEST_BUILD':
                steps.sh './gradlew clean build -x test'
                break
            case 'BOOTJAR':
                steps.sh './gradlew clean bootJar'
                break
        }
    }

    def imageBuild(String projectDir, String imageName, String imageTag, boolean useKaniko = false) {
        steps.sh "cd ${projectDir}"
        if (useKaniko) {
            container('kaniko') {
                steps.sh "/kaniko/executor --context . --dockerfile Dockerfile --destination ${imageName}:${imageTag}"
            }
        }
        else {
            steps.sh "docker build -t ${imageName}:${imageTag} ."
            steps.sh "docker push ${imageName}:${imageTag}"
        }
    }
}
