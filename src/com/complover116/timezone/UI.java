package com.complover116.timezone;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class UI {
	public static final Color uiColor = new Color(200,100,255);
	public static void drawUI(Graphics2D g2d) {
		String topLine = CurGame.c.status.getDescription();
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g2d.setColor(uiColor);
		g2d.fill(new Rectangle(500, 0, 400, 30));
		g2d.setColor(new Color(0,0,0));
		g2d.drawString(topLine, 500, 20);
	}
}
