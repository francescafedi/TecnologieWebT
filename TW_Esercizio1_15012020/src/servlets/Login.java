package servlets;


import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.hsqldb.Session;

import beans.GruppoUtenti;
import beans.Utente;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;

public class Login extends HttpServlet {
	
	@Override
	public void init(ServletConfig config) throws ServletException 
	{ 
		super.init(config);
		
		
		
	}
	
	 public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
		 String gid = req.getParameter("gid");
		 Map<String,GruppoUtenti> gruppi = (Map<String, GruppoUtenti>) this.getServletContext().getAttribute("gruppi");
		 
		
		 this.getServletContext().setAttribute("gruppi", gruppi);
		 RequestDispatcher rd = this.getServletContext().getRequestDispatcher("home.html");
		 rd.forward(req, res);
		 return;
	 }

	 public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
		 
		 
		 String username = req.getParameter("username");
		 String pwd = req.getParameter("pwd");
		 String gid = req.getParameter("gid");
		 
		 Map<String,GruppoUtenti> gruppi = (Map<String, GruppoUtenti>) this.getServletContext().getAttribute("gruppi");
		 GruppoUtenti g = gruppi.get(gid);
		 HttpSession session = req.getSession();
		 
		 if(username.compareTo("admin")==0 && pwd.compareTo("admin")==0)
		 {
			 gruppi.put(g.getNomeGruppo(), g);
			 this.getServletContext().setAttribute("gruppi", gruppi);
			 RequestDispatcher rd = this.getServletContext().getRequestDispatcher("admin.jsp");
			 rd.forward(req, res);
			 return;
		 }
	
		 
		 Utente currentUser = g.getUtenteByName(username);
		 if(currentUser ==null)
		 {
			 session.setAttribute("err", "2");
			 gruppi.put(g.getNomeGruppo(), g);
			 this.getServletContext().setAttribute("gruppi", gruppi);
			 RequestDispatcher rd = this.getServletContext().getRequestDispatcher("index.jsp");
			 rd.forward(req, res);
			 return;
		 }
		 
		 if(g.checkValidity()>=3)
		 {
			 session.setAttribute("err", "4");
			 //  pwd scaduta a 3 o piu del gruppo.. reset
			 gruppi.put(g.getNomeGruppo(), g);
			 this.getServletContext().setAttribute("gruppi", gruppi);
			 RequestDispatcher rd = this.getServletContext().getRequestDispatcher("index.jsp");
			 rd.forward(req, res);
			 return;
		 }
		 
		 if(currentUser.getPassword().compareTo(pwd)==0)
		 {
			 if(!currentUser.isStillValid())
			 {
				 session.setAttribute("err", "3");
				 //  pwd scaduta.. reset
				 gruppi.put(g.getNomeGruppo(), g);
				 this.getServletContext().setAttribute("gruppi", gruppi);
				 RequestDispatcher rd = this.getServletContext().getRequestDispatcher("index.jsp");
				 rd.forward(req, res);
				 return;
			 }
			 session.setAttribute("success", username);
			 session.setAttribute(currentUser, 0);
			 session.removeAttribute(currentUser.getNomeUtente());
			 gruppi.put(g.getNomeGruppo(), g);
			 this.getServletContext().setAttribute("gruppi", gruppi);
			 RequestDispatcher rd = this.getServletContext().getRequestDispatcher("main.jsp");
			 rd.forward(req, res);
			 return;
		
		 }
		 else
		 {
			 session.setAttribute("err", "5");
			 //  tentativo pwd sbagliata.. riprova
			 gruppi.put(g.getNomeGruppo(), g);
			 this.getServletContext().setAttribute("gruppi", gruppi);
			 Object tentativi = session.getAttribute(currentUser.getNomeUtente());
			 if(tentativi == null)
			 {
				 session.setAttribute(currentUser.getNomeUtente(), 1);
				 session.setAttribute("err", "5");
				 gruppi.put(g.getNomeGruppo(), g);
				 this.getServletContext().setAttribute("gruppi", gruppi);
				 RequestDispatcher rd = this.getServletContext().getRequestDispatcher("index.jsp");
				 rd.forward(req, res);
				 return;
			 }
			 else
			 {
				 int t = (Integer)session.getAttribute(currentUser.getNomeUtente());
				 if(t<3)
				 {
					 t++;
					 session.setAttribute(currentUser.getNomeUtente(), t);
					 session.setAttribute("err", "5");
					 gruppi.put(g.getNomeGruppo(), g);
					 this.getServletContext().setAttribute("gruppi", gruppi);
					 RequestDispatcher rd = this.getServletContext().getRequestDispatcher("index.jsp");
					 rd.forward(req, res);
					 return;
				 }
				 else
				 {
					 session.setAttribute("err", "6");
					 currentUser.setPassword("asifblsiudgfhspiugfhpsiegf"); // cosi non entra piu
					 gruppi.put(g.getNomeGruppo(), g);
					 this.getServletContext().setAttribute("gruppi", gruppi);
					 RequestDispatcher rd = this.getServletContext().getRequestDispatcher("index.jsp");
					 rd.forward(req, res);
					 return;
				 }
			 }
			 
		 }
		 
	
		 
		 

		 
	
	
	 }
}
