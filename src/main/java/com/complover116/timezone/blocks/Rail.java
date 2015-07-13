package com.complover116.timezone.blocks;
import com.complover116.timezone.*;

public class Rail extends Block {

	@Override
	public void onTick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void construct() {
		this.drawthing.img = "railcross";	
	}
	public void update() {
	}
	public boolean isBlockRail(int x, int y) {
		if(CurGame.c.terra.terrain[x][y] instanceof Rail){
			return true;
		}
		return false;
	}
}
