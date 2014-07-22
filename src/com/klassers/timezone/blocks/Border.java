package com.klassers.timezone.blocks;
import com.klassers.timezone.*;

public class Border extends Block {

	@Override
	public void onTick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void construct() {
			this.drawthing.img = ImageContainer.images.get("border");	
			solid = true;
	}
	
}
