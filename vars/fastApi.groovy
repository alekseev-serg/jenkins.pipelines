def call ()

    node("Builder") {
        def webhookPayload = readJSON text: env.JSON_PAYLOAD;
        def webhookQuery = readJSON text: env.JSON_QUERY;
    }