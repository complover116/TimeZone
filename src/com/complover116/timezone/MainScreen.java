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
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.complover116.timezone.blocks.Ground;
import com.complover116.timezone.blocks.Rail;
import com.complover116.timezone.blocks.Wall;
import com.complover116.timezone.entities.Factory;
import com.complover116.timezone.entities.RailMount;
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

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		width = this.getWidth();
		height = this.getHeight();
		Graphics2D g2d = (Graphics2D) g;
		if (CurGame.overstat > -1) {
			g2d.translate(-CurGame.c.scrollX, -CurGame.c.scrollY);
			g2d.transform(AffineTransform.getScaleInstance(1 - shear / 100,
					1 - shear / 100));
			int sizebefore = objects.size();
			for (int ser = 0; ser < objects.size(); ser++) {
				try {
					// objects.get(i).x-CurGame.c.scrollX >
					// width||objects.get(i).y-CurGame.c.scrollY > height
					if (objects.get(ser) == null && objects.get(ser).draw) {

					} else {
						AffineTransform rt = AffineTransform.getRotateInstance(
								Math.toRadians(objects.get(ser).rot),
								objects.get(ser).rotX, objects.get(ser).rotY);
						AffineTransform tr = AffineTransform
								.getTranslateInstance(objects.get(ser).x,
										objects.get(ser).y);
						tr.concatenate(rt);
						g2d.drawImage(ImageContainer.images.get((objects
								.get(ser).img)), tr, this);
					}
				} catch (Exception e) {

				}
			}
			g2d.setColor(new Color(255, 0, 0, 255));
			for (int i = 0; i < shapes.size(); i++) {
				try {
					g2d.setColor(shapes.get(i).color);
					if (shapes.get(i).fill)
						g2d.fill(shapes.get(i).shape);
					g2d.draw(shapes.get(i).shape);
				} catch (Exception e) {

				}
			}
			shapes.clear();
			if (CurGame.c.terra != null) {
				g2d.setColor(new Color(0, 0, 0, 255));
				g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30));
				g2d.drawString("Zone of team " + CurGame.c.terra.owner, 2, 20);
			}
			g2d.translate(CurGame.c.scrollX, CurGame.c.scrollY);
			g2d.drawString(
					"Time left:"
							+ Metrics
									.timeFromSeconds((int) CurGame.c.attackTime),
					0, 20);
			if (CurGame.c.timespeed == 0) {
				g2d.setColor(new Color(255, 0, 0, 255));
				g2d.drawString("Time stopped", 0, 450);
			} else {
				g2d.setColor(new Color(0, 255, 0, 255));
				g2d.drawString("Time speed:" + CurGame.c.timespeed + "%", 0,
						450);
			}
			g2d.setColor(new Color(185, 185, 185, 255));
			g2d.drawString("Metal:"
					+ CurGame.c.teams[CurGame.c.terra.owner].dismetal, 200, 20);
			if (CurGame.c.status == 2) {
				if (CurGame.c.terra.preview.selent != null) {
					g2d.drawString(CurGame.c.terra.preview.selent.readName, 32,
							height - 20);
					shapes.add(new ShapeModel(new Rectangle2D.Double(
							CurGame.c.terra.preview.selent.model.x
									+ CurGame.c.terra.preview.selent.collideX,
							CurGame.c.terra.preview.selent.model.y
									+ CurGame.c.terra.preview.selent.collideY,
							CurGame.c.terra.preview.selent.collideX2,
							CurGame.c.terra.preview.selent.collideY2),
							new Color(0, 255, 0, 255), false));
					if (CurGame.c.terra.preview.entSwitch < CurGame.c.terra.preview.entsAvail - 1) {
						DrawThing leftArr = new DrawThing();
						leftArr.setModel("echooser_right");
						leftArr.x = 32 + (CurGame.c.terra.preview.selent.readName
								.length() * 20);
						leftArr.y = height - 52;
						indepobjects.add(leftArr);
					}
					if (CurGame.c.terra.preview.entSwitch > 0) {
						DrawThing leftArr = new DrawThing();
						leftArr.setModel("echooser_left");
						leftArr.x = 0;
						leftArr.y = height - 52;
						indepobjects.add(leftArr);
					}
				}
			}
		}
		if (CurGame.overstat == -5) {
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30));
			g2d.setColor(new Color(0, 255, 0, 255));
			g2d.drawString("LOADING", width / 2, height / 2);
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			g2d.setColor(new Color(0, 255, 0, 255));
			g2d.drawString(CurGame.loadStep, width / 2 - 100, height / 2 + 30);
		}
		for (int i = 0; i < indepobjects.size(); i++) {
			try {
				if (false) {

				} else {
					AffineTransform rt = AffineTransform.getRotateInstance(
							Math.toRadians(indepobjects.get(i).rot),
							indepobjects.get(i).rotX, indepobjects.get(i).rotY);
					AffineTransform tr = AffineTransform.getTranslateInstance(
							indepobjects.get(i).x, indepobjects.get(i).y);
					tr.concatenate(rt);
					g2d.drawImage(
							ImageContainer.images.get(indepobjects.get(i).img),
							tr, this);
				}
			} catch (Exception e) {

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

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (CurGame.c.status == 0) {
			if (arg0.getKeyCode() == 87) {
				CurGame.c.terra.controlledEnt.movDir = 1;
			}
			if (arg0.getKeyCode() == 83) {
				CurGame.c.terra.controlledEnt.movDir = -1;
			}
			if (arg0.getKeyCode() == 68) {
				CurGame.c.terra.controlledEnt.turn = 1;
			}
			if (arg0.getKeyCode() == 65) {
				CurGame.c.terra.controlledEnt.turn = -1;
			}
		} else if (CurGame.c.status > 0 && CurGame.c.status < 5
				&& !arg0.isShiftDown()) {
			if (arg0.getKeyCode() == 27) {
				try {
					SaveGameHandler.savegame();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (arg0.getKeyCode() == 192) {
				try {
					SaveGameHandler.loadGame();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (arg0.getKeyCode() == 87) {
				CurGame.c.scrollingY = -1;
			}
			if (arg0.getKeyCode() == 83) {
				CurGame.c.scrollingY = 1;
			}
			if (arg0.getKeyCode() == 68) {
				CurGame.c.scrollingX = 1;
			}
			if (arg0.getKeyCode() == 65) {
				CurGame.c.scrollingX = -1;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (CurGame.c.status == 0) {
			if (arg0.getKeyCode() == 87
					&& CurGame.c.terra.controlledEnt.movDir == 1) {
				CurGame.c.terra.controlledEnt.movDir = 0;
			}
			if (arg0.getKeyCode() == 83
					&& CurGame.c.terra.controlledEnt.movDir == -1) {
				CurGame.c.terra.controlledEnt.movDir = 0;
			}
			if (arg0.getKeyCode() == 68
					&& CurGame.c.terra.controlledEnt.turn == 1) {
				CurGame.c.terra.controlledEnt.turn = 0;
			}
			if (arg0.getKeyCode() == 65
					&& CurGame.c.terra.controlledEnt.turn == -1) {
				CurGame.c.terra.controlledEnt.turn = 0;
			}
		} else if (CurGame.c.status > 0 && CurGame.c.status < 5) {
			if (arg0.getKeyCode() == 87 && CurGame.c.scrollingY == -1) {
				CurGame.c.scrollingY = 0;
			}
			if (arg0.getKeyCode() == 83 && CurGame.c.scrollingY == 1) {
				CurGame.c.scrollingY = 0;
			}
			if (arg0.getKeyCode() == 68 && CurGame.c.scrollingX == 1) {
				CurGame.c.scrollingX = 0;
			}
			if (arg0.getKeyCode() == 65 && CurGame.c.scrollingX == -1) {
				CurGame.c.scrollingX = 0;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		if (CurGame.c.status == 21) {
			if (arg0.getKeyChar() == 'g') {
				BlockBuildTool bb = new BlockBuildTool(new Wall(),
						CurGame.c.controllingTeam, 10, "wall_unbuilt", 1);
				bb.setPos(CurGame.c.terra.preview.getPos());
				CurGame.c.terra.regEntity(bb);
				CurGame.c.status = 2;
				CurGame.c.terra.preview.tool = bb;
				SoundHandler.playSound("sentry/seek_1");
			}
			if (arg0.getKeyChar() == 'r') {
				BlockBuildTool bb = new BlockBuildTool(new Rail(),
						CurGame.c.controllingTeam, 10, "railcross", 2);
				bb.setPos(CurGame.c.terra.preview.getPos());
				CurGame.c.terra.regEntity(bb);
				CurGame.c.status = 2;
				CurGame.c.terra.preview.tool = bb;
				SoundHandler.playSound("sentry/seek_1");
			}
			if (arg0.getKeyChar() == 'x') {
				BlockBuildTool bb = new BlockBuildTool(new Ground(),
						CurGame.c.controllingTeam, 5, "delete", 0);
				bb.setPos(CurGame.c.terra.preview.getPos());
				CurGame.c.terra.regEntity(bb);
				CurGame.c.status = 2;
				CurGame.c.terra.preview.tool = bb;
				SoundHandler.playSound("sentry/seek_1");
			}
		}
		if (CurGame.c.status == 22) {
			if (arg0.getKeyChar() == 'g') {
				ConstructionTool bb = new ConstructionTool(new Sentry(
						CurGame.c.controllingTeam));
				bb.setPos(CurGame.c.terra.preview.getPos());
				CurGame.c.terra.regEntity(bb);
				CurGame.c.status = 2;
				CurGame.c.terra.preview.tool = bb;
				SoundHandler.playSound("sentry/seek_1");
			}
			if (arg0.getKeyChar() == 'f') {
				ConstructionTool bb = new ConstructionTool(new Factory(
						CurGame.c.controllingTeam));
				bb.setPos(CurGame.c.terra.preview.getPos());
				CurGame.c.terra.regEntity(bb);
				CurGame.c.status = 2;
				CurGame.c.terra.preview.tool = bb;
				SoundHandler.playSound("sentry/seek_1");
			}
			if (arg0.getKeyChar() == 'r') {
				ConstructionTool bb = new ConstructionTool(new RailMount(
						CurGame.c.controllingTeam));
				bb.setPos(CurGame.c.terra.preview.getPos());
				CurGame.c.terra.regEntity(bb);
				CurGame.c.status = 2;
				CurGame.c.terra.preview.tool = bb;
				SoundHandler.playSound("sentry/seek_1");
			}
		}
		if (CurGame.c.status == 2) {
			if (arg0.getKeyChar() == 'W') {
				CurGame.c.terra.preview.model.y -= 16;
				CurGame.c.terra.preview.updateEnt();
				SoundHandler.playSound("ui/click1");
			}
			if (arg0.getKeyChar() == 'S') {
				CurGame.c.terra.preview.model.y += 16;
				CurGame.c.terra.preview.updateEnt();
				SoundHandler.playSound("ui/click1");
			}
			if (arg0.getKeyChar() == 'A') {
				CurGame.c.terra.preview.model.x -= 16;
				CurGame.c.terra.preview.updateEnt();
				SoundHandler.playSound("ui/click1");
			}
			if (arg0.getKeyChar() == 'D') {
				CurGame.c.terra.preview.model.x += 16;
				CurGame.c.terra.preview.updateEnt();
				SoundHandler.playSound("ui/click1");
			}
			if (arg0.getKeyChar() == 'e') {
				CurGame.c.terra.preview.entSwitch++;
				CurGame.c.terra.preview.updateEnt();
				SoundHandler.playSound("ui/click1");
			}
			if (arg0.getKeyChar() == 'q') {
				CurGame.c.terra.preview.entSwitch--;
				CurGame.c.terra.preview.updateEnt();
				SoundHandler.playSound("ui/click1");
			}
			if (arg0.getKeyChar() == '\\') {
				if (CurGame.c.terra.preview.tool != null) {
					CurGame.c.terra.preview.tool.use2();
					SoundHandler.playSound("sentry/seek_1");
				}
			}
			// char russianChars[] = {'ö','ô','û','â'};
		}
		if (arg0.getKeyChar() == '\n') {
			if (CurGame.c.status == 1) {
				SoundHandler.playSound("sentry/seek_1");
				CurGame.c.status = 2;
			}
			if (CurGame.c.status == 2) {

				if (CurGame.c.terra.preview.tool == null) {
					if (CurGame.c.terra.preview.selent == null) {
						{
							try {
								SoundHandler.playSound("sentry/seek_1");
								Thread.sleep(100);
								SoundHandler.playSound("sentry/seek_1");
								Thread.sleep(100);
								SoundHandler.playSound("sentry/seek_1");
							} catch (InterruptedException e) {

							}
						}
					} else {
						OrderTool bb = new OrderTool(
								CurGame.c.terra.preview.selent);
						bb.setPos(CurGame.c.terra.preview.getPos());
						CurGame.c.terra.regEntity(bb);
						CurGame.c.status = 2;
						CurGame.c.terra.preview.tool = bb;
						SoundHandler.playSound("sentry/seek_1");
					}
				} else {

					if (CurGame.c.terra.preview.tool.use()) {
						SoundHandler.playSound("sentry/seek_1");
					}
				}
			}
		}
		if (arg0.getKeyChar() == 'q') {
			if (CurGame.c.status == 0 && CurGame.c.terra.controlledEnt != null) {
				CurGame.c.terra.controlledEnt.fire1();
			}
		}
		if (arg0.getKeyChar() == 'b') {
			if (CurGame.c.status == 2) {
				SoundHandler.playSound("sentry/seek_1");
				if (CurGame.c.terra.preview.tool == null) {
					CurGame.c.terra.preview.model.img = TeamData.getTeamImage(
							"cursor2", CurGame.c.terra.owner);
					CurGame.c.status = 21;
				} else {
					CurGame.c.terra.preview.tool.remove();
					CurGame.c.terra.preview.tool = null;
					CurGame.c.terra.preview.model.img = TeamData.getTeamImage(
							"cursor", CurGame.c.terra.owner);
				}
			}
		}
		if (CurGame.c.status == 2) {
			if (arg0.getKeyChar() == 'p') {
				WorldTicker.tickWorld();
			}
		}
		if (arg0.getKeyChar() == 'x') {
			if (CurGame.c.status == 2 && CurGame.c.terra.preview.selent != null) {
				CurGame.c.terra.preview.selent.onDeath();
				CurGame.c.terra.preview.selent.remove();
			}
		}
		if (arg0.getKeyChar() == 'c') {
			if (CurGame.c.status == 2) {
				SoundHandler.playSound("sentry/seek_1");
				if (CurGame.c.terra.preview.tool == null) {
					CurGame.c.terra.preview.model.img = TeamData.getTeamImage(
							"cursor2", CurGame.c.terra.owner);
					CurGame.c.status = 22;
				} else {
					CurGame.c.terra.preview.tool.remove();
					CurGame.c.terra.preview.tool = null;
					CurGame.c.terra.preview.model.img = TeamData.getTeamImage(
							"cursor", CurGame.c.terra.owner);
				}
			}
		}
		if (arg0.getKeyChar() == ' ') {

			if (CurGame.c.status == 0) {
				if (!arg0.isShiftDown()) {
					CurGame.c.gamego = false;
				} else {
					CurGame.c.status = 10;
					WorldTicker.randomflag = false;
				}
			}
			if (CurGame.c.status == 1) {
				CurGame.c.gamego = true;
				CurGame.c.status = 0;
			}
			if (CurGame.c.status == 2) {
				SoundHandler.playSound("sentry/seek_1");
				if (CurGame.c.terra.preview.tool != null)
					CurGame.c.terra.preview.tool.remove();
				CurGame.c.terra.preview.tool = null;
				CurGame.c.terra.preview.model.img = TeamData.getTeamImage(
						"cursor", CurGame.c.terra.owner);
				if (CurGame.c.terra.owner == 0) {
					CurGame.c.controllingTeam = 1;
				} else {
					CurGame.c.controllingTeam = 0;
				}
				CurGame.c.status = 1;
			}
		}
	}
}