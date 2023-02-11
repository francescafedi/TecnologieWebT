package servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.websocket.*;
import javax.websocket.server.*;

import com.google.gson.Gson;

import beans.OperationReq;
import beans.OperationResp;


import java.util.*;


@ServerEndpoint("/actions")
public class ProvaWS{
	private Gson g;
	int count;
	@OnOpen
	public void open (Session session) {
		g=new Gson();	
	}
	
	@OnClose
	public void close(Session session) {
		
	}
	
	@OnError
	public void onError (Throwable error) {
		System.out.println("Errore ritrovato: " + error);
		
		
	}
	
	@OnMessage
	public void handleMessage (String message, Session session) {
		OperationResp response=new OperationResp();
		OperationReq request = g.fromJson(message, OperationReq.class);		
		
		if(request.getTypeOperation()!=1) {
		if(!session.getUserProperties().containsKey("start")) {
			
		
		int countSession=0;
		for(Session s : session.getOpenSessions()) {
			countSession++;
		}
		if(countSession<4) {
			response.setResult(-1); // non ci sono abbastanza giocatori
			String res=g.toJson(response).toString();

			try {
				session.getBasicRemote().sendText(res);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}else {
			response.setResult(1);
			boolean start=(boolean)session.getUserProperties().containsKey("start");
			if(start) {
				response.setResult(-2); // partita già avviata non puoi inserire una nuova parola
				String res=g.toJson(response).toString();
				try {
					session.getBasicRemote().sendText(res);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}else { //avvi partita
				// Per tutte le sessioni metti start = true
				Set<Session> giocatori;
				if(session.getUserProperties().get("giocatori")==null) {
					giocatori= new HashSet<Session>();
					giocatori.add(session);
					
				}else {
					giocatori=(Set<Session>)session.getUserProperties().get("giocatori");
					giocatori.add(session);
				}
				session.getUserProperties().put("giocatori", giocatori);
				Set<Session> sperem=(Set<Session>) session.getUserProperties().get("giocatori");
				int turno=0;
				response.setParola(request.getParola());
				String result=g.toJson(response).toString();
				for(Session s : session.getOpenSessions()) {
					System.out.println("1."+session.getId());
					s.getUserProperties().put("start", true);
					s.getUserProperties().put("parolaGioco", request.getParola());
					s.getUserProperties().put("capo", session);
					s.getUserProperties().put("giocatori", giocatori);
					if(s.getId()!=session.getId() && turno==0) {
						turno=1;
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
		}
		}else {


		 Set<Session> giocatori=(Set<Session>)session.getUserProperties().get("giocatori");
			giocatori.add(session);

		int turno=0;
		//Modifico la parola...
		response.setParola(request.getParola());
		String result=g.toJson(response).toString();
		int aperte=session.getOpenSessions().size();
		for(Session s : session.getOpenSessions()) {
			s.getUserProperties().put("giocatori", giocatori);
				if(!giocatori.contains(s) && turno==0) {
					turno=1;
					try {
						response.setResult(1);
					String res=g.toJson(response).toString();
					s.getBasicRemote().sendText(res);
					return;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Errore");
					}
			}
			
		}
		if(giocatori.size()==aperte) {
			try {
				response.setResult(5);
			String res=g.toJson(response).toString();
			Session capo= (Session)session.getUserProperties().get("capo");
			capo.getBasicRemote().sendText(res);
			//Gioco finito
			return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Errore");
			}

}}}else {
	//Aministratore
	for(Session s : session.getOpenSessions()) {
		
	}
}
		
	
		}
	private static char randomChar() {
        Random r = new Random();
        return (char)(r.nextInt(26) + 'A');
    }

}
