package com.complover116.timezone;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	public static String getSavePath() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Save current game");
		fc.showSaveDialog(ms);
		return fc.getSelectedFile().getAbsolutePath();
	}
	public static void gameMenu() {
		JDialog d = new JDialog();
		d.setTitle("Game menu");
		d.setResizable(false);
		d.setSize(100, 300);
		d.show();
	}
	public static void infoDialog(String title, String text) {
		JDialog d = new JDialog();
		d.setTitle(title);
		JLabel txt = new JLabel();
		txt.setText(text);
		d.add(txt);
		d.setResizable(false);
		d.setSize(7*text.length(), 60);
		d.show();
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
	public static String getLoadPath() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Load game");
		fc.showOpenDialog(ms);
		return fc.getSelectedFile().getAbsolutePath();
	}
}
