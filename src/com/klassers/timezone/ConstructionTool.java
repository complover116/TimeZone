package com.klassers.timezone;

import com.klassers.timezone.entities.Constructor;

public class ConstructionTool extends Tool {
	int cost = 1;
	int maxhealth = 0;
	private byte owner;
	private EntityHurtable btc;
	public ConstructionTool(EntityHurtable bttc, byte team, int cst) {
		
		btc = bttc;
		this.model.rotX = btc.model.rotX;
		this.model.rotY = btc.model.rotY;
		owner = team;
		cost = cst;
		this.model.setModel(btc.anim.getFrame("unbuilt", 0));
		this.maxhealth = btc.maxhealth;
	}
	@Override
	public boolean use() {
		place();
		return true;
	}

	@Override
	public void renderInfo2() {
		// TODO Auto-generated method stub

	}
	public Constructor copy() {
		Constructor build = null;
		try {
			build = new Constructor(btc.getClass().newInstance(), (byte) owner, cost);
			build.setPos(this.getPos());
			return build;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return build;
		
	}
	public boolean place() {
		CurGame.terra.regEntity(copy());
		return true;
	}

}
