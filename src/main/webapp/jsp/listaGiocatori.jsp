<%@page import="nytro.model.bean.GiocatoreBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="nytro.model.bean.AccountBean, java.util.Collection"%>
<%
	Collection<AccountBean> users = (Collection<AccountBean>) request.getAttribute("users");
%>
<link href="/NYTRO/css/listaGiocatori.css" type="text/css" rel="stylesheet">
<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Lista Giocatori"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->	
	<div id="pagina">
	<h1>Lista giocatori</h1>
	</div>
	<div id="select">
		<form action="<%=response.encodeURL("/NYTRO/ListaGiocatori")%>" method="get">
		<label>Seleziona un criterio di ordinamento
		 <select name="order">
		  <option value="">Nessuno</option>
		  <option value="account.Username">Username</option>
		  <option value="Data">Data ultimo accesso</option>
		  <option value="IP">Indirizzo IP</option>
		</select> 
		</label>
		<input type="submit" value="Vai"/>
		</form>	
	</div>
	<div id="tabella">
		<table>
			<tr>
				<th>Username</th>
				<th>Password</th>
				<th>E-mail</th>
				<th>E-mail secondaria</th>
				<th>Cellulare</th>
				<th colspan="3">Ultimo accesso</th>
				<th>Data di nascita</th>
				<th>Data iscrizione</th>
				<th>Genere</th>
			</tr>
			<%
				for(AccountBean x : users){
					GiocatoreBean tmp = (GiocatoreBean) x;
			%>
			<tr>
				<td><%=x.getUsername() %></td>
				<td><%=x.getPassword() %></td>
				<td><%=x.getEmail() %></td>
				<td><%=x.getEmailRecupero() %></td>
				<td><%=x.getCellulare() %></td>
				<td><%=x.getData() %></td>
				<td><%=x.getOra() %></td>
				<td><%=x.getIp() %></td>
				<td><%=tmp.getDataNascita() %></td>
				<td><%=tmp.getDataIscrizione() %></td>
				<td><%=tmp.getGenere() %></td>
			</tr>
			<%
				}
			%>
		</table>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
		$("document").ready(function prova(){
			$("#user").addClass("selected");
		})
	</script>
</div>
<%@include file="footer.jsp"%>			