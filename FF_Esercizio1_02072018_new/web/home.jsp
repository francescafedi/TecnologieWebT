<%@ page session="true"%>
<%@ page import="java.io.File"%>
<%@ page import="it.unibo.tw.web.beans.Item"%>
<%@ page import="java.util.*"%>
<html>
   <head>
		
      <title>Start Web Application</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script type="text/javascript" src="scripts/utils.js"></script>
   </head>
   <body>

      <!-- 
       ...so we offer the user something to read, meanwhile.
      
      This is the first page being shown, while the JSF Servlet starts up the environment,
      upon the first reqeust.
      This message avoid letting the user linger without knowing what's going on.
      -->
 <%	
 

 		%>

 
 
 
      <p>
      	Ecco la lista dei file contenuti nella cartella  <br>	
<% 
	String directory=application.getInitParameter("dirurl");
	Item i=(Item)session.getAttribute("folder");
	File []folder;
	if(i==null){
	File dir=new File(directory);
	Item inew= new Item();
	inew.setFolder(dir.listFiles()); 
	session.setAttribute("folder",inew);
	folder=inew.getFolder();
	}else{
	folder=i.getFolder();
	}

	for(File f : folder)
 		{
%>
 		
      	<input id="el" type="checkbox" value=<%=f.getName()%> onclick="contaCaratteri( 'dis', 'ejbean.jsp', myGetElementById('result'), myGetElementById('result1') )"/><%=f.getName()%><br>
      	<%
 		}

      	%>

      </p>

	<p id="result"></p>
	<p id="result1"></p>
   </body>
</html>

