<%@page import="nytro.model.bean.CasaEditriceBean" %>
<%@page import="java.time.LocalDate" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         import="nytro.model.bean.VideogiocoBean, java.util.Collection, nytro.model.bean.AccountBean, nytro.model.bean.RecensioneBean, nytro.model.bean.GiocatoreBean" %>
<%@ page import="java.util.List" %>
<%
    VideogiocoBean videogiocoDetailed = (VideogiocoBean) request.getAttribute("videogiocoDetailed");
    Collection<RecensioneBean> recensioni = (Collection<RecensioneBean>) request.getAttribute("recensioni");
    Collection<AccountBean> amici = (Collection<AccountBean>) request.getAttribute("amici");
    AccountBean account = (AccountBean) session.getAttribute("account");
    String possibileAggiungereAllaLibreria = (String) request.getAttribute("possibileAggiungereAllaLibreria");
    String possibileAggiungereAgliAcquisti = (String) request.getAttribute("possibileAggiungereAgliAcquisti");
    List<String> consoleDisponibili = (List<String>) request.getAttribute("consoleDisponibili");
    if (videogiocoDetailed == null) {
        String url = response.encodeURL("Videogioco");
        response.sendRedirect(url);
        return;
    }
%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="<%=videogiocoDetailed.getTitolo() %>"/>
</jsp:include>
<!-- Inclusione dinamica di header.jsp" -->
<link href="/NYTRO/css/videogiocoStyle.css" type="text/css" rel="stylesheet">
<div id="pagina">
    <h1><%=videogiocoDetailed.getTitolo() %>
    </h1>
