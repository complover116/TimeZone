package com.complover116.timezone;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.RepaintManager;


public class GUI implements Runnable {
	public static MainScreen ms;
	public static double rot = 45;
	public static void redraw() {
		ms.repaint();
	}
	public static void shutDown() {
		//ms.setVisible(false);
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
	    ms.setMinimumSize(new Dimension(100,100));
	    ms.setSize(1000, 1000);
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