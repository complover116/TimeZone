package com.complover116.timezone.entities;
import com.complover116.timezone.*;
public class Bullet extends EntityObject{
	int time;
	double direction;
	double speed = 8;
	Entity attacker;
	@Override
	public void construct() {
		this.model.img = ImageContainer.images.get("sentry_1_bullet");
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
				if(CurGame.terra.terrain[x][y].solid) {
					//MainScreen.shapes.add(new ShapeModel(new Rectangle(x*16,y*16,16,16), new Color(0,0,255)));
					if(this.checkBlockCollision(x, y)) {
						this.remove();
						SmallExplosion1 bul = new SmallExplosion1();
						bul.setPos(this.getPos());
						CurGame.terra.regEntity(bul);
						flag = true;
						break;
					}
				}
				}
			}
			if(flag) break;
		}
		for(int i = 0; i < CurGame.terra.entities.size(); i++) {
			if(EntityHurtable.class.isInstance(CurGame.terra.entities.get(i))) {
				if(CurGame.terra.entities.get(i)!=attacker){
					if(CurGame.terra.entities.get(i)!=this){
						if(((EntityObject)CurGame.terra.entities.get(i)).checkCollision(this)){
							this.remove();
							((EntityHurtable) CurGame.terra.entities.get(i)).takeDamage(attacker, 1);
							SmallExplosion1 bul = new SmallExplosion1();
							bul.setPos(this.getPos());
							CurGame.terra.regEntity(bul);
						}
					}
				}
			}
		}
	}
	
}
