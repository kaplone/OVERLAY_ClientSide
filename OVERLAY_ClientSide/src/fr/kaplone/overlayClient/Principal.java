package fr.kaplone.overlayClient;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Principal {

	public static void main(String[] args) throws IOException {
		
		File path = new File("images/images_source");
		File pathOut = new File("images/images_out");
		
		BufferedImage device = ImageIO.read(new File(path, "Main_F_iPhones_1.png"));
		int w = device.getWidth();
		int h = device.getHeight();
		Point pointDevice = new Point(w/2, h/2);
		
		BufferedImage main = ImageIO.read(new File(path, "Main_F_Clic_1_.png"));
		main = ImageUtils.scale(main, 0.4);
		Point pointDoigt = new Point(650, 120);
		
		BufferedImage fond = ImageIO.read(new File(path, "vert.png"));
		
		BufferedImage composition = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		
		List<String> frames = OtherUtils.sortedList(new File("images/frames_WakeApp"));
		
		Point temp;
		
//		for (Point p : PP){
//			ImageUtils.writeImage(ImageUtils.compose4Layers(fond, device, contenu(p.getImageNumber()), main, p.getCoordX(), p.getCoordY(), composition), pathOut, String.format("test_mvt_%05d.png", p.getImageNumber()));
//		}
		
//		for (int i = 1; i < frames.size(); i++){
//		    temp = pointDoigt.doigtRelatifToDevice(0, 0);// remplacer  (0, 0) par les accesseurs à la liste des coordonnees
//		    ImageUtils.ecrireImage(ImageUtils.composition4Niveaux(fond, device, contenu(i), main, temp.getCoordX(), temp.getCoordY(), composition), pathOut, String.format("test_%05d.png", i));
//		}
		
	}
	
	public static BufferedImage contenu(int i) throws IOException {
		File pathFrames = new File("images/frames_WakeApp/");
		BufferedImage contenu = ImageIO.read(new File(pathFrames, String.format("image2_%05d.png", i)));
		contenu = ImageUtils.scale(contenu, 0.7);
		
		return contenu;
	}

}
