import java.net.*;
import java.io.*;
import java.util.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Client{


	public final static String DEFAULT_IPP = "localhost:8909";
	public final static int DEFAULT_PORT_NUMBER = 21212;

	/** Machine name where other peer is running */
	public final static String REMOTE_MACHINE_NAME = "localhost";
	
	/** Command-Line Switches */
	public final static String INTRODUCTION = "--ipp";
	public final static String START_ROOM = "--start_room";
	public final static String NAME = "--name";
	public final static String ROOMS = "--rooms";
	public final static String KILL = "--kill";
	public final static String PORT = "--port";
	public final static String HELP = "--help";

	/** This Member's Name */
	public static String name;

	/** This Users IP:Port pair */
	public static String IPP;
	public Integer localPortNumber;
	
	/** Room Initially Starting in */
	public static Room room;
	
	/** Local Machine Address */
	public InetAddress localMachineAddress;

	/** Distant Machine Address */
	public InetAddress distantMachineAddress;
	
	public BlockingQueue<String> commandQueue;
	public BlockingQueue<String> messageQueue;
	public boolean kill;

	/** Communication Socket */
	public DatagramSocket datagramSocket;
	public Member member;

	public Socket socket;

	
	public Client(String IPP, String room, String name, Integer port, boolean kill){
		this.IPP = IPP;	
		this.localPortNumber = port;
		this.member = new Member(IPP, name, room);
		this.kill = kill;
	}

	public static void main(String [] args){
		String IPP = DEFAULT_IPP;
		String room = "lobby";
		String name = "DarthVader";
		Integer port = DEFAULT_PORT_NUMBER;
		boolean kill = false;
		

		/*
		 * Parsing parameters. argNdx will move forward across the indices;
		 * remember for arguments that have their own parameters, you must
		 * advance past the value for the argument too.
		 */

		int argNdx = 0;

		while (argNdx < args.length) {
		  String curr = args[argNdx];

		  if (curr.equals(INTRODUCTION)) {
		    ++argNdx;
		    IPP = args[argNdx];

		  } else if (curr.equals(START_ROOM)) {
		    ++argNdx;

		  } else if (curr.equals(NAME)){
			++argNdx;
			name = args[argNdx];		
	
		  } else if(curr.equals(KILL)) {
			kill = true;
		  }else if(curr.equals(PORT)){
			++argNdx;
			
			String arg = args[argNdx];
			port = Integer.parseInt(arg);
		  }else if(curr.equals(HELP))
			help();
		  else{
		    // if there is an unknown parameter, give usage and quit
		    System.err.println("Unknown parameter \"" + curr + "\"");
		    System.exit(1);
		  }

      		++argNdx;
    	}		
		new Client(IPP,room,name,port,kill).run();
	}

	public static void help(){
		System.out.println("--introduction <IPP> --start_room <RM>" +
									" --name<NM> --rooms --port <port> --kill"); 
	}	

	public void run(){
		
		this.messageQueue = new LinkedBlockingQueue<String>();
		this.commandQueue = new LinkedBlockingQueue<String>();
		
		try{//Trying to connect to IntroServer
			
			KeyboardToQueue keyboard = new KeyboardToQueue(System.in,
											messageQueue,commandQueue);			

			Socket socket = new Socket(this.member.IP, this.member.port);
			Scanner sin = new Scanner(socket.getInputStream());

			ServerReceiver serverReceiver = new ServerReceiver(sin);
			String message = "";
			serverReciever.run();
			PrintStream sout = new PrintStream(socket.getOutputStream());
			
			System.out.println("Connected to " + socket);

			this.localMachineAddress = this.member.IP;
			
			this.distantMachineAddress = socket.getInetAddress();			
			this.datagramSocket = new DatagramSocket(localPortNumber);

      		// KeyboardToQueue reads the keyboard and posts lines into
      		// the message and command queues (all communication is through
      		// safe queues so communication is thread safe)			
			
			// DatagramReceiver watches the wire; when something is ready it
      		// is read and put on the screen.
			DatagramReceiver receiver = new DatagramReceiver(this.datagramSocket);

			DatagramSender sender = new DatagramSender(this.datagramSocket, messageQueue, 
												   distantMachineAddress, localPortNumber);
			
			System.out.print("UDP Socket on " + socket.getLocalPort());
			receiver.run();
			keyboard.run();
			System.out.println(".");
			sender.run();
			System.out.println(".");
			//Chat with server
			while(!message.equals("\\\\quit")){
				System.out.println("Sending: " + message);
				sout.println(message);
				System.out.print("Server Response: ");
				String serverResponse = sin.nextLine();
				System.out.println(serverResponse);
				message = sin.nextLine(); 
			}
		  } catch (SocketException e){
				System.err.println("Error in establishing local or distant connection.");
				e.printStackTrace();
				System.exit(2);
		  } catch (UnknownHostException e){
				System.err.println("Unknown host " + distantMachineAddress);
				e.printStackTrace();
		  } catch (IOException ioe){
		  }
	}
}

