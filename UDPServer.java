import java.io.*;
import java.net.*;
class UDPServer
{
public static void main(String args[]) throws Exception
{
	DatagramSocket serverSocket = new DatagramSocket(9876);
	System.out.println("--------Server Started-------");
	BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
	
	byte[] receiveData = new byte[200];
	byte[] sendData;
	
	
	DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	serverSocket.receive(receivePacket);
	InetAddress clientAddress = receivePacket.getAddress();
	int port = receivePacket.getPort();
	
	System.out.println("Client with IP Address "+clientAddress.toString()+" connected...");
	System.out.println("------------------------------------------------------------");
	System.out.println("Enter the message to send to client:");
	
	
while(true)
	{
		String input = inFromUser.readLine();
		sendData = new byte[200];
		sendData = input.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, port);
		serverSocket.send(sendPacket);
	}
}

}