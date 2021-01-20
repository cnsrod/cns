import java.io.*;
import java.net.*;
public class FileClient
{
public static void main(String[] args)
{
new FileClient();
}


//TCP Socket Program
public FileClient()
{
	BufferedReader bufReader=new BufferedReader(new InputStreamReader(System.in));
	try
	{
	System.out.println("Enter IP address of the server:");
	String saddr = bufReader.readLine();
	Socket clientsocket=new Socket(saddr,8000);
	System.out.println("Connecting to Server....");

	DataInputStream input=new DataInputStream(clientsocket.getInputStream());
	DataOutputStream output=new DataOutputStream(clientsocket.getOutputStream());

	System.out.println("Enter File Name:");
	String Name=bufReader.readLine();
	output.writeUTF(Name);

	String EchoedFile=input.readUTF();
	System.out.println("-----------------------------------------------");
	System.out.println("Content of a File:\n\n"+EchoedFile);
	System.out.println("-----------------------------------------------");
	clientsocket.close();
	}


//TCP Socket Program
catch(IOException ex)
	{
	ex.printStackTrace();
	}
}
}