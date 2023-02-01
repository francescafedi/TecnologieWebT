package it.unibo.tw.web.servlets;

import it.unibo.tw.web.beans.FeedDb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class SuggesterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() {
		FeedDb feedDb = (FeedDb)this.getServletContext().getAttribute("feedDb");
		if ( feedDb == null ){
			// creiamo un novo FeedDb solo se non esiste ancora
			feedDb = new FeedDb();
			this.getServletContext().setAttribute("feedDb", feedDb);
		}
	}
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
	throws ServletException, IOException {

		String startsWith = request.getParameter("startsWith");
		if ( startsWith == null || startsWith.length() == 0 ){
			return;
		}
		
		FeedDb feedDb = (FeedDb)this.getServletContext().getAttribute("feedDb");
		List<String> categories = feedDb.getCategories(startsWith);
		if ( categories == null || categories.size() == 0 ){
			return;
		}
		
		String category = categories.get(0);

		PrintWriter out = response.getWriter();
		out.write(category);

	}
}
