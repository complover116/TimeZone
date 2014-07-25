package com.complover116.timezone;


public class ConstructionTool extends Tool {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1999194455222596940L;
	int cost = 1;
	int maxhealth = 0;
	public EntityHurtable btc;
	public ConstructionTool(EntityHurtable bttc) {
		this.model.setModel(bttc.anim.getFrame("unbuilt", 0));
		this.model.rotX = bttc.model.rotX;
		this.model.rotY = bttc.model.rotY;
		btc = bttc;
	}
	@Override
	public boolean use() {
		btc.setPos(this.getPos());
		CurGame.c.terra.regEntity(btc);
		CurGame.c.terra.preview.tool.remove();
		CurGame.c.terra.preview.tool = null;
		CurGame.c.terra.preview.model.img = TeamData.getTeamImage("cursor", CurGame.c.terra.owner);
		return true;
	}

	@Override
	public void renderInfo2() {
		// TODO Auto-generated method stub

	}
	public boolean place() {
		//CurGame.c.terra.regEntity(copy());
		return true;
	}

}
