package com.complover116.timezone;

import com.complover116.timezone.blocks.Rail;

/**
 * Class representing a 2D Vector
 * @author complover116
 *
 */
public class Pos {
	public double x;
	public double y;
	
	public Pos() {
		
	}
	public Pos(double X, double Y) {
		x = X;
		y = Y;
	}
	public Pos(double X, double Y, boolean f) {
		x = X*16+8;
		y = Y*16+8;
	}
	public double distance(Pos pos2) {
		double deltaX = pos2.x - this.x;
		double deltaY = pos2.y - this.y;
		double res = Math.sqrt(deltaX*deltaX+deltaY*deltaY);
		return res;
	}
	public Pos sub(Pos pos2) {
		return new Pos(this.x - pos2.x,this.y - pos2.y);
	}
	public Pos add(Pos pos2) {
		return new Pos(this.x + pos2.x,this.y + pos2.y);
	}
	public Pos add2(Pos pos2) {
		return new Pos(this.x + pos2.y,this.y + pos2.x);
	}
	public Pos mul(double i ) {
		return new Pos(this.x*i,this.y*i);
	}
	public Pos normal() {
		double newX = Math.cos(Math.atan2(this.x, this.y));
		double newY = Math.sin(Math.atan2(this.x, this.y));
		return new Pos(newX, newY);
	}
	public boolean LOS(Pos pos2) {
		for(int i = 0; i < distance(pos2); i++) {
			int posX = (int)(this.add2(pos2.sub(this).normal().mul(i)).x/16);
			int posY = (int)(this.add2(pos2.sub(this).normal().mul(i)).y/16);
			if(CurGame.c.terra.terrain[posX][posY].solid) {
				//MainScreen.shapes.add(new ShapeModel(new Rectangle(posX*16,posY*16,16,16),new Color(255,0,0),false));
				return false;
			}else {
				//MainScreen.shapes.add(new ShapeModel(new Rectangle(posX*16,posY*16,16,16),new Color(0,255,0),false));
			}
		}
		return true;
	}
	public boolean SentryLOM(Pos pos2) {
		for(int i = 0; i < distance(pos2); i++) {
			int posX = (int)(this.add2(pos2.sub(this).normal().mul(i)).x/16);
			int posY = (int)(this.add2(pos2.sub(this).normal().mul(i)).y/16);
			if(!(CurGame.c.terra.terrain[posX][posY] instanceof Rail)) {
				//MainScreen.shapes.add(new ShapeModel(new Rectangle(posX*16,posY*16,16,16),new Color(255,0,0),false));
				return false;
			}else {
				//MainScreen.shapes.add(new ShapeModel(new Rectangle(posX*16,posY*16,16,16),new Color(0,255,0),false));
			}
		}
		return true;
	}
}
