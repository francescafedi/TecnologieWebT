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

import com.google.gson.Gson;

import it.unibo.tw.web.beans.Cart;
import it.unibo.tw.web.beans.Catalogue;
import it.unibo.tw.web.beans.Group;
import it.unibo.tw.web.beans.Item;
import it.unibo.tw.web.beans.User;

public class login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String homeURL = null;
	
	private Gson g;
	@Override
	public void init(ServletConfig config) throws ServletException {
			super.init(config);
			g=new Gson();
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
			
			//Creo gruppi
			Map<String,Group> gruppi=new HashMap<String,Group>();
			Group g=new Group();
			g.setgroupId("gr1");
			gruppi.put("gr1", g);
			g=new Group();
			g.setgroupId("gr2");
			gruppi.put("gr2", g);
						
			this.getServletContext().setAttribute("gruppi", gruppi);
			
			
			//Creo il catalogo
			Catalogue catalogo=new Catalogue();
			Item i=new Item();
			i.setDescription("Frutta");
			i.setPrice(7);
			i.setQuantity(100);
			catalogo.getItems().add(i);
			Item b=new Item();
			b.setDescription("Carne");
			b.setPrice(7);
			b.setQuantity(100);
			catalogo.getItems().add(b);
			Item c=new Item();
			c.setDescription("Pane");
			c.setPrice(7);
			c.setQuantity(100);
			catalogo.getItems().add(c);
			Item d=new Item();
			d.setDescription("Latte");
			d.setPrice(7);
			d.setQuantity(100);
			catalogo.getItems().add(d);
			this.getServletContext().setAttribute("catalogo", catalogo);
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		Map<String,User> utentiRegistrati=(Map<String,User>)this.getServletContext().getAttribute("utentiRegistrati");
		Map<String,Group> gruppi=(Map<String,Group>)this.getServletContext().getAttribute("gruppi");

			//Gestisco la login
			if(!utentiRegistrati.isEmpty() && utentiRegistrati.containsKey(username)) {
				if(utentiRegistrati.get(username).getPassword().compareTo(password)==0 ){
					//Utente può entrare
					System.out.println("Utente loggato");
					//Veridico se essite già una sessione per quell'utente
					HttpSession session = request.getSession();
					if(utentiRegistrati.get(username).getSessione()!=null) {
						session.setAttribute("success", 4);
						RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");			
						rd.forward(request, response);
	
						return;
					}
					
					
					Group myGroup=gruppi.get(utentiRegistrati.get(username).getGroupId()); //prendo il mio gruppo di appartenza
					
					User u= utentiRegistrati.get(username);
					u.setSessione(session);
					myGroup.getUtenti().add(u);
					session.setAttribute("mygroup", myGroup);
					session.setAttribute("currentUser", u);
					Catalogue cat=(Catalogue)this.getServletContext().getAttribute("catalogo");
					Item[] itemsInCatalogue=new Item[cat.getItems().size()];
					itemsInCatalogue =  cat.getItems().toArray(itemsInCatalogue);
					String strCatalogue=this.g.toJson(itemsInCatalogue);
					System.out.println(strCatalogue);	
					this.getServletContext().setAttribute("catalogoJson", strCatalogue);
					response.sendRedirect("catalogo.jsp");
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
