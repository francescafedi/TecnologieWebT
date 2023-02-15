package it.unibo.tw.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unibo.tw.web.beans.Asta;
import it.unibo.tw.web.beans.Regalo;
import it.unibo.tw.web.beans.User;

public class login extends HttpServlet {

	private static final long serialVersionUID = 1L;


	
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
			
			List<Regalo> regali=new ArrayList<Regalo>();
			Regalo r=new Regalo();
			r.setNome("Palla");
			r.setDescrizione("descrizione palla");
			r.setContinente("Spagna");
			r.setAsta(false);
			regali.add(r);
			r=new Regalo();
			r.setNome("Gioco");
			r.setDescrizione("descrizione gioco");
			r.setContinente("Svezia");
			r.setAsta(false);
			regali.add(r);
			r=new Regalo();
			r.setNome("Zaino");
			r.setDescrizione("descrizione zaino");
			r.setContinente("Germania");
			r.setAsta(false);
			regali.add(r);
			r=new Regalo();
			r.setNome("Matita");
			r.setDescrizione("descrizione matita");
			r.setContinente("Italia");
			r.setAsta(false);
			regali.add(r);
			r=new Regalo();
			r.setNome("Letto");
			r.setDescrizione("descrizione letto");
			r.setContinente("Italia");
			r.setAsta(false);
			regali.add(r);
			Asta asta=new Asta();
			asta.setRegali(regali);
			asta.setAvviata(false);
			this.getServletContext().setAttribute("Asta", asta);
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		Map<String,User> utentiRegistrati=(Map<String,User>)this.getServletContext().getAttribute("utentiRegistrati");
		Map<String,User> utentiOnline=(Map<String,User>)this.getServletContext().getAttribute("utentiOnline");
		if(utentiOnline==null) {
			utentiOnline=new HashMap<String,User>();
		}
		
			//Gestisco la login
			if(!utentiRegistrati.isEmpty() && utentiRegistrati.containsKey(username)) {
				if(utentiRegistrati.get(username).getPassword().compareTo(password)==0 ){
					//Utente registrato
					System.out.println("Utente loggato");
					utentiOnline.put(username, utentiRegistrati.get(username));
					if(utentiOnline.size()>=4) {
						Asta a=(Asta)this.getServletContext().getAttribute("Asta");
						a.setAvviata(true);
					}
					request.getSession().setAttribute("currentUser", utentiRegistrati.get(username));
						RequestDispatcher rd = getServletContext().getRequestDispatcher("/s1.jsp");	
						request.getSession().setAttribute("resultLogin", 1);
						rd.forward(request, response);
					
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
