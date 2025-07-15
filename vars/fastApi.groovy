import groovy.json.JsonSlurper

def call () {

    node("builder") {

        echo "RAW JSON: ${env.JSON_PAYLOAD ?: 'JSON_PAYLOAD is null'}"

        def webhookPayload = readJSON text: env.JSON_PAYLOAD

        def context = init(webhookPayload)

        echo "${context.gitUrl} ${context.branch} ${context.appName}"

        stage('Get source code') {
            checkout([
                $class: 'GitSCM',
                branches: [[name: context.branch]],
                userRemoteConfigs: [[
                    url: context.gitUrl,
                    credentialsId: 'git-ssh'
                ]],
            ]);
            sh "ls -la"
        }
    }
}
