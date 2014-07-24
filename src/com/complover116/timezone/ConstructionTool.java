package com.complover116.timezone;


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
	public boolean place() {
		//CurGame.terra.regEntity(copy());
		return true;
	}

}
