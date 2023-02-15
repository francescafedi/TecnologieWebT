package servlets;

import java.io.File;
import java.io.IOException;
import java.util.Timer;

import javax.servlet.http.HttpServlet;
import javax.websocket.*;
import javax.websocket.server.*;

import com.google.gson.Gson;

import beans.OperationReq;
import beans.OperationResp;
import beans.Partita;

import java.util.*;


@ServerEndpoint("/actions")
public class ProvaWS{
	private Gson g;
	int count;
	Timer timer = new Timer();
	String pathfile="C:\\aaa\\ciao.txt";
	private static Partita partita= new Partita();
	@OnOpen
	public void open (Session session) throws InterruptedException {
		g=new Gson();	
		timer.scheduleAtFixedRate(new TimerTask() {
			File f=new File(pathfile);
			long oldL=f.length();
			  @Override
			  public void run() {
			   File f=new File(pathfile);
			   if(f.length()!=oldL) {
				   System.out.println("File cambiato");
				   oldL=f.length();
				   for(Session s : session.getOpenSessions()) {
							try {
							s.getBasicRemote().sendText("Modificato");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								System.out.println("Errore");
							}
						}
					}
			   }
			  
			}, 2*60*100, 2*60*100);

	}
	
	@OnClose
	public void close(Session session) {
		session.getOpenSessions().remove(session);
		partita.getGiocatori().remove(session);
					
	}
	
	
	@OnError
	public void onError (Throwable error) {
		System.out.println("Errore ritrovato: " + error);
		
		
	}
	
		
	@OnMessage
	public void handleMessage (String message, Session session) throws InterruptedException {	
		OperationReq richiesta=g.fromJson(message, OperationReq.class);

		OperationResp response= new OperationResp();
		if(richiesta.getAction().equalsIgnoreCase("login")) {
			if(richiesta.getUsername().equalsIgnoreCase("admin")) {
				response.setResult(150);
				response.setGiocatori(partita.getGiocatori().toArray(new Session[0])); //Restituisco la lista di sessioni attive
			}
		}
		if(richiesta.getAction().equalsIgnoreCase("play")) {
			if(!partita.isAvviata()) {
				partita.getGiocatori().add(session);
				response.setResult(1); //Giocatore aggiunto
			}else {
				response.setResult(-1); //Partita già avviata non è possibile iscriversi
			}
		}
		if(richiesta.getAction().equalsIgnoreCase("start")) {
			if(partita.getGiocatori().size()>1 && partita.getGiocatori().contains(session) && partita.isAvviata()==false) {
				partita.setAvviata(true);
				response.setResult(2); //Partita avviata
				response= new OperationResp();
				while(!partita.isFine() || partita==null) {
					Thread.sleep(6000);
					if(partita.isAvviata()) {
						int numero=estraiNumero();
						if(numero==100) {
							partita.setFine(true);
						}
						response.setNumero(numero);
						response.setResult(0); //numero da guardare
						if(partita.isFine()) {
							response.setResult(100);
						}
						for(Session s : partita.getGiocatori()) {
							try {
							String res=g.toJson(response).toString();
							s.getBasicRemote().sendText(res);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								System.out.println("Errore");
							}
						}
					}	
				}
				return;
			}else {
				response.setResult(-2); //non è possibile avviare la partita
			}
		}
		
		try {
			String res=g.toJson(response).toString();
			session.getBasicRemote().sendText(res);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Errore");
			}

		}
	
	public static int estraiNumero() {
        Random r = new Random();
        int num=r.nextInt(90);
        while(partita.getNumeriEstratti().contains(num)) {
        	if(partita.getNumeriEstratti().size()==90) {
        		return 100;
        	}
        	num=r.nextInt(90);
        }
        partita.getNumeriEstratti().add(num);
        return num;
    }

}
