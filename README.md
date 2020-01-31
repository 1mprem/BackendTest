# BackendTest

I've used api's to create ,Read and Delete operation.

Create API:
	Get : http://localhost:8080/test/add
	Request JSON:
			{
    "key": "KeyName",
    "value": {
        "value": "KeyValue",
        "exiprytime": 200
              }
       }
//Value part we can git as any form of json it should accept. 
"value "  :{"value" : {[1,2,3]}}      
       
 
Read API:
 	GET: http://localhost:8080/test/get/KeyName
  
Delete API:
	GET: http://localhost:8080/test/delete/KeyName


OUTPUT FILE NAME:  info.properties

Code Definitions:
Controller Path:
/test/src/main/java/com/test/controller/TestController.java

Service Path:
/test/src/main/java/com/test/service/TestService.java

Constant Path:
 -I've used constants to avoid runtime memory allocations to string 
/test/src/main/java/com/test/model/TestConstants.java

