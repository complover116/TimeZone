package com.complover116.timezone.entities;
import com.complover116.timezone.*;
public class Bullet extends EntityObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7999239675034748667L;
	int time;
	double direction;
	double speed = 16;
	byte team;
	Entity attacker;
	@Override
	public void construct() {
		
		this.model.rotX = 3.5;
		this.model.rotY = 3.5;
		this.collideX2 = 7;
		this.collideY2 = 7;
		direction = 0;
		this.model.rot = direction;
		SoundHandler.playSound("weapon/bullet/fire_1");
	}
	public void renderInfo2() {
		
	}
	public Bullet() {
		
	}
	public Bullet(byte team) {
		this.team = team;
		this.model.img = TeamData.getTeamImage("sentry_1_bullet", team);
	}
	@Override
	public void onTick() {
		double newX = this.model.x + Math.cos(Math.toRadians(direction-90))*speed;
		double newY = this.model.y + Math.sin(Math.toRadians(direction-90))*speed;
		this.model.x = newX;
		this.model.y = newY;
		if(this.model.x > 1600||this.model.y > 1600||this.model.x < 0||this.model.y < 0) {
			this.remove();
		}
		boolean flag = false;
		for(int x = (int)(this.getPos().x/16) - 2; x < (int)(this.getPos().x)/16 + 3; x++) {
			for(int y = (int)(this.getPos().y/16) - 2; y < (int)(this.getPos().y)/16 + 3; y++) {
				if(x > -1&&y>-1&&x<100&&y<100){
				//MainScreen.shapes.add(new ShapeModel(new Rectangle(x*16,y*16,16,16), new Color(0,255,0)));
				if(CurGame.c.terra.terrain[x][y].solid) {
					//MainScreen.shapes.add(new ShapeModel(new Rectangle(x*16,y*16,16,16), new Color(0,0,255)));
					if(this.checkBlockCollision(x, y)) {
						this.remove();
						SmallExplosion1 bul = new SmallExplosion1();
						bul.setPos(this.getPos());
						CurGame.c.terra.regEntity(bul);
						flag = true;
						break;
					}
				}
				}
			}
			if(flag) break;
		}
		for(int i = 0; i < CurGame.c.terra.entities.size(); i++) {
			if(EntityHurtable.class.isInstance(CurGame.c.terra.entities.get(i))) {
				if(CurGame.c.terra.entities.get(i)!=attacker){
					if(CurGame.c.terra.entities.get(i)!=this){
						if(((EntityHurtable)CurGame.c.terra.entities.get(i)).checkCollision(this)&&((EntityHurtable)CurGame.c.terra.entities.get(i)).team != this.team){
							this.remove();
							((EntityHurtable) CurGame.c.terra.entities.get(i)).takeDamage(attacker, 1);
							SmallExplosion1 bul = new SmallExplosion1();
							bul.setPos(this.getPos());
							CurGame.c.terra.regEntity(bul);
						}
					}
				}
			}
		}
	}
	
}
