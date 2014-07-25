package com.complover116.timezone;

import com.complover116.timezone.entities.RailMount;
import com.complover116.timezone.entities.Sentry;

public class OrderTool extends Tool {
	EntityHurtable envokeOn;
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
		if(envokeOn instanceof Sentry) {
				Order order = new Order();
				order.type = 1;
				order.pos = this.getPos();
				this.envokeOn.orders.add(order);
		}
		return false;
	}

	@Override
	public void renderInfo2() {
		// TODO Auto-generated method stub

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

}