</div>
<div id="informazioni">
    <div id="video">
        <iframe width="100%" height="100%" frameBorder="0" src="<%=videogiocoDetailed.getTrailer()%>"></iframe>
    </div>
    <div id="dettagli">
        <%if (videogiocoDetailed.getImg() != null) {%>
        <img id="img" src="<%=response.encodeURL("/NYTRO/image?codice="+videogiocoDetailed.getCodice())%>"
             alt="<%=videogiocoDetailed.getTitolo()%>">
        <%} else { %>
        <img id="img" src="/NYTRO/img/no-cover.jpg" alt="<%=videogiocoDetailed.getTitolo()%>">
        <%} %>
        <a href=<%=response.encodeURL("/NYTRO/CatalogoCasaEditrice?isinCasaEditrice=" + videogiocoDetailed.getISIN())%>>
            <h2><%=request.getAttribute("nomeCasaEd") %>
            </h2></a>
        <p>
            <b>Genere: </b><%=videogiocoDetailed.getGeneri().toString().substring(1, (videogiocoDetailed.getGeneri().toString().length() - 1))%>
        </p>
        <p>
        <p>
            <b>Console: </b><%=videogiocoDetailed.getConsole().toString().substring(1, (videogiocoDetailed.getConsole().toString().length() - 1))%>
        </p>
        <p>
        <p>
        <p><b>Voto: </b><%=videogiocoDetailed.getVotoMedio() %> ★</p>
        <p><b>PEGI: </b><%=videogiocoDetailed.getPEGI() %>
        </p>
        <%if (account.getRuolo() == 1 && !amici.isEmpty()) {%>
        <h4>Alcuni tuoi amici lo hanno gia'!</h4>
        <%for (AccountBean x : amici) {%>
        <%if (x.getImgProfilo() != null) {%>
        <img class="prof" src="<%=response.encodeURL("/NYTRO/image?id="+x.getUsername())%>" alt="<%=x.getUsername()%>"
             title="<%=x.getUsername()%>">
        <%} else { %>
        <img class="prof" src="/NYTRO/img/default-profile.png" alt="<%=x.getUsername()%>" title="<%=x.getUsername()%>">
        <%} %>
        <%} %>
        <%}%>
        <p>Rilasciato il <%=videogiocoDetailed.getDataRilascio() %>
        </p>
        <h2 style="font-size: 20px;">

            <%
                if (account.getRuolo() == 1) {
                    GiocatoreBean giocatore = (GiocatoreBean) request.getAttribute("account");
                    String url = response.encodeURL("GestoreCarrello");
                    if (possibileAggiungereAllaLibreria != null && possibileAggiungereAllaLibreria.equalsIgnoreCase("true")) {
                        if (videogiocoDetailed.getPEGI() < 18) {
            %><a href="<%=response.encodeURL("Libreria?aggiungiVideogioco="+videogiocoDetailed.getCodice())%>">Inserisci
            nella libreria</a><br/><%
        } else {
            if (giocatore.getDataNascita() == null) {
        %><a href="<%=response.encodeURL("/NYTRO/Profilo")%>"><span>necessario inserire la data di nascita per procedere
            ad insetire il videogioco nella libreria.</span><br></a><%
        } else {
            LocalDate data = LocalDate.parse(giocatore.getDataNascita());
            if (LocalDate.now().getYear() - data.getYear() < 18) {
        %><span>necessario avere almeno 18 anni per inserire il videogioco nella libreria.</span>><br/><%
        } else {
        %><a href="<%=response.encodeURL("Libreria?aggiungiVideogioco="+videogiocoDetailed.getCodice())%>">Inserisci
            nella libreria</a><br/><%
                        }
                    }
                }
            }

            if (possibileAggiungereAgliAcquisti != null && possibileAggiungereAgliAcquisti.equalsIgnoreCase("true")) {
                if (videogiocoDetailed.getPEGI() < 18) {
        %>
            <form action="<%=url%>" method="get">
				<input type="hidden" name="action" value="addCart"/>
				<input type="hidden" name="codiceVideogioco" value="<%=videogiocoDetailed.getCodice()%>"/>
                <label>Seleziona una console
                    <select name="consoleScelta">
                        <%
                            for (String console : consoleDisponibili) {
                        %>
                        <option value="<%=console%>"><%=console%>
                        </option>
                        <%
                            }
                        %>
                    </select>
                </label>
                <span class="identita">
									<input type="submit" value="Inserisci nel carrello"/>
								</span>
            </form>
            <%
            } else {
                if (giocatore.getDataNascita() == null) {
            %><a href="<%=response.encodeURL("/NYTRO/Profilo")%>"><span>necessario inserire la data di nascita per procedere ad acquistare il videogioco</span><br></a><%
        } else {
            LocalDate data = LocalDate.parse(giocatore.getDataNascita());
            if (LocalDate.now().getYear() - data.getYear() < 18) {
        %><span>necessario avere almeno 18 anni per procedere ad acquistare il videogioco. </span><br/><%
        } else {
        %>
            <form action="<%=url%>" method="get">
				<input type="hidden" name="action" value="addCart"/>
				<input type="hidden" name="codiceVideogioco" value="<%=videogiocoDetailed.getCodice()%>"/>
                <label>Seleziona una console
                    <select name="consoleScelta">
                        <%
                            for (String console : consoleDisponibili) {
                        %>
                        <option value="<%=console%>"><%=console%>
                        </option>
                        <%
                            }
                        %>
                    </select>
                </label>
                <span class="identita">
									<input type="submit" value="Inserisci nel carrello"/>
								</span>
            </form>
            <br/><%
                            }
                        }
                    }
                }
            }
        %>

        </h2>
    </div>
    <div id="recensioni">
        <h2>Recensioni</h2>
        <div id="rilasciate">
            <p>
                    <%if(recensioni!=null){
	%>
            <div id="ordina">
                <form action="<%=response.encodeURL("/NYTRO/Videogioco")%>" method="get">
                    <input type="hidden" name="codiceVideogioco" value="<%=videogiocoDetailed.getCodice()%>">
                    <!-- Mi serve perche' se no perdo il codice del videogioco -->
                    <label>Seleziona un criterio di ordinamento
                        <select name="orderRecensioni">
                            <option value="" selected>Nessuno</option>
                            <option value="Num_Recensione">Numero di recensione del videogioco</option>
                            <option value="Username">Username di chi ha rilasciato la recensione</option>
                            <option value="Voto">Voto della recensione</option>
                        </select>
                    </label>
                    <input type="hidden" name="rangeRecensione" value="true">
                    <label>Voto minimo:<input type="number" name="min" min="1" max="5" step="1" value="1"
                                              required></label>
                    <label>Voto massimo:<input type="number" name="max" min="1" max="5" step="1" value="5"
                                               required></label>
                    <input type="submit" value="Vai"/>
                </form>
            </div>
            <%
                for (RecensioneBean x : recensioni) {%>
            <div class="recensione">
                <div class="identita">
                    <p><b><%=x.getVoto() %> ★</b></p>
                    <h2><%=x.getUsername()%>
                    </h2>
                    <%if (x.getUsername().equals(account.getUsername())) {%>
                    <form action="<%=response.encodeURL("/NYTRO/Videogioco")%>" method="post">
                        <input type="hidden" name="codiceVideogioco" value="<%=videogiocoDetailed.getCodice()%>">
                        <!-- Mi serve perche' se no perdo il codice del videogioco -->
                        <input type="hidden" name="rimuovereRecensione" value="true">
                        <input type="submit" value="Rimuovi recensione">
                    </form>
                    <%} %>
                </div>
                <div class="commento">
                    <p><%if (x.getCommento().length() > 0) {%>
                        <%=x.getCommento() %>
                        <%} else { %>
                        <i>Il giocatore non ha rilasciato un commento per la recensione.</i>
                        <%} %>
                    </p>
                </div>
            </div>
            <% }
            }
            %>
            </p>
        </div>
        <div id="azioni">
            <%if (account.getRuolo() == 1) {%>
            <h3 class="opzione" onclick='show(document.getElementById("insRecensione"))'>Inserisci recensione</h3>

            <form id="insRecensione" action="<%=response.encodeURL("/NYTRO/Videogioco")%>" method="post">
                <input type="hidden" name="codiceVideogioco" value="<%=videogiocoDetailed.getCodice()%>">
                <!-- Mi serve perche' se no perdo il codice del videogioco -->
                <label>Commento (max 200 caratteri): </label><textarea maxlength="200" rows="4" cols="50"
                                                                       name="commentoRecensione" required> </textarea>
                <label>Voto: <input type="number" name="votoRecensione" min="1" max="5" step="0.25" required></label>
                <input type="submit" value="Inserisci" id="insert">
            </form>

            <%} %>

            <%
                if (account.getRuolo() == 2) {
                    CasaEditriceBean casa = (CasaEditriceBean) request.getAttribute("account");
                    if (casa.getISIN().equals(videogiocoDetailed.getISIN())) {
            %>
            <h3 class="opzione" onclick='show(document.getElementById("cambiaImg"))'>Cambia immagine del videogioco</h3>

            <form id="cambiaImg"
                  action="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+videogiocoDetailed.getCodice())%>"
                  method="post" enctype="multipart/form-data">
                <input type="hidden" name="cambiaImmagineVideogioco" value="true" required>
                <input type="file" name="photo" size="50"/>
                <input type="submit" value="Vai">
            </form>

            <h3 class="opzione" onclick='show(document.getElementById("cambiaTrailer"))'>Cambia trailer</h3>

            <form id="cambiaTrailer"
                  action="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+videogiocoDetailed.getCodice())%>"
                  method="post">
                <input type="text" name="cambiaTrailer" required placeholder="Trailer*"/>
                <input type="submit" value="Vai">
            </form>

            <h3 class="opzione" onclick='show(document.getElementById("addGenere"))'>Aggiungi genere</h3>

            <form id="addGenere" action="<%=response.encodeURL("/NYTRO/Videogioco")%>" method="post"
                  enctype="multipart/form-data">
                <input type="hidden" name="codiceVideogioco" value="<%=videogiocoDetailed.getCodice()%>">
                <input type="hidden" name="cambiaGenere" value="true">
                <input type="text" name="newGenere" placeholder="Genere*" required/>
                <input type="submit" value="Vai">
            </form>


            <h3 class="opzione" onclick='show(document.getElementById("cambiaPrezzo"))'>Cambia prezzo</h3>

            <form id="cambiaPrezzo" action="<%=response.encodeURL("/NYTRO/Videogioco")%>" method="post"
                  enctype="multipart/form-data">
                <input type="hidden" name="codiceVideogioco" value="<%=videogiocoDetailed.getCodice()%>">
                <input type="hidden" name="cambiaPrezzo" value="true">
                <input type="number" name="newPrezzo" min="0" step="0.01" required/>
                <input type="submit" value="Vai">
            </form>



            <%}%>
            <%} %>
        </div>
    </div>
</div>
<script>
    function show(elem) {
        if (elem.style.display == "none")
            elem.style.display = "block";
        else
            elem.style.display = "none";
    }
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
    $("document").ready(function prova() {
        $("#esplora").addClass("selected");
    })
</script>
<%@include file="footer.jsp" %>