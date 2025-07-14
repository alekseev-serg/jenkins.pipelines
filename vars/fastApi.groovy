import groovy.json.JsonSlurper

def call () {

    node("builder") {

        if (env.JSON_PAYLOAD) {
            def json = new JsonSlurper().parseText(env.JSON_PAYLOAD)
        }

        echo "${webhookPayload}"
        // def context = init(webhookPayload);

        // echo "${context.gitUrl}\n ${context.branch}\n ${context.appName}";

    }
}
