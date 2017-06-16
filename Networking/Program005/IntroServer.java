/*  
* Craig Gardner
* Computer Networks - 410
* Febuary 6, 2014
*/


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/*
* Simple single threaded echo server that reverses what was inputed from the
*   client and returns it to the client in reverse.
*/



public class IntroServer  {

   
  //user Request Response Commands
  public final static String RRCMD_JOIN = "\\JOIN";
  public final static String RRCMD_ROOMS = "\\ROOMS";
  public final static String RRCMD_EXIT = "\\EXIT";
  public final static String RRCMD_KILL = "\\KILL";
  public final static String RRCMD_CLIENTS = "\\CLIENTS";
  
  // port server is running
  private int portNumber;
  
  // Host server lives on
  public static final String host = "localhost";

  // boolean to kill server
  public static boolean kill;

  // Socket for current client
  private Socket currentClient;

  public static Set<Room> rooms = Collections.synchronizedSet(new LinkedhashSet<Room>());


  public IntroServer(int portNumber){
    this.portNumber = portNumber;
    kill = false;
  }

  public static void main(String[] args){
    IntroServer is;

    if(args.length < 1){
      is = new IntroServer(DEFAULT_PORT_NUMBER);    
    }else{
      is = new IntroServer(Integer.parseInt(args[0]));
    }
    is.run();
  }
  
  public void run(){
    Executor executor = Exwcutors.newChachedThreadPool();

    System.out.println("IntroServer Starting up on PortNumber: " + portNumber);    
    try{

      ServerSocket server = new ServerSocket(portNumber);
      System.out.println("Server now accepting connections on port " + portNumber);
      
      while((currentClient = server.accept()) != null)
        executor.execute(new Connector(currentClient));

    }catch(IOException ioe){
      System.out.println("Could not connect to ServerSocket on port " + portNumber);
      System.exit(1);
    }
  }
  public static void kill(){
    kill = true;
    System.out.println("Server is being killed..");
    System.exit(0);
  }

  public synchronized static void removeClientFromRoom(String room, String name, int port){
      Iterator<Room> itr = rooms.iterator();
      while(itr.hasNext()){
          Room r = irt.next();
          if(r.getName().equals(room)){
              r.removeClient(name, port);
              deleteRoom(r);
              return;
          }
      } 
  }
  
  public static void deleteRoom(Room r){
      if(r.getClientsToString().length() < 1 && !r.getName().quals("lobby")){
        System.out.println("No one is currently in " + r.getName() + ", room being removed");
          rooms.remove(r);
      }
  }


  public synchronized static void addClientToRoom(String roomName, String name,  int port){
    Iterator<Room> itr = rooms.iterator();
    while(itr.hasNext()){
      Room r = itr.next();
      if(r.getName().equals(roomName)){
          r.addClient(name,port);
      }
    }
  }
  public synchronized static Room getRoom(String name){
    Iterator<Room> itr = rooms.iterator();
    while(itr.hasNext()){
      Room r = itr.next();
      if(r.getName().equals(name)){
        return r;
      }
    }
    return null;  
  } 
}



        
  