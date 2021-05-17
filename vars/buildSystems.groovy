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
    
    bat("curl --location --request POST 'http://gitrepsrv:3000/api/v1/repos/aravind.a/GitSync/statuses/444b84683bd56fb3f9755c1d344802c653a9d91f' \
    --header 'Authorization: Bearer feb40616d4d730b6c89a9f74aafe93a3e05230fb' \
    --header 'Content-Type: application/json' \
    --header 'Cookie: lang=en-US; i_like_gitea=065687f7d2e19dc7; _csrf=Tpb0WeN8LRGzgVLkxEgarI7ZZd46MTYyMTIzMjUxNjI1MTU2MzMwMA' \
    --data-raw "{
     "state": "failure",
     "target_url": "http://192.168.4.60:8080",
     "description": "SonarQube Failed",
     "context": "continuous-integration/automation"
     }"")

 
}


