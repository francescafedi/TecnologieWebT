package Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Beans.Catalogo;
import Beans.GruppoUtenti;
import Beans.Item;
import Beans.User;

public class LogIn extends HttpServlet{
	
	private Gson g;
	
	@Override
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		g = new Gson();
		Map<String,User> utentiRegistrati = new HashMap<String, User>();
		User u = new User();
		u.setUserName("pinco pallino");
		u.setPwd("asdasd");
		u.setGroupId("g01");
		
		utentiRegistrati.put(u.getUserName(), u);
		
		
		u = new User();
		u.setUserName("tizio");
		u.setPwd("asdasd");
		u.setGroupId("g02");
		
		utentiRegistrati.put(u.getUserName(), u);
		
		u = new User();
		u.setUserName("caio");
		u.setPwd("asdasd");
		u.setGroupId("g02");
		
		utentiRegistrati.put(u.getUserName(), u);
		
		u = new User();
		u.setUserName("sempronio");
		u.setPwd("asdasd");
		u.setGroupId("g02");
		
		utentiRegistrati.put(u.getUserName(), u);
		
		
		u = new User();
		u.setUserName("admin");
		u.setPwd("admin");
		
		utentiRegistrati.put(u.getUserName(), u);
		
		this.getServletContext().setAttribute("utentiRegistrati", utentiRegistrati);
		
		Map<String, GruppoUtenti> gruppi = new HashMap<String, GruppoUtenti>();
		GruppoUtenti gu = new GruppoUtenti();
		gu.setId("g01");
		gruppi.put(gu.getId(), gu);
		gu = new GruppoUtenti();
		gu.setId("g02");
		gruppi.put(gu.getId(), gu);
		this.getServletContext().setAttribute("gruppi", gruppi);
		
	
		
	}
	
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		String name = request.getParameter("userName");
		Map<String,User> utentiRegistrati = (Map<String, User>) this.getServletContext().getAttribute("utentiRegistrati");
		User utente = utentiRegistrati.get(name);
		//Controllo se l'utente è registrato
		HttpSession session = request.getSession();
		if(utente == null)
		{
			//utente non registrato
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");	
			rd.forward(request, response);
			return;
		}
		session.setAttribute("currentUser", utente);
		
		if(utente.getUserName().compareTo("admin")==0 && utente.getPwd().compareTo("admin")==0)
		{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminPage.jsp");	
			rd.forward(request, response);
			return;
		}
		if(utente.getPwd().compareTo(request.getParameter("pwd"))!=0)
		{
			//pwd errata		
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");	
			rd.forward(request, response);
			return;
		}
		
		
		utente.setSession(session);
		Map<String, GruppoUtenti> gruppi = (Map<String, GruppoUtenti>)this.getServletContext().getAttribute("gruppi");//Prendo i gruppi
		GruppoUtenti gruppo = gruppi.get(utente.getGroupId()); //Prendo il gruppo dell'utente in questione
		gruppo.addUserToGroup(utente);		//Aggiungo l'utente al gruppo
		session.setAttribute("gruppo", gruppo);	//mi salvo il gruppo di cui faccio parte dentro la sessione
		response.sendRedirect("catalogo.jsp");
//		RequestDispatcher rd = getServletContext().getRequestDispatcher("/catalogo.jsp");
//		
//		rd.forward(request, response);
		
	}
	
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{}
	

}
