package com.complover116.timezone;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Path {
	public ArrayList<Pos> path = new ArrayList<Pos>();
	public boolean valid = true;
	public boolean sentry;
	public ArrayList<Order> toOrders(int type) {
		ArrayList<Order> orders = new ArrayList<Order>();
		for(int i = 0; i < path.size(); i++){
			Order order = new Order();
			order.pos = path.get(i);
			order.type = type;
			orders.add(order);
		}
		return orders;
	}
	public void render() {
		for(int i = 1; i < path.size(); i ++) {
			MainScreen.shapes.add(new ShapeModel(new Line2D.Double(path.get(i-1).x,path.get(i-1).y,path.get(i).x,path.get(i).y), new Color(255,0,0,255), false));
		}
	}
	public boolean isStillValid() {
		if(sentry){
			for(int i = 1; i < path.size(); i ++) {
				if(!path.get(i-1).SentryLOM(path.get(i))) {
					return false;
				}
			}
		} else
		for(int i = 1; i < path.size(); i ++) {
			if(!path.get(i-1).LOS(path.get(i))) {
				return false;
			}
		}
		return true;
	}
}
