package servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.websocket.*;
import javax.websocket.server.*;

import com.google.gson.Gson;

import beans.OperationReq;
import beans.OperationResp;
import beans.UpdateReq;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/actions2")
public class ProvaWS_2 {
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
		// update=new UpdateReq();
		OperationResp response=new OperationResp();
		UpdateReq update = g.fromJson(message, UpdateReq.class);
		
		if(update.getOp().equals("op1")) {
			response.setOp1(update.getValore());
		}else if(update.getOp().equals("op2")) {
			response.setOp2(update.getValore());
		}else if(update.getOp().equals("risultato")) {
			response.setRisultato(update.getValore());
		}
		//invio
				String result=g.toJson(response).toString();
				try {
					for(Session sess : session.getOpenSessions()) {
						if(sess.getId() != session.getId())
						sess.getBasicRemote().sendText(result);
						System.out.println("Risposta inviata a"+sess);
					}		
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Errore");
				}
		
		
	}
	
	


}
