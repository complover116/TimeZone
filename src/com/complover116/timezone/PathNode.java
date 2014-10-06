package com.complover116.timezone;

public class PathNode {
	public PathNode(int x2, int y2, int i) {
		this.x = x2;
		this.y = y2;
		this.weight = i;
	}
	public PathNode(int x2, int y2) {
		this.x = x2;
		this.y = y2;
	}
	public int x;
	public int y;
	public int weight;
	public boolean worse(PathNode onode) {
		if(this.x==onode.x&&this.y==onode.y&&this.weight>=onode.weight) return true;
		return false;
	}
	public boolean nextToNode(PathNode onode) {
		if(onode.x == this.x) {
			if(onode.y == this.y+1||onode.y == this.y-1) return true;
		}
		if(onode.y == this.y) {
			if(onode.x == this.x+1||onode.x == this.x-1) return true;
		}
		/*if(onode.x == this.x+1||onode.x == this.x-1) {
			if(onode.y == this.y+1||onode.y == this.y-1) return true;
		}*/
		return false;
	}
	public double heuristic(PathNode goal) {
		Pos pos = new Pos(this.x*16-8,this.y*16-8);
		Pos pos2 = new Pos(goal.x*16-8,goal.y*16-8);
		return pos2.distance(pos);
	}
}
