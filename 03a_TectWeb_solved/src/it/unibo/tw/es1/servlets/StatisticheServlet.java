
package it.unibo.tw.es1.servlets;


import it.unibo.tw.es1.beans.Articolo;
import it.unibo.tw.es1.beans.InsiemeDiArticoli;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class StatisticheServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String richiesta = req.getParameter("req");
		if( richiesta!=null && richiesta.equals("calcola") ){
			InsiemeDiArticoli vendite = (InsiemeDiArticoli)this.getServletContext().getAttribute("merceVenduta");
			
			// recupero i parametri della ricerca
			Vector<Articolo> articoliVenduti = vendite.getMerce();
			String id = req.getParameter("id");
			int firstDay = Integer.parseInt(req.getParameter("firstDay"));
			int lastDay = Integer.parseInt(req.getParameter("lastDay"));
			
			// effettuo il calcolo del guadagno
			float guadagno = 0;
			for(int i=0; i<articoliVenduti.size(); i++){
				Articolo ar = articoliVenduti.elementAt(i);
				if( id.equals("") || id.equals(ar.getId())){
					if( firstDay<=ar.getDay() && lastDay>=ar.getDay() ){
						guadagno += ar.getAmount()*ar.getPrice();
					}
				}
			}
			
			// inserisco il risultato in vari cookie
			Cookie cookie1 = new Cookie("id", id);
			Cookie cookie2 = new Cookie("firstDay", ""+firstDay);
			Cookie cookie3 = new Cookie("lastDay", ""+lastDay);
			Cookie cookie4 = new Cookie("guadagno", ""+guadagno);
			cookie1.setMaxAge(60*60); // 1 ora
			cookie2.setMaxAge(60*60); // 1 ora
			cookie3.setMaxAge(60*60); // 1 ora
			cookie4.setMaxAge(60*60); // 1 ora
	        resp.addCookie(cookie1);
	        resp.addCookie(cookie2);
	        resp.addCookie(cookie3);
	        resp.addCookie(cookie4);
	        
	        // inserisco il risultato nella richiesta che viene passata alla JSP
	        req.setAttribute("guadagnoRichiestaAttuale", guadagno);
		}

		// passo il controllo alla JSP
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/statistiche.jsp");
		dispatcher.forward(req, resp);
		
	}
	
}
