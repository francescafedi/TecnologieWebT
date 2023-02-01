package Beans;
import java.util.*;

public class Catalogo {

	private List<Item> catalog = new ArrayList<Item>();

	public List<Item> getCatalog() {
		return catalog;
	}

	public void setCatalog(List<Item> catalog) {
		this.catalog = catalog;
	}

	public Catalogo() {
		super();
	}

	public boolean add(Item i)
	{
		return this.catalog.add(i);
	}
	
	public boolean remove(Item i)
	{
		return this.catalog.remove(i);
	}
	
	public Item remove(int index)
	{
		return this.catalog.remove(index);
	}
	
	
	public Item getItemFromCatalogue(Item it)
	{
		for(Item i : this.catalog)
		{
			if(i.compareTo(it)==0)
				return i;
		}
		return null;
	}
	
}
