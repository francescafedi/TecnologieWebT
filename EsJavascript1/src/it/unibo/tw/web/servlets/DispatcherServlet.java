package it.unibo.tw.web.servlets;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import it.unibo.tw.web.beans.Counter;

public class DispatcherServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Gson g;
	@Override
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		g=new Gson();
		this.getServletContext().setAttribute("counter", new Counter());
		this.getServletContext().setAttribute("index", 0);
		
	}
	
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
    	
    	String fileName=request.getParameter("file");
    	String testo=request.getParameter("textarea");
    	Counter c1=new Counter();
    	Counter counterGlobal=(Counter)this.getServletContext().getAttribute("counter");
    	int index=(int)this.getServletContext().getAttribute("index");
    	if(testo!=null) {
    		c1.setTextUpperCase(testo);
    		for(int i=0;i<testo.length(); i++) {
    			String str=(testo.charAt(i)+"").toUpperCase();
    			String originale= testo.charAt(i)+"";
    			if(!str.equals(originale)){
    				//siamo in presenza di una lettera minuscola originariamente
    				c1.setCount(c1.getCount()+1);
    			}
    		}
    		synchronized(this) {
    		File f=new File("C:\\aaa\\"+fileName);
    		if(f.exists()) {
    			FileWriter br=new FileWriter(f); 
    			br.append(c1.getTextUpperCase());
    			br.close();
    		}    		
    		int old=c1.getCount(); //conto attuale

    		c1.setCount(old+counterGlobal.getCount()); //Aggiorno il conto totale
    		

    		if(index==0) { //Dobbiamo elaborare la prima metà
    		c1.setFinale(0);
    		index++;
    		this.getServletContext().setAttribute("index", index);
    		this.getServletContext().setAttribute("counter", c1 );
    		}
    		else {
    			c1.setFinale(1);
    			this.getServletContext().setAttribute("counter", new Counter());
    			this.getServletContext().setAttribute("index", 0);
    		}
    		String res=g.toJson(c1);
    		response.getWriter().println(res);
    		}
    	}
     }
    
    
	public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
		throw new ServletException("This servlet only supports HTTP GET REQUEST");
    }

}
