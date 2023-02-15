package it.unibo.tw.web.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unibo.tw.web.beans.User;

public class login extends HttpServlet {

	private static final long serialVersionUID = 1L;


	
	@Override
	public void init(ServletConfig config) throws ServletException {
			super.init(config);
			//Creo la lista di utenti registrati
			Map<String,User> utentiRegistrati=new HashMap<String,User>();
			User u=new User();
			u.setGroupId("gr1");
			u.setPassword("password");
			u.setUsername("Alice");
			utentiRegistrati.put(u.getUsername(), u);
			u= new User();
			u.setGroupId("gr1");
			u.setPassword("password");
			u.setUsername("Francesca");
			utentiRegistrati.put(u.getUsername(), u);
			u=new User();
			u.setGroupId("gr2");
			u.setPassword("password");
			u.setUsername("francesca");
			utentiRegistrati.put(u.getUsername(), u);
			u=new User();
			u.setGroupId("gr2");
			u.setPassword("password");
			u.setUsername("alice");
			utentiRegistrati.put(u.getUsername(), u);
			u=new User();
			u.setGroupId("admin");
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
		
			//Gestisco la login
			if(!utentiRegistrati.isEmpty() && utentiRegistrati.containsKey(username)) {
				if(utentiRegistrati.get(username).getPassword().compareTo(password)==0 ){
					//Utente registrato
					System.out.println("Utente loggato");
					if(username.equals("admin")) {
						RequestDispatcher rd = getServletContext().getRequestDispatcher("/paginaadmin.jsp");
						request.getSession().setAttribute("resultLogin", 1);
						rd.forward(request, response);
					}else {
						RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");	
						request.getSession().setAttribute("resultLogin", 1);
						rd.forward(request, response);
					}
					return;
					}
				}
					//password o nome utente errato
						System.out.println("Problemi di login");
						RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");	
						request.getSession().setAttribute("resultLogin", -1);
						rd.forward(request, response);
						return;
					
			}
	
}
