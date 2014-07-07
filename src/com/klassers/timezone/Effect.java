package com.klassers.timezone;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Effect extends Entity {
	public ArrayList<BufferedImage> frames;
	int framenum;
	int curframe = -1;
	public int frametime;
	int time = 0;
	@Override
	public void construct() {
		this.model.rotX = 8.5;
		this.model.rotY = 8.5;
		this.frametime = 1;
		frames = new ArrayList<BufferedImage>();
		framenum = loadFrames();
		this.model.img = frames.get(0);
	}
	public abstract int loadFrames();
	public void renderInfo2() {
		
	}
	@Override
	public void onTick() {
		time++;
		if(time == frametime) {
		curframe++;
		if(curframe == framenum) {
			this.remove(); 
		} else {
		this.model.img = frames.get(curframe);
		}
		time = 0;
		}
	}
}
