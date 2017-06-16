import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Iterator;
import java.util.Scanner;

public class Connector extends Thread{

	Socket client;

	public boolean flag;

	public Connector(Socket client,IntroServer server){
		this.client = client;
		flag = true;
	}

	public void run(){
		System.out.println("Connection from " + client);
		try{

			PrintStream cout = new PrintStream(this.client.getOutputStream());
			Scanner cin = new Scanner(this.client.getInputStream());
			while(flag){

				String input = cin.nextLine();
				
				while(cin.hasNextLine()) {
					System.out.println("Server saw \"" + input + "\"");
					parseServerCommand(input);
				}
				flag = false;
				cin.close();
			}

		}catch(IOException ioe){
			System.exit(0);
		}
	}


public void parseServerCommand(String s, PrintStream pout) throws SocketException {
		String [] line = s.split("\\s+");
		
		//Kills the Server
		if(line[0].equals(IntroServer.RRCMD_KILL)){
			pout.println(IntroServer.RRCMD_KILL);
			pout.flush();
			IntroServer.kill();
		}
			
		//Exits client from <RM>
		else if(line[0].equals(IntroServer.RRCMD_EXIT)){
			//Removing all of the clients in the room from local list to send / receive from
			String room = line[1];
			String [] ipp = line[2].split(":"); 
			IntroServer.removeClientFromRoom(room,ipp[0],Integer.parseInt(ipp[1]));
			pout.println(IntroServer.RRCMD_EXIT + " " + line[1]);

		}

		//Sends a list of Clients in a <RM> to the client
		else if(line[0].equals(IntroServer.RRCMD_CLIENTS)){
			String room = line[1];
			Room r = IntroServer.getRoom(room);
			if(r != null)
				pout.println(IntroServer.RRCMD_CLIENTS + " " + room + " " + r.getClientsToString());
			else
				pout.println(IntroServer.RRCMD_CLIENTS + " Could not find room " + room);
			
		}
		
		//Sends a list of rooms to the client
		else if(line[0].equals(IntroServer.RRCMD_ROOMS)){
			String result = "";
			Iterator<Room> itr = IntroServer.rooms.iterator();
			while(itr.hasNext()){
				Room r = itr.next();
				result += " " + r.getName();
				
			}
			pout.println(IntroServer.RRCMD_ROOMS + result);	
		}
		
		//Joins a room
		else if(line[0].equals(IntroServer.RRCMD_JOIN)){
			String room = line[1];
			String [] ipp = line[2].split(":");
			String joinResult = IntroServer.RRCMD_JOIN + " " + room;
			IntroServer.addClientToRoom(room,ipp[0],Integer.parseInt(ipp[1]));
			Room r = IntroServer.getRoom(room);
			if(r != null){
				joinResult += " " + r.getClientsToString();
			}
			pout.println(joinResult);
			
		//Otherwise
		}else{
			pout.println("Invaild Server command");
		}
		
		System.out.println();
		pout.flush();
	}

}
		