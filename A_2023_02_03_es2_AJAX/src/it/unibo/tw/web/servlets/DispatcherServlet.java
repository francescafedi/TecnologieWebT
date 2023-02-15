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
import it.unibo.tw.web.beans.Domanda;
import it.unibo.tw.web.beans.Operation;


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
		List<Domanda> domande=new ArrayList<Domanda>(); 
		this.getServletContext().setAttribute("listaRisposte", domande);
	}
	
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
    	throw new ServletException("This servlet only supports HTTP GET REQUEST");
     }
    
    
	public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
		//{"matrice1":"[5 6 7] [8 9 6]","matrice2":"[5 6 7] [8 9 6]","numRighe":"2","numColonne":"3"}
		//Prendo i dati dal body della richeista
		List<Domanda> domande=(List<Domanda>)this.getServletContext().getAttribute("listaRisposte");
		Domanda newd=new Domanda();
		newd.setSession(request.getSession());
		boolean add=false;
		for(Domanda d : domande) {
			if(d.getSession().getId()==newd.getSession().getId()) {
				d.setCount(d.getCount()+1);
			}else {
				newd.setCount(1);
				add=true;
			}
		}
		if(add==true)domande.add(newd);
		add=false;
		if(domande.size()==0) {
			newd.setCount(1);
			domande.add(newd);
		}
		InputStream is= request.getInputStream();
		BufferedReader in= new BufferedReader(new InputStreamReader(is));
		String line=in.readLine();
		in.close();
		Richiesta richiesta = g.fromJson(line, Richiesta.class);
		int [][] m1Parte1 = new int[richiesta.getNumRighe()/2][richiesta.getNumColonne()];  
		int [][] m1Parte2 = new int[richiesta.getNumRighe()/2][richiesta.getNumColonne()];  
		int [][] m2Parte1 = new int[richiesta.getNumRighe()/2][richiesta.getNumColonne()];  
		int [][] m2Parte2 = new int[richiesta.getNumRighe()/2][richiesta.getNumColonne()];  
		 StringTokenizer st1 = new StringTokenizer(richiesta.getMatrice1()," ");  
		 StringTokenizer st2 = new StringTokenizer(richiesta.getMatrice2()," ");  
		for(int i =0 ; i<richiesta.getNumRighe()/2; i++) {
			for(int j=0;j<richiesta.getNumColonne();j++) {
				if(i<richiesta.getNumRighe()/2) {
					if(st1.hasMoreTokens()) {
						m1Parte1[i][j]=Integer.parseInt(st1.nextToken());
					}
				
				if(st2.hasMoreTokens()) {
					m2Parte1[i][j]=Integer.parseInt(st2.nextToken());
					}
				}}}
		for(int i =0 ; i<richiesta.getNumRighe()/2; i++) {
			for(int j=0;j<richiesta.getNumColonne();j++) {
					if(st1.hasMoreTokens()) {
						m1Parte2[i][j]=Integer.parseInt(st1.nextToken());
					}
				
				if(st2.hasMoreTokens()) {
						m2Parte2[i][j]=Integer.parseInt(st2.nextToken());
					}
				
				}
		}
		

		Operation op1=new Operation(m1Parte1,m2Parte1,richiesta.getNumRighe()/2, richiesta.getNumColonne());
		op1.start();
		Operation op2=new Operation(m1Parte2,m2Parte2,richiesta.getNumRighe()/2, richiesta.getNumColonne());
		op2.start();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int [][]result=new int[richiesta.getNumRighe()][richiesta.getNumColonne()];
		String result1 ="";
		for(int i =0 ; i<richiesta.getNumRighe()/2; i++) {
			for(int j=0;j<richiesta.getNumColonne();j++) {
					if(i<richiesta.getNumRighe()/2) {
						result1=result1+' '+ op1.getResult()[i][j];
					}
				}
		}
		for(int i =0 ; i<richiesta.getNumRighe()/2; i++) {
			for(int j=0;j<richiesta.getNumColonne();j++) {
					
						result1=result1+' '+ op2.getResult()[i][j];
					
				
				}
		}
		System.out.println(result1);
		
		
		Risposta oggettorisposta=new Risposta();
		oggettorisposta.setResult(result1);
	//	List<Risposta> listaRisposte=(List<Risposta>)this.getServletContext().getAttribute("listaRisposte");
		//listaRisposte.add(oggettorisposta);
		String res=g.toJson(oggettorisposta);
		response.getWriter().println(res);

    }

}
