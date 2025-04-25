import com.example.Buildutils

def call() {
    def buildutils = new Buildutils(steps)
    echo 'BuildTools version: 0.1'
}

def gradle(String options = 'BUILD') {
    def buildutils = new Buildutils(steps)
    buildutils.gradleBuild("${env.WORKSPACE}", "${options}")
}

def setDockerfile(String projectType) {
    if (projectType == 'springboot') {
        steps.sh """
            PROJECT_NAME=\$(sed -nE "s/^rootProject.name *= *'([^']+)'/\\1/p" settings.gradle)
            sed -i "s/{PROJECT_NAME}/\${PROJECT_NAME}/g" Dockerfile
            VERSION=\$(sed -nE "s/^version *= *'([^']+)'/\\1/p" build.gradle)
            sed -i "s/{VERSION}/\${VERSION}/g" Dockerfile
        """
    } else {
        error "Unsupported project type: ${projectType}"
    }
}

def image(String imageName, String imageTag, boolean useKaniko = false) {
    def buildutils = new Buildutils(steps)
    buildutils.imageBuild("${env.WORKSPACE}", "${imageName}", "${imageTag}", useKaniko)
}