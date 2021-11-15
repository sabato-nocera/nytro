<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"><jsp:param name="pageTitle" value="Registrazione casa editrice"/></jsp:include>
<link href="/NYTRO/css/RegStyle.css" type="text/css" rel="stylesheet">
<div id="pagina">
<h1>Registrazione casa editrice</h1>
</div>
<div id="registrazioneCasa">
<form class="box" name="registrazione" action="<%=response.encodeURL("/NYTRO/RegistrazioneCasaEditrice")%>" method="post" enctype="multipart/form-data">
	<input class="registrazione" name="nomeCasaEditrice" type="text" placeholder="Nome Azienda*" oninput="checkName()">
	<input class="registrazione" name="ISIN" type="text" placeholder="ISIN*" oninput="validaISIN()">
	<p id="errorISIN"></p>
	<input class="registrazione" name="nomeCEO" type="text" placeholder="Nome CEO*" oninput="checkCEO()" required>
	<input class="registrazione" name="username" type="text" oninput="validaUsername()" placeholder="Username*"> 
	<p id="errorUsr"></p>  
	<input class="registrazione" type="password" name="password" oninput="validaPassword()" placeholder="Password*"> 
	<input class="registrazione" type="password" name="passwordConferma" oninput="validaPassword()" placeholder="Conferma Password*"> 
	<p id="errorPssw"></p>
	<input class="registrazione" type="text" name="email" oninput="validaEmail()" placeholder="Email*">	
	<p id="errorMail"></p>
	<input class="registrazione" type="text" name="emailRec" oninput="validaEmailRec()" placeholder="Email di recupero*">
	<p id="errorMailRec"></p>
	<input class="registrazione" id="telefono" type="tel" name="phone" oninput="validaTelefono()" oninput="checkPhoneField()" placeholder="Cellulare">
	<p id="errorPhone"></p>
	<input class="registrazione" name="sitoWeb" type="text" placeholder="Sito Web*" oninput="checkSite()" required>
	<p id="errorSite"></p>
	<p>Se vuoi, inserisci un'immagine del profilo (max 16MB)</p>  
    <input type="file" name="photo" size="50">
	<p id="errorSub">Compila tutti i campi obbligatori</p>
	<input type="submit" value="Registra" id="sub" disabled>
</form>
</div>


<script type="text/javascript">

var borderOk = '2px solid green';
var borderNo = '2px solid red';
var usernameOk = false;
var passwordOk = false;
var emailOk = false;
var emailRecOk = false;
var phoneOk = true;
var isinOk = false;
var nameOk = false;
var sitoOk = false;
var stdBorder = document.forms['registrazione']['phone'].style.border;

function validaUsername() {
	var ok = "<ok/>";
	var input = document.forms['registrazione']['username'];
	if (input.value.length >= 6 && input.value.match(/^[0-9a-zA-Z]+$/)) {
		// verifica se esiste un utente con lo stesso username
	
		var xmlHttpReq = new XMLHttpRequest();
		xmlHttpReq.onreadystatechange = function() {
			if (xmlHttpReq.readyState == 4 && xmlHttpReq.status == 200 && xmlHttpReq.responseText == ok) {
				input.style.border = borderOk;
				document.getElementById("errorUsr").innerHTML = "";
				usernameOk = true;
			} else if(xmlHttpReq.readyState == 4 && xmlHttpReq.status == 200 && xmlHttpReq.responseText != ok){
				input.style.border = borderNo;
				document.getElementById("errorUsr").innerHTML = "Attenzione! Questo Username esiste gia'.";
				usernameOk = false;
			}
			checkForm();
		} 
		xmlHttpReq.open("GET", "/NYTRO/VerificaUsername?username=" + encodeURIComponent(input.value), true);
		xmlHttpReq.send();
	}
	else {
		input.style.border = borderNo;
		document.getElementById("errorUsr").innerHTML = "Attenzione! Deve contenere almeno 6 caratteri e non puo' contenere caratteri speciali come: &./_-";
		usernameOk = false;
		checkForm();
	}
}


function checkName() {
	var input = document.forms['registrazione']['nomeCasaEditrice'];
	if(input.value == "") {
		input.style.border = borderNo;
		nameOk = false;
	} else {
		input.style.border = borderOk;
		nameOk = true;
	}
	checkForm();
}

function checkCEO() {
	var input = document.forms['registrazione']['nomeCEO'];
	if(input.value == "") {
		input.style.border = borderNo;
	} else {
		input.style.border = borderOk;
	}
}

