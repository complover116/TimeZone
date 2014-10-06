package com.complover116.timezone;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;



public class Pathfinding {
	//PATHFINDING V2
	int x;
	int y;
	int x2;
	int y2;
	ArrayList<PathNode> opennodes = new ArrayList<PathNode>();
	ArrayList<PathNode> nodes = new ArrayList<PathNode>();
	ArrayList<PathNode> tnodes = new ArrayList<PathNode>();
	int counter = 0;
	boolean finish = false;
	public Path tick() {
		counter++;
		if(!finish) {
			if(counter%100 == 0) {
				System.out.println("openn:"+opennodes.size()+" n:"+nodes.size());
			}
		tnodes.clear();
		PathNode curnode = getBestNode();
		if(curnode == null||nodes.size()>Config.maxPathLength) {
			Path p= new Path();
			p.valid = false;
			return p;
		}
		opennodes.remove(curnode);
		nodes.add(curnode);
		tnodes.add(new PathNode(curnode.x + 1,curnode.y,curnode.weight + 1));
		tnodes.add(new PathNode(curnode.x - 1,curnode.y,curnode.weight + 1));
		tnodes.add(new PathNode(curnode.x,curnode.y+1,curnode.weight + 1));
		tnodes.add(new PathNode(curnode.x,curnode.y-1,curnode.weight + 1));
		/*tnodes.add(new PathNode(curnode.x + 1,curnode.y+1,curnode.weight + 1));
		tnodes.add(new PathNode(curnode.x - 1,curnode.y-1,curnode.weight + 1));
		tnodes.add(new PathNode(curnode.x+1,curnode.y-1,curnode.weight + 1));
		tnodes.add(new PathNode(curnode.x-1,curnode.y+1,curnode.weight + 1));*/
		for(int i = 3; i > -1; i--) {
			try{
			if(CurGame.c.terra.terrain[tnodes.get(i).x][tnodes.get(i).y].solid) {
				tnodes.remove(i);
			} else if(tnodes.size()>i){
			boolean flag = true;
			for(int j = 0;j < nodes.size();j++) {
				
				if(tnodes.get(i).worse(nodes.get(j))) {
					tnodes.remove(i);
					flag = false;
					break;
				}
			}
			if(flag) {
				for(int j = 0;j < opennodes.size();j++) {
					
					if(tnodes.get(i).worse(opennodes.get(j))) {
						tnodes.remove(i);
						break;
					}
				}
			}
			}
			} catch (IndexOutOfBoundsException e){
				System.out.println("IOOB Exception while pahfinding: i:"+i+", tnodes.size():"+tnodes.size()+" "+e.getStackTrace()[2].getLineNumber());
			}
		}
		for(int k = 0; k < tnodes.size(); k++) {
			opennodes.add(tnodes.get(k));
			if(tnodes.get(k).x == x&&tnodes.get(k).y == y){
				finish = true;
			}
		}
		return null;
		} else {
			//Part 2: Trace the path based on nodes' weights
			PathNode curnode = new PathNode(x,y,0);
			Path path = new Path();
			
			while(true) {
				int besti = 0;
				int bestweight = 999999999;
				for(int i = 0; i < nodes.size(); i ++) {
					if(curnode.nextToNode(nodes.get(i))) {
						if(nodes.get(i).weight<bestweight) {
							bestweight = nodes.get(i).weight;
							besti = i;
						}
					}
				}
				path.path.add(new Pos(nodes.get(besti).x,nodes.get(besti).y,true));
				curnode = nodes.get(besti);
				if(curnode.x == x2&&curnode.y == y2) break;
			}
			return path;
		}
	}
	public PathNode getBestNode() {
		// TODO Auto-generated method stub
		double best = 99999;
		PathNode n = null;
		for(int i = 0; i < opennodes.size();i++) {
			if(best>opennodes.get(i).heuristic(new PathNode(x,y))) {
				best = opennodes.get(i).heuristic(new PathNode(x,y));
				n = opennodes.get(i);
			}
		}
		return n;
	}
	/*
	 * 
		
		while(true) {
			
			
		}
		
	 */
	public void render() {
		for(int i = 0; i < this.nodes.size(); i++) {
			MainScreen.shapes.add(new ShapeModel(new Rectangle(nodes.get(i).x*16+4,nodes.get(i).y*16+4, 12, 12), new Color(255,0,0,255), false));
		}
		for(int i = 0; i < this.opennodes.size(); i++) {
			MainScreen.shapes.add(new ShapeModel(new Rectangle(opennodes.get(i).x*16+4,opennodes.get(i).y*16+4, 12, 12), new Color(255,0,0,255), true));
		}
		for(int i = 0; i < this.tnodes.size(); i++) {
			MainScreen.shapes.add(new ShapeModel(new Rectangle(tnodes.get(i).x*16+4,tnodes.get(i).y*16+4, 12, 12), new Color(0,255,0,255), true));
		}
	}
	public Pathfinding(int xz, int yz, int x2z, int y2z) {
		x = xz;
		y = yz;
		x2 = x2z;
		y2 = y2z;
		opennodes.add(new PathNode(x2,y2,0));
		
	}
}
