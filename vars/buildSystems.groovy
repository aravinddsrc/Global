import groovy.json.*
import groovyx.net.http.ContentType

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

    httpRequest(
            acceptType: 'APPLICATION_JSON',
            authentication: 'feb40616d4d730b6c89a9f74aafe93a3e05230fb',
            contentTypes: 'APPLICATION_JSON',
            requestMethod : 'POST',
            requestBody: jsonRequest,
            responseHandle: 'NONE',
            url: "http://gitrepsrv:3000/api/v1/repos/${Organisation}/${repository}/statuses/${currentSha}")
   
    
    
    http.request(POST) {
    uri.path = "http://gitrepsrv:3000/api/v1/repos/${Organisation}/${repository}/statuses/${currentSha}"
    body = jsonRequestdata
    requestContentType = ContentType.JSON
    http.auth.basic('aravind.a', 'Arav123')

    response.success = { resp ->
        println "Success! ${resp.status}"
    }

    response.failure = { resp ->
        println "Request failed with status ${resp.status}"
    }
}
}


