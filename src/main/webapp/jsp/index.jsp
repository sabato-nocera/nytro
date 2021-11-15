<%@page import="nytro.model.bean.GiocatoreBean"%>
<%@page import="nytro.model.bean.AccountBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="nytro.model.bean.VideogiocoBean, java.util.Collection"%>
<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Home"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->
<link href="/NYTRO/css/indexStyle.css" type="text/css" rel="stylesheet">

<%
Collection<VideogiocoBean> videogiochiPiuAcquistati = (Collection<VideogiocoBean>) request.getAttribute("videogiochiPiuAcquistati");
Collection<VideogiocoBean> videogiochiPiuGiocati = (Collection<VideogiocoBean>) request.getAttribute("videogiochiPiuGiocati");
Collection<VideogiocoBean> videogiochiPiuVotati = (Collection<VideogiocoBean>) request.getAttribute("videogiochiPiuVotati");

Collection<VideogiocoBean> videogiochiPiuGiocatiMaschi = (Collection<VideogiocoBean>) request.getAttribute("videogiochiPiuGiocatiMaschi");
Collection<VideogiocoBean> videogiochiPiuGiocatiFemmine = (Collection<VideogiocoBean>) request.getAttribute("videogiochiPiuGiocatiFemmine");
Collection<VideogiocoBean> videogiochiPiuGiocatiGenderless = (Collection<VideogiocoBean>) request.getAttribute("videogiochiPiuGiocatiGenderless");

Collection<VideogiocoBean> videogiochiPiuGiocatiFemmineCasaEditrice = (Collection<VideogiocoBean>) request.getAttribute("videogiochiPiuGiocatiFemmineCasaEditrice");
Collection<VideogiocoBean> videogiochiPiuGiocatiMaschiCasaEditrice = (Collection<VideogiocoBean>) request.getAttribute("videogiochiPiuGiocatiMaschiCasaEditrice");
Collection<VideogiocoBean> videogiochiPiuGiocatiGenderlessCasaEditrice = (Collection<VideogiocoBean>) request.getAttribute("videogiochiPiuGiocatiGenderlessCasaEditrice");
Collection<VideogiocoBean> videogiochiPiuVotatiCasaEditrice = (Collection<VideogiocoBean>) request.getAttribute("videogiochiPiuVotatiCasaEditrice");


String message = (String) request.getAttribute("message");
AccountBean account = (AccountBean) request.getAttribute("account");

if(videogiochiPiuAcquistati==null || videogiochiPiuGiocati==null || videogiochiPiuVotati==null){
	String url = response.encodeURL("/NYTRO/Index");
	response.sendRedirect(url);
	return ;
}
List<VideogiocoBean> list = new ArrayList<VideogiocoBean>(videogiochiPiuAcquistati);
List<VideogiocoBean> list2 = new ArrayList<VideogiocoBean>(videogiochiPiuGiocati);
List<VideogiocoBean> list3 = new ArrayList<VideogiocoBean>(videogiochiPiuVotati);
List<VideogiocoBean> list4 = null;
List<VideogiocoBean> list5 = null;
List<VideogiocoBean> mas = new ArrayList<VideogiocoBean>(videogiochiPiuGiocatiMaschi);
List<VideogiocoBean> fem = new ArrayList<VideogiocoBean>(videogiochiPiuGiocatiFemmine);
List<VideogiocoBean> masCasa = null;
List<VideogiocoBean> femCasa = null;
List<VideogiocoBean> genCasa = null;

if(account != null && account.getRuolo() == 2) {
	list5 = new ArrayList<VideogiocoBean>(videogiochiPiuVotatiCasaEditrice);
	masCasa = new ArrayList<VideogiocoBean>(videogiochiPiuGiocatiMaschiCasaEditrice);
	femCasa = new ArrayList<VideogiocoBean>(videogiochiPiuGiocatiFemmineCasaEditrice);
	genCasa = new ArrayList<VideogiocoBean>(videogiochiPiuGiocatiGenderlessCasaEditrice);
}

