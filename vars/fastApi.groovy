import groovy.json.JsonSlurper

def call () {

    node("builder") {
        sh "env"

        echo "RAW JSON: ${env.JSON_PAYLOAD ?: 'JSON_PAYLOAD is null'}"

        def webhookPayload = new JsonSlurper().parseText(env.JSON_PAYLOAD)

        def context = init(webhookPayload)

        echo "${context.gitUrl}"
        echo "${context.branch}"
        echo "${context.appName}"

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
