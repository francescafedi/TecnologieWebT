package it.unibo.tw.web;

import it.tecnologieweb.app.HelloWorld;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Cookie[] cookies= request.getCookies() ;
		String nome=new String();
		if(cookies.length>0) {
			for(int i=0;i<cookies.length;i++) {
				if(cookies[i].getName()=="testo") {
					Cookie c= cookies[i];
					nome= c.getValue();
				}
			}		
		}
		restituisciHtml(out,nome);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie c=new Cookie("testo",request.getParameter("testo")); //Prendo il valore passato dal form
		c.setSecure(false);
		c.setMaxAge(-1);
		response.addCookie(c);
		String nome=c.getValue();
		PrintWriter out = response.getWriter();
		restituisciHtml(out,nome);	
	}

	public void restituisciHtml(PrintWriter out,String nome) {
        out.println("<html>");
	        out.println("<head>");
	        out.println("<title>Esercizio</title>");
	        out.println("</head>");
	        out.println("<body>");		
	        out.println("\n" + 
	        		"<form ation=\"http://localhost:8080/02_TecWeb/cookie\" method=\"post\">\n" + 
	        		"<p>\n" + 
	        		"Inserisci <input type=\"text\" name=\"testo\" placeholder="+nome+">\n" + 
	        		"</p>\n" + 
	        		"<button type=\"submit\">Invia</button>\n" + 
	        		"</form>");
	        out.println("</body>");
	        out.println("</html>");
	}
}
