import groovy.json.JsonSlurper

def call () {

    node("builder") {

        echo "RAW JSON: ${env.JSON_PAYLOAD ?: 'JSON_PAYLOAD is null'}"

        def webhookPayload = new JsonSlurper().parseText(env.JSON_PAYLOAD)

        def context = init(webhookPayload)

        echo "${context.gitUrl}"
        echo "${context.branch}"
        echo "${context.appName}"
    }
}
