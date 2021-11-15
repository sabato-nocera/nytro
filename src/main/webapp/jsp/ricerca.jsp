<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="nytro.model.bean.VideogiocoBean, java.util.List"%>
<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Ricerca"/>	</jsp:include>
<link href="/NYTRO/css/RegStyle.css" type="text/css" rel="stylesheet">

<div id="pagina">
	<h2>Lista videogiochi trovati</h2>
	</div>
	
<div id="risultati">
<%
	List<VideogiocoBean> listaVideogiochi = (List<VideogiocoBean>) request.getAttribute("listaVideogiochi");
	if(listaVideogiochi!=null){
		for(VideogiocoBean x : listaVideogiochi){
%>
	<a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+x.getCodice())%>"><%=x.getTitolo()%></a><br/>
<%
		}
	} else {
%>
	Nessun videogioco trovato
<%} %>

</div>

<%@include file="footer.jsp"%>						