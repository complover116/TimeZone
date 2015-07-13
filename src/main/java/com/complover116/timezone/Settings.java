package com.complover116.timezone;

import java.io.File;

public class Settings {
	public static String GameFolder = "%HOMEPATH%/TimeZone";
	public static void makeDirs() {
		new File(GameFolder).mkdir();
		new File(GameFolder+"/save").mkdir();
		new File(GameFolder+"/save/current").mkdir();
	}
}
