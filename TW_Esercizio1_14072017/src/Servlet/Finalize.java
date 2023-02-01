package Servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Beans.Cart;
import Beans.Catalogo;
import Beans.GruppoUtenti;
import Beans.Item;
import Beans.User;

public class Finalize extends HttpServlet{
	
	private Gson g;
	
	@Override
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		g = new Gson();
	}
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
//		Catalogo catalogo = (Catalogo) this.getServletContext().getAttribute("catalogo");
//		Item[] itemsInCatalog = new Item[catalogo.getCatalog().size()];
//		itemsInCatalog = catalogo.getCatalog().toArray(itemsInCatalog);
//		String strCatalogo = this.g.toJson(itemsInCatalog);
//		System.out.println(strCatalogo);
//		response.getWriter().println(strCatalogo);
	}

	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		GruppoUtenti gruppo = (GruppoUtenti) session.getAttribute("gruppo");
		
		User currentUser = (User) session.getAttribute("currentUser");
		Cart cart = gruppo.getCarrello();
		
		if(currentUser.getUserName().compareTo("admin")==0)
		{
			String finalize = request.getParameter("finalize");
			String reset = request.getParameter("finalize");
			if(finalize!=null && finalize.length()>0)
			{
				String gid = request.getParameter("gid");
				Map<String, GruppoUtenti> gruppi = (Map<String, GruppoUtenti>) this.getServletContext().getAttribute("gruppi");
				GruppoUtenti group = gruppi.get(gid);
				group.setCarrello(null);
				gruppi.replace(gruppo.getId(), gruppo);
				this.getServletContext().setAttribute("gruppi", gruppi);
				session.setAttribute("success", 3);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");			
				rd.forward(request, response);
				return;
				
			}
			
			if(reset!=null && reset.length()>0)
			{
				String gid = request.getParameter("gid");
				Map<String, GruppoUtenti> gruppi = (Map<String, GruppoUtenti>) this.getServletContext().getAttribute("gruppi");
				GruppoUtenti group = gruppi.get(gid);
				group.setCarrello(null);
				gruppi.replace(gruppo.getId(), gruppo);
				this.getServletContext().setAttribute("gruppi", gruppi);
				session.setAttribute("success", 4);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");			
				rd.forward(request, response);
				return;
			}
		}
		
		if(cart == null)
		{
			session.setAttribute("success", 5);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");			
			rd.forward(request, response);
			return;
		}
		
		int sessionCounter =0;
		for(User u : gruppo.getUtenti())
		{
			if(u.equals(currentUser))
			{
				u.setSession(null);
			}
			
			if(u.getSession() == null)
			{
				sessionCounter++;
			}
			
		}
		
		if(gruppo.getUtenti().size() == sessionCounter)
		{
			//finalizza
			cart.empty();
			gruppo.setCarrello(cart);
			Map<String, GruppoUtenti> gruppi = (Map<String, GruppoUtenti>) this.getServletContext().getAttribute("gruppi");
			gruppi.replace(gruppo.getId(), gruppo);
			this.getServletContext().setAttribute("gruppi", gruppi);
			session.setAttribute("success", 2);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");			
			rd.forward(request, response);
			return;
		}
		else
		{
			session.setAttribute("success", 1);
			gruppo.setCarrello(cart);
			Map<String, GruppoUtenti> gruppi = (Map<String, GruppoUtenti>) this.getServletContext().getAttribute("gruppi");
			gruppi.replace(gruppo.getId(), gruppo);
			this.getServletContext().setAttribute("gruppi", gruppi);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");		
			rd.forward(request, response);
			return;
		}
			
	}
}
