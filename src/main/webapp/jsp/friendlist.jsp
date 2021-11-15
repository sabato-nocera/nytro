<%@page import="org.apache.tomcat.util.codec.binary.Base64" %>
<%@page import="java.awt.image.BufferedImage" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="java.util.Collection, nytro.model.bean.AccountBean" %>
<%
    Collection<AccountBean> amici = (Collection<AccountBean>) request.getAttribute("amici");
    Boolean possessori = (Boolean) request.getAttribute("possessori");
%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Friendlist"/>
</jsp:include>
<!-- Inclusione dinamica di header.jsp" -->
<link href="/NYTRO/css/friendlistStyle.css" type="text/css" rel="stylesheet">
<div id="lista">

    <div id="pagina">
        <%if (!possessori) {%>
        <h1>Lista amici</h1>
        <%} else {%>
        <h1>Lista amici possessori del videogioco</h1>
        <%}%>
    </div>
    <%if (amici != null) {%>
    <div id="tabella">
        <%
            for (AccountBean x : amici) {
        %>
        <ul>
            <div class="amico">
                <li>
                    <div class="profilo">
                        <%if (x.getImgProfilo() != null) {%>
                        <img src="/NYTRO/image?id=<%= x.getUsername()%>" alt="<%=x.getUsername()%>">
                        <%} else {%>
                        <img src="/NYTRO/img/default-profile.png" alt="<%=x.getUsername()%>">
                        <%} %></div>
                </li>
                <%if (!possessori) {%>
                <li>
                    <form action="<%=response.encodeURL("/NYTRO/Friendlist")%>" method="post">
                        <input type="hidden" name="eliminatoAmico" value="<%=x.getUsername()%>">
                        <input type="submit" value="Rimuovi">
                    </form>
                </li>
                <%}%>
                <li>
                    <form action="<%=response.encodeURL("/NYTRO/Libreria")%>" method="post">
                        <input type="hidden" name="libreriaAmicoDaVisualizzare" value="<%=x.getUsername()%>">
                        <input type="submit" value="Visualizza libreria">
                    </form>
                </li>
                <li class="username"><%=x.getUsername() %>
                </li>
                <li><%=x.getData() %> <%=x.getOra() %>
                </li>
            </div>
        </ul>
        <%
                }
            }
        %>
        <% if (amici.isEmpty()) { %>
        <div id="vuoto">
            <%if (possessori) {%>
            <h1>Non ci sono amici nella tua friendlist che possiedono questo gioco.</h1>
            <%} else {%>
            <h1>Non hai ancora amici nella tua lista.</h1>
            <p>Aggiungine subito uno!</p>
            <%}%>
        </div>
        <%} %>
    </div>
    <%if (!possessori) {%>
    <div id="aggiungi">
        <form action="<%=response.encodeURL("/NYTRO/Friendlist")%>" method="post">
            <label>Inserisci l'username del giocatore da aggiungere:</label>
            <input type="text" name="futuroAmico" list="ricerca-datalist-2" placeholder="Username" onkeyup="ricercaGiocatore(this.value)"> <!-- value="<c:out value="${param.q}" />" -->
            <datalist id="ricerca-datalist-2"></datalist>
            <input type="submit" value="Aggiungi">
        </form>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script>
            $("document").ready(function prova() {
                $("#friendlist").addClass("selected");
            })
        </script>
    </div>
    <%}%>
</div>
<%@include file="footer.jsp" %>