import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
public class EchoClient {
	
	public static void main(String[] args){
		
		//initialize sockets, streams, etc
		
		Socket comm = null;
		DataOutputStream out = null;
		BufferedReader in = null;
		InputStreamReader reader = null;
		
		//grab args for port and hostname

		String host = args[0];
		int port = Integer.parseInt(args[1]);
		
		//append the almighty return symbol just in case
		String input = getinput() + "\n";

		try {
			//open and connect socket, set up input and output streams
			comm = new Socket(host, port);
			out = new DataOutputStream(comm.getOutputStream());
			reader = new InputStreamReader(comm.getInputStream());
			in = new BufferedReader(reader);
		} catch (UnknownHostException e) {
			System.err.println("Unknown host.");
		} catch (IOException e) {
			System.err.println("I/O error while trying to connect.");
		}
		
		if(comm != null && out != null && in != null){
			try {
				//write output to out stream
				out.writeBytes(input);
				//read and print one line from the input stream
				String line = "";
				if ((line = in.readLine()) != null){
					System.out.println(line);
				}
			} catch (IOException e) {
				System.err.println("I/O error while trying to send/receive message.");
				
			}
			//close all streams and socket
			try{
				in.close();
				out.close();
				comm.close();
				reader.close();
			} catch (IOException e){
				System.err.println("I/O error while trying to close streams/socket");
			}
			
		}
	}
	
	//Grabs a line of input from the terminal and returns it to the program as a string

	public static String getinput() {
		Console c = System.console();
		String input = c.readLine("Enter string: ");
		return input;
	}
}