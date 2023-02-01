package it.unibo.tw.web.servlets;

import java.io.IOException;
import it.unibo.tw.web.beans.Feed;
import it.unibo.tw.web.beans.FeedDb;
import java.util.List;
import java.util.ArrayList;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

public class FeedJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Gson gson;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void init() {
    	FeedDb feedDb= (FeedDb)this.getServletContext().getAttribute("feedDb");
    	if(feedDb==null) {
    		feedDb=new FeedDb();
    		this.getServletContext().setAttribute("feedDb", feedDb);
    	}
    	gson=new Gson();
    }

    public void service (ServletRequest request, ServletResponse response)
   throws ServletException, IOException {
    	String categoria=request.getParameter("category");
    	FeedDb feedDb=(FeedDb) getServletContext().getAttribute("feedDb");
    	List<Feed> someFeeds = feedDb.find(categoria);
    	PrintWriter out = response.getWriter();
    	for ( Feed feed : someFeeds ) {
    		out.println(gson.toJson(someFeeds.toArray(new Feed[0])));
				}
    }
}
