import groovy.json.*
import groovy.net.http.HTTPBuilder
import groovy.net.http.RESTClient
import groovy.net.http.HttpResponseDecorator
import static groovy.net.http.ContentType.*
import static groovy.net.http.Method.*



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

    def http = new HTTPBuilder("http://gitrepsrv:3000")

        http.request(POST, JSON) { req ->
            uri.path = "/api/v1/repos/${Organisation}/${repository}/statuses/${currentSha}"
            body = jsonRequestdata
            headers.'Authorization' = "token feb40616d4d730b6c89a9f74aafe93a3e05230fb"
            headers.'Accept' = 'application/vnd.github.v3.text-match+json'
            headers.'User-Agent' = 'Mozilla/5.0'
            response.success = { resp, json ->
                println "Got response: ${resp.statusLine}"
                println "Content-Type: ${resp.headers.'Content-Type'}"
                println json
            }
            response.failure = { resp, json ->
                print json
            }
    
    
        }
 
}


