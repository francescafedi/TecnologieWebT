package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Richiesta implements Serializable {

	private static final long serialVersionUID = 1L;

	String matrice1;
	String matrice2;
	int numRighe;
	int numColonne;
	int id;
	
	
	public Richiesta() {
		super();
	}


	public String getMatrice1() {
		return matrice1;
	}


	public void setMatrice1(String matrice1) {
		this.matrice1 = matrice1;
	}


	public String getMatrice2() {
		return matrice2;
	}


	public void setMatrice2(String matrice2) {
		this.matrice2 = matrice2;
	}


	public int getNumRighe() {
		return numRighe;
	}


	public void setNumRighe(int numRighe) {
		this.numRighe = numRighe;
	}


	public int getNumColonne() {
		return numColonne;
	}


	public void setNumColonne(int numColonne) {
		this.numColonne = numColonne;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	
}
