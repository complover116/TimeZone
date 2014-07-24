package com.complover116.timezone;


public class StuffLoader {

	public static void main(String[] args) {
		System.out.println("=====Loading resources=====");
		ImageContainer.load();
		System.out.println("=====Preparing teams and their zones=====");
		for(int i = 0; i<CurGame.teams.length; i++) {
			CurGame.teams[i] = new TeamData();
			
		}
		CurGame.teams[0].dataname = "blue";
		CurGame.teams[1].dataname = "red";
		CurGame.teams[0].zone = new Territory(0);
		CurGame.teams[1].zone = new Territory(1);
		CurGame.terra=CurGame.teams[1].zone;
		System.out.println("=====Displaying=====");
		CurGame.status = -10;
		GUI gui = new GUI();
		Thread uiThread = new Thread(gui, "UI Thread");
		uiThread.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("DELAY ERROR");
		}
		Render.render();
		WorldTicker.run();
	}

}
