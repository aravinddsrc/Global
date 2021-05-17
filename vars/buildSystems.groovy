def reportQualityGate(script, organisation, repository, status, context, description) {
    def currentSha = commitHashForBuild(currentBuild.rawBuild)

    def request = [
            context: context,
            description: description,
            state: status,
            target_url: script.env.RUN_DISPLAY_URL
    ]

    def jsonRequestdata = JsonOutput.toJson(request)
    script.httpRequest(
            acceptType: 'APPLICATION_JSON',
            authentication: 'feb40616d4d730b6c89a9f74aafe93a3e05230fb',
            contentType: 'APPLICATION_JSON',
            httpMode: 'POST',
            requestBody: jsonRequestdata,
            responseHandle: 'NONE',
            url: "http://gitrepsrv:3000/api/v1/repos/$organisation/$repository/statuses/$currentSha")
}

def commitHashForBuild(build) {
    def scmAction = build?.actions.find { action -> action instanceof jenkins.scm.api.SCMRevisionAction }
    if (scmAction?.revision instanceof org.jenkinsci.plugins.github_branch_source.PullRequestSCMRevision) {
        return scmAction?.revision?.pullHash
    } else if (scmAction?.revision instanceof jenkins.plugins.git.AbstractGitSCMSource$SCMRevisionImpl) {
        return scmAction?.revision?.hash
    } else {
        error("Build doesn't contain revision information. Do you run this from GitHub organization folder?")
    }
}
