package it.unibo.tw.web.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import com.google.gson.Gson;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import it.unibo.tw.web.beans.Turista;
import it.unibo.tw.web.beans.Group;
import it.unibo.tw.web.beans.Response;
import it.unibo.tw.web.beans.Richiesta;
import it.unibo.tw.web.beans.Attrazione;

public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Gson g;
	@Override
	public void init(ServletConfig config) throws ServletException {
			super.init(config);

			g=new Gson();
			//Creo la lista di utenti registrati
			Map<String,Attrazione> attrazioni=new HashMap<String,Attrazione>();
			Attrazione u=new Attrazione();
			u.setNome("Garisenda");
			u.setDescrizione("Descrizione garisenda");
			u.setPosX(50);
			u.setPosY(0);
			attrazioni.put(u.getNome(), u);
			u=new Attrazione();
			u.setNome("Asinelli");
			u.setDescrizione("Descrizione asinelli");
			u.setPosX(100);
			u.setPosY(0);
			attrazioni.put(u.getNome(), u);
			u=new Attrazione();
			u.setNome("Casa");
			u.setDescrizione("Descrizione casa");
			u.setPosX(50);
			u.setPosY(50);
			attrazioni.put(u.getNome(), u);
			u=new Attrazione();
			u.setNome("Biblioteca");
			u.setDescrizione("Descrizione biblioteca");
			u.setPosX(80);
			u.setPosY(0);
			attrazioni.put(u.getNome(), u);
			u=new Attrazione();
			u.setNome("Fornaio");
			u.setDescrizione("Descrizione fornaio");
			u.setPosX(20);
			u.setPosY(0);
			attrazioni.put(u.getNome(), u);
			this.getServletContext().setAttribute("attrazioni", attrazioni);
			Map<String,Turista> turisti=new HashMap<String,Turista>();
			this.getServletContext().setAttribute("turisti", turisti);
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long K=100;
		Map<String,Attrazione> attrazioni=(Map<String,Attrazione>)this.getServletContext().getAttribute("attrazioni");
		Map<String,Turista> turisti=(Map<String,Turista>)this.getServletContext().getAttribute("turisti");
		

		InputStream is= request.getInputStream();
		BufferedReader in= new BufferedReader(new InputStreamReader(is));
		String line=in.readLine();
		in.close();
		System.out.println(line);
		int result=0;
		int count=0;
		List<Attrazione> risposta=  new ArrayList<Attrazione>();
		Richiesta richiesta = g.fromJson(line, Richiesta.class);
		//elaboro richiesta
		for(Attrazione a : attrazioni.values()) {
			if(Integer.toUnsignedLong(a.getPosX()-richiesta.getX())<K && Integer.toUnsignedLong(a.getPosY()-richiesta.getY())<K) {
				for(Turista t : turisti.values()) {
					if(Integer.toUnsignedLong(a.getPosX()-t.getX())<100 && Integer.toUnsignedLong(a.getPosY()-t.getY())<100) {
						count++;
					}
				}
				if(count<10) {
						risposta.add(a);
					}
				}
			count=0;
			}
		
		Response risp= new Response();
		risp.setRisposta(risposta);
		risp.setType(richiesta.getType());
		String res=g.toJson(risp);
		System.out.println(res);
		response.getWriter().println(res);
	}
}
