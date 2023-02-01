package it.tecnologieweb.app;

public class HelloWorld {

	public static final String GREETING = "Hello World, ";

	// -----------------------------------
	
	private String name;
	
	// -----------------------------------

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// -----------------------------------

	public HelloWorld() {
	}
	public HelloWorld(String name) {
		this.name = name;
	}

	// -----------------------------------

	public String sayIt() {
		return GREETING + name;
	}
	
}
