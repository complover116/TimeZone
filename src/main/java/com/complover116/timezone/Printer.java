package com.complover116.timezone;

import java.util.HashMap;

public class Printer {
	public static HashMap<String, Boolean> errors = new HashMap<String, Boolean>();
	public static void errorOnce(String error) {
		if(errors.get(error) == null) {
			errors.put(error, true);
			System.err.println(error);
		}
		
	}
}
