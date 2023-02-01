package it.unibo.tw.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// esempio di memorizzazione dati sessione lato servitore,
// in particolare dentro il server container che nel nostro caso è Tomcat
// viene salvato in locale solo JSESSIONID che ha lo scopo di abbinare l'utente alla sua sessione personale
// accessibile tramite devTools (Chrome) Application/Cookies

public class LearningServlet2 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private static final String type = "session"; 
	
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {

    	// retrieve former value
    	String something = (String) request.getSession().getAttribute("something");
    	if ( something == null ) something = "";
    	
    	PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
	    
        out.println("<title>Learning Servlet - " + type + "</title>");
	    out.println("<link rel=\"stylesheet\" href=\"styles/default.css\" type=\"text/css\"></link>");
        
	    out.println("</head>");
        out.println("<body>");
        
        // default action targets the current URL
        // out.println("<form method=\"post\" action=\"learning2\" />");
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

        // store the value as a session attribute
        request.getSession().setAttribute("something", something );
        
        out.println("<input type=\"submit\" value=\"do it again!\"/>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");

    }

}
