import java.util.*;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class Member{
	
	public InetAddress IP; 
	public Integer port;
	public String name;
	public String room;

	public Member(String IPP, String name, String room){
		this.room = room;
		this.port = getPort(IPP);
		this.name = name;
		try{
			this.IP = InetAddress.getByName(this.getIP(IPP));
	
		}catch(UnknownHostException e){
			System.out.print("Can not find host: " + getIP(IPP));
		}
	}

	public String getIP(String IPP){
		String IP = IPP.substring(0,IPP.indexOf(':'));
		return IP;
	}

	public Integer getPort(String IPP){
		String port = IPP.substring(IPP.indexOf(':') + 1);
		Integer PORT = Integer.parseInt(port);
		return PORT;
	}
}