package it.unibo.tw.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// esempio di memorizzazione dati tramite passaggio di parametri in GET 
// infatti nella barra degli indirizzi appare in coda all'url il parametro "something"
// e.g. http://localhost:8080/02b_TecWeb_solved/LearningServlet1?something=123

public class LearningServlet1 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private static final String type = "hidden field";
	
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {

    	// retrieve former value
    	String something = request.getParameter("something");
    	if ( something == null ) something = "";
    	
    	PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
	    
        out.println("<title>Learning Servlet - " + type + "</title>");
	    out.println("<link rel=\"stylesheet\" href=\"styles/default.css\" type=\"text/css\"></link>");
        
	    out.println("</head>");
        out.println("<body>");
        
        // default action targets the current URL
        // out.println("<form method=\"post\" action=\"learning1\" />");
        out.println("<form method=\"post\" />");
        out.println("Teach me something I can remember:<br/><br/>");
        out.println("<input type=\"text\" name=\"something\" value=\"" + something + "\" />");
        out.println("<input type=\"submit\" value=\"teach me!\"/>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");

    }
    
	public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {

    	// retrieve former value
    	String something = request.getParameter("something");
    	if ( something == null ) something = "";
    	
    	PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
	    
        out.println("<title>Learning Servlet - " + type + "</title>");
	    out.println("<link rel=\"stylesheet\" href=\"styles/default.css\" type=\"text/css\"></link>");
        
	    out.println("</head>");
        out.println("<body>");
        
        out.println("I've just learned that: <i>" + something + "</i>");
        // default action targets the current URL
        out.println("<form method=\"get\" />");
        
        // store the value as a form hidden field
        out.println("<input type=\"hidden\" name=\"something\" value=\"" + something + "\" />");
        
        out.println("<input type=\"submit\" value=\"do it again!\"/>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");

    }

}
