package com.klassers.timezone;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Effect extends Entity {
	public ArrayList<BufferedImage> frames;
	int framenum;
	int curframe = -1;
	int frametime = 1;
	@Override
	public void construct() {
		frames = new ArrayList<BufferedImage>();
		framenum = loadFrames();
		this.model.img = frames.get(0);
	}
	public abstract int loadFrames();
	@Override
	public void onTick() {
		curframe++;
		if(curframe == framenum) {
			this.remove(); 
		} else {
		this.model.img = frames.get(curframe);
		}
	}
}
