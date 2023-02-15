<!-- accesso alla sessione -->
<%@ page session="true"%>

<%@ page import="it.unibo.tw.web.beans.Richiesta"%>
<%@ page import ="java.io.InputStream"%>
<%@ page import ="java.io.InputStreamReader"%>
<%@ page import ="java.io.BufferedReader"%>
<%@ page import ="java.io.InputStream"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>




					<% 
						
					System.out.println("ciao");
					Gson g;
					g=new Gson();

					InputStream is= request.getInputStream();
					BufferedReader in= new BufferedReader(new InputStreamReader(is));
					String line=in.readLine();
					in.close();
					System.out.println(line);
					int result=0;
					Richiesta richiesta = g.fromJson(line, Richiesta.class);
					Map<Integer, Richiesta> richieste=(Map<Integer,Richiesta>)this.getServletContext().getAttribute("richieste");
					if(richieste==null) {
						richieste=new HashMap<Integer,Richiesta>();
					}
						if(richiesta.getTurno()==1){
							richiesta.setId(richieste.size()+1);
						}
						if(!richieste.containsKey(richiesta.getId())){
							richieste.put(richiesta.getId(), richiesta);
							this.getServletContext().setAttribute("richieste", richieste);
						}else{
							richieste.replace(richiesta.getId(), richiesta);
						}
						
					
					String temp= richiesta.getTesto().toUpperCase(); //operazione
					richiesta.setTesto(temp);
					session.setAttribute("richiesta",richiesta);
				
										
			%>
			<jsp:forward page="s2" />
