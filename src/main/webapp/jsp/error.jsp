<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" import="java.util.Enumeration"%>
<jsp:include page="header.jsp"> <jsp:param name="pageTitle" value="Errore"/> </jsp:include>
	<div id="errorePagina">
		<h1>Oops... si e' verificato un errore! :(</h1>
	</div>
	<div id="errore">
		<h1>Errore <%=response.getStatus() %></h1>
		<p><%
			if (exception != null) {
				out.flush();
				response.getWriter().println(exception.getMessage());
			}
		%></p>
	</div>
<%@include file="footer.jsp"%>	