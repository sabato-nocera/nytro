<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="nytro.model.bean.VideogiocoBean, java.util.Collection, nytro.model.bean.AccountBean"%>
<%@ page import="java.util.Map" %>
<%
	Collection<VideogiocoBean> libreria = (Collection<VideogiocoBean>) request.getAttribute("libreria");
	Collection<Map> acquisti = (Collection<Map>) request.getAttribute("acquisti");
	String libreriaAmicoDaVisualizzare=request.getParameter("libreriaAmicoDaVisualizzare");
%>

<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Libreria"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->	
<link href="/NYTRO/css/libreriaStyle.css" type="text/css" rel="stylesheet">	

<div id="lista">
	<div id="pagina">
	<h1>Libreria - 
		<%if(libreriaAmicoDaVisualizzare!=null && !libreriaAmicoDaVisualizzare.equals("")) {%>
			<%=libreriaAmicoDaVisualizzare %>
		<%} else { 
			AccountBean account = (AccountBean) request.getSession().getAttribute("account");%>
			<%=account.getUsername() %>
		<%} %>
	</h1>
	</div>
	<div id="ordina">
	<form action="<%=response.encodeURL("/NYTRO/Libreria")%>" method="get">
	 <%if(libreriaAmicoDaVisualizzare!=null && !libreriaAmicoDaVisualizzare.equals("")){ %>
	 	<input type="hidden" name="libreriaAmicoDaVisualizzare" value="<%=libreriaAmicoDaVisualizzare %>"/>
	 <%} %>
	<label>Seleziona un criterio di ordinamento
	 <select name="order">	
	  <option value="" selected>Nessuno</option>
	  <option value="Data_Rilascio">Data di rilascio</option>
	  <option value="Titolo">Titolo</option>
	  <option value="Voto_Medio">Voto medio delle recensioni</option>
	  <option value="PEGI">PEGI</option>
	</select> 
	</label>
	<input type="submit" value="Vai"/>
	</form>
	</div>
	<%if(!libreria.isEmpty()) {%>
		<div id="tabella">
			<%
				for(VideogiocoBean x : libreria){
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
			<%if(libreriaAmicoDaVisualizzare==null || libreriaAmicoDaVisualizzare.equals("")){%>
			<li><a><button id="download" style="width:200px;">Codici Download<br><br>
				<%for(Map y : acquisti){
					VideogiocoBean videogioco = (VideogiocoBean) y.get("videogioco");
					if(videogioco.getCodice()==x.getCodice()){
				%>
				<span><%=y.get("console").toString()%> : <%=y.get("codiceDownload")%></span><br>

				<%}}%>
			</button></a>
			</li><%}%>
			<li class="titolo"><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+x.getCodice())%>"><%=x.getTitolo()%></a></li><br>
			<span><%=x.getISIN() %></span><br>
			<span>Voto: <%=x.getVotoMedio() %></span><br>
			<span>PEGI: <%=x.getPEGI()%></span><br>
			<span>Data di Rilascio: <%=x.getDataRilascio()%></span><br>
			<%if(x.getDataRimozione()!=null){ %>
				<span style="color: #b33f3f;"><b>ATTENZIONE</b> il gioco è stato rimosso dalla casa editrice, non sarà più possibile visualizzarlo nel catalogo</span>
			<%}%>
			<li><a href="<%=response.encodeURL("/NYTRO/Friendlist?codiceVideogioco="+x.getCodice()+"&possessedutoAmici=true")%>">Amici possessori</a></li>
			<li><form action="<%=response.encodeURL("/NYTRO/Libreria?cancellaVideogioco="+x.getCodice())%>" method="post"> <input type="submit" value="Rimuovi"/></form>
			</li>
		</div>
		</ul>
			<%
				}
			%>
		
	<%} else if(libreria.isEmpty()) { %>
		<div id="vuoto">
		<h1>Nessun gioco presente nella libreria.</h1>
		<p>Cosa aspetti? Sfoglia il catalogo!</p>
		</div>
	<%} %>
</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
		$("document").ready(function prova(){
			$("#libreria").addClass("selected");
		})
	</script>
<%@include file="footer.jsp"%>				