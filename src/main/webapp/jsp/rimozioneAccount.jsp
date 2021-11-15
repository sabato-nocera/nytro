<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="header.jsp"><jsp:param name="pageTitle" value="Rimozione account"/></jsp:include>
<link href="/NYTRO/css/RegStyle.css" type="text/css" rel="stylesheet">
<div id="pagina">
<h1>Ricerca e rimuovi un account</h1>
</div>
<div id="rimozioneForm">
<form class="box" name="rimozione" action="<%=response.encodeURL("/NYTRO/RimozioneAccount")%>" method="post">
	<input type="text" class="registrazione" name="username" id ="username" required>
	<p id="errorUsr"></p>
	<p id="usrn" style="color:black"></p>
	<p id="email" style="color:black"></p>
	<p id="ruolo" style="color:black"></p>
	<input type="button" value="Ricerca Account" id="button" onclick="checkUsername()">
	<input type="submit" value="Rimuovi Account" id="sub" hidden='true' disabled>

</form>
</div>


	<script>
	
		var borderOk = '2px solid green';
		var borderNo = '2px solid red';
		var usernameOk = false;
		
		function checkUsername() {
			var no = "<no/>";
			var input = document.forms['rimozione']['username'];
			if (input.value.length >= 6 && input.value.match(/^[0-9a-zA-Z]+$/)) {
				// verifica se esiste un utente con lo stesso username
			
				var xmlHttpReq = new XMLHttpRequest();
				xmlHttpReq.onreadystatechange = function() {
					if(xmlHttpReq.readyState == 4 && xmlHttpReq.status == 200 && xmlHttpReq.responseText == no){					
						input.style.border = borderNo;
						document.getElementById("errorUsr").innerHTML = "Questo Account Non Esiste!";
						document.getElementById("usrn").innerHTML = "";
						document.getElementById("email").innerHTML = "";
						document.getElementById("ruolo").innerHTML = "";
						document.getElementById("sub").hidden = true;
						document.getElementById("sub").disabled = true;
						usernameOk = false;
					} else if (xmlHttpReq.readyState == 4 && xmlHttpReq.status == 200 && xmlHttpReq.responseText != no) {
							try {
								var doc = xmlHttpReq.responseXML;
								var usrn = doc.getElementsByTagName("username")[0].firstChild.nodeValue;
								var email = doc.getElementsByTagName("email")[0].firstChild.nodeValue;
								var ruolo = doc.getElementsByTagName("ruolo")[0].firstChild.nodeValue;
								document.getElementById("usrn").innerHTML = "Username: " + usrn;
								document.getElementById("email").innerHTML = "Email: " + email;
								document.getElementById("ruolo").innerHTML = "Account: " + ruolo;
								document.getElementById("errorUsr").innerHTML = "Account Trovato!";
								document.getElementById("sub").hidden = false;
								document.getElementById("sub").disabled = false;
							} catch(e1) {
								document.getElementById("errorUsr").innerHTML = e1;
							}
							
							input.style.border = borderOk;
							usernameOk = true;
					} 
					
				} 
				xmlHttpReq.open("GET", "/NYTRO/RimozioneAccount?username=" + encodeURIComponent(input.value) + "&" + "option=1", true);
				xmlHttpReq.send();
			}
			else {
				input.style.border = borderNo;
				document.getElementById("errorUsr").innerHTML = "Attenzione! Deve contenere almeno 6 caratteri";
				document.getElementById("usrn ").innerHTML = "";
				document.getElementById("email").innerHTML = "";
				document.getElementById("ruolo").innerHTML = "";
				document.getElementById("sub").hidden = true;
				document.getElementById("sub").disabled = true;
				usernameOk = false;
				
			}

		}
	
	
	</script>
	
<%@include file="footer.jsp"%>	