package Beans;





public class Cart {
	
	private int numberOfTickets;

	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	public Cart() {
		this.numberOfTickets = 0;
	}

	@Override
	public String toString() {
		return "Cart [numberOfTickets=" + numberOfTickets + "]";
	}
	
	public void empty()
	{
		this.numberOfTickets =0;
	}
	
	public void add(int number)
	{
		this.numberOfTickets = this.numberOfTickets+number;
	}
	
	

}
