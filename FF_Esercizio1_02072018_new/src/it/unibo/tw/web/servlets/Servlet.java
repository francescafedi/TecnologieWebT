package it.unibo.tw.web.servlets;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import it.unibo.tw.web.beans.Counter;



public class Servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Gson g;
	@Override
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		g=new Gson();		
	}
	
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
    	LocalTime timeStart=LocalTime.now(); 
    	int length=Integer.parseInt(request.getParameter("length"));
    	List<String> files= new ArrayList<String>();
    	for(int i=0; i<length;i++) {
    		String index="p"+i;
    		String elemento=request.getParameter(index);
    		files.add(elemento); //Ottengo una lista di file
    	}
    	//Conto per ogni file il numero di caratteri
       	Counter c=new Counter();
    	for(String file : files) { //Per ogni file selezionato lo apro
    		
    		File f=new File(this.getServletContext().getInitParameter("dirurl")+file);
    		if(f.exists()) {
    			BufferedReader br=new BufferedReader(new FileReader(f)); 
    			String temp=null;
    			String testo=null;
    			while((temp=br.readLine())!=null) {
    				testo+=temp;
    			}
    			for(int i=0;i<testo.length(); i++) {
        			String str=(testo.charAt(i)+"").toUpperCase();
        			String originale= testo.charAt(i)+"";
        			if(!str.equals(originale)){
        				//siamo in presenza di una lettera minuscola originariamente
        				c.setCount(c.getCount()+1);
        			}
        		}		
    			br.close();
    		} 
    	}
    	LocalTime timeFinish=LocalTime.now();
    	c.setTempo(Duration.between(timeStart, timeFinish).toNanos());
    	String res=g.toJson(c);
		response.getWriter().println(res);
		
     }
    
    
	public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
		throw new ServletException("This servlet only supports HTTP GET REQUEST");
    }

}
