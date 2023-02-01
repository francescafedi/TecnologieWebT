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
		
		if(session.getUserProperties().containsKey("numSessione")) {
			count=(int) session.getUserProperties().get("numSessione");
		}else {
			count=0;
		}
		if(count<100) {
			count=count+1;
			session.getUserProperties().put("numSessione",count);
		System.out.println("Count = "+ count);
		
		OperationReq request = g.fromJson(message, OperationReq.class);
		
		if(request.getOperazione().equals("somma")) {
			response.setRisultato(request.getOp1()+request.getOp2());
		}else if(request.getOperazione().equals("sottrazione")) {
			response.setRisultato(request.getOp1()-request.getOp2());
		}else if(request.getOperazione().equals("moltiplicazione")) {
			response.setRisultato(request.getOp1()*request.getOp2());
		}else if(request.getOperazione().equals("divisone")) {
			response.setRisultato(request.getOp1()/request.getOp2());
			if(request.getOp2()==0) {
				response.setSuccess(false);
			}
		}
		response.setValid(true);
		
		Double oldResult=(Double)session.getUserProperties().get("oldResult");
		
		for(Session s : session.getOpenSessions()) {
			if(s.getUserProperties().containsKey("oldResult")) {
				response.setOldRis(oldResult);
			}
			s.getUserProperties().put("oldResult", response.getRisultato());
		}
		
		

		}else {
			response.setValid(false);
			System.out.println("Numero di sessioni superate");
			//close(session);
		}
		//invio
				String result=g.toJson(response).toString();
				try {
					for(Session sess : session.getOpenSessions()) {
					sess.getBasicRemote().sendText(result);
					System.out.println("Risposta inviata");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Errore");
				}
		
		
	}
	
	
	

}
