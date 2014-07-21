package com.klassers.timezone;
/**
 * Class representing a 2D Vector
 * @author complover116
 *
 */
public class Pos {
	public double x;
	public double y;
	
	public Pos(double X, double Y) {
		x = X;
		y = Y;
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
	public Pos normal() {
		System.out.println("oX:"+this.x+" oY:"+this.y);
		double newX = Math.cos(Math.atan2(this.x, this.y));
		double newY = Math.sin(Math.atan2(this.x, this.y));
		System.out.println("X:"+newX+" Y:"+newY);
		return new Pos(newX, newY);
	}
}
