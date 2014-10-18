package com.complover116.timezone;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.RepaintManager;

@SuppressWarnings("deprecation")
public class GUI implements Runnable {
	public static MainScreen ms;
	public static JDialog dialog;
	public static double rot = 45;
	public static void redraw() {
		ms.repaint();
	}
	public static void shutDown() {
		//ms.setVisible(false);
	}
	public static void hideDialogs() {
		dialog.hide();
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
		dialog = new JDialog();
		dialog.setTitle(title);
		JLabel txt = new JLabel();
		txt.setText(text);
		dialog.add(txt);
		dialog.setResizable(false);
		dialog.setSize(7*text.length(), 60);
		dialog.show();
		dialog.setAlwaysOnTop(true);
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
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    double width = screenSize.getWidth();
	    double height = screenSize.getHeight();
	    ms.setPreferredSize(new Dimension((int)width,(int)height));
        ms.requestFocusInWindow();
        ms.setIgnoreRepaint(true);
		frame.add(ms);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RepaintManager.setCurrentManager(new NoRepaint());
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
