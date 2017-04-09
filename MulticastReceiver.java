import java.io.*;
import java.net.*;

public class MulticastReceiver implements Runnable{

	private Thread t;
	
	public MulticastReceiver(){
		
	}
	
	public void run(){
		
		//UDP datagram socket
		MulticastSocket socket = null;
		
		DatagramPacket inPacket = null;
		
		//message can be 256 bytes long
		byte [] inBuff = new byte[256];
		
		try{
			
			//socet defined above on port 8888
			socket = new MulticastSocket(8888);
			
			//define ip address 224.2.2.3
			InetAddress address = InetAddress.getByName("224.2.2.3");
			//join ip address
			socket.joinGroup(address);
			
			//all going well
			while(true){
				
				//inPacket variable holds info of the buffer and the length
				inPacket = new DatagramPacket(inBuff, inBuff.length);
				//recieve the info from inPacket
				socket.receive(inPacket);
				//set up the message variable, containing info from sent multicast message
				String message = new String(inBuff, 0, inPacket.getLength());
				//Print the message and address it was sent from on the screen
				System.out.println(message + " \t(" + inPacket.getAddress() + ")");
			}
		}
		//if something goes wrong
		catch(IOException e){
			System.out.println(e);
		}
	}
		
		public void start () {
			//verify we reach this method
			System.out.println("Starting Receiver Thread");
			//if there is no thread stated, start one
			if(t == null){
				t = new Thread(this);
				t.start();
			}
				
		}
	}
	
	

