package com.complover116.timezone;

import javax.swing.RepaintManager;

public class NoRepaint extends RepaintManager {

	@Override
	public void paintDirtyRegions(){
		return;
	}

}
