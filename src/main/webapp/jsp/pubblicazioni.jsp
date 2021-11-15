<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="nytro.model.bean.VideogiocoBean, java.util.Collection"%>
<%
	Collection<VideogiocoBean> videogiochi = (Collection<VideogiocoBean>) request.getAttribute("videogiochi");
	
%>

<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Pubblicazioni"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->	
<link href="/NYTRO/css/pubblicazioni.css" type="text/css" rel="stylesheet">
	<div id="pagina">
	<h1>Videogiochi pubblicati</h1>
	</div>
	<div id="ordina">
		<form action="<%=response.encodeURL("/NYTRO/Pubblicazioni")%>" method="get">
		<label>Seleziona un criterio di ordinamento
		 <select name="order">
		  <option value="" selected>Nessuno</option>
		  <option value="Data_Rilascio">Data di rilascio</option>
		  <option value="Titolo">Titolo</option>
		  <option value="Voto_Medio">Voto medio delle recensioni</option>
		  <option value="PEGI">PEGI</option>
		</select> 
		</label><br/>
		<input type="submit" value="Vai"/>
		</form>
	</div>
	
	<%if(videogiochi!=null){ %>
		<div id="tabella">
		<table>
			<tr>
				<th>Immagine</th>
				<th>Codice</th>
				<th>Titolo</th>
				<th>Data di rilascio</th>
				<th>Data di rimozione</th>				
				<th>Voto medio</th>
				<th>PEGI</th>
				<th>Trailer</th>
				<th>Azioni</th>
			</tr>
			<%
				for(VideogiocoBean x : videogiochi){
			%>
			<tr>
				<td><div class="img">
					<%if(x.getImg()!= null) {%>
						<img id="img" src="<%=response.encodeURL("/NYTRO/image?codice="+x.getCodice())%>" alt="<%=x.getTitolo()%>">
					<%}else{ %>
						<img id="img" src="/NYTRO/img/no-cover.jpg" alt="<%=x.getTitolo()%>">
					<%} %></div>
				</td>
				<td><%=x.getCodice() %></td>
				<td><%=x.getTitolo() %></td>
				<td><%=x.getDataRilascio() %></td>
				<td><%=x.getDataRimozione() %></td>
				<td><%=x.getVotoMedio() %></td>
				<td><%=x.getPEGI() %></td>
				<td><%=x.getTrailer()%></td>
				<td>
					<span class = "buttonLink"><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+x.getCodice())%>">Informazioni</a></span>
					<%if(x.getDataRimozione()==null) {%>
						<br/><br/><a href="<%=response.encodeURL("/NYTRO/Pubblicazioni?cancelVideogioco="+x.getCodice())%>">Rimuovi videogioco</a>
					<%} else { %>
					<br/><br/>	<form action="<%=response.encodeURL("/NYTRO/Pubblicazioni")%>" method="post">
					<input hidden name="setDisponibleVideogioco" value="setDisponibleVideogioco">
					<a><button style ="background: none; border: none; color: #b33f3f; text-decoration: underline; text-align: left; font-size:16px;"
							   type="submit" value="<%=x.getCodice()%>" name="codiceVideogioco">Reintegra</button></a>
					</form>
					<%}%>
				</td>
			</tr>
			<%
				}
			%>
		</table>
		</div>
	<%} %>
	<div id="aggiungi" onclick='show(document.getElementById("formVideogioco"))'>
	<h2>Aggiungi un nuovo videogioco</h2>
	</div>
	<div id="formVideogioco">
		<form action="<%=response.encodeURL("/NYTRO/Pubblicazioni")%>" method="post" enctype="multipart/form-data">
			<input type="hidden" name="aggiungiVideogioco" value="true">
			<div id="campiFormVideogiocoObbligatori">
			<label>Titolo: <input type="text" name="aggTitolo" placeholder="Titolo*" required></label><br/>
			<label>PEGI: <input type="number" name="aggPegi" min="1" max="18" step="1" required></label><br/>
			<label>Genere: <input type="text" name="aggGenere" placeholder="Genere*" required></label><br/>
			<label>Inserisci immagine:<input type="file" name="photo" size="50"></label><br/>
			</div>
			<div id="radioButtonsScelta">
				<label>Console: <input type="text" name="aggConsole" placeholder="Console*" required></label><br/>
				<label>Trailer: <input type="text" name="aggTrailer" placeholder="Trailer*" required></label> <br/>
				<label>Prezzo: <input type="number" name="aggPrezzo" placeholder="Prezzo*" min="0" step="0.01" required></label><br/>
			</div>
			<div id="aggiungiVideogiocoForm"></div>
			<input type="submit" value="Aggiungi">
		</form>
	</div>
	<script>

	function show(elem) {
		if(elem.style.display == "none")
			elem.style.display ="block";
		else
			elem.style.display ="none";
	}

	</script>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
		$("document").ready(function prova(){
			$("#user").addClass("selected");
		})
	</script>
<%@include file="footer.jsp"%>							