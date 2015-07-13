package com.complover116.timezone;

public class Metrics {
	public static String timeFromSeconds(int seconds) {
		String res = "";
		if(seconds/360 > 0){
			res = res + seconds/360+":";
			seconds = seconds%360;
		}
		if(seconds/60 > 0){
			res = res + seconds/60+":";
		}
		res = res + seconds%60;
		return res;
	}
}
