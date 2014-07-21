package com.klassers.timezone;

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
		double direction = this.model.rot;
		double newX = this.getPos().x + Math.cos(Math.toRadians(direction-90))*speedForward;
		double newY = this.getPos().y + Math.sin(Math.toRadians(direction-90))*speedForward;
		this.setPos(new Pos(newX, newY));
		
	}
}
