import java.io.IOException;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.*;

public class ServerReceiver extends Thread {
  
  public byte[] receiveData;
  public Scanner sin;

  public final static int bufferSize = 1024;

  public ServerReceiver(Scanner sin) {
	this.sin = sin;    
	receiveData = new byte[bufferSize];
  }

  @Override public void run() {
      while (true) {
        String message = this.sin.nextLine();
		    System.out.println("Server :" + message);
      }
  }

}