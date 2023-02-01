package it.unibo.tw.web;

import it.tecnologieweb.app.HelloWorld;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession(true); //prendi la sessione esistente
		String nome=session.getAttribute("testo").toString();
		restituisciHtml(out,nome);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome=request.getParameter("testo"); //Prendo dalla post
		HttpSession session=request.getSession(true);
		session.setAttribute("testo", nome);
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
