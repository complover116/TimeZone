package com.complover116.timezone;

import com.complover116.timezone.entities.BlockBuilder;

public class BlockBuildTool extends Tool {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5375606897494007766L;
	public Block btc;
	public int owner;
	public int wt = 0;
	public String unbuiltim;
	public int cost = 1;
	public int maxh;
	@Override
	public boolean use() {
		if(CurGame.c.terra.terrain[(int) (this.getPos().x/16)]
				[(int) (this.getPos().y/16)].getClass() == btc.getClass()) {
			return false;
		}
		for(int i = 0; i < CurGame.c.terra.entities.size(); i++) {
			if(EntityHurtable.class.isInstance(CurGame.c.terra.entities.get(i))) {
					if(CurGame.c.terra.entities.get(i)!=this){
						if(((EntityObject)CurGame.c.terra.entities.get(i)).checkCollision(this)){
							return false;
						}
					}
			}
		}
		CurGame.c.terra.regEntity(copy());
		return true;
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
	}
	public BlockBuildTool(Block bttc, byte team, int mh, String unbuiltimg, int cst){
		btc = bttc;
		owner = team;
		cost = cst;
		this.model.setModel(unbuiltimg);
		this.maxh = mh;
		this.unbuiltim = unbuiltimg;
		
	}
	public BlockBuilder copy() {
		BlockBuilder build = null;
		try {
			build = new BlockBuilder(btc.getClass().newInstance(), (byte) owner, maxh, unbuiltim, cost);
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
}
