node {

    sh "env"

    def ctx = init(webhookPayload)

    stage('List Files') {
        echo "Project directory contents:"
        sh 'ls -la'
    }
}