package servlets;


import java.io.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import beans.*;


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
       

	InputStream is= request.getInputStream();
	BufferedReader in= new BufferedReader(new InputStreamReader(is));
	String line=in.readLine();
	in.close();
	System.out.println(line);
	int result=0;
	String richiesta = g.fromJson(line, String.class);
	StringTokenizer st1 = new StringTokenizer(richiesta," ");  
	int riga1=0;
	int riga2=0;
	int riga3=0;
	for(int i =0 ; i<9; i++) {
		if(st1.hasMoreTokens()) {
			if(i<3) {
					riga1+=Integer.parseInt(st1.nextToken());
			}
			if(i>2 && i<6) {
				riga2+=Integer.parseInt(st1.nextToken());
			}
			if(i>5) {
				riga3+=Integer.parseInt(st1.nextToken());
			}
		}}
	
	
	Risposta resp=new Risposta();
	if(riga1==riga2 && riga2==riga3) {
		resp.setRisultato(true);
	}else {
		resp.setRisultato(false);
	}
	resp.setSomma(riga1+riga2+riga3);
	String res=g.toJson(resp);
	response.getWriter().println(res);
	}
	
}
