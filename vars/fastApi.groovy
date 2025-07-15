import groovy.json.JsonSlurper

def call () {

    node("builder") {

        echo "RAW JSON: ${env.JSON_PAYLOAD ?: 'JSON_PAYLOAD is null'}"
        def webhookPayload = readJSON text: env.JSON_PAYLOAD
        def context = init(webhookPayload)

        echo "GIT URL: ${context.gitUrl}"
        echo "BRANCH: ${context.branch}"
        echo "APP NAME: ${context.appName}"
        echo "COMMIT: ${context.commit}"

        stage('Get source code') {
            checkout([
                $class: 'GitSCM',
                branches: [[name: context.branch]],
                userRemoteConfigs: [[
                    url: context.gitUrl,
                    credentialsId: 'git-ssh'
                ]],
            ]);
        }

        stage('Build app') {
            def imageTag = "${context.appName}:${context.commit}"
            sh """
                docker build -t ${imageTag} .
            """
        }

        try {
            echo "DONE...)"
        } finally {
            echo "Cleaning workspace..."
            cleanWs()
        }
    }
}
