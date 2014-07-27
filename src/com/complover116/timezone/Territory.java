package com.complover116.timezone;

import java.io.Serializable;
import java.util.ArrayList;

import com.complover116.timezone.blocks.Border;
import com.complover116.timezone.blocks.Ground;
import com.complover116.timezone.entities.MainFrame;
import com.complover116.timezone.entities.Preview;
import com.complover116.timezone.entities.Teleporter;

public class Territory implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1046606549993722053L;
	public Block[][] terrain = new Block[100][100];
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public EntityControllable controlledEnt;
	public Preview preview;
	public int owner;
	public boolean firstround = false;
	public Territory() {
		
	}
	public Territory(int own) {
		owner = own;
		System.out.println("Territory of team "+owner+" is now preparing");
		fill();
		System.out.println("Territory of team "+owner+" is ready");
	}
	public void fill() {
		for(int i = 0; i < 100; i ++) {
			for(int j = 0; j < 100; j ++) {
				terrain[i][j] = new Ground();
			}
		}
		for(int i = 0; i < 100; i ++) {
				terrain[i][0] = new Border();
		}
		for(int i = 0; i < 100; i ++) {
			terrain[0][i] = new Border();
		}
		for(int i = 0; i < 100; i ++) {
			terrain[i][99] = new Border();
		}
		for(int i = 0; i < 100; i ++) {
			terrain[99][i] = new Border();
		}
		/*Sentry sentry2 = new Sentry(this.owner);
		sentry2.model.x = 256;
		sentry2.model.y = 256;
		entities.add(sentry2);*/
		byte tm;
		if(this.owner == 0) {
			tm = 1;
		} else {
			tm = 0;
		}
		MainFrame mf = new MainFrame(this.owner);
		mf.model.x = 1024;
		mf.model.y = 1024;
		entities.add(mf);
		Teleporter tp = new Teleporter();
		tp.model.x = 16;
		tp.model.y = 16;
		entities.add(tp);
		/*BaseVehicle bv = new BaseVehicle(tm);
		bv.model.x = 64;
		bv.model.y = 64;
		entities.add(bv);
		controlledEnt = bv;
		BaseVehicle bv2 = new BaseVehicle((byte) this.owner);
		bv2.model.x = 128;
		bv2.model.y = 128;
		entities.add(bv2);*/
		Preview pv = new Preview();
		pv.model.x = 16;
		pv.model.y = 16;
		//entities.add(pv);
		preview = pv;
		preview.model.img = TeamData.getTeamImage("cursor", this.owner);
	}
	public void regEntity(Entity entity) {
		entities.add(entity);
	}
	public void delEnt(Entity entity) {
		entities.remove(entity);
		entity = null;
	}
}
