import com.example.BuildUtils

def call() {
    echo 'BuildTools version: 0.1'
}

def gradle() {
    def buildUtils = new BuildUtils(steps)
    buildUtils.gradleBuild("${env.WORKSPACE}")
}

def docker(String imageName = 'none', String imageTag = 'none', boolean useKaniko = false) {
    if (imageName == 'none' || imageTag == 'none') {
        error 'Image name and tag must be provided.'
    }
    def buildUtils = new BuildUtils(steps)
    buildUtils.gradleBuild("${env.WORKSPACE}", imageName, imageTag, useKaniko)
}
