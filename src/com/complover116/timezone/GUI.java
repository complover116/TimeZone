package com.complover116.timezone;

import javax.swing.JFrame;
import javax.swing.RepaintManager;


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
		ms.addMouseListener(ms);
	    ms.addKeyListener(ms);
	    ms.setFocusable(true);
        ms.requestFocusInWindow();
        RepaintManager rmg = RepaintManager.currentManager(ms);
        rmg.markCompletelyClean(ms);
        ms.setIgnoreRepaint(true);
		frame.add(ms);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.pack();
        frame.setVisible(true);
        
	}
}
