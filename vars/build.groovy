import com.example.Buildutils

def call() {
    def buildutils = new Buildutils(steps)
    echo 'BuildTools version: 0.1'
}

def gradle(String options = 'BUILD') {
    def buildutils = new Buildutils(steps)
    buildutils.gradleBuild(env.WORKSPACE, options)
}

def npm(boolean needsInstall = false) {
    def buildutils = new Buildutils(steps)
    buildutils.nodejsBulid(env.WORKSPACE, 'npm', needsInstall)
}

def yarn(boolean needsInstall = false) {
    def buildutils = new Buildutils(steps)
    buildutils.nodejsBulid(env.WORKSPACE, 'yarn', needsInstall)
}

def pnpm(boolean needsInstall = false) {
    def buildutils = new Buildutils(steps)
    buildutils.nodejsBulid(env.WORKSPACE, 'pnpm', needsInstall)
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

def getProjectVersion(String projectType) {
    def version = 'unknown'
    switch (projectType) {
        case 'springboot':
            version = steps.sh(script: "sed -nE \"s/^version *= *'([^']+)'/\\1/p\" build.gradle", returnStdout: true).trim()
            break
        case 'nodejs':
            version = steps.sh(script: "sed -n 's/^[[:space:]]*\"version\":[[:space:]]*\"\\([^\"]*\\)\".*/\\1/p' package.json", returnStdout: true).trim()
            break
        default:
            error "Unsupported project type: ${projectType}"
    }
    return version
}

def image(String imageName, String imageTag, boolean useKaniko = false) {
    def buildutils = new Buildutils(steps)
    buildutils.imageBuild(env.WORKSPACE, imageName, imageTag, useKaniko)
}