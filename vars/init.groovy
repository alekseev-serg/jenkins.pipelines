def call(webhookPayload){
    def gitUrl;
    def commitHash;
    def branch;
    def appName;

    gitUrl = webhookPayload.repository?.ssh_url
    branch = webhookPayload.ref.split('/')[-1]
    appName = webhookPayload.repository.name
    
    return [
        gitUrl: gitUrl,
        branch: branch,
        appName: appName,
    ];
}