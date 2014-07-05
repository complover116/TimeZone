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
	  if(objects.get(i).x-CurGame.scrollX > 500||objects.get(i).y-CurGame.scrollY > 500) {
		  
	  } else {
	  AffineTransform rt = AffineTransform.getRotateInstance(Math.toRadians(objects.get(i).rot),objects.get(i).rotX,objects.get(i).rotY);
	  AffineTransform tr = AffineTransform.getTranslateInstance(objects.get(i).x-CurGame.scrollX, objects.get(i).y-CurGame.scrollY);
	  tr.concatenate(rt);
	  g2d.drawImage(objects.get(i).img, tr, this);
	  }
	 }
}
}