package com.klassers.timezone;

import javax.swing.JFrame;


public class GUI implements Runnable {
	public static MainScreen ms;
	public static double rot = 45;
	public static void redraw() {
		ms.repaint();
	}
	public void run() {
		//Create a panel and add components to it.
		JFrame frame = new JFrame("SNAIPAR");
		frame.setResizable(true);
		frame.setSize(200, 200);
		ms = new MainScreen();
		frame.add(ms);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
        frame.setVisible(true);
        for(int i = 0; i < 100; i ++) {
        	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	rot = rot + 5;
        	frame.repaint();
        }
	}
}
