package com.complover116.timezone;

import javax.swing.RepaintManager;

public class NoRepaint implements Runnable {

	@Override
	public void run() {
		while(true){
			RepaintManager rmg = RepaintManager.currentManager(GUI.ms);
			rmg.markCompletelyClean(GUI.ms);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
