import java.io.*;
import java.net.*;
class UDPClient
{
public static void main(String args[]) throws Exception
{
	BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Enter the IP address of the Server:");

	String saddr = inFromUser.readLine();
	DatagramSocket clientSocket = new DatagramSocket();
	InetAddress IPAddress = InetAddress.getByName(saddr);

	byte[] receiveData;
	byte[] sendData = new byte[200];

	String sentence = "Hello";
	sendData = sentence.getBytes();
	DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
	clientSocket.send(sendPacket);

	while(true)
	{
		receiveData = new byte[200];
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);

		String incomingData = new String(receivePacket.getData());
		InetAddress SAddress = receivePacket.getAddress();
		System.out.println("FROM SERVER"+"("+SAddress.toString()+"): " + incomingData);
		System.out.println("------------------------------------------------------------");
	}
}
}