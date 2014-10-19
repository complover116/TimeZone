package com.complover116.timezone;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import org.yaml.snakeyaml.Yaml;

public class ImageContainer {
	public static BufferedImage test = null;
	public static HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();
	public static HashMap<String, Clip> sounds = new HashMap<String, Clip>();
	@SuppressWarnings("unchecked")
	public static void load() throws Exception {
		Yaml yaml = new Yaml();
		InputStream stream = GUI.class.getResourceAsStream("/resources.yml");
		HashMap<String, ArrayList<HashMap<String,String>>> test = (HashMap<String, ArrayList<HashMap<String, String>>>) yaml.load(stream);
		ArrayList<HashMap<String,String>> imgs = test.get("images");
		ArrayList<HashMap<String,String>> sonds = test.get("sounds");
		for(int i = 0; i < imgs.size(); i++) {
			CurGame.loadStep = imgs.get(i).get("file");
			GUI.redraw();
			InputStream is = GUI.class.getResourceAsStream(imgs.get(i).get("file"));
			BufferedImage img = null;
			try {
				img = ImageIO.read(is);
				images.put(imgs.get(i).get("name"), img);
				System.out.println(imgs.get(i).get("file")+" loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println(imgs.get(i).get("file")+" failed to load!");
			} catch (IllegalArgumentException e1) {
				System.err.println(imgs.get(i).get("file")+" failed to load!");
			}
			
		}
		int soundsFailed = 0;
		for(int i = 0; i < sonds.size(); i++) {
			String file = "/sound/"+sonds.get(i).get("name")+".wav";
			CurGame.loadStep = file;
			GUI.redraw();
			URL is = GUI.class.getResource(file);
			
			
			try {
				Clip clip = AudioSystem.getClip();
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(is);
				clip.open(audioIn);
				sounds.put(sonds.get(i).get("name"), clip);
				System.out.println(file+" loaded");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				soundsFailed ++;
				e.printStackTrace();
				System.err.println(sonds.get(i).get("name")+" failed to load!");
			} 
			
		}
		if(soundsFailed>0) {
			if(soundsFailed < sonds.size()) {
				JOptionPane.showMessageDialog(GUI.ms, soundsFailed+" sounds failed to load and will not be played! Check console for more details", "Sounds failed to load", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(GUI.ms, "No sounds were able to load - this is probably your system's problem. Sounds will be disabled. Check console for more details", "Sounds failed to load", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
