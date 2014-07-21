package com.klassers.timezone;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MainScreen extends JPanel implements MouseListener, KeyListener {
	public static ArrayList<DrawThing> objects = new ArrayList<DrawThing>();
	public static ArrayList<ShapeModel> shapes = new ArrayList<ShapeModel>();
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
		  g2d.setColor(shapes.get(i).color);
		  g2d.fill(shapes.get(i).shape);
		  g2d.draw(shapes.get(i).shape);
	  }
	  g2d.setColor(new Color(0,0,0,255));
	  g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
	  g2d.drawString("TEST", 2, 20);
}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.isShiftDown()) {
			for(int i = 0; i < CurGame.terra.entities.size(); i++) {
				if(arg0.getX() > CurGame.terra.entities.get(i).model.x&&arg0.getY() > CurGame.terra.entities.get(i).model.y&&arg0.getX() < CurGame.terra.entities.get(i).model.x+CurGame.terra.entities.get(i).model.img.getWidth()&&arg0.getY() < CurGame.terra.entities.get(i).model.y+CurGame.terra.entities.get(i).model.img.getHeight())
				{
					CurGame.terra.entities.get(i).drawInfo = true;
				}
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
			for(int i = 0; i < CurGame.terra.entities.size(); i++) {
					CurGame.terra.entities.get(i).drawInfo = false;
			}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == 87){
			CurGame.terra.controlledEnt.movDir = 1;
		}
		if(arg0.getKeyCode() == 83){
			CurGame.terra.controlledEnt.movDir = -1;
		}
		if(arg0.getKeyCode() == 68){
			CurGame.terra.controlledEnt.turn = 1;
		}
		if(arg0.getKeyCode() == 65){
			CurGame.terra.controlledEnt.turn = -1;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {;
		if(arg0.getKeyCode() == 87&&CurGame.terra.controlledEnt.movDir == 1){
			CurGame.terra.controlledEnt.movDir = 0;
		}
		if(arg0.getKeyCode() == 83&&CurGame.terra.controlledEnt.movDir == -1){
			CurGame.terra.controlledEnt.movDir = 0;
		}
		if(arg0.getKeyCode() == 68&&CurGame.terra.controlledEnt.turn == 1){
			CurGame.terra.controlledEnt.turn = 0;
		}
		if(arg0.getKeyCode() == 65&&CurGame.terra.controlledEnt.turn == -1){
			CurGame.terra.controlledEnt.turn = 0;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}