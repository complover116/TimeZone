package com.klassers.timezone;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MainScreen extends JPanel {
	public static ArrayList<DrawThing> objects = new ArrayList<DrawThing>();
	/**
	 * 
	 */
	private static final long serialVersionUID = -2355840993291782784L;

	public void paintComponent(Graphics g) {
	  super.paintComponent(g);
	  Graphics2D g2d = (Graphics2D) g;
	  for(int i = 0; i < objects.size(); i++){
	  if(objects.get(i).x > 500||objects.get(i).y > 500) {
		  
	  } else {
	  AffineTransform rt = AffineTransform.getRotateInstance(Math.toRadians(objects.get(i).rot),8,8);
	  AffineTransform tr = AffineTransform.getTranslateInstance(objects.get(i).x, objects.get(i).y);
	  tr.concatenate(rt);
	  g2d.drawImage(objects.get(i).img, tr, this);
	  }
	 }
}
}