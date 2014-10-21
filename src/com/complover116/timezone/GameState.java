package com.complover116.timezone;

public enum GameState {
	TIME_GOES,
	ATTACKERS_CONTROL,
	DEFENDERS_CONTROL,
	BUILD_MODE,
	CONSTRUCT_MODE,
	FAST_FORWARD,
	LOADING_SCREEN,
	TERRITORY_LOADING,
	TERRITORY_LOAD_DELAY,
	ERROR;
	
	public String getDescription() {
		if(this == TIME_GOES) return "Time is running";
		if(this == ATTACKERS_CONTROL) return "Attacking team gives orders";
		if(this == DEFENDERS_CONTROL) return "Defending team gives orders";
		if(this == BUILD_MODE) return "Defending team builds blocks";
		if(this == CONSTRUCT_MODE) return "Defending team constructs buildings";
		if(this == FAST_FORWARD) return "Fast-forwarding...";
		return "ERROR: NO DESCRIPTION FOUND";
	}
}
