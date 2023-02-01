package Servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.CounterResult;
import Beans.SlaveCounterBean;

import com.google.gson.Gson;

public class FIleManager extends HttpServlet{
	
	private Gson g;
	private File directory;
	
	@Override
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		g = new Gson();
		String dir = this.getServletContext().getInitParameter("dir");
		directory  = new File(this.getServletContext().getRealPath("/"+dir).trim());
	}
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		 
		
		String[] fileList = null;
		if(directory.exists() && directory.isDirectory())
		{
			fileList = directory.list();
		}
		HttpSession session = request.getSession();
		session.setAttribute("fileList", g.toJson(fileList));
		RequestDispatcher rd = getServletContext().getRequestDispatcher("home.jsp");
		rd.forward(request, response);
		return;
	}
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		String[] fileNames = new String[3];
		fileNames[0] = request.getParameter("fileName1");
		fileNames[1] = request.getParameter("fileName2");
		fileNames[2] = request.getParameter("fileName3");
		
		SlaveCounterBean scb = new SlaveCounterBean();
		scb.setFileNames(fileNames);
		int conteggio =0;
		
		long startTime = new Date().getTime();
		try{
			for(int i=0;i<fileNames.length;i++)
			{
				FileInputStream fis = new FileInputStream(new File(fileNames[0]));
				int c =fis.read();
				if(c>=65 && c<= 90)
					conteggio++;
				while(c>=0)
				{
					if(c>=65 && c<= 90)
						conteggio++;
					c=fis.read();		
				}
				fis.close();
			}
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = new Date().getTime();
		long elapesedMillisec = endTime - startTime;
		try {
			scb.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CounterResult cr = new CounterResult();
		cr.setBeanCounter(scb.getConteggio());
		cr.setBeanTime(scb.getElapesedMillisec());
		cr.setServerCounter(conteggio);
		cr.setServerTime(elapesedMillisec);
		String result = g.toJson(cr);
		response.getWriter().println(result);
		
	}

}
