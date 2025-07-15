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

        sshagent (credentials: ['git-ssh']) {
            sh "git clone -b ${context.branch} ${context.gitUrl}"
        }
        sh "ls -la"
    }
}
