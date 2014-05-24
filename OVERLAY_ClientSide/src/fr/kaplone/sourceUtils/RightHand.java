package fr.kaplone.sourceUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RightHand {
	

	String imagePath;
	File imageFile;
	double scaleValue;
	Point edge;
	
	double[] imageSize = new double[2];
	
	
	public RightHand(String imagePath, double scaleValue, Point edge) throws IOException {
		this.imagePath= "images/sources_pictures/" + imagePath;
		this.imageFile = new File(imagePath);
		BufferedImage image = ImageIO.read(this.imageFile);
		this.scaleValue = scaleValue;
		this.imageSize[0] = image.getWidth();
		this.imageSize[1] = image.getHeight();
		this.edge = edge;
	}


	public double getScaleValue() {
		return scaleValue;
	}


	public Point getEdge() {
		return edge;
	}


	public double[] getImageSize() {
		return imageSize;
	}
}
