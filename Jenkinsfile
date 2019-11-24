node('') {
	stage('Poll') {
		checkout scm
	}
	stage('Static Code Analysis'){
		withMaven(maven:'mm'){
			bat 'mvn clean verify sonar:sonar -Dsonar.projectName=exemple-sonar -Dsonar.projectKey=exemple-sonar -Dsonar.projectVersion=$BUILD_NUMBER';
		}
	}
	stage ('Publish'){
		def server = Artifactory.server 'arti'
		def uploadSpec = """{
			"files": [
				{
					"pattern": "target/hello-0.0.1.war",
					"target": "exemple/${BUILD_NUMBER}/",
					"props": "Integration-Tested=Yes;Performance-Tested=No"
				}
			]
		}"""
		server.upload(uploadSpec)
	}
	stage('Clean Package') {
            steps{
		withMaven(maven:'mm'){    
                	bat 'mvn clean verify';
		}
            }
        }
	stage ('Build Package'){
            steps{
		withMaven(maven:'mm'){
                	bat 'mvn package';
		}
            }
           
        }
	stage ('Create And Publish Docker Image'){
            steps{
		withMaven(maven:'mm'){
                	bat 'mvn package dockerfile:build';
			bat 'mvn dockerfile:push';
		}
            }
        }
}
