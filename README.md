#Example of resource server using OAuth2 with jwt tokens

This requires my other repo of OAuth2-demo as the 
Auth server.

The Auth server needs to be started first or this will 
fail on startup.  This runs on port 9090, the Auth server on 8080.

##To run basic tests on it, use these curl commands:
* curl service-account-1:service-account-1-secret@localhost:8080/auth/oauth/token -d grant_type=client_credentials
    * This will return a token, copy the token.
* export TOKEN="token"
    * Paste token in quotes instead of typing the word token there.
* curl -H "Authorization: Bearer $TOKEN" -v localhost:9090
    * This should give a response of "Hello world!"
* curl -H "Content-Type: application/json" -H "Authorization: Bearer $TOKEN" -X POST -d    “Bonjour monde” -v localhost:9090
    * This does not give a response, but changes the response from "Hello world!" to "Bonjour monde"
* curl -H "Authorization: Bearer $TOKEN" -v localhost:9090
    * This should now give a response of "Bonjour monde"
* curl -H "Authorization: Bearer $TOKEN" -v localhost:9090/user
    * This should give you the values in the decoded jwt token.
