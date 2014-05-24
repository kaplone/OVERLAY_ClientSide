package fr.kaplone.sourceUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Device {
	
	String imagePath;
	File imageFile;
	double scaleValue;
	
	double[] imageSize = new double[2];
	double[] screenSize = new double[2];
	double[] screenPos = new double[2];
	
	/**
	 * 
	 * @param pathImage
	 * @param scaleValue
	 * @param offset
	 * @param screenSize
	 * @throws IOException
	 */
	
	public Device(String pathImage, double scaleValue, double[] offset, double [] screenSize) throws IOException {
		this.imagePath = pathImage;
		this.imageFile = new File(pathImage);
		BufferedImage image = ImageIO.read(this.imageFile);
		this.imageSize[0] = image.getWidth();
		this.imageSize[1] = image.getHeight();
		this.scaleValue = scaleValue;
		this.screenSize = screenSize;
		this.screenPos = offset;
	}
	
	public double getImageWidth(){
		return this.imageSize[0];
	}
	public double getImageHeight(){
		return this.imageSize[1];
	}
	
	public String getImagePath() {
		return imagePath;
	}

	public double[] getImageSize() {
		return imageSize;
	}

	public File getImageFile() {
		return imageFile;
	}
	
	public double[] getScreenSize() {
		return screenSize;
	}

	public double getScreenWidth(){
		return this.screenSize[0];
	}
	public double getScreenHeight (){
		return this.screenSize[1];
	}
	
	public double getOffsetX(){
		return this.screenPos[0];
	}

	public double getOffsetY(){
		return this.screenPos[1];
	}
	
	public Device scalingDevice(double standard) throws IOException{
		double[] scaledScreen = {this.getScreenWidth() * standard, this.getScreenHeight() * standard};
		double[] scaledOffset = {this.getOffsetX() * standard, this.getOffsetY() * standard};
		return new Device(this.getImagePath(), 0, scaledScreen, scaledOffset);
	}
	

}
