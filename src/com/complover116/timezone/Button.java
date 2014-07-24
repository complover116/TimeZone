package com.complover116.timezone;

import java.awt.Rectangle;

public abstract class Button {
	DrawThing model = new DrawThing();
	public Rectangle collision;
	public abstract void onClick();
	public void setPos(int x, int y) {
		model.x = x;
		model.y = y;
		int width = model.img.getWidth();
		int height = model.img.getHeight();
		collision = new Rectangle((int)model.x,(int)model.y,width,height);
	}
}
