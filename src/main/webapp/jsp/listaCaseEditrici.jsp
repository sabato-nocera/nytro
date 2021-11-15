<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="nytro.model.bean.AccountBean, java.util.Collection, nytro.model.bean.CasaEditriceBean"%>
<%
	Collection<AccountBean> caseEditrici = (Collection<AccountBean>) request.getAttribute("caseEditrici");
	
%>

<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Lista Case Editrici"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->	
	<link href="/NYTRO/css/listaGiocatori.css" type="text/css" rel="stylesheet">
	<div id="pagina">
	<h1>Lista case editrici</h1>
	</div>
		<div id="ordina">
			<form action="<%=response.encodeURL("/NYTRO/ListaCaseEditrici")%>" method="get">
			<label>Seleziona un criterio di ordinamento
			 <select name="order">
			  <option value="">Nessuno</option>
			  <option value="account.Username">Username</option>
			  <option value="account.Data">Data ultimo accesso</option>
			  <option value="account.IP">Indirizzo IP</option>
			</select> 
			</label>
			<input type="submit" value="Vai"/>
			</form>
		</div>
		<%if(caseEditrici!=null) {%>
		<div id="tabella">
			<table>
				<tr>
					<th>Username</th>
					<th>Password</th>
					<th>E-mail</th>
					<th>E-mail secondaria</th>
					<th>Cellulare</th>
					<th colspan="3">Ultimo accesso</th>
					<th>ISIN</th>
					<th>Nome</th>
					<th>CEO</th>
					<th>Sito</th>
				</tr>
				<%
					for(AccountBean x : caseEditrici){
						CasaEditriceBean tmp = (CasaEditriceBean) x;
						System.out.println(tmp.getISIN());
				%>
				<tr>
					<td><a href="<%=response.encodeURL("/NYTRO/CatalogoCasaEditrice?isinCasaEditrice="+tmp.getISIN())%>"><%=x.getUsername() %></a></td>
					<td><%=x.getPassword() %></td>
					<td><%=x.getEmail() %></td>
					<td><%=x.getEmailRecupero() %></td>
					<td><%=x.getCellulare() %></td>
					<td><%=x.getData() %></td>
					<td><%=x.getOra() %></td>
					<td><%=x.getIp() %></td>
					<td><%=tmp.getISIN() %></td>
					<td><%=tmp.getNomeCasaEditrice() %></td>
					<td><%=tmp.getCEO()	%></td>
					<td><%=tmp.getSitoWeb() %></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
	<%} %>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
		$("document").ready(function prova(){
			$("#user").addClass("selected");
		})
	</script>
	
<%@include file="footer.jsp"%>							