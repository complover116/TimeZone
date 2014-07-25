package com.complover116.timezone.entities;

import com.complover116.timezone.Entity;

public class EpicBoom extends Entity {
	int timepassed = 0;
	int height;
	int width;
	int time;
	int speed;
	@Override
	public void construct() {
		
	}
	public EpicBoom(int height, int width, int time, int speed) {
		this.height = height;
		this.width = width;
		this.time = time;
		this.speed = speed;
	}

	@Override
	public void onTick() {
		timepassed ++;
		if(timepassed%speed == 0) {
			for(int i = 0; i < height*width/10; i ++) {
				
			}
		}
	}

	@Override
	public void renderInfo2() {
		// TODO Auto-generated method stub
		
	}

}
