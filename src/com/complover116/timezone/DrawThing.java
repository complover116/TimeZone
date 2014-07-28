package com.complover116.timezone;

import java.io.Serializable;

public class DrawThing implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3901263258568087086L;
	public double x;
	public double y;
	public double rot;
	public double rotX;
	public double rotY;
	public boolean onTop;
	public boolean independent;
	public boolean draw = true;
	public String img;
	public double[] getPointPos(int X, int Y) {
		double newX = this.x - Math.cos(Math.toRadians(this.rot-90))*X;
		double newY = this.y - Math.sin(Math.toRadians(this.rot-90))*Y;
		return new double[]{newX,newY};
	}
	public DrawThing() {
		setModel("pingas");
	}
	public DrawThing(String model, double x, double y, boolean indep) {
		setModel(model);
		this.x = x;
		this.y = y;
		this.independent = indep;
	}
	public void setModel(String name) {
		img = name;
	}
}
