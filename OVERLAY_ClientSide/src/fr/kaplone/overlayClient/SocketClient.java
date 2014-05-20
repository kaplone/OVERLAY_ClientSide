package fr.kaplone.overlayClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class SocketClient {
	
	//public void newSocketClient() throws IOException{
	public static void main (String args[]) throws IOException{
		int port = 8095;
		Socket s = new Socket();
		String host = "localhost";
		
		PrintStream outStream = null;
        BufferedReader inStream = null;
        String message = null;
		
		try
	    {
	    s.connect(new InetSocketAddress(host , port));
	    outStream = new PrintStream(s.getOutputStream());
        outStream.flush();
        inStream = new BufferedReader(new InputStreamReader(s.getInputStream()));
        }

	     
	    //Host not found
	    catch (UnknownHostException e) 
	    {
	        System.err.println("Don't know about host : " + host);
	        System.exit(1);
	    }
	     
	    System.out.println("Connected");
	    
	    Scanner sc = new Scanner(new File("records/ref.txt"));
        while (sc.hasNextLine()){
            outStream.println(sc.nextLine());
        }
        sc.close();
    }

}
