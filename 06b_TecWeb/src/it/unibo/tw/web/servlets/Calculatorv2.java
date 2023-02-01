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

public class Calculatorv2 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Gson gson;
	
	@Override
	public void init() {
		gson = new Gson();	
	
	}
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
	throws ServletException, IOException {
		String operazione=request.getParameter("operazione");
		double result=0;
		int valido=0;
		if(operazione.startsWith("log")) {
			result=Math.log(Double.parseDouble(operazione.substring(3)));
			valido=1;
		}else if(operazione.startsWith("sqrt")) {
			result=Math.sqrt(Double.parseDouble(operazione.substring(4)));
			valido=1;
		}else if(operazione.startsWith("e")) {
			result=Math.exp(Double.parseDouble(operazione.substring(1)));
			valido=1;
		}else if(operazione.startsWith("1/")) {
			result=1/(Double.parseDouble(operazione.substring(2)));
			valido=1;
		}
		response.getWriter().println(gson.toJson(new ResultCalculator(result, valido)));
	}
}