function checkSite() {
	var input = document.forms['registrazione']['sitoWeb'];
	
	if (input.value.match(/^(?:http(s)?:\/\/)?[\w.-]+(?:\.[\w\.-]+)+[\w\-\._~:/?#[\]@!\$&\(\)\*\+,;=.]+$/)) {
		//https://www.regextester.com/94502
		input.style.border = borderOk;
		document.getElementById("errorSite").innerHTML = "";
		sitoOk = true;
	} else {
		input.style.border = borderNo;
		document.getElementById("errorSite").innerHTML = "Sito web non valido";
		sitoOk = false;
	}
	checkForm();
}

function validaISIN() {
	var input = document.forms['registrazione']['ISIN'];
	var ok = "<ok/>";
	if(/^\w{12}$/.test(input.value)) {		
		var xmlHttpReq = new XMLHttpRequest();
		xmlHttpReq.onreadystatechange = function() {
			if (xmlHttpReq.readyState == 4 && xmlHttpReq.status == 200 && xmlHttpReq.responseText == ok) {
				input.style.border = borderOk;
				document.getElementById("errorISIN").innerHTML = "";
				isinOk  = true;
			} else if(xmlHttpReq.readyState == 4 && xmlHttpReq.status == 200 && xmlHttpReq.responseText != ok){
				input.style.border = borderNo;
				document.getElementById("errorISIN").innerHTML = "Attenzione! Questo ISIN e' gia' utilizzato.";
				isinOk  = false;
			}
			checkForm();
		} 
		xmlHttpReq.open("GET", "/NYTRO/VerificaIsin?isin=" + encodeURIComponent(input.value), true);
		xmlHttpReq.send();
	} else {
		input.style.border = borderNo;
		document.getElementById("errorISIN").innerHTML = "Formato ISIN errato";
		isinOk = false;
		checkForm();
	}
	checkForm();
}

function validaPassword() {
	var inputpw = document.forms['registrazione']['password'];
	var inputpwconf = document.forms['registrazione']['passwordConferma'];
	var password = inputpw.value;
	if (password.length >= 8 && password.toUpperCase() != password
			&& password.toLowerCase() != password && /[0-9]/.test(password)) {
		inputpw.style.border = borderOk;

		if (password == inputpwconf.value) {
			inputpwconf.style.border = borderOk;
			document.getElementById("errorPssw").innerHTML = "";
			passwordOk = true;
		} else {
			inputpwconf.style.border = borderNo;
			document.getElementById("errorPssw").innerHTML = "Le due password devono coincidere"
			passwordOk = false;
		}
	} else {
		inputpw.style.border = borderNo;
		inputpwconf.style.border = borderNo;
		document.getElementById("errorPssw").innerHTML = "La password deve contenere almeno una maiuscola, un numero e almeno 8 caratteri";
		passwordOk = false;
	}
	checkForm();
}


function validaEmail() {
	var input = document.forms['registrazione']['email'];
	if (input.value.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/)) {
		input.style.border = borderOk;
		document.getElementById("errorMail").innerHTML = "";
		emailOk = true;
	} else {
		input.style.border = borderNo;
		document.getElementById("errorMail").innerHTML = "Indirizzo mail non valido";
		emailOk = false;
	}
	checkForm();
}

function validaEmailRec() {
	var input = document.forms['registrazione']['emailRec'];
	if (input.value.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/)) {
		input.style.border = borderOk;
		document.getElementById("errorMailRec").innerHTML = "";
		emailRecOk = true;
	} else {
		input.style.border = borderNo;
		document.getElementById("errorMailRec").innerHTML = "Indirizzo mail non valido";
		emailRecOk = false;
	}
	checkForm();
}


function validaTelefono() {
	var input = document.forms['registrazione']['phone'];
	if(input.value.match(/^\d{10}$/)) {
		input.style.border = borderOk;
		document.getElementById("errorPhone").innerHTML = "";
		document.getElementById("sub").disabled = false;
		phoneOk = true;
	} else {
		input.style.border = borderNo;
		document.getElementById("errorPhone").innerHTML = "Formato sbagliato";
		document.getElementById("sub").disabled = true;
		phoneOk = false;
	}
	checkForm();
}

function checkPhoneField() {
	var input = document.forms['registrazione']['phone'];
	if(!phoneOk) {
		document.getElementById("errorPhone").innerHTML = "";
		input.style.border = stdBorder;
		input.value = null;
		phoneOk = true;
		checkForm();
	}
}
 


function checkForm() {
	if(usernameOk && passwordOk && emailOk && emailRecOk && phoneOk && nameOk && isinOk && sitoOk) {
		document.getElementById("sub").disabled = false;
		document.getElementById("errorSub").innerHTML = "";
	} else {
		document.getElementById("sub").disabled = true;
		document.getElementById("errorSub").innerHTML = "Compila i campi obbligatori";
	}
	
}	

</script>

<%@include file="footer.jsp"%>	