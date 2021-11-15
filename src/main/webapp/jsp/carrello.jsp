<%@page import="nytro.model.bean.VideogiocoBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="nytro.model.bean.VideogiocoBean, nytro.model.bean.Cart, nytro.model.bean.AccountBean, java.util.Collection, java.util.List"%>

<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Carrello"/>	</jsp:include>
<link href="/NYTRO/css/cartStyle.css" type="text/css" rel="stylesheet">	

<%	
	Cart cart = (Cart) session.getAttribute("carrello");
	String message = (String) request.getAttribute("message");
	AccountBean account = (AccountBean) session.getAttribute("account");
	
%>

<div id="lista">
	<div id="pagina">
	<h1>Carrello - <%=account.getUsername() %>	</h1>
	</div>
	<%if(cart!=null) {%>
	<%
		List<VideogiocoBean> carrelloVideogiochi = cart.getItems();
		if(carrelloVideogiochi.size()>0){
	%>
			<div id="ordina">			
			<form action="<%=response.encodeURL("/NYTRO/GestoreCarrello")%>" method="post">
				<input type="hidden" name="action" value="buy">
				<label>Inserisci una carta di pagamento: <input type="text" name="cartaDiPagamento" placeholder="Carta di pagamento*" required></label>
				<input type="submit" value="Effettua l'acquisto">
			</form>
			</div>
		
		<div id="tabella">
			<%
				for(VideogiocoBean x:carrelloVideogiochi){
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
			<li class="titolo"><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+x.getCodice())%>"><%=x.getTitolo()%></a></li>
			<li>Prezzo: <%=x.getPrezzo()%></li>
			<li>Console: <%=x.getConsole().get(0)%></li>
			<li><a href="<%=response.encodeURL("GestoreCarrello?action=deleteCart&codiceVideogioco="+x.getCodice())%>"><button id="rimuovi">Rimuovi dal carrello</button></a></li>
		</div>
		</ul>
		
	</div>
			<%
				}
		}
			%>
		
	<%} 
	if (cart == null || (cart!= null && cart.getItems().size() < 1) || (cart != null && cart.getItems().isEmpty())){ %>
		<div id="vuoto">
		<h1>Nessun videogioco presente nel carrello.</h1>
		<p>Cosa aspetti? Compra nuovi videogiochi!</p>
		</div>
	<%} %>
<div id="svuota">
		<%if(cart != null && (cart != null && cart.getItems().size() > 0)) {%>
			<a href="<%=response.encodeURL("GestoreCarrello?action=clearCart")%>">Svuota carrello</a><br/>
	<%}
		if(message != null && !message.equals("")) {
	%>
		
		<p><%=message.toString()%></p>
	
	<%
		}
	%>
</div>
<%@include file="footer.jsp"%>	