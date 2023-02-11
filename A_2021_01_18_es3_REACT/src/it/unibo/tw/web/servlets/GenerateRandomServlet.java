package it.unibo.tw.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import it.unibo.tw.web.pojo.*;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;
import java.util.Random;

public class GenerateRandomServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Gson gson;
	private Random rand;
	
	@Override
	public void init() {
		gson = new Gson();	
		rand = new Random();
	}
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
	throws ServletException, IOException {
		
		int upperbound = 6;
		int generated_random_int = rand.nextInt(upperbound - 1) + 1;
		RandomNumber random_int = new RandomNumber(generated_random_int);
		response.getWriter().println(gson.toJson(random_int));
	}
}
