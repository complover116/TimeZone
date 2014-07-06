package com.klassers.timezone;


public abstract class Entity {
	public DrawThing model = new DrawThing();
	public abstract void construct();
	public abstract void onTick();
	public boolean isDead = false;
	//8.5
	//11
	public Entity(){
		construct();
	}
	public void remove() {
		this.isDead = true;
	}
	public Pos getPos() {
		Pos pos = new Pos(this.model.x+this.model.rotX,this.model.y+this.model.rotY);
		//Ellipse2D.Double el = new Ellipse2D.Double(pos.x - 4,pos.y - 4, 8, 8);
		//MainScreen.shapes.add(el);
		return pos;
	}
	public void setPos(Pos newPos) {
		this.model.x = newPos.x - this.model.rotX;
		this.model.y = newPos.y - this.model.rotY;
	}
}
