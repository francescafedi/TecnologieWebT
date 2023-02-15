package it.unibo.tw.web.servlets;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import it.unibo.tw.web.beans.Richiesta;
import it.unibo.tw.web.beans.Risposta;
import it.unibo.tw.web.beans.User;

public class servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Gson g;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
			super.init(config);
			g=new Gson();
	}
	
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
    	//Ricezione della richiesta
    	List<User> sessioniAttive=(List<User>)this.getServletContext().getAttribute("sessioniAtttive");
    	for(int i=0; i<sessioniAttive.size();i++) {
    		if(sessioniAttive.get(i)==(User)request.getSession().getAttribute("currentUser")) {
    			sessioniAttive.get(i).setNumSessione(sessioniAttive.get(i).getNumRichiesta()+1);
    		}
    	}
		String parola=request.getParameter("parola");
		if(parola!=null) {
			BufferedReader reader;
			File f=new File("C:\\aaa\\"+parola);
			reader=new BufferedReader(new FileReader(f));
			String c;
			String result="";
			int count=0;
			while((c=reader.readLine())!=null) { 
				for(int i=0; i<c.length();i++) {
					if(c.charAt(i)=='a') { //a lettera scelta a caso
						count++;
					}else {
						result=result+c.charAt(i);
					}
				}
			}
			
		
    			Risposta ris1=new Risposta();
    			ris1.setCount(count);
    			ris1.setResult(result);
    			request.getSession().setAttribute("ris1", ris1);
    			//Invio della risposta
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/j2.jsp");			
		rd.forward(request, response);
    }
    }
    
    
	public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
    	
    }

}
