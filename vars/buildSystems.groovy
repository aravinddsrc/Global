import groovy.json.*

 //url: "${URLValue}"


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
    def URLValue=  "http://gitrepsrv:3000/api/v1/repos/${Organisation}/${repository}/statuses/${currentSha}"
  
    
   def response =  httpRequest(acceptType: 'APPLICATION_JSON', contentType: 'APPLICATION_JSON',
                         customHeaders  : [[name: 'authorization' , value: 'bearer feb40616d4d730b6c89a9f74aafe93a3e05230fb']],
                         httpMode: 'POST', timeout: 900, responseHandle: 'NONE' ,url: "${URLValue}", requestBody: "${jsonRequestdata}"  )
                               
}


