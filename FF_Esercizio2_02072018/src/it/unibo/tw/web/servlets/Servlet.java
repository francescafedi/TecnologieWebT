package it.unibo.tw.web.servlets;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import it.unibo.tw.web.beans.Counter;
import it.unibo.tw.web.beans.Result;
import static java.time.temporal.ChronoUnit.MINUTES;


public class Servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Gson g;
	@Override
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		g=new Gson();		
	}
	
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
    	throw new ServletException("This servlet only supports HTTP GET REQUEST");
     }
    
    
	public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
		int funzione= Integer.parseInt(this.getServletContext().getInitParameter("funzione"));

String body = "";

BufferedReader in = new BufferedReader(new InputStreamReader(
                request.getInputStream()));
String line = in.readLine();
while (line != null) {
    body += line;
    line = in.readLine();
}
		Counter c= g.fromJson(body, Counter.class);
		int primo= c.getP();
		int secondo=c.getS();
		
		Result r= new Result();
		r.setRisultato(primo+secondo);
		r.setServlet(0);
		String res=g.toJson(r);
		response.getWriter().println(res);
		
    
	}

}
