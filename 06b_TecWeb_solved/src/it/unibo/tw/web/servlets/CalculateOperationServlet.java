package it.unibo.tw.web.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import it.unibo.tw.web.pojo.*;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;

public class CalculateOperationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Gson gson;
	
	@Override
	public void init() {
		gson = new Gson();	
	}
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
	throws ServletException, IOException {
		
		String number = request.getParameter("number");
		String operation = request.getParameter("operation");
		
		CalculationResult calculation_result = new CalculationResult(number, operation);
		
		PrintWriter out = response.getWriter();
	    
		response.getWriter().println(gson.toJson(calculation_result));
	}
}
