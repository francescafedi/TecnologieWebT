package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.List;

public class Cartella implements Serializable {

	private static final long serialVersionUID = 1L;

	int size;
	List<FileServer> files;
	
	// --- constructor ----------
	
	public Cartella() {
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<FileServer> getFiles() {
		return files;
	}

	public void setFiles(List<FileServer> files) {
		this.files = files;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// --- getters and setters --------------
	


}
