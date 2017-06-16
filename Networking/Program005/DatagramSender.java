import java.io.IOException;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import java.util.concurrent.BlockingQueue;

public class DatagramSender extends Thread {

  private InetAddress distantMachine;
  private int distantPort;

  private BlockingQueue<String> messageQueue;
  private byte[] sendData;
  private DatagramSocket socket;

  public DatagramSender(DatagramSocket socket,
      BlockingQueue<String> messageQueue, InetAddress distantMachine,
      int distantPort) {
    this.messageQueue = messageQueue;
    this.socket = socket;
    this.distantMachine = distantMachine;
    this.distantPort = distantPort;
  }

  @Override
  public void run() {
    while (true) {
      try {
        String message = messageQueue.take();
        sendData = message.getBytes();

        DatagramPacket packet = new DatagramPacket(sendData,
            sendData.length, distantMachine, distantPort);
        socket.send(packet);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }
}