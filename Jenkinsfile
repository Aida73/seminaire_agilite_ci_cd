pipeline {
    agent any

    tools {
        maven "maven"
        jdk "jdk"
    }
   
    environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "localhost:8081"
        NEXUS_REPOSITORY = "maven-releases"
        NEXUS_CREDENTIAL_ID = "Nexus-Cred"
    }
    stages {
        stage("Checkout Code") {
            steps {
                checkout scm
            }
        }
        stage('Init'){
              steps {
                  echo "-----------Tools Initialization------------"
                  sh "mvn --version"
                  sh "java --version"
                
            }
            
        }
        stage('Build') {
            steps {
                dir('tracking') {
                    sh 'ls'
                    sh "mvn clean package"                
                }

            }
            
        }
        stage('Tests') {
            steps {
                dir('tracking') {
                    sh "mvn test"
                }

            }
            
        }
        stage("Quality Gate"){
            steps{
                dir('tracking') {
                    withSonarQubeEnv('sonar'){
                        sh 'mvn sonar:sonar'
                    }
                
                }
            }
        }
        
        stage("Check Quality Gate"){
            steps{
                timeout(time: 1, unit: 'HOURS'){
                    waitForQualityGate abortPipeline: true
                    script{
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        } 
                    }
                    
                }
            }
            
        } 
        stage("Deploy Dev"){
            when{
                branch 'main'
            }
            steps{
                deploy adapters: [tomcat9(credentialsId: 'tomcat', path: '', url: 'http://localhost:8888')], contextPath: 'tracking-dev', war: '**/*.war'

            }
        }   
        stage("Deploy rec"){
            when{
                branch 'rec'
            }
            steps{
                deploy adapters: [tomcat9(credentialsId: 'tomcat', path: '', url: 'http://localhost:8888')], contextPath: 'tracking-rec', war: '**/*.war'

            }
        }
        stage("Test Deploy dev"){
            when{
                branch 'main'
            }
            steps{
                echo "testing if dev deployment is successfully done"
                sleep(time:1,unit:"MINUTES") 
                echo "Run test"
                
            }
        }
        stage("Test Deploy rec"){
            when{
                branch 'rec'
            }
            steps{
                echo "testing if rec deployment is successfully done"
                sleep(time:1,unit:"MINUTES") 
                echo "Run test"
                
            }
        }
        stage("Install On Nexus"){
            when{
                branch 'release'
            }
            steps{
                script {
                    dir('tracking'){
                        sh "pwd"
                        sh "mvn install"
                        // Read POM xml file using 'readMavenPom' step , this step 'readMavenPom' is included in: https://plugins.jenkins.io/pipeline-utility-steps
                        pom = readMavenPom file: "pom.xml";
                        // Find built artifact under target folder
                        filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                        // Print some info from the artifact found
                        echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                        // Extract the path from the File found
                        // Extract the path from the File found
                        artifactPath = filesByGlob[0].path;
                        // Assign to a boolean response verifying If the artifact name exists
                        artifactExists = fileExists artifactPath;

                        if(artifactExists) {
                            echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
    
                            nexusArtifactUploader(
                                nexusVersion: NEXUS_VERSION,
                                protocol: NEXUS_PROTOCOL,
                                nexusUrl: NEXUS_URL,
                                groupId: pom.groupId,
                                version: pom.version,
                                repository: NEXUS_REPOSITORY,
                                credentialsId: NEXUS_CREDENTIAL_ID,
                                artifacts: [
                                    // Artifact generated such as .jar, .ear and .war files.
                                    [artifactId: pom.artifactId,
                                    classifier: '',
                                    file: artifactPath,
                                    type: pom.packaging],
    
                                    // Lets upload the pom.xml file for additional information for Transitive dependencies
                                    [artifactId: pom.artifactId,
                                    classifier: '',
                                    file: "pom.xml",
                                    type: "pom"]
                                ]
                            );

                            } else {
                                error "*** File: ${artifactPath}, could not be found";
                            }
                    }
                }
                    
                        
            }
                
            }   
       
        }
    post {
        success{
            emailext body: '''Votre build a été lancé avec succès !
                $PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS:
                Pour plus de détails cliquer sur ce lien: $BUILD_URL.''',recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!'
        
        }
        unstable{
            emailext body: '''Le build que vous venez de lancer rend le projet instable!
                $PROJECT_NAME - Build # $BUILD_NUMBER:
                Pour plus de détails cliquer sur ce lien: $BUILD_URL.''',recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!'
        

        }
        failure{
            emailext body: '''Le build que vous venez de lancer a échoué!
                $PROJECT_NAME - Build # $BUILD_NUMBER $BUILD_STATUS:
                Pour plus de détails cliquer sur ce lien: $BUILD_URL.''',recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!'
        

        }
        changed{
            emailext body: '''Le build que vous venez de lancer a été changé!
                $PROJECT_NAME - Build # $BUILD_NUMBER $BUILD_STATUS:
                Pour plus de détails cliquer sur ce lien: $BUILD_URL.''', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']],subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!'

        }
        always {
            dir('tracking') {
                sh "mvn clean"
            }
        }
    }
}


