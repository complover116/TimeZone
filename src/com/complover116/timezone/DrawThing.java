package com.complover116.timezone;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class DrawThing {
	public double x;
	public double y;
	public double rot;
	public double rotX;
	public double rotY;
	public boolean draw = true;
	public BufferedImage img;
	public double[] getPointPos(int X, int Y) {
		double newX = this.x - Math.cos(Math.toRadians(this.rot-90))*X;
		double newY = this.y - Math.sin(Math.toRadians(this.rot-90))*Y;
		return new double[]{newX,newY};
	}
	public DrawThing() {
		setModel("pingas");
	}
	public void desolidify(){
		BufferedImage img2 = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
		WritableRaster r = img2.getRaster();
		img.copyData(r);
		WritableRaster ra = img2.getAlphaRaster();
		for(int i = 0; i <ra.getWidth(); i++)
			for(int j = 0; j <ra.getHeight(); j++){
				double pixel[] = r.getPixel(i, j, new double[]{0,0,0,0});
				r.setPixel(i, j, pixel);
			}
		img = img2;
	}
	public void setModel(String name) {
		img = ImageContainer.images.get(name);
		if(img == null) {
			img = ImageContainer.images.get("notexture");
		}
	}
}
