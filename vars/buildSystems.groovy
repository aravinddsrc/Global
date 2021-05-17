@Grab('io.github.http-builder-ng:http-builder-ng-okhttp:0.14.2')
import static groovy.json.JsonOutput.toJson
import static groovyx.net.http.HttpBuilder.configure
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

    
    
    @NonCPS
    def posts = configure {
    request.uri ="http://gitrepsrv:3000/api/v1/repos/${Organisation}/${repository}/statuses/${currentSha}"
    request.contentType = 'application/json'
    request.body = jsonRequestdata
    request.auth.basic 'aravind.a', 'Arav123'
}.post()
    
    
 
}


