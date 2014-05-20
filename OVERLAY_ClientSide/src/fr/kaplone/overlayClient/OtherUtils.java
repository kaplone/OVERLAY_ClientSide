package fr.kaplone.overlayClient;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OtherUtils {
	
	public static List<String> sortedList (File d){
		
		String[] listDir = d.list();
		List<String> ld = Arrays.asList(listDir);
		Collections.sort(ld);
		return ld;

	}
	
	
}
