node {
    properties([
        parameters ([
            string(name: 'JSON_PAYLOAD', description: 'Generic body state'),
            string(name: 'JSON_QUERY', description: 'Generic query state'),
        ]),
    ]);
    
    def webhookPayload = readJSON text: env.JSON_PAYLOAD;
    def webhookQuery = readJSON text: env.JSON_QUERY;

    def ctx = init(webhookPayload)

    stage('List Files') {
        echo "Project directory contents:"
        sh 'ls -la'
    }
}