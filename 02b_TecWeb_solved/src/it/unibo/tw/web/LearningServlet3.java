package it.unibo.tw.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// esempio di memorizzazione dati sessione lato client,
// viene salvato in locale l'attributo "something" che contiene direttamente in chiaro i dati immessi dall'utente
// accessibile tramite devTools (Chrome) Application/Cookies

public class LearningServlet3 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private static final String type = "cookie"; 
	
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {

    	// retrieve former value
    	String something = null;
    	Cookie[] cookies = request.getCookies();
    	if ( cookies != null && cookies.length > 0 ) {
    		for ( Cookie cookie : cookies ) {
        		if ( cookie.getName().equals("something") ) {
        			something = cookie.getValue();
        			break;
        		}
    		}
    	}
    	if ( something == null ) something = "";
    	
    	PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
	    
        out.println("<title>Learning Servlet - " + type + "</title>");
	    out.println("<link rel=\"stylesheet\" href=\"styles/default.css\" type=\"text/css\"></link>");
        
	    out.println("</head>");
        out.println("<body>");
        
        // default action targets the current URL
        // out.println("<form method=\"post\" action=\"learning3\" />");
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

        // store the value as a cookie
        Cookie cookie = new Cookie("something",something);
        response.addCookie(cookie);
        
        out.println("<input type=\"submit\" value=\"do it again!\"/>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");

    }

}
