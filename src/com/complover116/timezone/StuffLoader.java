package com.complover116.timezone;

import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class StuffLoader {

	public static void main(String[] args) {
		
		
		try{
			CurGame.c.status = GameState.LOADING_SCREEN;
			System.out.println("=====Preparing teams=====");
			for(int i = 0; i<CurGame.c.teams.length; i++) {
				CurGame.c.teams[i] = new TeamData();
				
			}
			CurGame.c.teams[0].dataname = "blue";
			CurGame.c.teams[1].dataname = "red";
			System.out.println("=====Displaying=====");
			CurGame.c.status = GameState.TERRITORY_LOAD_DELAY;
			GUI gui = new GUI();
			Thread uiThread = new Thread(gui, "UI Thread");
			uiThread.start();
			Thread.sleep(500);
			System.out.println("=====Loading resources=====");
			
			ImageContainer.load();
			

			System.out.println("=====Preparing zones=====");
			CurGame.c.teams[0].zone = new Territory(0);
			CurGame.c.teams[1].zone = new Territory(1);
			CurGame.c.terra=CurGame.c.teams[1].zone;
			CurGame.c.status = GameState.DEFENDERS_CONTROL;
			CurGame.overstat = 1;
			Render.render();
			System.out.println("=====Launching game=====");
		WorldTicker.run();
		} catch (Exception e) {
			e.printStackTrace();
			GUI.shutDown();
			JFrame frame = new JFrame("Crash report");
			frame.setResizable(false);
			frame.setSize(200, 200);
			JTextArea label = new JTextArea();
			JPanel pn = new JPanel();
			pn.setLayout(new BoxLayout(pn, BoxLayout.Y_AXIS));
			frame.add(pn);
			pn.add(label);
			label.setEditable(false);
			label.setText("The game has encountered a fatal error and was unable to recover.\nIf you understand the cause of the error - feel free to email me at complover116@gmail.com\nIt will help me deal with the problem");
			JFormattedTextField divider = new JFormattedTextField();
			pn.add(divider);
			divider.setText("TECHNICAL DETAILS:");
			JTextArea details = new JTextArea();
			details.setEditable(false);
			pn.add(details);
			String dt = "Exception: "+e.getClass().getName() +"\nStackTrace:\n";
			for(StackTraceElement sttr:e.getStackTrace()) {
				dt = dt + sttr.toString()+"\n";
			}
			details.setText(dt);
			frame.pack();
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}

}
