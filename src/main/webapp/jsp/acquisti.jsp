<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="nytro.model.bean.VideogiocoBean, java.util.Collection, nytro.model.bean.AccountBean"%>
<%@ page import="java.util.Map" %>
<%
	Collection<Map> acquisti = (Collection<Map>) request.getAttribute("acquisti");
%>

<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Acquisti"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->
<link href="/NYTRO/css/libreriaStyle.css" type="text/css" rel="stylesheet">	

<div id="lista">
	<div id="pagina">
	<h1>Acquisti -
		<%
			AccountBean account = (AccountBean) request.getSession().getAttribute("account");%>
			<%=account.getUsername() %>

	</h1>
	</div>
	<%if(!acquisti.isEmpty()) {%>
		<div id="tabella">
			<%
				for(Map x : acquisti){
					System.out.println("\t\t"+x);
			%>
		<ul>
		<div class="gioco">
			<li><div class="copertina">
				<% VideogiocoBean videogioco = (VideogiocoBean) x.get("videogioco");%>
			<%if (videogioco.getImg() != null){%>
					<img src="/NYTRO/image?codice=<%= videogioco.getCodice()%>" alt="<%=videogioco.getTitolo()%>">
			<%} else {%>
					<img src="/NYTRO/img/no-cover.jpg" alt="<%=videogioco.getTitolo()%>">
			<%} %></div></li>
			<li class="titolo"><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+videogioco.getCodice())%>"><%=videogioco.getTitolo()%></a></li><br>
			<span><%=videogioco.getISIN() %></span><br>
			<span>Voto: <%=videogioco.getVotoMedio() %></span><br>
			<span>PEGI: <%=videogioco.getPEGI()%></span><br>
			<span>Data di Rilascio: <%=videogioco.getDataRilascio()%></span><br>
			<span>Data Acquisto: <%=x.get("data").toString()%></span><br>
			<span>Ora Acquisto: <%=x.get("ora").toString()%></span><br>
			<span>Console Acquisto: <%=x.get("console").toString()%></span><br>

			</li>
		</div>
		</ul>
			<%
				}
			%>
		
	<%} else if(acquisti.isEmpty()) { %>
		<div id="vuoto">
		<h1>Nessun acquisto effettuato.</h1>
		<p>Cosa aspetti? Sfoglia il catalogo!</p>
		</div>
	<%} %>
</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
		$("document").ready(function prova(){
			$("#acquisti").addClass("selected");
		})
	</script>
<%@include file="footer.jsp"%>				