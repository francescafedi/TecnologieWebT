package Beans;

public class Item implements Comparable{
	
	private String itemId;
	private String descrizione;
	private double prezzo;
	private int quantita;
	
	
	
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", descrizione=" + descrizione
				+ ", prezzo=" + prezzo + "]";
	}
	public Item() {
		this.descrizione ="";
		this.itemId ="";
		this.prezzo =-1;
	}
	@Override
	public int compareTo(Object obj) {
		Item i = (Item)obj;
		return this.getDescrizione().compareTo(i.getDescrizione());
	}
	
	
	
	

}
