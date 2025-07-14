def call () {

    node("Builder") {
        def webhookPayload = readJSON text: env.JSON_PAYLOAD;

        echo "${webhookPayload}"
    }
}
