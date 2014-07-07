package com.klassers.timezone;

import java.awt.geom.Rectangle2D;

public abstract class EntityObject extends Entity {
	public int collideX = 0;
	public int collideY = 0;
	public int collideX2;
	public int collideY2;
	public boolean checkCollision(EntityObject e) {
		/*double point1[] = this.model.getPointPos(collideX, collideY);
		double point2[] = this.model.getPointPos(collideX2, collideY2);
		int X1 = (int) point1[0];
		int Y1 = (int) point1[1];
		int X2 = (int) point2[0];
		int Y2 = (int) point2[1];
		double opoint1[] = e.model.getPointPos(e.collideX, e.collideY);
		double opoint2[] = e.model.getPointPos(e.collideX2, e.collideY2);
		int eX1 = (int) opoint1[0];
		int eY1 = (int) opoint1[1];
		int eX2 = (int) opoint2[0];
		int eY2 = (int) opoint2[1];
		Polygon rec = new Polygon();
		rec.addPoint(X1, Y1);
		rec.addPoint(X2, Y1);
		rec.addPoint(X2, Y2);
		rec.addPoint(X1, Y2);
		Polygon rec2 = new Polygon();
		rec2.addPoint(eX1, eY1);
		rec2.addPoint(eX2, eY1);
		rec2.addPoint(eX2, eY2);
		rec2.addPoint(eX1, eY2);*/
		Rectangle2D.Double rec = new Rectangle2D.Double(this.model.x, this.model.y, this.collideX2,this.collideY2);
		Rectangle2D.Double rec2 = new Rectangle2D.Double(e.model.x, e.model.y, e.collideX2, e.collideY2);
		/*MainScreen.shapes.add(rec);
		MainScreen.shapes.add(rec2);*/
		return rec.intersects(rec2);
	}
}