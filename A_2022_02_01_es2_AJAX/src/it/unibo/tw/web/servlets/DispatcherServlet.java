package it.unibo.tw.web.servlets;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import it.unibo.tw.web.beans.Richiesta;
import it.unibo.tw.web.beans.Risposta;


import java.time.Duration;
import java.time.LocalTime;


public class DispatcherServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Gson g;
	@Override
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		g=new Gson();		
		List<Risposta> risposte=new ArrayList<Risposta>(); 
		this.getServletContext().setAttribute("listaRisposte", risposte);
	}
	
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
    	throw new ServletException("This servlet only supports HTTP GET REQUEST");
     }
    
    
	public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
		LocalTime timeStart=LocalTime.now(); 
		//Prendo i dati dal body della richeista
		InputStream is= request.getInputStream();
		BufferedReader in= new BufferedReader(new InputStreamReader(is));
		String line=in.readLine();
		in.close();
		Richiesta richiesta = g.fromJson(line, Richiesta.class);
		String testo="prova di testo super lungo mega blablab labalbalbbla";
		String porzioneTesto=new String();
		if(richiesta.getConto()==1) {
			porzioneTesto=testo;
		}else {
		int indextemp=testo.length()/richiesta.getConto();
		int beginIndex =0, endIndex=0;
		for(int i=0; i<richiesta.getConto();i++) {
			if(i==0) {
				beginIndex=0;
				char temp=testo.charAt(indextemp+1);
				if((temp=testo.charAt(indextemp+1))!=' ') {
					while((temp=testo.charAt(indextemp+1))!=' ') {
						indextemp++;
					}
				}
				endIndex=indextemp+1;
			}else {
				beginIndex=endIndex;
				endIndex=beginIndex+indextemp;
				if(endIndex>testo.length()) {
					endIndex=testo.length();
				}
			}
			if(richiesta.getId()==i) {
			porzioneTesto=testo.substring(beginIndex, endIndex);
			System.out.println("PARTE "+i);
			System.out.println(porzioneTesto);
			}
		}
		}
		//ho il testo (porzioneTesto) e la parola da cercare e conteggiare
		int times = 0;
        for (int i = 0; i < porzioneTesto.length(); i++) {
            if (porzioneTesto.substring(i).startsWith(richiesta.getParola())) {
                times ++;
            }
        }

		LocalTime timeFinish=LocalTime.now();
		long tempoImpiegato= (Duration.between(timeStart, timeFinish).toNanos());

		Risposta oggettorisposta=new Risposta();
		oggettorisposta.setNumOccorenze(times);
		oggettorisposta.setTempoImpiegato(tempoImpiegato);
		oggettorisposta.setId(richiesta.getId());
		List<Risposta> listaRisposte=(List<Risposta>)this.getServletContext().getAttribute("listaRisposte");
		listaRisposte.add(oggettorisposta);
		String res=g.toJson(oggettorisposta);
		response.getWriter().println(res);
		
    }

}
