package fr.kaplone.overlayClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.Timer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;

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

		OutputStream outWriter;
		BufferedReader inReader;

		try{
			s.connect(new InetSocketAddress(host , port));
			outWriter = s.getOutputStream();
			inReader = new BufferedReader(new InputStreamReader(s.getInputStream()));

			System.out.println("Connected");

			Path path = Paths.get("records/ref.txt");
			byte[] data = Files.readAllBytes(path);
			boolean boucle = true;

			for (int i = 0; i < data.length; i++){
				outWriter.write(data[i]);
			}
			outWriter.write(-1);
			outWriter.flush();

			System.out.println("fin de l'envoi");
			boucle = false; 
			System.out.println("envoi client->serveur clos");  

			int z;
			boolean boucle2 = true;
			int lecture2;
			Scanner scan = null;
			ArrayList<ArrayList<Integer>> ca = new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> cl = new ArrayList<Integer>();
			ArrayList<Integer> datas = new ArrayList<Integer>();

			StringBuilder result = new StringBuilder(datas.size());

			while (boucle2){
				lecture2 = inReader.read();

				if(lecture2 != -1){
					System.out.println("début de la lecture coté client : ");

					while (true){
						if (lecture2 == 65533) break;
						result.append(Character.toChars(lecture2)[0]);
						lecture2 = inReader.read();
					}

					boucle2 = false;
					String lectureString = result.toString();
					System.out.println(lectureString);

					BufferedReader lignes = new BufferedReader(new StringReader(lectureString));

					String ligne = lignes.readLine();
					while (ligne != null){
						System.out.println(ligne);
						scan = new Scanner(ligne);
						while (scan.hasNextInt()){
							z = scan.nextInt();
							cl.add(z);
						}
						ca.add(cl);   
						ligne = lignes.readLine();
						cl = new ArrayList<Integer>();
						scan.close();
					}
					//break;

				}
				System.out.println("cloture de la reception des datas sur le serveur");
				scan.close();
				boucle = false; 

			}
		}

		//Host not found
		catch (UnknownHostException e) 
		{
			System.err.println("Don't know about host : " + host);
			System.exit(1);
		}
	}
}
