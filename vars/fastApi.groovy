import groovy.json.JsonSlurper

def call () {

    node("builder") {

        echo "RAW JSON: ${env.JSON_PAYLOAD ?: 'JSON_PAYLOAD is null'}"
        
        if (env.JSON_PAYLOAD) {
            def json = new JsonSlurper().parseText(env.JSON_PAYLOAD)
        }

        echo "${json}"
        // def context = init(webhookPayload);

        // echo "${context.gitUrl}\n ${context.branch}\n ${context.appName}";

    }
}
