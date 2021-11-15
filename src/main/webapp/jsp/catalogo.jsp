<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="nytro.model.bean.VideogiocoBean, java.util.Collection, java.util.ArrayList"%>
<%
	Collection<VideogiocoBean> catalogo = (Collection<VideogiocoBean>) request.getAttribute("catalogo");
	ArrayList<String> generiPresenti = (ArrayList<String>) request.getAttribute("generiPresenti");
	List<String> nomi = (List<String>) request.getAttribute("nomeCasa");
%>

<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Catalogo"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->	
<link href="/NYTRO/css/libreriaStyle.css" type="text/css" rel="stylesheet">	

	<div id="lista">
	<div id="pagina">
	<h1>Catalogo completo dei videogiochi</h1>
	</div>
	<div id="ordina">
	<form action="<%=response.encodeURL("/NYTRO/Catalogo")%>" method="get">
	<label>Seleziona un criterio di ordinamento
	 <select name="order">
	  <option value="" selected>Nessuno</option>
	  <option value="Data_Rilascio">Data di rilascio</option>
	  <option value="Titolo">Titolo</option>
	  <option value="Voto_Medio">Voto medio delle recensioni</option>
	  <option value="PEGI">PEGI</option>
	</select> 
	</label><br/>
	<label>Seleziona il genere dei videogiochi che vuoi visualizzare
	 <select name="genere">
	  <option value="" selected>Nessuno</option>
	  <%if(generiPresenti.size()>0){
	  		for(String x : generiPresenti) {%>
	  	<option value="<%=x%>"><%=x %></option>
	  <%	}
  		}%>
	</select> 
	</label><br/>
	<input type="submit" value="Vai"/>
	</form>
	</div>
	<%if(catalogo!=null) {
		int i = 0;%>
		<div id="tabella">
			<%
				for(VideogiocoBean x : catalogo){
					System.out.println("\t\t"+x);
			%>
		<ul>
		<div class="gioco">
			<li><div class="copertina">
			<%if (x.getImg() != null){%>
					<img src="/NYTRO/image?codice=<%= x.getCodice()%>" alt="<%=x.getTitolo()%>">
			<%} else {%>
					<img src="/NYTRO/img/no-cover.jpg" alt="<%=x.getTitolo()%>">
			<%} %></div></li>
				
			<li class="titolo"><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+x.getCodice())%>"><%=x.getTitolo()%></a></li><br>
			<span><%=nomi.get(i) %></span><br>
			<span><%=x.getGeneri().toString().substring(1, (x.getGeneri().toString().length() - 1))%></span><br>
			<span>Voto: <%=x.getVotoMedio() %></span><br>
			<span>PEGI: <%=x.getPEGI()%></span><br>
			<span>Data di rilascio: <%=x.getDataRilascio()%></span><br>

		</div>
		</ul>	
			<%
				i++;
				}
			%>
	<%} %>
	</div>
</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
		$("document").ready(function prova(){
			$("#esplora").addClass("selected");
		})
	</script>
<%@include file="footer.jsp"%>						