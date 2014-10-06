package com.complover116.timezone;

import java.io.Serializable;


public abstract class Entity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4756283948833607945L;
	public DrawThing model = new DrawThing();
	public abstract void construct();
	public abstract void onTick();
	//public abstract void saveStuff(HashMap hm);
	//public abstract void loadStuff(HashMap hm);
	public void renderStuff() {
		
	}
	public boolean isDead = false;
	public boolean drawInfo = false;
	//8.5
	//11
	public Entity(){
		construct();
	}
	public void remove() {
		this.isDead = true;
		this.model.draw = false;
		this.model.setModel("nil");
		Render.render();
	}
	public void renderInfo() {
		renderInfo2();
	}
	public abstract void renderInfo2();
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
