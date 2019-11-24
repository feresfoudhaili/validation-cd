node('') {
	stage('Poll') {
		checkout scm
	}
	stage ('Email-Notification'){
		mail bcc: '', body: '''Hello,
		
		project build successfully,
		
		cordially,
		
		Jenkins_Admin.
		
		''', cc: '', from: '', replyTo: '', subject: 'Jenkins Job', to: 'feres.foudhaili@esprit.tn'
	}
}
