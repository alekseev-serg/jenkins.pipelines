def call () {

    node("builder") {
        def webhookPayload = readJSON text: env.JSON_PAYLOAD;

        echo "${webhookPayload}"
    }
}
