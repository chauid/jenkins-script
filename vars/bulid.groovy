import com.example.BuildUtils

def call() {
    echo 'BuildTools version: 0.1'
}

def gradle() {
    def buildUtils = new BuildUtils(steps)
    buildUtils.gradleBuild("${env.WORKSPACE}")
}

def docker(String imageName, String imageTag, boolean useKaniko = false) {
    def buildUtils = new BuildUtils(steps)
    buildUtils.gradleBuild("${env.WORKSPACE}", imageName, imageTag, useKaniko)
}
