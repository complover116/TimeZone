package com.klassers.timezone;

import java.time.Duration;

public class StuffLoader {

	public static void main(String[] args) {
		System.out.println("=====Loading=====");
		ImageContainer.load();
		CurGame.terra = new Territory();
		CurGame.terra.fill();
		System.out.println("=====Displaying=====");
		GUI gui = new GUI();
		Thread uiThread = new Thread(gui, "UI Thread");
		uiThread.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("DELAY ERROR");
		}
		Render.render();
		CurGame.time = Duration.ofMinutes(5);
		WorldTicker.run();
	}

}
