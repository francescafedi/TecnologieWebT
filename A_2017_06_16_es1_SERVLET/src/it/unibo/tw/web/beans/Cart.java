package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Item> items = new ArrayList<Item>();

	public List<Item> getItems() {
		return items;
	}

	public void empty() {
		this.items = new ArrayList<Item>();
	}



}
