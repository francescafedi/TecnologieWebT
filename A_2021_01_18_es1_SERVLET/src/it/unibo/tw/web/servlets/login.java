package it.unibo.tw.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unibo.tw.web.beans.Cart;
import it.unibo.tw.web.beans.Group;
import it.unibo.tw.web.beans.User;

public class login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String homeURL = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
			super.init(config);
			//Creo la lista di utenti registrati
			Map<String,User> utentiRegistrati=new HashMap<String,User>();
			User u=new User();
			u.setPassword("password");
			u.setUsername("Alice");
			utentiRegistrati.put(u.getUsername(), u);
			u= new User();
			u.setPassword("password");
			u.setUsername("Francesca");
			utentiRegistrati.put(u.getUsername(), u);
			u=new User();
			u.setPassword("password");
			u.setUsername("francesca");
			utentiRegistrati.put(u.getUsername(), u);
			u=new User();
			u.setPassword("password");
			u.setUsername("alice");
			utentiRegistrati.put(u.getUsername(), u);
			u=new User();
			u.setPassword("admin");
			u.setUsername("admin");
			utentiRegistrati.put(u.getUsername(), u);
			this.getServletContext().setAttribute("utentiRegistrati", utentiRegistrati);
			
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		Map<String,User> utentiRegistrati=(Map<String,User>)this.getServletContext().getAttribute("utentiRegistrati");
		Map<String, User> utentiAttivi=(Map<String,User>)this.getServletContext().getAttribute("utentiAttivi");
		if(utentiAttivi==null) {
			utentiAttivi=new HashMap<String,User>();
			this.getServletContext().setAttribute("utentiAttivi", utentiAttivi);
		}		
			//Gestisco la login
			if(!utentiRegistrati.isEmpty() && utentiRegistrati.containsKey(username)) {
				if(utentiRegistrati.get(username).getPassword().compareTo(password)==0 ){
					//Utente pu� entrare
					System.out.println("Utente loggato");
					utentiAttivi.put(username, utentiRegistrati.get(username)); //In questo modo ho la lista degli utenti attivi per admin
					if(username.equals("admin")) {
						RequestDispatcher rd = getServletContext().getRequestDispatcher("/paginaadmin.jsp");	
						rd.forward(request, response);
					}else {
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/start.html");	
					rd.forward(request, response);
					}
					return;
					}
				}
					//password o nome utente errato
						System.out.println("Problemi di login");
						RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");	
						rd.forward(request, response);
						return;
					
			}
	
}
