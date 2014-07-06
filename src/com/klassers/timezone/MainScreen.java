package com.klassers.timezone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MainScreen extends JPanel {
	public static ArrayList<DrawThing> objects = new ArrayList<DrawThing>();
	public static ArrayList<Shape> shapes = new ArrayList<Shape>();
	/**
	 * 
	 */
	private static final long serialVersionUID = -2355840993291782784L;

	public void paintComponent(Graphics g) {
	  super.paintComponent(g);
	  Graphics2D g2d = (Graphics2D) g;
	  for(int i = 0; i < objects.size(); i++){
	  try{
	  if(objects.get(i).x-CurGame.scrollX > 500||objects.get(i).y-CurGame.scrollY > 500) {
		  
	  } else {
	  AffineTransform rt = AffineTransform.getRotateInstance(Math.toRadians(objects.get(i).rot),objects.get(i).rotX,objects.get(i).rotY);
	  AffineTransform tr = AffineTransform.getTranslateInstance(objects.get(i).x-CurGame.scrollX, objects.get(i).y-CurGame.scrollY);
	  tr.concatenate(rt);
	  g2d.drawImage(objects.get(i).img, tr, this);
	  }
	  }catch(NullPointerException e) {
		  System.out.println("Nullpoiner in draw thread.");
	  }
	 }
	  g2d.setColor(new Color(255,0,0,255));
	  for(int i = 0; i < shapes.size(); i++){
		  g2d.draw(shapes.get(i));
	  }
}
}