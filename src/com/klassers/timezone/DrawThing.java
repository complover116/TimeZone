package com.klassers.timezone;

import java.awt.image.BufferedImage;

public class DrawThing {
	public double x;
	public double y;
	public double rot;
	public double rotX;
	public double rotY;
	public BufferedImage img;
	public double[] getPointPos(int X, int Y) {
		double newX = this.x - Math.cos(Math.toRadians(this.rot-90))*X;
		double newY = this.y - Math.sin(Math.toRadians(this.rot-90))*Y;
		return new double[]{newX,newY};
	}
}
