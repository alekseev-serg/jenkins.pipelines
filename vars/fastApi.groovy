import groovy.json.JsonSlurper

def call () {

    node("builder") {
        sh "env"

        echo "RAW JSON: ${env.JSON_PAYLOAD ?: 'JSON_PAYLOAD is null'}"

        def webhookPayload = readJSON text: env.JSON_PAYLOAD


        def gitUrl = webhookPayload.repository?.ssh_url
        def branch = webhookPayload.ref.tokenize('/').last()
        def appName = webhookPayload.repository?.name

        echo "${gitUrl} ${branch} ${appName}"

        stage('Get source code') {
            checkout([
                $class: 'GitSCM',
                branches: [[name: branch]],
                userRemoteConfigs: [[
                    url: gitUrl,
                    credentialsId: 'git-ssh'
                ]],
            ]);
            sh "ls -la"
        }
    }
}
