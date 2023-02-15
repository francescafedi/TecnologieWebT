package it.unibo.tw.web.servlets;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import it.unibo.tw.web.beans.*;

public class servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Gson g;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
			super.init(config);
			g=new Gson();
	}
	
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {

    }
    
    
	public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
    	//Ricezione della richiesta
		g=new Gson();

		InputStream is= request.getInputStream();
		BufferedReader in= new BufferedReader(new InputStreamReader(is));
		String line=in.readLine();
		in.close();
		System.out.println(line);
		int result=0;
		Offerta richiesta = g.fromJson(line, Offerta.class);

		//Elaborazione
		//controllo
		Asta astaOld=(Asta)this.getServletContext().getAttribute("Asta");
		for(Regalo r : astaOld.getRegali()) {
			if(r.isAsta()) {
				r.getOfferte().add(richiesta);
			}
		}
		this.getServletContext().setAttribute("Asta",(astaOld));
		try {
			Thread.sleep(12000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Asta astaNew=(Asta)this.getServletContext().getAttribute("Asta");
		if(astaOld==astaNew) {
			//Nessuna nuoca richiesta
			for(Regalo r : astaOld.getRegali()) {
				if(r.isAsta() && r.getAcquirente()==null) {
					String max;
					Offerta old=new Offerta();
					old.setDeanro(0);
					for(Offerta o : r.getOfferte()) {
						if(old.getDeanro()<o.getDeanro()) {
							max=o.getUtente().getUsername();
						}
					}//nella jsp 1 manca la scelta random del nuovo profotto da mttere in vendita
					
				}
			}
		}
		
		//Invio della risposta
		
    }

}
