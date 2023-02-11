package it.unibo.tw.web.beans;

import java.io.File;
import java.io.Serializable;

public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	File []folder;
	
	// --- constructor ----------
	
	public Item() {
	}

	public File[] getFolder() {
		return folder;
	}

	public void setFolder(File[] folder) {
		this.folder = folder;
	}

	// --- getters and setters --------------
	
}
