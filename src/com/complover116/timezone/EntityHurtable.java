package com.complover116.timezone;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.HashMap;

public abstract class EntityHurtable extends EntityObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6917109684915505608L;
	public AnimationSet anim;
	public int health;
	public int maxhealth = 100;
	public int mmaxhealth;
	public String readName;
	public int displayHealth = 0;
	public byte team;
	public static String animname;
	/*@Override
	public void saveStuff(HashMap hm){
		hm.put("health", health);
		hm.put("team", team);
		hm.put("curAnim", anim.curAnim);
		//saveStuff2(hm);
		return;
	}*/
	//public abstract void saveStuff2(HashMap hm);
	public void loadStuff(HashMap hm){
		health = (int) hm.get("health");
		team = (byte) hm.get("team");
		anim.curAnim = (int) hm.get("curAnim");
	//	loadStuff2(hm);
		return;
	}
	//public abstract void loadStuff2(HashMap hm);
	public void takeDamage(Entity attacker, int damage) {
		this.health = this.health - damage;
		displayHealth = 400;
		if(this.health < 0){
			this.onDeath();
			this.remove();
		}
	}
	public void renderInfo2() {
		
	}
	public void instantiate(byte team) {
		System.out.print("Instantiation skipped");
	}
	public abstract void onDeath();
	@Override
	public void renderStuff(){
		if(displayHealth > 0){
		displayHealth --;
		Rectangle rec1 = new Rectangle((int)this.model.x, (int)this.model.y - 5, (int)this.model.rotX*2, 3);
		if(displayHealth > 255){
		MainScreen.shapes.add(new ShapeModel(rec1, new Color(150,255,150,255), true));
		}else {
			MainScreen.shapes.add(new ShapeModel(rec1, new Color(150,255,150,displayHealth),true));
		}
		Rectangle rec = new Rectangle((int)this.model.x, (int)this.model.y - 5, (int)(this.model.rotX*2*((double)health/(double)maxhealth)), 3);
		ShapeModel sh;
		if(displayHealth > 255){
			sh = new ShapeModel(rec, new Color((int) (255-((double)health/(double)maxhealth)*255),(int) (((double)health/(double)maxhealth)*255),0),true);
		} else {
			sh = new ShapeModel(rec, new Color((int) (255-((double)health/(double)maxhealth)*255),(int) (((double)health/(double)maxhealth)*255),0,displayHealth),true);
		}
		MainScreen.shapes.add(sh);
		}
	}
}
