node {
    stage('Clone FastAPI Repo') {
        def repoUrl = 'git@github.com:your-user/devops-wiki-fastapi.git'
        def branch = 'main'

        echo "Cloning ${repoUrl} (branch: ${branch})"
        // Очищаем рабочую директорию перед сборкой
        deleteDir()
        // Клонируем репозиторий
        git branch: branch, url: repoUrl
    }

    stage('List Files') {
        echo "Project directory contents:"
        sh 'ls -la'
    }
}