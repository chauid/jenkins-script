package com.example

class Buildutils implements Serializable {
    def steps

    Buildutils(steps) {
        this.steps = steps
    }

    def gradleBuild(String projectDir, String options) {
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

    def nodejsBulid(String projectDir, String packageManager, boolean needsInstall) {
        steps.sh "cd ${projectDir}"
        if (needsInstall) {
            steps.sh "${packageManager} install"
        }
        steps.sh "${packageManager} run build"
    }

    def imageBuild(String projectDir, String imageName, String imageTag, boolean useKaniko) {
        steps.sh "cd ${projectDir}"
        if (useKaniko) {
            steps.container('kaniko') {
                steps.sh "/kaniko/executor --context . --dockerfile Dockerfile --destination ${imageName}:${imageTag}"
            }
        }
        else {
            steps.sh "docker build -t ${imageName}:${imageTag} ."
            steps.sh "docker push ${imageName}:${imageTag}"
        }
    }
}
