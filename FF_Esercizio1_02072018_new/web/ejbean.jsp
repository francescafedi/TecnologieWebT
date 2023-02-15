<%@ page session="true"%>
<%@ page import="java.io.File"%>
<%@ page import="it.unibo.tw.web.beans.Regalo"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.Duration"%>
<%@ page import="java.time.LocalTime"%>
<%@ page import="java.io.*"%>
<%@ page import="com.google.gson.Gson"%>

		
		<jsp:useBean id="c" class="it.unibo.tw.web.beans.Counter"  scope = "request"/>

<% 
response.setContentType("application/json");
LocalTime timeStart=LocalTime.now(); 
int length=Integer.parseInt(request.getParameter("length"));
List<String> files= new ArrayList<String>();
for(int i=0; i<length;i++) {
	String index="p"+i;
	String elemento=request.getParameter(index);
	files.add(elemento); //Ottengo una lista di file
}
//Conto per ogni file il numero di caratteri
String directory=application.getInitParameter("dirurl");
for(String file : files) { //Per ogni file selezionato lo apro
	File f=new File(directory+file);
	if(f.exists()) {
		BufferedReader br=new BufferedReader(new FileReader(f)); 
		String temp=null;
		String testo=null;
		while((temp=br.readLine())!=null) {
			testo+=temp;
		}
		for(int i=0;i<testo.length(); i++) {
			String str=(testo.charAt(i)+"").toUpperCase();
			String originale= testo.charAt(i)+"";
			if(!str.equals(originale)){
				//siamo in presenza di una lettera minuscola originariamente
				c.setCount(c.getCount()+1);
			}
		}		
		br.close();
	} 
}
LocalTime timeFinish=LocalTime.now();
c.setTempo(Duration.between(timeStart, timeFinish).toNanos());
Gson g=new Gson();
String res=g.toJson(c);
%>
<%=res %>


