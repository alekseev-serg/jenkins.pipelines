node {

    sh "echo ${env.JSON_PAYLOAD}"

    // def ctx = init(webhookPayload)

    stage('List Files') {
        echo "Project directory contents:"
        sh 'ls -la'
    }

}