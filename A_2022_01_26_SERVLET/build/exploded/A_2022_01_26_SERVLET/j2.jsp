
<%@ page session="true"%>

<%@ page import="it.unibo.tw.web.beans.Risposta"%>
<%@ page import ="java.io.InputStream"%>
<%@ page import ="java.io.InputStreamReader"%>
<%@ page import ="java.io.BufferedReader"%>
<%@ page import ="java.io.InputStream"%>
<%@ page import ="java.io.File"%>
<%@ page import ="java.io.FileReader"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>




					<%
					Gson g=new Gson();
					Risposta richieste=(Risposta)session.getAttribute("ris1");
					if(richieste==null) {
					return;	
					}
					String parola=request.getParameter("parola");
					if(parola!=null) {
						BufferedReader reader;
						File f=new File("C:\\aaa\\"+parola);
						reader=new BufferedReader(new FileReader(f));
						String c;
						String result="";
						int count=0, maiuscole=0;
						while((c=reader.readLine())!=null) { 
							for(int i=0; i<c.length();i++) {
								if(c.charAt(i)=='n') { //a lettera scelta a caso
									count++;
								}else {
									result=result+c.charAt(i);
								}
								if(String.valueOf(c.charAt(i)).toLowerCase()==String.valueOf(c.charAt(i))){
									//niente
								}else{
									maiuscole++;
								}
							}
						}
						
					
			    			Risposta ris1=new Risposta();
			    			ris1.setCount(richieste.getCount());
			    			ris1.setResult(result);
			    			ris1.setMaiuscole(maiuscole);
			    			ris1.setCount2(count);
					
			    			String res=g.toJson(ris1);
			    			response.getWriter().println(res);
				
					}
			%>
