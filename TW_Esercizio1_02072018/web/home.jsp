<%@ page session="true"%>
<html>
   <head>
      <title>Files View</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script type="text/javascript" src="scripts/utils.js"></script>
		<script type="text/javascript" src="scripts/splitCounting.js"></script>
   </head>
   <%
    String[] fileList = (String[])session.getAttribute("fileList");
   %>
   <body>
		<h5>Benvenuto, segue la lista di file prensenti lato server</h5>
		<div id="files">
		<%for(int i=0;i<fileList.length;i++){ %>
			<input type="checkbox" name="file<%=i %>" value="<%=fileList[i] %>" onchange="myfunction(this);"> <%=fileList[i] %><br>
		<%} %>
		</div>
		<div id="result">
		</div>
      <!-- 
       ...so we offer the user something to read, meanwhile.
      
      This is the first page being shown, while the JSF Servlet starts up the environment,
      upon the first reqeust.
      This message avoid letting the user linger without knowing what's going on.
      -->

      </p>

   </body>
</html>

