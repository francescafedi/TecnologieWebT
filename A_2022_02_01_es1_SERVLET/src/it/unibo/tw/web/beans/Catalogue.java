package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalogue implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Prenotazione> items = new ArrayList<Prenotazione>();

	public List<Prenotazione> getItems() {
		return items;
	}

	public void empty() {
		this.items = new ArrayList<Prenotazione>();
	}

	public Catalogue() {
		super();
		
	}

	
}
