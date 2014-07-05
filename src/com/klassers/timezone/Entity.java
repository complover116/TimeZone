package com.klassers.timezone;

public abstract class Entity {
	public DrawThing model = new DrawThing();
	public abstract void construct();
	public abstract void onTick();
	//8.5
	//11
	public Entity(){
		construct();
	}
}
