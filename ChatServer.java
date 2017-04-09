import java.util.*;
public class ChatServer {

	public static void main(String[] args){
		//scanner so the user can enter their screen user-name
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter a username: ");
		String name = scan.nextLine();
		System.out.println();
		
		//Create and start the thread for receiving multicast messages
		MulticastReceiver receiver = new MulticastReceiver();
		receiver.start();
		
		//Create and start the thread for sending multicast messages
		MulticastSender sender = new MulticastSender(name);
		sender.start();
		
	}
}
