package com.complover116.timezone;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import com.complover116.timezone.entities.Factory;
import com.complover116.timezone.entities.Sentry;

public class OrderTool extends Tool {
	EntityHurtable envokeOn;
	Order curorder;
	public byte status = 0;
	/**
	 * 
	 */
	private static final long serialVersionUID = -6855043476994094521L;

	public OrderTool(EntityHurtable selent) {
		this.envokeOn = selent;
	}
	public OrderTool() {
		
	}
	@Override
	public boolean use() {
		if(this.status == 0){
		if(envokeOn instanceof Sentry) {
				curorder = new Order();
				curorder.type = 1;
				curorder.pos = this.getPos();
				this.envokeOn.orders.add(curorder);
		}
		if(envokeOn instanceof EntityControllable) {
			curorder = new Order();
			curorder.type = 1;
			curorder.pos = this.getPos();
			this.envokeOn.orders.add(curorder);
		}
		if(envokeOn instanceof Factory) {
			curorder = new Order();
			curorder.type = 1;
			curorder.pos = envokeOn.getPos();
			this.status = 1;
			this.curorder.addorders = new ArrayList<Order>();
		}
		return true;
		} else if(this.status == 1) {
			Order order = new Order();
			order.type = 1;
			order.pos = this.getPos();
			this.curorder.addorders.add(order);
			return true;
		}
		return true;
	}

	@Override
	public void renderInfo2() {
		// TODO Auto-generated method stub

	}
	@Override
	public void renderStuff() {
		if(this.status == 1&&this.curorder.addorders.size()>0) {
			Line2D.Double line = new Line2D.Double(this.envokeOn.getPos().x,this.envokeOn.getPos().y, this.curorder.addorders.get(0).pos.x, this.curorder.addorders.get(0).pos.y);
			MainScreen.shapes.add(new ShapeModel(line, new Color(0,255,0,255), true));	
		if(this.curorder.addorders.size() > 1) {
			for(int i = 1; i < this.curorder.addorders.size(); i ++){
			Line2D.Double line2 = new Line2D.Double(this.curorder.addorders.get(i-1).pos.x,this.curorder.addorders.get(i-1).pos.y, this.curorder.addorders.get(i).pos.x, this.curorder.addorders.get(i).pos.y);
			MainScreen.shapes.add(new ShapeModel(line2, new Color(0,255,0,255), true));	
			}
		}
		}
	}
	@Override
	public void construct() {
		this.model.rotX = 7.5;
		this.model.rotY = 7.5;
		this.collideX = 0;
		this.collideY = 0;
		this.collideX2 = 16;
		this.collideY2 = 16;
		this.model.setModel("order");
	}
	@Override
	public boolean use2() {
		if(envokeOn instanceof EntityControllable||this.envokeOn instanceof Sentry) {
		if(this.status == 0) {
			if(this.envokeOn.orders.size() > 0) {
				this.envokeOn.orders.remove(this.envokeOn.orders.size() - 1);
			}
		}
		}
		if(envokeOn instanceof Factory) {
			curorder = new Order();
			curorder.type = 2;
			curorder.pos = envokeOn.getPos();
			this.status = 1;
			this.curorder.addorders = new ArrayList<Order>();
		}
		if(this.status == 1) {
			this.status = 0;
			this.envokeOn.orders.add(curorder);
		}
		return true;
	}

}
