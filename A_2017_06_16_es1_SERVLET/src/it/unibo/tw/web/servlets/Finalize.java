package it.unibo.tw.web.servlets;

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


import it.unibo.tw.web.beans.Cart;
import it.unibo.tw.web.beans.Catalogue;
import it.unibo.tw.web.beans.Group;
import it.unibo.tw.web.beans.Item;
import it.unibo.tw.web.beans.User;

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
	}

	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{ // gruppo utente in sessione - catalogo in applicazione - utente in sessione (da mandarmi)
		HttpSession session = request.getSession();
		Group gruppoUtente = (Group) session.getAttribute("mygroup");
		User currentUser = (User) session.getAttribute("currentUser");


		Cart cart = gruppoUtente.getCart();
		int check=0;
		int finTutti=0;
		int utentiOnline=0;
		int utentiOffline=0;
		int countSessChiuse=0;
		currentUser.setFinalized(true); //Setto finalizzato il carrello del mio utente
		for(User u : gruppoUtente.getUtenti()) {
			if(u.getSessione()!=null)  { //Se sono online e ho finito il carrello
				utentiOnline++;
				if(u.isFinalized()) {
					finTutti++;
				}
				}
			
			if(u.getSessione()==null  ) {
				utentiOffline++;
			
				if(!u.isFinalized()) {
					countSessChiuse++;
				}
				
			}
		}
		if (utentiOffline == countSessChiuse && utentiOffline!=0) {
			//Resetto il carrello
			gruppoUtente.getCart().empty();
			session.setAttribute("success", 3);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");			
			rd.forward(request, response);
			return;
		}
		
		if(finTutti == utentiOnline) {
			//TUTTI GLI UTENTI ONLINE HANNO FINALIZZATO IL CARRELLO
			Catalogue cat=(Catalogue)this.getServletContext().getAttribute("catalogo");
			Item[] itemsCart = cat.getItems().toArray(new Item[0]);
			Item[] carrelloUtente = gruppoUtente.getCart().getItems().toArray(new Item[0]);
			for( Item i : itemsCart) {
				for(Item j : carrelloUtente) {
					if(i.getDescription().equals(j.getDescription())) {
						i.setQuantity(i.getQuantity()-j.getQuantity());
					}
				}
			}
			gruppoUtente.setCart(new Cart());
			session.setAttribute("success", 2);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");			
			rd.forward(request, response);
			return;
			
		}else {
			//FINALIZZO SOLO IL MIO 
			session.setAttribute("success", 1);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");			
			rd.forward(request, response);
			return;
		}
		
		
		

		
		
		
	}
}
