package com.complover116.timezone;

public class Animation {
	public String name;
	public int length;
	public int frametime;
	public int next;
	public Animation(String nam, int len, int frtm, int nxt) {
		name = nam;
		length = len;
		frametime = frtm;
		next = nxt;
	}
}
