package com.complover116.timezone;

import java.util.ArrayList;

public class AnimationSet {
	public String setname;
	public byte team;
	public DrawThing model;
	public int curAnim = 0;
	public int curFrame = 1;
	public int curFrameTime = 0;
	public ArrayList<Animation> animations = new ArrayList<Animation>();
	
	public AnimationSet() {
		
	}
	public AnimationSet(String name, int teamd) {
		setname = name;
		team = (byte) teamd;
	}
	public void animTick() {
		this.curFrameTime++;
		if(curFrameTime > animations.get(curAnim).frametime){
			this.curFrame++;
			this.curFrameTime= 0;
			if(curFrame > animations.get(curAnim).length){
				if(animations.get(curAnim).next < 0) {
					curFrame = 1;
				} else  {
					curAnim = animations.get(curAnim).next;
					curFrame = 1;
				}
			}
		}
	}
	public void setAnim(int anim) {
		this.curAnim = anim;
		this.curFrame = 1;
		this.curFrameTime = 0;
	}
	public String getFrame() {
		if(this.animations.get(this.curAnim).length > 1){
		return this.setname+"_"+this.animations.get(this.curAnim).name+this.curFrame+"_"+CurGame.c.teams[this.team].dataname;
		} else {
			return this.setname+"_"+this.animations.get(this.curAnim).name+"_"+CurGame.c.teams[this.team].dataname;
		}
	}
	public String getFrame(int anim, int frame) {
		if(this.animations.get(anim).length > 1){
			return this.setname+"_"+this.animations.get(anim).name+frame+"_"+CurGame.c.teams[this.team].dataname;
			} else {
				return this.setname+"_"+this.animations.get(anim).name+"_"+CurGame.c.teams[this.team].dataname;
			}
	}
	public String getFrame(String anim, int frame) {
		if(frame>0){
			return this.setname+"_"+anim+frame+"_"+CurGame.c.teams[this.team].dataname;
			} else {
				return this.setname+"_"+anim+"_"+CurGame.c.teams[this.team].dataname;
			}
	}
}
