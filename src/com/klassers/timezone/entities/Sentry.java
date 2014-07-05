package com.klassers.timezone.entities;
import com.klassers.timezone.*;
public class Sentry extends Entity{
	boolean turningright;
	@Override
	public void construct() {
		this.model.img = ImageContainer.images.get("sentry_1");
		this.model.rotX = 8.5;
		this.model.rotY = 11;
	}

	@Override
	public void onTick() {
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
	}
	
}
