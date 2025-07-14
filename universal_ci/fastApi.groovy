node {

    stage('Debug Env') {
        sh 'printenv | sort'
    }


    stage('List Files') {
        echo "Project directory contents:"
        sh 'ls -la'
    }

}