In the uploaded files there is a jar file for sqllite databse which is connected to the messaging server, for retrieving and adding clients.
This file needs to run together with the other files in order for it to work.

Besides the client/server and clientthread/serverthread, there are 3 more classes used for the UI. 
The uploaded code currently works in the consle because we couldn't make the client's messages to be synchronized, it could on;y send messages but the others couldn't see it, 
so we made the modifications back to when the messages can be seen in the console.

To run the code, you must first run the jar file, atfer that the clientserver and one or many client class/es.
