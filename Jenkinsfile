node('') {
	stage('Poll') {
		checkout scm
	}
	stage('Build & Unit test'){
		withMaven(maven:'mm'){
			bat 'mvn clean verify -DskipITs=true';}
		junit '**/target/surefire-reports/TEST-*.xml'
		archive 'target/*.jar'
	}
	stage('Static Code Analysis'){
		withMaven(maven:'mm'){
			bat 'mvn clean verify sonar:sonar -Dsonar.projectName=exemple-sonar -Dsonar.projectKey=exemple-sonar -Dsonar.projectVersion=$BUILD_NUMBER';
		}
	}
	stage ('Integration Test'){
		withMaven(maven:'mm'){
			bat 'mvn clean verify -Dsurefire.skip=true';}
		junit '**/target/failsafe-reports/TEST-*.xml'
		archive 'target/*.jar'
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
	stage ('Email-Notification'){
		mail bcc: '', body: '''Hello,
		project build successfully,
		cordially,
		Jenkins_Admin.
		''', cc: '', from: '', replyTo: '', subject: 'Jenkins Job', to: 'feres.foudhaili@esprit.tn'
	}
}
