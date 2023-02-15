package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Operation extends Thread {

	int matrice1 [][];
	int matrice2[][];
	int result[][];
	int numRighe;
	int numColonne;
	int id;
	
	
	public Operation() {
		super();
	}


	

	public Operation(int[][] matrice1, int[][] matrice2, int numRighe, int numColonne) {
		super();
		this.matrice1 = matrice1;
		this.matrice2 = matrice2;
		this.numRighe = numRighe;
		this.numColonne = numColonne;
	}




	public int[][] getMatrice1() {
		return matrice1;
	}




	public void setMatrice1(int[][] matrice1) {
		this.matrice1 = matrice1;
	}




	public int[][] getMatrice2() {
		return matrice2;
	}




	public void setMatrice2(int[][] matrice2) {
		this.matrice2 = matrice2;
	}




	public int[][] getResult() {
		return result;
	}




	public void setResult(int[][] result) {
		this.result = result;
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







	@Override
	public String toString() {
		return Arrays.toString(result);
	}




	@Override
	public void run() {
		this.result=new int [this.numRighe][this.numColonne];
		for(int i =0 ; i<this.numRighe; i++) {
			for(int j=0;j<this.numColonne;j++) {
				this.result[i][j]=this.matrice1[i][j]-this.matrice2[i][j];
			}
		}
	}
	
}