%>	
<div id="welcome">
<%if (account == null) {%>
	<%if(message!=null && !message.equals("")) {	//Ad esempio per l'avvenuta e corretta registrazione%>
		<h1><%=message%></h1>
	<%} else {%>
	<h1>Entra a far parte della miglior community sui videogame!</h1>
	<p>Registrati o accedi per giocare ai migliori titoli del momento.</p>
	<%} %>
<%} else if(account != null && account.getRuolo() == 0) {%>
	<h1>Bentornato Amministratore!</h1>
	<p>Dai un'occhiata all'andamento della tua piattaforma.</p>
<%} else if(account != null && account.getRuolo() == 1) {%>
	<h1>Bentornato <%=account.getUsername() %>!</h1>
	<p>Dai un'occhiata ai giochi migliori selezionati per te.</p>
<%} else if(account != null && account.getRuolo() == 2) {%>
	<h1>Bentornato <%=account.getUsername() %>!</h1>
	<p>Dai un'occhiata all'andamento dei tuoi giochi sulla piattaforma.</p>
<%} %>
</div>
<div class="evidenza" id="dispari">
	<% if(account == null || (account!= null && account.getRuolo() == 1)|| (account!= null && account.getRuolo() == 0)) {%>
	<h1>In evidenza</h1>
	<div id="gioco1">
		<%if (list.get(0).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list.get(0).getCodice()%>" alt="<%=list.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list.get(0).getTitolo()%>">
		<%} %>
		<section class="prezzo"><%=list.get(0).getPrezzo() %>€</section>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list.get(0).getTrailer()%>"></iframe>
			<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+list.get(0).getCodice())%>"><%=list.get(0).getTitolo() %></a></h2>
			<h3><%=list.get(0).getGeneri().toString().substring(1, list.get(0).getGeneri().toString().length()-1) %></h3>
			<h3><%=list.get(0).getVotoMedio() %> ★</h3>
		</div>
	</div>
	<div id="gioco2">
		<%if (list.get(1).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list.get(1).getCodice()%>" alt="<%=list.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list.get(1).getTitolo()%>">
		<%} %>
		<section class="prezzo"><%=list.get(1).getPrezzo() %>€</section>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list.get(1).getTrailer()%>"></iframe>
			<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+list.get(1).getCodice())%>"><%=list.get(1).getTitolo() %></a></h2>
			<h3><%=list.get(1).getGeneri().toString().substring(1, list.get(1).getGeneri().toString().length()-1) %></h3>
			<h3><%=list.get(1).getVotoMedio() %> ★</h3>
		</div>
	</div>
	<div id="gioco3">
		<%if (list.get(2).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list.get(2).getCodice()%>" alt="<%=list.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list.get(2).getTitolo()%>">
		<%} %>
		<section class="prezzo"><%=list.get(2).getPrezzo() %>€</section>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list.get(2).getTrailer()%>"></iframe>
			<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+list.get(2).getCodice())%>"><%=list.get(2).getTitolo() %></a></h2>
			<h3><%=list.get(2).getGeneri().toString().substring(1, list.get(2).getGeneri().toString().length()-1) %></h3>
			<h3><%=list.get(2).getVotoMedio() %> ★</h3>
		</div>
	</div>
	<%} else if (account != null && account.getRuolo() == 2) { %>
		<h1>I tuoi giochi piu' giocati dagli utenti</h1>
	<%if(list4 != null && list4.size() > 0 && list4.get(0) != null) {%>
	<div id="gioco1">
		<%if (list4.get(0) != null && list4.get(0).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list4.get(0).getCodice()%>" alt="<%=list4.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list4.get(0).getTitolo()%>">
		<%} %>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list4.get(0).getTrailer()%>"></iframe>
			<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+list4.get(0).getCodice())%>"><%=list4.get(0).getTitolo() %></a></h2>
			<h3><%=list4.get(0).getGeneri().toString().substring(1, list4.get(0).getGeneri().toString().length()-1) %></h3>
			<h3><%=list4.get(0).getVotoMedio() %> ★</h3>
		</div>
	</div>
	<%} if(list4 != null && list4.size() > 1 && list4.get(1) != null) { %>
	<div id="gioco2">
		<%if (list4.get(1) != null && list4.get(1).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list4.get(1).getCodice()%>" alt="<%=list4.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list4.get(1).getTitolo()%>">
		<%} %>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list4.get(1).getTrailer()%>"></iframe>
			<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+list4.get(1).getCodice())%>"><%=list4.get(1).getTitolo() %></a></h2>
			<h3><%=list4.get(1).getGeneri().toString().substring(1, list4.get(1).getGeneri().toString().length()-1) %></h3>
			<h3><%=list4.get(1).getVotoMedio() %> ★</h3>
		</div>
	</div>
	<%} if(list4 != null && list4.size() > 2 && list4.get(2) != null) { %>
	<div id="gioco3">
		<%if (list4.get(2) != null && list4.get(2).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list4.get(2).getCodice()%>" alt="<%=list4.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list4.get(2).getTitolo()%>">
		<%} %>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list4.get(2).getTrailer()%>"></iframe>
			<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+list4.get(2).getCodice())%>"><%=list4.get(2).getTitolo() %></a></h2>
			<h3><%=list4.get(2).getGeneri().toString().substring(1, list4.get(2).getGeneri().toString().length()-1) %></h3>
			<h3><%=list4.get(2).getVotoMedio() %> ★</h3>
		</div>
	</div>
	<%} else if(list4 == null || list4.isEmpty() || list4.size() <= 0) { %>
		<h1>A quanto pare non sono presenti giochi :(</h1>
	<%} %>
	<%} %>
