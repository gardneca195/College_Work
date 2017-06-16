import java.util.*;

public class Room{
		
	
	public ArrayList<Member> members;
	public String name;
	 
	public Room(String name){
		this.name = name;
		this.members = new ArrayList<Member>();
	}
	
	public void addMember(Member member){
		members.add(member);
	}
	
	public void remove(Member member){
		members.remove(member);
	}

}