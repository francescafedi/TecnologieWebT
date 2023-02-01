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
			Map<String,User> utenti=new HashMap<String,User>();
			User u=new User();
			u.setGroup("gr1");
			u.setPassword("password");
			u.setUsername("Alice");
			utenti.put(u.getUsername(), u);
			u= new User();
			u.setGroup("gr1");
			u.setPassword("password");
			u.setUsername("Francesca");
			utenti.put(u.getUsername(), u);
			u=new User();
			u.setGroup("gr2");
			u.setPassword("password");
			u.setUsername("francesca");
			utenti.put(u.getUsername(), u);
			u=new User();
			u.setGroup("gr2");
			u.setPassword("password");
			u.setUsername("alice");
			utenti.put(u.getUsername(), u);
			u=new User();
			u.setGroup("admin");
			u.setPassword("admin");
			u.setUsername("admin");
			utenti.put(u.getUsername(), u);
			this.getServletContext().setAttribute("utentiRegistrati", utenti);
			
			//Creo gruppi
			Map<String,Group> gruppi=new HashMap<String,Group>();
			Group g=new Group();
			g.setGroupName("gr1");
			gruppi.put("gr1", g);
			g=new Group();
			g.setGroupName("gr2");
			gruppi.put("gr2", g);
			//Inserisco gli utenti nel gruppo
			for(User ut : utenti.values()) {
				for(Group gr : gruppi.values()) {
					if(gr.getGroupName().equals(ut.getGroup())) {
						gr.setUtenti(ut);
					}
				}
			}
			this.getServletContext().setAttribute("gruppi", gruppi);
			
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		Map<String,User> utenti=(Map<String,User>)this.getServletContext().getAttribute("utentiRegistrati");
		Map<String,Group> gruppi=(Map<String,Group>)this.getServletContext().getAttribute("gruppi");
		//Se non esistono i miei oggetti globali modificabili li creo
		if(this.getServletContext().getAttribute("gruppiGlobal")==null) {
			Map<String,Group> gruppiGlobal=(Map<String,Group>)this.getServletContext().getAttribute("gruppi");	
			for(Group g:gruppiGlobal.values()) {
				g.getUtenti().clear(); //Svuoto gli utenti
			}
			this.getServletContext().setAttribute("gruppiGlobal", gruppiGlobal);
		}
		
			//Gestisco la login
			if(!utenti.isEmpty() && utenti.containsKey(username)) {
				if(utenti.get(username).getPassword().compareTo(password)==0 ){
					//Utente può entrare
					System.out.println("Utente loggato");
					/*Aggiorno user di sessione mettendo login=true
					 * sessione
					 * Inserisco lo user nel gruppoGlobal
					 * 
					 * 
					 */
					User utenteLoggato=new User();
					utenteLoggato.setUsername(username);
					utenteLoggato.setPassword(password);
					utenteLoggato.setLogin(true);
					utenteLoggato.setSession(request);
					utenteLoggato.setGroup(utenti.get(username).getGroup());
					
					request.getSession().setAttribute("userNow", utenteLoggato);
					
					ServletContext cx= getServletContext();
					Map<String,Group> gruppiGlobal=(Map<String,Group>)cx.getAttribute("gruppiGlobal");
					Group gruppoGlobal=gruppiGlobal.get(utenti.get(username).getGroup());
					gruppoGlobal.setUtenti(utenteLoggato);
					gruppiGlobal.remove(utenteLoggato.getGroup());
					gruppiGlobal.put(utenteLoggato.getGroup(), gruppoGlobal);
					cx.setAttribute("gruppiGlobal", gruppiGlobal);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/catalogue.jsp");	
					rd.forward(request, response);
					return;
					}
				}
					//password o nome utente errato
						RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");	
						rd.forward(request, response);
						return;
					
			}
	
}