</div>
<div class="evidenza" id="pari">
<% if(account == null || (account!= null && account.getRuolo() == 1)|| (account!= null && account.getRuolo() == 0)) {%>
	<h1>Giochi del momento</h1>
	<div id="gioco4">
		<%if (list2.get(0).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list2.get(0).getCodice()%>" alt="<%=list2.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list2.get(0).getTitolo()%>">
		<%} %>
		<section class="prezzo"><%if (list2.get(0) instanceof VideogiocoBean){
			VideogiocoBean x = (VideogiocoBean)list2.get(0);%><%=x.getPrezzo() %>€
			<%} else { %>FREE <%} %></section>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list2.get(0).getTrailer()%>"></iframe>
			<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+list2.get(0).getCodice())%>"><%=list2.get(0).getTitolo() %></a></h2>
			<h3><%=list2.get(0).getGeneri().toString().substring(1, list2.get(0).getGeneri().toString().length()-1) %></h3>
			<h3><%=list2.get(0).getVotoMedio() %> ★</h3>
		</div>
	</div>
	<div id="gioco5">
		<%if (list2.get(1).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list2.get(1).getCodice()%>" alt="<%=list2.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list2.get(1).getTitolo()%>">
		<%} %>
		<section class="prezzo"><%if (list2.get(1) instanceof VideogiocoBean){
			VideogiocoBean x = (VideogiocoBean)list2.get(1);%><%=x.getPrezzo() %>€
			<%} else { %>FREE <%} %></section>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list2.get(1).getTrailer()%>"></iframe>
			<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+list2.get(1).getCodice())%>"><%=list2.get(1).getTitolo() %></a></h2>
			<h3><%=list2.get(1).getGeneri().toString().substring(1, list2.get(1).getGeneri().toString().length()-1) %></h3>
			<h3><%=list2.get(1).getVotoMedio() %> ★</h3>
		</div>
	</div>
	<div id="gioco6">
		<%if (list2.get(2).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list2.get(2).getCodice()%>" alt="<%=list2.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list2.get(2).getTitolo()%>">
		<%} %>
		<section class="prezzo"><%if (list2.get(2) instanceof VideogiocoBean){
			VideogiocoBean x = (VideogiocoBean)list2.get(2);%><%=x.getPrezzo() %>€
			<%} else { %>FREE <%} %></section>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list2.get(2).getTrailer()%>"></iframe>
			<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+list2.get(2).getCodice())%>"><%=list2.get(2).getTitolo() %></a></h2>
			<h3><%=list2.get(2).getGeneri().toString().substring(1, list2.get(2).getGeneri().toString().length()-1) %></h3>
			<h3><%=list2.get(2).getVotoMedio() %> ★</h3>
		</div>
	</div>
	<%} else if (account != null && account.getRuolo() == 2) { %>
			<h1>I tuoi giochi piu' votati dagli utenti</h1>
	<%if(list5 != null && list5.size() > 0 && list5.get(0) != null) {%>
	<div id="gioco4">
		<%if (list5.get(0) != null && list5.get(0).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list5.get(0).getCodice()%>" alt="<%=list5.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list5.get(0).getTitolo()%>">
		<%} %>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list5.get(0).getTrailer()%>"></iframe>
			<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+list5.get(0).getCodice())%>"><%=list5.get(0).getTitolo() %></a></h2>
			<h3><%=list5.get(0).getGeneri().toString().substring(1, list5.get(0).getGeneri().toString().length()-1) %></h3>
			<h3><%=list5.get(0).getVotoMedio() %> ★</h3>
		</div>
	</div>
	<%} if(list5 != null && list5.size() > 1 && list5.get(1) != null) { %>
	<div id="gioco5">
		<%if (list5.get(1) != null && list5.get(1).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list5.get(1).getCodice()%>" alt="<%=list5.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list5.get(1).getTitolo()%>">
		<%} %>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list5.get(1).getTrailer()%>"></iframe>
			<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+list5.get(1).getCodice())%>"><%=list5.get(1).getTitolo() %></a></h2>
			<h3><%=list5.get(1).getGeneri().toString().substring(1, list5.get(1).getGeneri().toString().length()-1) %></h3>
			<h3><%=list5.get(1).getVotoMedio() %> ★</h3>
		</div>
	</div>
	<%} if(list5 != null && list5.size() > 2 && list5.get(2) != null) { %>
	<div id="gioco6">
		<%if (list5.get(2) != null && list5.get(2).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list5.get(2).getCodice()%>" alt="<%=list5.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list5.get(2).getTitolo()%>">
		<%} %>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list5.get(2).getTrailer()%>"></iframe>
			<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+list5.get(2).getCodice())%>"><%=list5.get(2).getTitolo() %></a></h2>
			<h3><%=list5.get(2).getGeneri().toString().substring(1, list5.get(2).getGeneri().toString().length()-1) %></h3>
			<h3><%=list5.get(2).getVotoMedio() %> ★</h3>
		</div>
	</div>
	<%} else if(list5 == null || list5.isEmpty() || list5.size() <= 0) { %> { %>
		<h1>A quanto pare non sono presenti giochi :(</h1>
	<%} %>
	<%} %>
</div>
<div class="evidenza" id="dispari2">
<% if(account == null || (account!= null && account.getRuolo() == 1)|| (account!= null && account.getRuolo() == 0)) {%>
	<h1>Consigliati per te</h1>
	<div id="gioco7">
	<%if (account != null && account.getRuolo() == 1){
		GiocatoreBean g = (GiocatoreBean) account;	%>
		<%if (g.getGenere() != null && g.getGenere().equalsIgnoreCase("M") && mas.get(0) != null) {%>
			<%if (mas.get(0) != null && mas.get(0).getImg() != null){%>
				<img src="/NYTRO/image?codice=<%=mas.get(0).getCodice()%>" alt="<%=mas.get(0).getTitolo()%>">
			<%} else {%>
				<img src="/NYTRO/img/no-cover.jpg" alt="<%=mas.get(0).getTitolo()%>">
			<%} %>
			<section class="prezzo"><%if (mas.get(0) instanceof VideogiocoBean){
				VideogiocoBean x = (VideogiocoBean)mas.get(0);%><%=x.getPrezzo() %>€
				<%} else { %>FREE <%} %></section>
			<div class="info">
				<iframe width="100%" height="40%" src="<%=mas.get(0).getTrailer()%>"></iframe>
				<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+mas.get(0).getCodice())%>"><%=mas.get(0).getTitolo() %></a></h2>
				<h3><%=mas.get(0).getGeneri().toString().substring(1, mas.get(0).getGeneri().toString().length()-1) %></h3>
				<h3><%=mas.get(0).getVotoMedio() %> ★</h3>
			</div>
		<%} else if (g.getGenere() != null && g.getGenere().equalsIgnoreCase("F") && fem.get(0) != null) {%>
			<%if (fem.get(0) != null && fem.get(0).getImg() != null){%>
				<img src="/NYTRO/image?codice=<%=fem.get(0).getCodice()%>" alt="<%=fem.get(0).getTitolo()%>">
			<%} else {%>
				<img src="/NYTRO/img/no-cover.jpg" alt="<%=fem.get(0).getTitolo()%>">
			<%} %>
			<section class="prezzo"><%if (fem.get(0) instanceof VideogiocoBean){
				VideogiocoBean x = (VideogiocoBean)fem.get(0);%><%=x.getPrezzo() %>€
				<%} else { %>FREE <%} %></section>
			<div class="info">
				<iframe width="100%" height="40%" src="<%=fem.get(0).getTrailer()%>"></iframe>
				<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+fem.get(0).getCodice())%>"><%=fem.get(0).getTitolo() %></a></h2>
				<h3><%=fem.get(0).getGeneri().toString().substring(1, fem.get(0).getGeneri().toString().length()-1) %></h3>
				<h3><%=fem.get(0).getVotoMedio() %> ★</h3>
			</div>
		<%} else { %>	
			<%if (list3.get(0).getImg() != null){%>
				<img src="/NYTRO/image?codice=<%=list3.get(0).getCodice()%>" alt="<%=list3.get(0).getTitolo()%>">
			<%} else {%>
				<img src="/NYTRO/img/no-cover.jpg" alt="<%=list3.get(0).getTitolo()%>">
			<%} %>
			<section class="prezzo"><%if (list3.get(0) instanceof VideogiocoBean){
				VideogiocoBean x = (VideogiocoBean)list3.get(0);%><%=x.getPrezzo() %>€
				<%} else { %>FREE <%} %></section>
			<div class="info">
				<iframe width="100%" height="40%" src="<%=list3.get(0).getTrailer()%>"></iframe>
				<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+list3.get(0).getCodice())%>"><%=list3.get(0).getTitolo() %></a></h2>
				<h3><%=list3.get(0).getGeneri().toString().substring(1, list3.get(0).getGeneri().toString().length()-1) %></h3>
				<h3><%=list3.get(0).getVotoMedio() %> ★</h3>
			</div>
		<%} %>
	<%} else { %>	
		<%if (list3.get(0).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list3.get(0).getCodice()%>" alt="<%=list3.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list3.get(0).getTitolo()%>">
		<%} %>
		<section class="prezzo"><%if (list3.get(0) instanceof VideogiocoBean){
			VideogiocoBean x = (VideogiocoBean)list3.get(0);%><%=x.getPrezzo() %>€
			<%} else { %>FREE <%} %></section>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list3.get(0).getTrailer()%>"></iframe>
			<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+list3.get(0).getCodice())%>"><%=list3.get(0).getTitolo() %></a></h2>
			<h3><%=list3.get(0).getGeneri().toString().substring(1, list3.get(0).getGeneri().toString().length()-1) %></h3>
			<h3><%=list3.get(0).getVotoMedio() %> ★</h3>
		</div>
	<%} %>
	</div>
	<div id="gioco8">
		<%if (account != null && account.getRuolo() == 1){
		GiocatoreBean g = (GiocatoreBean) account;	%>
		<%if (g.getGenere() != null && g.getGenere().equalsIgnoreCase("M") && mas.get(1) != null) {%>
			<%if (mas.get(1) != null && mas.get(1).getImg() != null){%>
				<img src="/NYTRO/image?codice=<%=mas.get(1).getCodice()%>" alt="<%=mas.get(1).getTitolo()%>">
			<%} else {%>
				<img src="/NYTRO/img/no-cover.jpg" alt="<%=mas.get(1).getTitolo()%>">
			<%} %>
			<section class="prezzo"><%if (mas.get(1) instanceof VideogiocoBean){
				VideogiocoBean x = (VideogiocoBean)mas.get(1);%><%=x.getPrezzo() %>€
				<%} else { %>FREE <%} %></section>
			<div class="info">
				<iframe width="100%" height="40%" src="<%=mas.get(1).getTrailer()%>"></iframe>
				<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+mas.get(1).getCodice())%>"><%=mas.get(1).getTitolo() %></a></h2>
				<h3><%=mas.get(1).getGeneri().toString().substring(1, mas.get(1).getGeneri().toString().length()-1) %></h3>
				<h3><%=mas.get(1).getVotoMedio() %> ★</h3>
			</div>
		<%} else if (g.getGenere() != null && g.getGenere().equalsIgnoreCase("F") && fem.get(1) != null) {%>
			<%if (fem.get(1) != null && fem.get(1).getImg() != null){%>
				<img src="/NYTRO/image?codice=<%=fem.get(1).getCodice()%>" alt="<%=fem.get(1).getTitolo()%>">
			<%} else {%>
				<img src="/NYTRO/img/no-cover.jpg" alt="<%=fem.get(1).getTitolo()%>">
			<%} %>
			<section class="prezzo"><%if (fem.get(1) instanceof VideogiocoBean){
				VideogiocoBean x = (VideogiocoBean)fem.get(1);%><%=x.getPrezzo() %>€
				<%} else { %>FREE <%} %></section>
			<div class="info">
				<iframe width="100%" height="40%" src="<%=fem.get(1).getTrailer()%>"></iframe>
				<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+fem.get(1).getCodice())%>"><%=fem.get(1).getTitolo() %></a></h2>
				<h3><%=fem.get(1).getGeneri().toString().substring(1, fem.get(1).getGeneri().toString().length()-1) %></h3>
				<h3><%=fem.get(1).getVotoMedio() %> ★</h3>
			</div>
		<%} else { %>	
			<%if (list3.get(1).getImg() != null){%>
				<img src="/NYTRO/image?codice=<%=list3.get(1).getCodice()%>" alt="<%=list3.get(1).getTitolo()%>">
			<%} else {%>
				<img src="/NYTRO/img/no-cover.jpg" alt="<%=list3.get(1).getTitolo()%>">
			<%} %>
			<section class="prezzo"><%if (list3.get(1) instanceof VideogiocoBean){
				VideogiocoBean x = (VideogiocoBean)list3.get(1);%><%=x.getPrezzo() %>€
				<%} else { %>FREE <%} %></section>
			<div class="info">
				<iframe width="100%" height="40%" src="<%=list3.get(1).getTrailer()%>"></iframe>
				<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+list3.get(1).getCodice())%>"><%=list3.get(1).getTitolo() %></a></h2>
				<h3><%=list3.get(1).getGeneri().toString().substring(1, list3.get(1).getGeneri().toString().length()-1) %></h3>
				<h3><%=list3.get(1).getVotoMedio() %> ★</h3>
			</div>
		<%} %>
	<%} else { %>	
		<%if (list3.get(1).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list3.get(1).getCodice()%>" alt="<%=list3.get(1).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list3.get(1).getTitolo()%>">
		<%} %>
		<section class="prezzo"><%if (list3.get(1) instanceof VideogiocoBean){
			VideogiocoBean x = (VideogiocoBean)list3.get(1);%><%=x.getPrezzo() %>€
			<%} else { %>FREE <%} %></section>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list3.get(1).getTrailer()%>"></iframe>
			<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+list3.get(1).getCodice())%>"><%=list3.get(1).getTitolo() %></a></h2>
			<h3><%=list3.get(1).getGeneri().toString().substring(1, list3.get(1).getGeneri().toString().length()-1) %></h3>
			<h3><%=list3.get(1).getVotoMedio() %> ★</h3>
		</div>
	<%} %>
	</div>
	<div id="gioco9">
		<%if (account != null && account.getRuolo() == 1){
		GiocatoreBean g = (GiocatoreBean) account;	%>
		<%if (g.getGenere() != null && g.getGenere().equalsIgnoreCase("M") && mas.get(2) != null) {%>
			<%if (mas.get(2) != null && mas.get(2).getImg() != null){%>
				<img src="/NYTRO/image?codice=<%=mas.get(2).getCodice()%>" alt="<%=mas.get(2).getTitolo()%>">
			<%} else {%>
				<img src="/NYTRO/img/no-cover.jpg" alt="<%=mas.get(2).getTitolo()%>">
			<%} %>
			<section class="prezzo"><%if (mas.get(2) instanceof VideogiocoBean){
				VideogiocoBean x = (VideogiocoBean)mas.get(2);%><%=x.getPrezzo() %>€
				<%} else { %>FREE <%} %></section>
			<div class="info">
				<iframe width="100%" height="40%" src="<%=mas.get(2).getTrailer()%>"></iframe>
				<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+mas.get(2).getCodice())%>"><%=mas.get(2).getTitolo() %></a></h2>
				<h3><%=mas.get(2).getGeneri().toString().substring(1, mas.get(2).getGeneri().toString().length()-1) %></h3>
				<h3><%=mas.get(2).getVotoMedio() %> ★</h3>
			</div>
		<%} else if (g.getGenere() != null && g.getGenere().equalsIgnoreCase("F") && fem.get(2) != null) {%>
			<%if (fem.get(2) != null && fem.get(2).getImg() != null){%>
				<img src="/NYTRO/image?codice=<%=fem.get(2).getCodice()%>" alt="<%=fem.get(2).getTitolo()%>">
			<%} else {%>
				<img src="/NYTRO/img/no-cover.jpg" alt="<%=fem.get(2).getTitolo()%>">
			<%} %>
			<section class="prezzo"><%if (fem.get(2) instanceof VideogiocoBean){
				VideogiocoBean x = (VideogiocoBean)fem.get(2);%><%=x.getPrezzo() %>€
				<%} else { %>FREE <%} %></section>
			<div class="info">
				<iframe width="100%" height="40%" src="<%=fem.get(2).getTrailer()%>"></iframe>
				<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+fem.get(2).getCodice())%>"><%=fem.get(2).getTitolo() %></a></h2>
				<h3><%=fem.get(2).getGeneri().toString().substring(1, fem.get(2).getGeneri().toString().length()-1) %></h3>
				<h3><%=fem.get(2).getVotoMedio() %> ★</h3>
			</div>
		<%} else { %>	
			<%if (list3.get(2).getImg() != null){%>
				<img src="/NYTRO/image?codice=<%=list3.get(2).getCodice()%>" alt="<%=list3.get(2).getTitolo()%>">
			<%} else {%>
				<img src="/NYTRO/img/no-cover.jpg" alt="<%=list3.get(2).getTitolo()%>">
			<%} %>
			<section class="prezzo"><%if (list3.get(2) instanceof VideogiocoBean){
				VideogiocoBean x = (VideogiocoBean)list3.get(2);%><%=x.getPrezzo() %>€
				<%} else { %>FREE <%} %></section>
			<div class="info">
				<iframe width="100%" height="40%" src="<%=list3.get(2).getTrailer()%>"></iframe>
				<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+list3.get(2).getCodice())%>"><%=list3.get(2).getTitolo() %></a></h2>
				<h3><%=list3.get(2).getGeneri().toString().substring(1, list3.get(2).getGeneri().toString().length()-1) %></h3>
				<h3><%=list3.get(2).getVotoMedio() %> ★</h3>
			</div>
		<%} %>
	<%} else { %>	
		<%if (list3.get(2).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list3.get(2).getCodice()%>" alt="<%=list3.get(2).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list3.get(2).getTitolo()%>">
		<%} %>
		<section class="prezzo"><%if (list3.get(2) instanceof VideogiocoBean){
			VideogiocoBean x = (VideogiocoBean)list3.get(2);%><%=x.getPrezzo() %>€
			<%} else { %>FREE <%} %></section>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list3.get(2).getTrailer()%>"></iframe>
			<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+list3.get(2).getCodice())%>"><%=list3.get(2).getTitolo() %></a></h2>
			<h3><%=list3.get(2).getGeneri().toString().substring(1, list3.get(2).getGeneri().toString().length()-1) %></h3>
			<h3><%=list3.get(2).getVotoMedio() %> ★</h3>
		</div>
	<%} %>
	</div>
	<%} else if (account != null && account.getRuolo() == 2) { %>
		<h1>Statistiche demografiche</h1>
	<%if(masCasa != null && masCasa.size() > 0 && masCasa.get(0) != null) {%>
	<div id="gioco7">
		<%if (masCasa.get(0) != null && masCasa.get(0).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=masCasa.get(0).getCodice()%>" alt="<%=masCasa.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=masCasa.get(0).getTitolo()%>">
		<%} %>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=masCasa.get(0).getTrailer()%>"></iframe>
			<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+masCasa.get(0).getCodice())%>"><%=masCasa.get(0).getTitolo() %></a></h2>
			<h3><%=masCasa.get(0).getGeneri().toString().substring(1, masCasa.get(0).getGeneri().toString().length()-1) %></h3>
			<h3><%=masCasa.get(0).getVotoMedio() %> ★</h3>
		</div>
	</div>
	<%} if(femCasa != null && femCasa.size() > 0 && femCasa.get(0) != null) { %>
	<div id="gioco8">
		<%if (femCasa.get(0) != null && femCasa.get(0).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=femCasa.get(0).getCodice()%>" alt="<%=femCasa.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=femCasa.get(0).getTitolo()%>">
		<%} %>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=femCasa.get(0).getTrailer()%>"></iframe>
			<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+femCasa.get(0).getCodice())%>"><%=femCasa.get(0).getTitolo() %></a></h2>
			<h3><%=femCasa.get(0).getGeneri().toString().substring(1, femCasa.get(0).getGeneri().toString().length()-1) %></h3>
			<h3><%=femCasa.get(0).getVotoMedio() %> ★</h3>
		</div>
	</div>
	<%} if(genCasa != null && genCasa.size() > 0 && genCasa.get(0) != null) { %>
	<div id="gioco9">
		<%if (genCasa.get(0) != null && genCasa.get(0).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=genCasa.get(0).getCodice()%>" alt="<%=genCasa.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=genCasa.get(0).getTitolo()%>">
		<%} %>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=genCasa.get(0).getTrailer()%>"></iframe>
			<h2><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+genCasa.get(0).getCodice())%>"><%=genCasa.get(0).getTitolo() %></a></h2>
			<h3><%=genCasa.get(0).getGeneri().toString().substring(1, genCasa.get(0).getGeneri().toString().length()-1) %></h3>
			<h3><%=genCasa.get(0).getVotoMedio() %> ★</h3>
		</div>
	</div>
	<%} else if((masCasa == null || masCasa.isEmpty() || masCasa.size() <= 0) && (femCasa == null || femCasa.isEmpty() || femCasa.size() <= 0) && (genCasa == null || genCasa.isEmpty() || genCasa.size() <= 0)) { %>
		<h1>A quanto pare non sono presenti giochi :(</h1>
	<%} %>
	<%} %>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	$("document").ready(function prova(){
		$("#home").addClass("selected");
	})
</script>
<%@include file="footer.jsp"%>							 <!-- Inclusione statica di footer.html" -->