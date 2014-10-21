package com.complover116.timezone;

public enum Block {
	GROUND,
	BORDER,
	WALL,
	RAIL,
	TELEPORT_ZONE;

	public boolean isSolid() {
		if(this == WALL) return true;
		if(this == BORDER) return true;
		return false;
	}

	public String getImage() {
		if(this == GROUND) return "ground_1";
		if(this == BORDER) return "border";
		if(this == WALL) return "wall";
		if(this == RAIL) return "railcross";
		if(this == TELEPORT_ZONE) return "teleportzone";
		return null;
	}
}