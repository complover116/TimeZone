package com.klassers.timezone.entities;
import com.klassers.timezone.*;
public class Sentry extends Entity{
	boolean turningright;
	int time;
	Entity target;
	@Override
	public void construct() {
		this.model.img = ImageContainer.images.get("sentry_1");
		this.model.rotX = 8.5;
		this.model.rotY = 11;
	}

	@Override
	public void onTick() {
		time ++;
		if(time > 100) {
			time = 0;
		}
		if(time == 100) {
			this.model.img = ImageContainer.images.get("sentry_1");
		}
		if(time == 50) {
			this.model.img = ImageContainer.images.get("sentry_1_light1");
			checkForTargets();
		}
		if (target == null){
		if(turningright) {
			this.model.rot ++;
			if(this.model.rot > 180) {
				this.turningright = false;
			}
		} else {
			this.model.rot --;
			if(this.model.rot < 0) {
				this.turningright = true;
			}
		}
	} else {
		if(false) {
			
			this.model.img = ImageContainer.images.get("sentry_1_light3");
		} else {
			this.model.img = ImageContainer.images.get("sentry_1_light2");
		}
	}
	}

	private void checkForTargets() {
		for(int i = 0; i < CurGame.terra.entities.size(); i ++) {
			if(CurGame.terra.entities.get(i).getClass() == TestTarget.class) {
				target = CurGame.terra.entities.get(i);
			}
		}
		
	}
	
}
