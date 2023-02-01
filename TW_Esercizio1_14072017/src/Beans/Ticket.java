package Beans;

public class Ticket {
	
	private double prezzo;

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public Ticket() {
		
	}

	@Override
	public String toString() {
		return "Ticket [prezzo=" + prezzo + "]";
	}
	
	
}
