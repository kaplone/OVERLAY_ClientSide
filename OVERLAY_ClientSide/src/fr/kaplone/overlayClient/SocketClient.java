package fr.kaplone.overlayClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.Timer;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintStream;

/* note temporaire :
 * 
 * par ce socket vont transiter :
 * 
 *  - en upload : 
 *    les valeurs contenues dans ref.txt
 *    (n° de frame, coordonnées x et y du doigt sur l'écran, code pour la position clé suivante, code pour le type d'action)
 *    
 *  - en download :
 *    les valeurs nécessaires pour construire l'overlay de toutes les images
 *    (n° des frames, coordonnées de placement des calques, valeurs de zoom des éléments)
 *  
 */

public class SocketClient {

	public static void main (String args[]) throws IOException, InterruptedException{
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
            outStream.flush();
            outStream.close();
        }
        sc.close();

        Scanner sc2 = new Scanner(inStream);
        while (sc2.hasNextLine()){
        	System.out.println("$");
            System.out.println(sc2.nextLine());
        }
        inStream.close();
        sc2.close();
    }

}
