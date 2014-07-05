package com.klassers.timezone;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageContainer {
	public static BufferedImage test = null;
	public static HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();
	public static final String imagenames[] = {"test","ground_1","ground_2","ground_3"};
	public static final String imagepaths[] = {"/img/terrain/test.png","/img/terrain/ground_1.png","/img/terrain/ground_2.png","/img/terrain/ground_3.png"};
	public static void load() {
		for(int i = 0; i < imagenames.length; i++) {
			InputStream is = GUI.class.getResourceAsStream(imagepaths[i]);
			BufferedImage img = null;
			try {
				img = ImageIO.read(is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			images.put(imagenames[i], img);
			System.out.println(imagepaths[i]+" loaded");
		}
	}
}
