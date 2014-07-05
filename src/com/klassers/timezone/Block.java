package com.klassers.timezone;


public abstract class Block {
	public DrawThing drawthing = new DrawThing();
	public Block() {
		construct();
	}
	public abstract void onTick();
	public abstract void construct();
}
