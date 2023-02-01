<?xml version='1.0' encoding='ISO-8859-1'?>

<!-- 
NOTA BENE!!!  
Il PROLOGO XML deve SEMPRE essere collocato nella PRIMA RIGA!

Nessun carattere in output fino a questo momento!! 

Usero' ora l'out della jsp per restituire l'xml che ajax si attende!!!!

OSSERVATE IL TRAFFICO HTTP NEL TUNNEL!!!!!

-->

<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="false"%>

<!-- import di classi Java -->
<%@ page import="it.unibo.tw.web.beans.Feed"%>
<%@ page import="it.unibo.tw.web.beans.FeedDb"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<!-- librerie di terze parti -->
<!-- n.d. -->

<!-- creazione Java Bean con scope di applicazione -->
<jsp:useBean id="feedDb" class="it.unibo.tw.web.beans.FeedDb"  scope = "application"/>

<%

// QUESTO E' IMPORTANTISSIMO AFFINCHE' L'INTERPRETE JAVASCRIPT RICONOSCA IL CONTENUTO COME XML!!!!!!
response.setHeader("Content-Type","application/xml");
response.setStatus(200);

%>
<!-- segue quindi il codice.... questa volta NON HTML!!!!! ... restituito al client -->

<%
String c="";
	// recupero il tipo di categoria cercata dai parametri della richiesta
	String category = request.getParameter("category");
if ( category != null || category.length() != 0 ){


	
	// recupero i feed corrispondenti da database
	List<String> someFeeds = feedDb.getCategories(category);
	
	if ( someFeeds != null || someFeeds.size() != 0 ){
		 c = someFeeds.get(0);

	}
}
	
	
	
	// li stampo su out
%>
		<item>
			<title><%=c%></title>
		</item>


