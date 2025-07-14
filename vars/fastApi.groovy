import groovy.json.JsonSlurper

def call () {

    node("builder") {
        def webhookPayload = readJSON text: env.JSON_PAYLOAD;

        // def ctx = init(webhookPayload)

        echo "RAW JSON: ${env.JSON_PAYLOAD ?: 'JSON_PAYLOAD is null'}"

        if (env.JSON_PAYLOAD) {
            def json = new JsonSlurper().parseText(env.JSON_PAYLOAD)
            echo "Repo: ${json.repository?.ssh_url}"
            echo "Ref: ${json.ref}"
        } else {
            error "No JSON payload received!"
        }

    }
}
