import groovy.json.*

def reportQualityGate(script, Organisation, repository, status, context, description) {
    def currentSha="${script}"
    println  "${description}"
    println  "${context}"
    println   "${status}"
    println   "${repository}"
    println   "${Organisation}"
    println   "${script}"
    
    def request = [
            context: context,
            description: description,
            state: status,
            target_url: 'http://192.168.4.60:8080'
    ]

    def jsonRequestdata = JsonOutput.toJson(request)
    println  "${jsonRequestdata}"
    println  "http://gitrepsrv:3000/api/v1/repos/${Organisation}/${repository}/statuses/${currentSha}"
    script.httpRequest(
            acceptType: 'Content-Type',
            authentication: 'feb40616d4d730b6c89a9f74aafe93a3e05230fb',
            contentType: 'application/json',
            httpMode: 'POST',
            requestBody: jsonRequestdata,
            responseHandle: 'NONE',
            url: "http://gitrepsrv:3000/api/v1/repos/${Organisation}/${repository}/statuses/${currentSha}")
}


