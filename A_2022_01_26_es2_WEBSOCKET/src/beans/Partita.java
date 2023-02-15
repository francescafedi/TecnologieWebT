package beans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.websocket.Session;


public class Partita {
	List<Session> giocatori;
	boolean avviata;
	boolean fine;
	Set<Integer> numeriEstratti;
	
	

	public Partita() {
		super();
		giocatori= new ArrayList<Session>();
		avviata=false;
		numeriEstratti= new HashSet<Integer>();
		fine=false;
	}

	


	@Override
	public String toString() {
		return "Partita [giocatori=" + giocatori + ", avviata=" + avviata + ", fine=" + fine + ", numeriEstratti="
				+ numeriEstratti + "]";
	}




	public boolean isFine() {
		return fine;
	}




	public void setFine(boolean fine) {
		this.fine = fine;
	}




	public Set<Integer> getNumeriEstratti() {
		return numeriEstratti;
	}



	public void setNumeriEstratti(Set<Integer> numeriEstratti) {
		this.numeriEstratti = numeriEstratti;
	}



	public List<Session> getGiocatori() {
		return giocatori;
	}



	public void setGiocatori(List<Session> giocatori) {
		this.giocatori = giocatori;
	}



	public boolean isAvviata() {
		return avviata;
	}



	public void setAvviata(boolean avviata) {
		this.avviata = avviata;
	}
	
	
	
	

}
