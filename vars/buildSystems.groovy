// Status can be: pending failure error success
static def reportQualityGate(script, organisation, repository, status, context, description) {
    def currentSha = getCurrentCommit(script)

    def request = [
            context: context,
            description: description,
            state: status,
            target_url: script.env.RUN_DISPLAY_URL
    ]

    def jsonRequest = JsonOutput.toJson(request)
    script.httpRequest(
            acceptType: 'APPLICATION_JSON',
            authentication: 'feb40616d4d730b6c89a9f74aafe93a3e05230fb',
            contentType: 'APPLICATION_JSON',
            httpMode: 'POST',
            requestBody: jsonRequest,
            responseHandle: 'NONE',
            url: "http://gitrepsrv:3000/api/v1/repos/$organisation/$repository/statuses/$currentSha")
}
