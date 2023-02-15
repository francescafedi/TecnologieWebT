package servlets;

import java.io.File;
import java.io.IOException;
import java.util.Timer;

import javax.servlet.http.HttpServlet;
import javax.websocket.*;
import javax.websocket.server.*;

import com.google.gson.Gson;

import beans.OperationReq;
import beans.Risposta;
import beans.Partita;

import java.util.*;


@ServerEndpoint("/actions")
public class ProvaWS{
	private Gson g;
	int count;
	@OnOpen
	public void open (Session session) throws InterruptedException {
		this.g=new Gson();

	}
	
	@OnClose
	public void close(Session session) {
							
	}
	
	
	@OnError
	public void onError (Throwable error) {
		System.out.println("Errore ritrovato: " + error);
		
		
	}
	
		
	@OnMessage
	public void handleMessage (String message, Session session) throws InterruptedException {	
		System.out.println(message);
		OperationReq richiesta=g.fromJson(message, OperationReq.class);		
		for(Session s : session.getOpenSessions()) {
			if(s.getId()!=session.getId()) {
				try {
				String res=g.toJson(richiesta);
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
