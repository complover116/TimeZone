package com.complover116.timezone;

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

import com.complover116.timezone.blocks.Ground;
import com.complover116.timezone.blocks.Wall;
import com.complover116.timezone.entities.Sentry;

public class MainScreen extends JPanel implements MouseListener, KeyListener {
	public static volatile ArrayList<DrawThing> objects = new ArrayList<DrawThing>();
	public static ArrayList<DrawThing> indepobjects = new ArrayList<DrawThing>();
	public static ArrayList<ShapeModel> shapes = new ArrayList<ShapeModel>();
	public static int width = 0;
	public static int height = 0;
	public static double shear = 0;
	/**
	 * 
	 */
	private static final long serialVersionUID = -2355840993291782784L;

	public void paintComponent(Graphics g) {
	
	  super.paintComponent(g);
	  width = this.getWidth();
	  height = this.getHeight();
	  Graphics2D g2d = (Graphics2D) g;
	  if(CurGame.status >-1){
	  g2d.translate(-CurGame.scrollX, -CurGame.scrollY);
	  g2d.transform(AffineTransform.getScaleInstance(1 - shear/100, 1 - shear/100));
	  int sizebefore = objects.size();
	  for(int ser = 0; ser < objects.size(); ser++){
      try{
    	  //objects.get(i).x-CurGame.scrollX > width||objects.get(i).y-CurGame.scrollY > height
	  if(objects.get(ser)==null&&objects.get(ser).draw) {
		  
	  } else {
	  AffineTransform rt = AffineTransform.getRotateInstance(Math.toRadians(objects.get(ser).rot),objects.get(ser).rotX,objects.get(ser).rotY);
	  AffineTransform tr = AffineTransform.getTranslateInstance(objects.get(ser).x, objects.get(ser).y);
	  tr.concatenate(rt);
	  g2d.drawImage(objects.get(ser).img, tr, this);
	  }
      } catch (Exception e) {
    	  
      }
	 }
	  g2d.setColor(new Color(255,0,0,255));
	  for(int i = 0; i < shapes.size(); i++){
		  try{
		  g2d.setColor(shapes.get(i).color);
		  if(shapes.get(i).fill)
		  g2d.fill(shapes.get(i).shape);
		  g2d.draw(shapes.get(i).shape);
		  }catch(Exception e) {
			  
		  }
	  }
	  shapes.clear();
	  if(CurGame.terra != null){
	  g2d.setColor(new Color(0,0,0,255));
	  g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
	  g2d.drawString("Zone of team "+CurGame.terra.owner, 2, 20);
	  }
	  g2d.translate(CurGame.scrollX, CurGame.scrollY);
	  g2d.drawString("Time left:"+Metrics.timeFromSeconds((int)CurGame.attackTime), 0, 20);
		  if(CurGame.timespeed == 0){
			  g2d.setColor(new Color(255,0,0,255));
			  g2d.drawString("Time stopped", 0, 450);
		  }else {
			  g2d.setColor(new Color(0,255,0,255));
			  g2d.drawString("Time speed:"+CurGame.timespeed+"%", 0, 450);
		  }
		  g2d.setColor(new Color(185,185,185,255));
		  g2d.drawString("Metal:"+CurGame.teams[CurGame.terra.owner].metal, 200, 20);
		  if(CurGame.status == 21){
		  g2d.setColor(new Color(235,235,235,255));
		  	g2d.drawString("G - Wall", 200, height - 20);
		  }
	  }
		  if(CurGame.status == -5){
			  g2d.setColor(new Color(0,255,0,255));
			  	g2d.drawString("LOADING", width/2, height/2);
			  	System.out.println("XX");
			  }
		  for(int i = 0; i < indepobjects.size(); i++){
			  try{
			  if(false) {
				  
			  } else {
			  AffineTransform rt = AffineTransform.getRotateInstance(Math.toRadians(indepobjects.get(i).rot),indepobjects.get(i).rotX,indepobjects.get(i).rotY);
			  AffineTransform tr = AffineTransform.getTranslateInstance(indepobjects.get(i).x, indepobjects.get(i).y);
			  tr.concatenate(rt);
			  g2d.drawImage(indepobjects.get(i).img, tr, this);
			  }
			  }catch(Exception e) {
				  
			  }
			 }
}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	
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
		if(CurGame.status == 0){
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
		} else if(CurGame.status > 0&&CurGame.status < 5&&!arg0.isShiftDown()) {
			if(arg0.getKeyCode() == 87){
				CurGame.scrollingY = -1;
			}
			if(arg0.getKeyCode() == 83){
				CurGame.scrollingY = 1;
			}
			if(arg0.getKeyCode() == 68){
				CurGame.scrollingX = 1;
			}
			if(arg0.getKeyCode() == 65){
				CurGame.scrollingX = -1;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(CurGame.status == 0){
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
		} else if(CurGame.status > 0&&CurGame.status < 5) {
			if(arg0.getKeyCode() == 87&&CurGame.scrollingY == -1){
				CurGame.scrollingY = 0;
			}
			if(arg0.getKeyCode() == 83&&CurGame.scrollingY == 1){
				CurGame.scrollingY = 0;
			}
			if(arg0.getKeyCode() == 68&&CurGame.scrollingX == 1){
				CurGame.scrollingX = 0;
			}
			if(arg0.getKeyCode() == 65&&CurGame.scrollingX == -1){
				CurGame.scrollingX = 0;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		if(CurGame.status == 21) {
			if(arg0.getKeyChar() == 'g') {
				BlockBuildTool bb = new BlockBuildTool(new Wall(), CurGame.controllingTeam, 20, "wall_unbuilt", 1);
				bb.setPos(CurGame.terra.preview.getPos());
				CurGame.terra.regEntity(bb);
				CurGame.status = 2;
				CurGame.terra.preview.tool = bb;
				SoundHandler.playSound("sentry/seek_1");
			}
			if(arg0.getKeyChar() == 'x') {
				BlockBuildTool bb = new BlockBuildTool(new Ground(), CurGame.controllingTeam, 5, "delete", 0);
				bb.setPos(CurGame.terra.preview.getPos());
				CurGame.terra.regEntity(bb);
				CurGame.status = 2;
				CurGame.terra.preview.tool = bb;
				SoundHandler.playSound("sentry/seek_1");
			}
		}
		if(CurGame.status == 22) {
			if(arg0.getKeyChar() == 'g') {
				ConstructionTool bb = new ConstructionTool(new Sentry(CurGame.controllingTeam));
				bb.setPos(CurGame.terra.preview.getPos());
				CurGame.terra.regEntity(bb);
				CurGame.status = 2;
				CurGame.terra.preview.tool = bb;
				SoundHandler.playSound("sentry/seek_1");
			}
		}
		if(CurGame.status == 2) {
			if(arg0.getKeyChar() == 'W') {
				CurGame.terra.preview.model.y -= 16;
				SoundHandler.playSound("ui/click1");
			}
			if(arg0.getKeyChar() == 'S') {
				CurGame.terra.preview.model.y += 16;
				SoundHandler.playSound("ui/click1");
			}
			if(arg0.getKeyChar() == 'A') {
				CurGame.terra.preview.model.x -= 16;
				SoundHandler.playSound("ui/click1");
			}
			if(arg0.getKeyChar() == 'D') {
				CurGame.terra.preview.model.x += 16;
				SoundHandler.playSound("ui/click1");
			}
			//char russianChars[] = {'ö','ô','û','â'};
		}
		if(arg0.getKeyChar() == '\n') {
			if(CurGame.status==1) {
				SoundHandler.playSound("sentry/seek_1");
				CurGame.status = 2;
			}
			if(CurGame.status==2) {
				
				if(CurGame.terra.preview.tool == null) {
					try {
					SoundHandler.playSound("sentry/seek_1");
					Thread.sleep(100);
					SoundHandler.playSound("sentry/seek_1");
					Thread.sleep(100);
					SoundHandler.playSound("sentry/seek_1");
				}catch(InterruptedException e){
					
				}
				} else {
					
					if(CurGame.terra.preview.tool.use()) {
						SoundHandler.playSound("sentry/seek_1");
					}
				}
			}
		}
		if(arg0.getKeyChar() == 'q') {
			if(CurGame.status == 0&&CurGame.terra.controlledEnt != null) {
				CurGame.terra.controlledEnt.fire1();
			}
		}
		if(arg0.getKeyChar() == 'b') {
			if(CurGame.status==2) {
				SoundHandler.playSound("sentry/seek_1");
				if(CurGame.terra.preview.tool == null) {
					CurGame.terra.preview.model.img = TeamData.getTeamImage("cursor2", CurGame.terra.owner);
				CurGame.status = 21;
				} else {
					CurGame.terra.preview.tool.remove();
					CurGame.terra.preview.tool = null;
					CurGame.terra.preview.model.img = TeamData.getTeamImage("cursor", CurGame.terra.owner);
				}
			}
		}
		if(arg0.getKeyChar() == 'c') {
			if(CurGame.status==2) {
				SoundHandler.playSound("sentry/seek_1");
				if(CurGame.terra.preview.tool == null) {
					CurGame.terra.preview.model.img = TeamData.getTeamImage("cursor2", CurGame.terra.owner);
				CurGame.status = 22;
				} else {
					CurGame.terra.preview.tool.remove();
					CurGame.terra.preview.tool = null;
					CurGame.terra.preview.model.img = TeamData.getTeamImage("cursor", CurGame.terra.owner);
				}
			}
		}
		if(arg0.getKeyChar() == ' ') {
			
			if(CurGame.status==0) {
				if(!arg0.isShiftDown()) {
				CurGame.gamego = false;
				} else {
					CurGame.status = 10;
					WorldTicker.randomflag = false;
				}
			}
			if(CurGame.status==1) {
				CurGame.gamego = true;
				CurGame.status = 0;
			}
			if(CurGame.status == 2) {
				SoundHandler.playSound("sentry/seek_1");
				if(CurGame.terra.preview.tool!= null)
				CurGame.terra.preview.tool.remove();
				CurGame.terra.preview.tool = null;
				CurGame.terra.preview.model.img = TeamData.getTeamImage("cursor", CurGame.terra.owner);
				if(CurGame.terra.owner == 0){
					CurGame.controllingTeam = 1;
				} else {
					CurGame.controllingTeam = 0;
				}
				CurGame.status = 1;
			}
		}
	}
}