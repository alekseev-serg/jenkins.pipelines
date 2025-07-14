import groovy.json.JsonSlurper

def call () {

    node("builder") {

        echo "RAW JSON: ${env.JSON_PAYLOAD ?: 'JSON_PAYLOAD is null'}"

        if (env.JSON_PAYLOAD) {
            def webhookPayload = new JsonSlurper().parseText(env.JSON_PAYLOAD)
        } 
    }
}
