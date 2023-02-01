package it.tecnologieweb.app;

public class Main {

	// creare un oggetto helloworld e assegnare il primo argomento in input alla sua proprieta' nome
	// stampare su console la frase che restituisce
	public static void main(String[] args){
		HelloWorld hw = new HelloWorld();
		if(args.length == 1){
			hw.setName(args[0]);
		}
		String out = hw.sayIt();
		System.out.println(out);
	}
}
