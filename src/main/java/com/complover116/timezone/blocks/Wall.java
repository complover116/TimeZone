package com.complover116.timezone.blocks;
import com.complover116.timezone.*;

public class Wall extends Block {

	@Override
	public void onTick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void construct() {
			this.drawthing.img = "wall";	
			solid = true;
	}
	
}
