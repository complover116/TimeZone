package com.klassers.timezone.blocks;
import com.klassers.timezone.*;

public class Ground extends Block {

	@Override
	public void onTick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void construct() {
		double random = Math.random();
		if(random < 0.33) {
			this.drawthing.img = ImageContainer.images.get("ground_1");
		} else if(random < 0.66) {
			this.drawthing.img = ImageContainer.images.get("ground_2");
		} else {
			this.drawthing.img = ImageContainer.images.get("ground_3");
		}
		
	}
	
}
