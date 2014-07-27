package com.complover116.timezone;

import java.awt.Color;
import java.awt.Rectangle;


public abstract class EntityControllable extends EntityBuildable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6226031706958380660L;
	public double speedForward = 0;
	public byte movDir = 0;
	public double maxSpeed = 1;
	public double speedPlus = 0.05;
	public double speedRight = 0;
	public double maxSpeedRight = 1;
	public byte turn = 0;
	@Override
	public void Think() {
if(this.orders.size() > 0) {
			Order curorder = this.orders.get(0);
			switch(curorder.type) {
				default:
					
					double deltaX = this.orders.get(0).pos.x - this.getPos().x;
					double deltaY = this.orders.get(0).pos.y - this.getPos().y;
					double deg = Math.atan2(deltaY, deltaX);
					deg = Math.toDegrees(deg);
					deg += 90;
					if(this.model.rot > 270) {
						this.model.rot -= 360;
					}
					if(this.model.rot < -90) {
						this.model.rot += 360;
					}
					if (this.model.rot < deg + 3 && this.model.rot > deg - 3) {
								this.movDir = 1;
						}
						if (this.model.rot < deg) {
							if(deg - this.model.rot > 180) {
								this.model.rot -= maxSpeedRight;
							}else{
								this.model.rot += maxSpeedRight;
							}
						}
						if (this.model.rot > deg) {
							if(this.model.rot - deg > 180) {
								
								this.model.rot += maxSpeedRight;
							} else {
								this.model.rot -= maxSpeedRight;
							}
						}
					if(curorder.pos.distance(this.getPos())<10) {
						this.movDir = 0;
						this.orders.remove(0);
					}
				break;
			}
}
		processMovement();
		Think2();
	}
	
	public abstract void Think2();
	public abstract void fire1();
	public void processMovement() {
		if(movDir == 0) {
			speedForward = speedForward / 1.05;
		}else
		if(movDir > 0&&speedForward < maxSpeed) {
			speedForward += speedPlus*movDir;
		}
		if(movDir < 0&&-speedForward < maxSpeed) {
			speedForward += speedPlus*movDir;
		}
		if(turn == 1) {
			this.model.rot += turn*maxSpeedRight;
		}
		if(turn == -1) {
			this.model.rot += turn*maxSpeedRight;
		}
		/*for(int i = 0; i < CurGame.c.terra.entities.size(); i++) {
			if(EntityHurtable.class.isInstance(CurGame.c.terra.entities.get(i))) {
					if(CurGame.c.terra.entities.get(i)!=this){
						if(((EntityObject)CurGame.c.terra.entities.get(i)).checkCollision(this)){
							this.speedForward = 0;
							Pos pos2 = CurGame.c.terra.entities.get(i).getPos();
							Pos pos1 = pos2.sub(this.getPos());
							this.setPos(this.getPos().sub(pos1.normal()));
						}
					}
			}
		}*/
		boolean flag = false;
		for(int x = (int)(this.getPos().x/16) - 2; x < (int)(this.getPos().x)/16 + 3; x++) {
			for(int y = (int)(this.getPos().y/16) - 2; y < (int)(this.getPos().y)/16 + 3; y++) {
				if(x > -1&&y>-1&&x<100&&y<100){
				//MainScreen.shapes.add(new ShapeModel(new Rectangle(x*16,y*16,16,16), new Color(0,255,0)));
				if(CurGame.c.terra.terrain[x][y].solid) {
					//MainScreen.shapes.add(new ShapeModel(new Rectangle(x*16,y*16,16,16), new Color(0,0,255)));
					if(this.checkBlockCollision(x, y)) {
						MainScreen.shapes.add(new ShapeModel(new Rectangle(x*16,y*16,16,16), new Color(255,0,0), true));
						
						this.speedForward = -Math.signum(speedForward);
						flag = true;
						break;
						
						//this.setPos(this.getPos().sub(new Pos(x*16+8,y*16+8).sub(this.getPos()).normal()));
					}
				}
				}
			}
			if(flag) break;
		}
		double direction = this.model.rot;
		double newX = this.getPos().x + Math.cos(Math.toRadians(direction-90))*speedForward;
		double newY = this.getPos().y + Math.sin(Math.toRadians(direction-90))*speedForward;
		this.setPos(new Pos(newX, newY));
		
	}
}
