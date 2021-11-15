<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="nytro.model.bean.AccountBean"%>
<%
	AccountBean account = (AccountBean) session.getAttribute("account");
	int ruolo=-1;
	if(account!=null)
		ruolo = account.getRuolo();
%>

<!DOCTYPE html>
<html>
	<head>
		<title>Nytro - ${param.pageTitle}</title>
		<meta charset="UTF-8">
		<meta name="description" content="NYTRO">
		<meta name="author" content="Montano Michele, Nocera Sabato, Volpicelli Veronica">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" >
		<script src="/NYTRO/js/ricerca.js"></script>
		<link href="/NYTRO/css/style.css" type="text/css" rel="stylesheet">
		<link rel="icon" href="/NYTRO/img/logo.png">
	</head>
<body>
<div id="wrapper">
	<header>
	<!-- Logo del sito + Nome del sito-->
		<a href="<%=response.encodeURL("/NYTRO/jsp/index.jsp")%>"><img id="logoHeader" src="/NYTRO/img/logo.png" alt="Logo"/><img id="nytro" src="/NYTRO/img/Nytro.png" alt="Nytro"></a>
		<div id="opzioni">
			<%if(account==null){
				String url = response.encodeURL("/NYTRO/jsp/loginForm.jsp");
				String url2 = response.encodeURL("/NYTRO/RegistrazioneForm");
			%>
				<a href="<%=url%>">Login</a> |
				<a href="<%=url2%>">Registrazione</a>
			<%}else {
				String url = response.encodeURL("/NYTRO/Profilo");
				String url2 = response.encodeURL("/NYTRO/Logout");
			%>
				<a href="<%=url%>"><%=account.getUsername()%></a> |
				<a href="<%=url2%>">Logout</a>
			<%} %>
		</div>
	</header>
	<nav>
		<ul>
			<li>
				<a href="<%=response.encodeURL("/NYTRO/jsp/index.jsp")%>" id="home">Home</a>
			</li>
			<% if(account!=null && (ruolo==1)){ %>
				<li>
					<a href="<%=response.encodeURL("/NYTRO/Libreria")%>" id="libreria">Libreria</a>
				</li>
				<li>
					<a href="<%=response.encodeURL("/NYTRO/Friendlist")%>" id="friendlist">Friendlist</a>
				</li>
				<li>
					<a href="<%=response.encodeURL("/NYTRO/Acquisti")%>" id="acquisti">Acquisti</a>
				</li>
				<li>
					<a href="<%=response.encodeURL("/NYTRO/jsp/carrello.jsp")%>">Carrello</a>
				</li>
			<% } %>
			<% if(account!=null && (ruolo==0 || ruolo==1 || ruolo == 2)){ %>
				<li>
				<div id = "menuEsplora">
					<a href="#" id="esplora">Esplora</a>
				<!--
				Il tag <menu> definisce una lista di comandi
				Il funzionamento e' il seguente: cliccando su "Catalogo" o su "Case Editrici" verra' richiamata la servlet corrispondente, la quale
				si preoccupera' di caricare in modo adeguato la jsp corrispondente.
				-->
					<div class = "elementoEsplora">
						<a href="<%=response.encodeURL("/NYTRO/Catalogo")%>">Catalogo</a>
						<a href="<%=response.encodeURL("/NYTRO/ElencoCaseEditrici")%>">Elenco Case Editrici</a>
					</div>
				</div>
				</li>
			<% } %>
			<li>
			<!--
			Gesione del login. All'inizio abbiamo ricavato le informazioni circa l'utente che sta visitando la pagina.
			Se l'utente non ha effettuato il login, gli verra' mostrato un format a tale scopo, oppure un link che rimanda ad un format di registrazione.
			Se l'utente e' registrato, a seconda del proprio ruolo, visualizzera' informazioni diverse.
			-->

				<%if(account!=null){ %>
					<div id = "menuUser">
						<a href="#" id="user"><%=account.getUsername()%></a>
						<div class = "elementoUser">
						<% if(ruolo==0){			//Admin%>
								<a href="<%=response.encodeURL("/NYTRO/ListaGiocatori")%>">Lista giocatori</a>
								<a href="<%=response.encodeURL("/NYTRO/ListaCaseEditrici")%>">Lista case editrici</a>
								<a href="<%=response.encodeURL("/NYTRO/jsp/registrazioneCasaEditrice.jsp")%>">Registra Casa Editrice</a>
								<a href="<%=response.encodeURL("/NYTRO/jsp/rimozioneAccount.jsp")%>">Rimuovi account</a>
							<hr/>
						<% }
							if(ruolo==2){		//Azienda %>
								<a href="<%=response.encodeURL("/NYTRO/Pubblicazioni")%>">Pubblicazioni</a>
							<hr>
						<%} %>
						<a href="<%=response.encodeURL("/NYTRO/Profilo")%>">Profilo</a>	<!-- Rimanda alla servlet per la gestione del profilo dell'utente (cambia a seconda del tipo di utente) -->
								<!-- Visualizza un pulsante attraverso cui richiamare la servlet per il Logout
							<form action="/NYTRO/Logout">
								<input type="submit" value="Logout">
							</form> Non e' necessario un form -->
						<a href="<%=response.encodeURL("/NYTRO/Logout")%>">Logout</a>
					</div>
				<%} %>
			</li>
			<%if(ruolo>=0){%>
			<li>
				<form action="<%=response.encodeURL("Ricerca")%>" method="get" id="ricerca" >
					<input type="text" name="testoParziale" list="ricerca-datalist" placeholder="    Ricerca" onkeyup="ricerca(this.value)" style="margin: 10px 0px 0px 0px!important; padding: 5px 0px 5px 10px!important;"> <!-- value="<c:out value="${param.q}" />" -->
					<datalist id="ricerca-datalist" ></datalist>
					<!--
					Il tag <datalist> viene utilizzato per fornire la funzione di "autocompletamento" per elementi di input;
					mostrera' un menu' a tendina con opzioni predefinite a seconda dell'input che usando.
					In questo modo otterremo una ricerca-ajax.
					-->
				</form>
			</li>
			<%} else {%>
			<li></li>
			<%} %>
		</ul>
	</nav>