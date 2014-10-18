package com.complover116.timezone;

import java.io.Serializable;

public class CurGame implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4802906741755687477L;
	public static CurGame c = new CurGame();
	public static int overstat = -5;
	public static String loadStep = "Initializing";
	public double attackTime = 180;
	public int curmenu = 0;
	public TeamData teams[] = new TeamData[2];
	public int status = -1;
	public int teamtoload = 0;
	public byte controllingTeam = 0;
	public int TPS = 0;
	public int tool = 0;
	public int timespeed = 0;
	public boolean gamego = true;
	public Territory terra;
	public double scrollX = 0;
	public double scrollY = 0;
	public double scrollingX = 0;
	public double scrollingY = 0;
}
