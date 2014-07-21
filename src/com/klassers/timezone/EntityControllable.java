package com.klassers.timezone;

import java.awt.Color;
import java.awt.Rectangle;


public abstract class EntityControllable extends EntityHurtable {
	public double speedForward = 0;
	public byte movDir = 0;
	public double maxSpeed = 2;
	public double speedPlus = 0.05;
	public double speedRight = 0;
	public double maxSpeedRight = 1;
	public byte turn = 0;
	@Override
	public void onTick() {
		processMovement();
		think();
	}
	
	public abstract void think();
	
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
		for(int i = 0; i < CurGame.terra.entities.size(); i++) {
			if(EntityHurtable.class.isInstance(CurGame.terra.entities.get(i))) {
					if(CurGame.terra.entities.get(i)!=this){
						if(((EntityObject)CurGame.terra.entities.get(i)).checkCollision(this)){
							this.speedForward = 0;
							this.setPos(this.getPos().sub(CurGame.terra.entities.get(i).getPos().sub(this.getPos()).normal()));
						}
					}
			}
		}
		for(int x = (int)(this.getPos().x/16) - 2; x < (int)(this.getPos().x)/16 + 3; x++) {
			for(int y = (int)(this.getPos().y/16) - 2; y < (int)(this.getPos().y)/16 + 3; y++) {
				if(x > -1&&y>-1&&x<100&&y<100){
				//MainScreen.shapes.add(new ShapeModel(new Rectangle(x*16,y*16,16,16), new Color(0,255,0)));
				if(CurGame.terra.terrain[x][y].solid) {
					//MainScreen.shapes.add(new ShapeModel(new Rectangle(x*16,y*16,16,16), new Color(0,0,255)));
					if(this.checkBlockCollision(x, y)) {
						MainScreen.shapes.add(new ShapeModel(new Rectangle(x*16,y*16,16,16), new Color(255,0,0)));
						this.speedForward = 0;
						this.setPos(this.getPos().sub(new Pos(x*16-8,y*16-8).sub(this.getPos()).normal()));
					}
				}
				}
			}
		}
		double direction = this.model.rot;
		double newX = this.getPos().x + Math.cos(Math.toRadians(direction-90))*speedForward;
		double newY = this.getPos().y + Math.sin(Math.toRadians(direction-90))*speedForward;
		this.setPos(new Pos(newX, newY));
		
	}
}
