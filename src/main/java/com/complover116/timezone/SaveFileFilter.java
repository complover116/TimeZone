package com.complover116.timezone;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class SaveFileFilter extends FileFilter {

	@Override
	public boolean accept(File file) {
		String extension = "";
		for(int i = 0; i < file.getName().length(); i++){
			if(file.getName().substring(file.getName().length() - i, 1).equalsIgnoreCase(".")) {
				break;
			} else {
				extension = file.getName().substring(file.getName().length() - i, 1)+extension;
			}
		}
		if(extension.equalsIgnoreCase("tzs")) return true;
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
