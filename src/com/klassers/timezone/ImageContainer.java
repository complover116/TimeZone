package com.klassers.timezone;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.yaml.snakeyaml.Yaml;

public class ImageContainer {
	public static BufferedImage test = null;
	public static HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();
	@SuppressWarnings("unchecked")
	public static void load() {
		Yaml yaml = new Yaml();
		InputStream stream = GUI.class.getResourceAsStream("/resources.yml");
		HashMap<String, ArrayList<HashMap<String,String>>> test = (HashMap<String, ArrayList<HashMap<String, String>>>) yaml.load(stream);
		ArrayList<HashMap<String,String>> imgs = test.get("images");
		for(int i = 0; i < imgs.size(); i++) {
			InputStream is = GUI.class.getResourceAsStream(imgs.get(i).get("file"));
			BufferedImage img = null;
			try {
				img = ImageIO.read(is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			images.put(imgs.get(i).get("name"), img);
			System.out.println(imgs.get(i).get("file")+" loaded");
		}
	}
}
