package com.klassers.timezone;


public abstract class Block {
	public DrawThing drawthing = new DrawThing();
	public Block() {
		drawthing.rotX = 8.5;
		drawthing.rotY = 8.5;
		construct();
	}
	public abstract void onTick();
	public abstract void construct();
}
