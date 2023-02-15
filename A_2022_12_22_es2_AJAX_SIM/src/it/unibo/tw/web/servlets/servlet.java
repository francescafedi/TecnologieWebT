package it.unibo.tw.web.servlets;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import it.unibo.tw.web.beans.Cartella;
import it.unibo.tw.web.beans.FileServer;
import it.unibo.tw.web.beans.Risposta;

public class servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Gson g;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
			super.init(config);
			g=new Gson();
			//Crea una variabile di sessione con la lista dei file che si mappa inizialmente
			Cartella cart=new Cartella();
			 //imposto il numero di file nella cartella saticamente per questioni di tempo
			List<FileServer> files=new ArrayList<FileServer>();
			File cartella=new File("C:\\aaa");
			for(File f: cartella.listFiles()) {
				FileServer fs=new FileServer();
				fs.setNome(f.getName());
				fs.setScaricato(false);
				
			     
				files.add(fs);
			}
			cart.setFiles(files);
			cart.setSize(cartella.listFiles().length);
			this.getServletContext().setAttribute("Cartella", cart);
	}
	
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
    	//Ricezione della richiesta
    	Cartella cart=(Cartella)this.getServletContext().getAttribute("Cartella"); //Prendo la cartella dei file
    	//Elaborazione
    	List<Risposta> risp=new ArrayList<Risposta>();
    	File cartella=new File("C:\\aaa");
    	for(FileServer f : cart.getFiles()) {
    		if(f.isScaricato()==false) {
    			f.setScaricato(true);
    			for(File fs : cartella.listFiles()) {
    				if(fs.getName().equals(f.getNome())) {
    					Risposta r=new Risposta();
    					r.setNomeCanzone(f.getNome());
    					r.setLunghezza(fs.getTotalSpace());
    					 int index = fs.getName().lastIndexOf('.');
    					String extension = fs.getName().substring(index + 1);
    					
    					System.out.println("File extension is " + extension);
    					r.setFormato(extension);
    					FileInputStream sorgente = new FileInputStream(fs.getAbsolutePath());
    			        int singloByte;
    			        r.setContenuto(sorgente.readAllBytes());
    					//Compilo la risposta
    					risp.add(r);
    				}
    			}
    			
    		}
    	}
    			
    	//Invio della risposta
    	String res=g.toJson(risp);
    	response.getWriter().println(res);
    }
    
    
	public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
    	
    }

}
