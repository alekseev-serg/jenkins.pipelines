import groovy.json.JsonSlurper

def call () {

    node("builder") {

        if (env.JSON_PAYLOAD) {
            def webhookPayload = new JsonSlurper().parseText(env.JSON_PAYLOAD)
        }

        def context = init(webhookPayload);

        echo "${context.gitUrl}\n ${context.branch}\n ${context.appName}";

    }
}
