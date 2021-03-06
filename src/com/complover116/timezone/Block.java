package com.complover116.timezone;


public abstract class Block {
	public boolean solid;
	public boolean shootthrough;
	public DrawThing drawthing = new DrawThing();
	public Block() {
		drawthing.rotX = 8.5;
		drawthing.rotY = 8.5;
		solid = false;
		construct();
	}
	public abstract void onTick();
	public abstract void construct();
}
