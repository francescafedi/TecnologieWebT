package it.unibo.tw.web.servlets;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import it.unibo.tw.web.beans.Richiesta;

public class s2 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Gson g;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
			super.init(config);
			g=new Gson();
	}
	
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
    	System.out.println("Ciao atutti sono proprio io");
    	HttpSession session=request.getSession();
    	Richiesta richiesta=(Richiesta) session.getAttribute("richiesta");
    	String newtesto=new String();
    	for(int i =0;i<richiesta.getTesto().length();i++) {
    		if(richiesta.getTesto().charAt(i)!='a') {
    			newtesto+=richiesta.getTesto().charAt(i);
    		}
    	}
    	richiesta.setTesto(newtesto);
    	String res=g.toJson(richiesta);
    	response.getWriter().println(res);
    }
    
    
	public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
    	System.out.println("Ciao atutti sono proprio io");
    	HttpSession session=request.getSession();
    	Richiesta richiesta=(Richiesta) session.getAttribute("richiesta");
    	String newtesto=new String();
    	for(int i =0;i<richiesta.getTesto().length();i++) {
    		if(richiesta.getTesto().charAt(i)!='A') {
    			newtesto+=richiesta.getTesto().charAt(i);
    		}
    	}
    	richiesta.setTesto(newtesto);
    	String res=g.toJson(richiesta);
    	response.getWriter().println(res);
    }

}
