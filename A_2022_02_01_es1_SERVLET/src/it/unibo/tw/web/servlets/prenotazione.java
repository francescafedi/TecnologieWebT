package it.unibo.tw.web.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
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
import it.unibo.tw.web.beans.User;
import it.unibo.tw.web.beans.Catalogue;
import it.unibo.tw.web.beans.Prenotazione;
import it.unibo.tw.web.beans.Request;

public class prenotazione extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String homeURL = null;
	private Gson g;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
			super.init(config);
			g=new Gson();
			Catalogue listap=new Catalogue();
			this.getServletContext().setAttribute("listaPrenotazioni", listap);
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Catalogue listaPrenotazioni=(Catalogue)this.getServletContext().getAttribute("listaPrenotazioni");
		HttpSession session = request.getSession();
		//Recupero i dati dalla richiesta
		InputStream is= request.getInputStream();
		BufferedReader in= new BufferedReader(new InputStreamReader(is));
		String line=in.readLine();
		in.close();
		System.out.println(line);
		int result=0;
		Request richiestaPrenotazione = g.fromJson(line, Request.class);
		for(Prenotazione p : listaPrenotazioni.getItems()) {
			if(richiestaPrenotazione.getNumerocampo()==p.getNumerocampo()) {
				if(richiestaPrenotazione.getDatag()==p.getDatag() && richiestaPrenotazione.getOrario()==p.getOrario()) {					
						result=-1; //Richiesta già presente					
				}
			}
		}
		if(result==0) {			
			Prenotazione newP=new Prenotazione();
			newP.setNumerocampo(richiestaPrenotazione.getNumerocampo());
			newP.setDatag(richiestaPrenotazione.getDatag());
			newP.setOrario(richiestaPrenotazione.getOrario());
			newP.setTipologia(richiestaPrenotazione.getTipologia());	
			newP.setDataoraprenotazione(LocalDate.now());
			User curent=(User)session.getAttribute("currentUser");
			newP.getGiocatori().add(curent);
			newP.setDefinitiva(false);
			listaPrenotazioni.getItems().add(newP);
			result=1;
		}
		String res=g.toJson(result);
		response.getWriter().println(res);
		return;
		
	}
	
}
