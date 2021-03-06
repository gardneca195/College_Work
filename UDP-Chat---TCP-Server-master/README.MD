Peer-to-Peer UDP Chat & TCP Server - by Collen Roller

This Set of Java Classes consists of 2 programs a TCP Server and a UDP Chat Client

The Server (TCP)
----------------------------------------------------------------------------------------
The Server runs on a specific port  and listens for connections to be made. 
ServerMain runs and creates a new instance of IntroServer which holds a set of rooms 
that clients can be in. The purpose of the server is to hold information of clients, 
so that other clients can connect to the server and send commands. 

|---------------------+-------------------------------------------------------|
| \JOIN <RM> <IPP>    | Introduction/join room request.                       |
| \JOIN <RM> (<IPP>)+ | Room member response; includes the member joining.    |
|---------------------+-------------------------------------------------------|
| \ROOMS              | List of active rooms request.                         |
| \ROOMS (<RM>)+      | Room list response; all non-empty rooms.              |
|---------------------+-------------------------------------------------------|
| \EXIT <RM> <IPP>    | Leave room request.                                   |
| \EXIT  <RM>         | Room client left; leaving a non-existent room or      |
|                     | a room the client is not in is NOT AN ERROR.          |
|---------------------+-------------------------------------------------------|
| \KILL               | Server kill command.                                  |
| \KILL               | Server kill response; server terminates after sending |
|                     | this response.                                        |
|---------------------+-------------------------------------------------------|
| \CLIENTS <RM>       | List of active clients in room request                |
| \CLIENTS (<IPP>)+   | Clients response; list of all clients in room         |
|---------------------+-------------------------------------------------------|

Each command thats parsed by the server will send back some information, which is
the second listing of each command.

//**********************************TO RUN THE SERVER**********************************\\

To Run the server - 

1) Compile the ServerMain as well as ServerThread from inside the src directory

"javac server/ServerMain.java server/ServerThread.java"

2) Then you must run the ServerMain which will create a new instance of IntroServer

he argument that is expected is the port number

"java server/ServerMain --port <port>"

----------------------------------------------------------------------------------------


The Client (UDP)
----------------------------------------------------------------------------------------
The Client attempts to connect to the server using a IPP, and is added to a room,
(lobby, if other not specified in args) using a default port of 21212 or a 
specific port specified in args. The following args can be used in this program

|----------------------+-----------------------------------------------|
| Parameter            | Meaning                                       |
|----------------------+-----------------------------------------------|
| --introduction <IPP> | REQUIRED The machine and port where the       |
|                      | introduction server is listening.             |
| --start_room <RM>    | The room to join initially. Default: lobby    |
| --name <NM>          | The name to prepend to all outgoing messages. |
|                      | Default: DarthVader                           |
| --rooms              | Start session with a call to \rooms.          |
|                      | Default: false                                |
| --port <port>        | The local port number where this peer expects |
|                      | chat communication. Default: 21212            |
| --kill               | Send the \KILL message to the introduction    |
|                      | server and terminate. Default: false          |
|----------------------+-----------------------------------------------|

The only required argument is --introduction <IPP> (To connect to the server)
However, the usage of --port is highly recommended as only one person can be using
the default port (21212). If this port is in useage already, then the client will
not be able to connect to the server

//****************************TO RUN THE CLIENT (EXAMPLE)*****************************\\

To Run the client - 

1) compile the client from inside the src directory

"javac client/Chat.java"

2) Run the client with necessary arguments

"java client/Chat --introduction <IPP> --port <port>
			|		   |
		     required		optional
						 
----------------------------------------------------------------------------------------

How Does the Client Connect to the Server?

********LIST OF CLIENT COMMANDS*******

|----------------+---------------------------------------------------------|
| Command Syntax | Semantics                                               |
|----------------+---------------------------------------------------------|
| \rooms         | Contacts the introduction server and asks for a list of |
|                | the rooms known to the server. Displays the results for | 
|----------------+---------------------------------------------------------|
| \clients <RM>  | Contacts the introduction server and asks for a list of |
|		 		 | clients in an existing room, if room doesn't exist the  |
|		 		 | server will send back \CLIENTS <RM> with nothing 	   |
|----------------+---------------------------------------------------------|
| \join <RM>     | Leave current room, and join a new or existing room     |
|		 		 | contacts the introduction server asking for             |
|                | a list of room members from the introduction server.    |
|                | When the reply comes back, HELLO goes out to            | 
|                | all members of the room other than this peer. Note that |
|                | this request results in the creation of a new room if   |
|                | the server has never heard of it.                       |
|----------------+---------------------------------------------------------|
| \leave <RM>    | Leave the given room. Send \EXIT to the server and      |
|                | sends GOODBYE to all other peers in the room which will |
|		 		 | cause each recipient to remove them from their list     |
|                | and the sender will stop displaying any more messages   |
|		 		 | from that room.            				   			   |
|                | The peer should set the current room to the start_room  |
|                | specified on the command-line. This implies, correctly, |
|                | that you cannot \leave the start room. Trying should    |
|                | give a reasonable error message.                        |
|----------------+---------------------------------------------------------|
| \name <NM>     | Set <NM> to be the "handle" used by this peer in front  |
|                | of all messages sent until it is reset. A message will  |
|                | send to all clients in room that clients name changed.  |
|----------------+---------------------------------------------------------|
| \quit          | This peer runs \leave for the current room and then	   |
|                | terminate.                                              |
|----------------+---------------------------------------------------------|
| \kill          | Send \KILL to the introduction server and terminate.    |
|----------------+---------------------------------------------------------|

Additionaly, Things to Note

[\leave] - When a client leave a room, they send Goodbye to everyone in the 
room by sending "GOODBYE". The ClientReceiver will remove the client from 
their local list if this message is received. After a client leaves a room they 
are put into the lobby, this implies yes that a user can't leave the lobby.

[\join] When a client joins a room they first leave their current room and then send
HELLO to everyone in the new room. Clients will all the sender to their list to
receive messages from.

[\name] When a client changes their name, a message \RENAME is sent to everyone
in the current room letting everyone know that clients name has changes from <NM> to <NM>'






