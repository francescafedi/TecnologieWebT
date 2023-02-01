package servlets;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.GruppoUtenti;
import beans.Utente;

public class Registration extends HttpServlet{
	
	@Override
	public void init(ServletConfig config) throws ServletException 
	{ 
		super.init(config);
		Map<String,GruppoUtenti> gruppi = new HashMap<String,GruppoUtenti>();
		GruppoUtenti g = new GruppoUtenti();
		g.setNomeGruppo("g1");

		 
		
		
		gruppi.put(g.getNomeGruppo(), g);
		
		g = new GruppoUtenti();
		g.setNomeGruppo("g2");
		
		gruppi.put(g.getNomeGruppo(), g);
		
		g = new GruppoUtenti();
		g.setNomeGruppo("g3");
		
		gruppi.put(g.getNomeGruppo(), g);
		
		this.getServletContext().setAttribute("gruppi", gruppi);	
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
		
	}
	
public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
		
	
	 String username = req.getParameter("username");
	 String pwd = req.getParameter("password");
	 String group = req.getParameter("group");
	 
	 Map<String,GruppoUtenti> gruppi = (Map<String, GruppoUtenti>) this.getServletContext().getAttribute("gruppi");
	 GruppoUtenti g = gruppi.get(group);
	 Utente u = new Utente();
	 u.setGruppo(group);
	 u.setNomeUtente(username);
	 u.setPassword(pwd);
	 HttpSession session = req.getSession();
	 
	 if(g == null)
	 {
		 session.setAttribute("err", "1");
		 gruppi.put(g.getNomeGruppo(), g);
		 this.getServletContext().setAttribute("gruppi", gruppi);
		 RequestDispatcher rd = this.getServletContext().getRequestDispatcher("index.jsp");
		 rd.forward(req, res);
		 return;
	 }
	 
	 if( !g.containsUser(u))
	 {
		 g.addUtente(u);
	 }
	 
	 if(g.containsUser(u) && !g.getUtenteByName(u.getNomeUtente()).isValid())
	 {
		 Utente ut = g.getUtenteByName(u.getNomeUtente());
		 ut.setPassword(pwd);
		 ut.setZeroTime(new Date());
		 ut.setValid(true);
		 g.addUtente(ut);
		 
	 }
		 
	 gruppi.put(g.getNomeGruppo(), g);
	 this.getServletContext().setAttribute("gruppi", gruppi);
	 
	 RequestDispatcher rd = this.getServletContext().getRequestDispatcher("index.jsp");
	 rd.forward(req, res);
	 return;
	}
}
