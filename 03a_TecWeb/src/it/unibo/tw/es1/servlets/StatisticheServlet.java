
package it.unibo.tw.es1.servlets;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unibo.tw.es1.beans.Articolo;
import it.unibo.tw.es1.beans.InsiemeDiArticoli;


public class StatisticheServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		int firstDay=Integer.parseInt(req.getParameter("firstDay"));
		int lastDay=Integer.parseInt(req.getParameter("lastDay"));
		InsiemeDiArticoli articoli=(InsiemeDiArticoli)this.getServletContext().getAttribute("merceVenduta"); //Recuperiamo insieme di articoli
		Vector<Articolo> merce=articoli.getMerce();
		float tot=0;
		if(!req.getParameter("id").isEmpty()) { 	//Caso con id prodotto
			String id=req.getParameter("id");
			for(Articolo art:merce) {
				if(art.getId()==id) {
					int day= art.getDay();
					if(day>=firstDay && day<=lastDay) {
						tot=tot+(art.getAmount()*art.getPrice());
					}
				}
			}
		}else { 									//Caso generale senda id prodotto
			for(Articolo art : merce) {
				int day= art.getDay();
				if(day>=firstDay && day<=lastDay) {
					tot=tot+(art.getAmount()*art.getPrice());
				}
			}
		}
		
		Cookie cookie=new Cookie("guadagno",""+tot);
		resp.addCookie(cookie);
		Cookie cookief=new Cookie("firstDay",""+firstDay);
		resp.addCookie(cookief);
		Cookie cookiel=new Cookie("lastDay",""+lastDay);
		resp.addCookie(cookiel);
		if(!req.getParameter("id").isEmpty()){
			Cookie cookiei=new Cookie("id",""+req.getParameter("id"));
			resp.addCookie(cookiei);	
		}
		
		req.setAttribute("guadagnoRichiestaAttuale", tot);  //Setto dentro la request un attributo con scope application
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/statistiche.jsp"); //é la servlet che inoltra la gestione della risposta alla jsp
		dispatcher.forward(req, resp);
	}
	
}
