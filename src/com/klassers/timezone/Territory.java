package com.klassers.timezone;

import com.klassers.timezone.blocks.*;

public class Territory {
	public Block[][] terrain = new Block[100][100];
	public void fill() {
		for(int i = 0; i < 100; i ++) {
			for(int j = 0; j < 100; j ++) {
				terrain[i][j] = new Ground();
			}
		}
		for(int i = 0; i < 100; i ++) {
				terrain[i][0] = new Test();
		}
	}
}
