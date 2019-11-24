node('') {
	stage('Poll') {
		checkout scm
	}
	stage('Static Code Analysis'){
		withMaven(maven:'mm'){
			bat 'mvn clean verify sonar:sonar -Ddockerfile.skip -Dsonar.projectName=exemple-sonar -Dsonar.projectKey=exemple-sonar -Dsonar.projectVersion=$BUILD_NUMBER';
		}
	}
	stage ('Publish'){
		def server = Artifactory.server 'arti'
		def uploadSpec = """{
			"files": [
				{
					"pattern": "target/reservation-service-0.0.1-SNAPSHOT.jar",
					"target": "cd-repo/${BUILD_NUMBER}/",
					"props": "Integration-Tested=Yes;Performance-Tested=No"
				}
			]
		}"""
		server.upload(uploadSpec)
	}
	stage('Clean Package') {
		withMaven(maven:'mm'){    
                	bat 'mvn clean verify';
		}
        }
	stage ('Build Package'){
		withMaven(maven:'mm'){
                	bat 'mvn package';
		}
        }
	stage ('Create And Publish Docker Image'){
		withMaven(maven:'mm'){
                	bat 'mvn package dockerfile:build';
			bat 'mvn dockerfile:push';
		}
        }
	stage ('Create And Publish Docker Image'){
                bat 'docker run -p 5050:8090 -d feresfoudhaili/validation-cd:0.0.1-SNAPSHOT';
        }
	stage ('Email-Notification'){
		mail bcc: '', body: '''Hello,
		
		project build successfully,
		
		cordially,
		
		Jenkins_Admin.
		
		''', cc: '', from: '', replyTo: '', subject: 'Jenkins Job', to: 'feres.foudhaili@esprit.tn'
	}
}
