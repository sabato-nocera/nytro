<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import="nytro.model.bean.AccountBean"%>
<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Login"/>	</jsp:include>	
<link href="/NYTRO/css/RegStyle.css" type="text/css" rel="stylesheet">
<%  AccountBean account = (AccountBean) session.getAttribute("account");
	
	String message = (String) request.getAttribute("message");
		
%>
<div id="pagina">
<h1>Login</h1>
</div>
<div id="loginForm">
<form action="<%=response.encodeURL("/NYTRO/Login")%>" method="post">
	<input type="text" name="username" placeholder="Username*" class="login" required><br/>
	<input type="password" name="password" placeholder="Password*" class="login" required><br/>
	<input type="submit" value="Vai" class="sub">
	<p id="password" onclick="showFormRecuperoPassword()">Hai dimenticato la password?</p>
</form>
</div>
<div id="recuperoPassword">
<form name="formRecuperoPassword" action="<%=response.encodeURL("/NYTRO/RecuperaPassword")%>" method="post">
	<input type="text" name="username" placeholder="Username*" class="login" required> <br/>
	<input type="password" name="password" placeholder="Password*" oninput="validaPassword()" class="login" required> <br/>
	<input type="password" name="passwordConferma" placeholder="Password*" oninput="validaPassword()" class="login" required> <br/>	
	<p id="errorPssw"></p>
	<input type="text" name="emailRecupero" oninput="validaEmailRec()" placeholder="Email di recupero*" class="login" required>
	<p id="errorMailRec"></p>
	<p id="errorSub">Compila tutti i campi</p>	
	<input type="submit" value="Inoltra" class="sub" id="subID" disabled>
</form>
</div>

<%if(message!=null && !message.equals("")) {%>
	<span><%=message.toString()%></span>
<%} %>


<script>
	function showFormRecuperoPassword() {
		document.getElementById("recuperoPassword").style.display = "block";
	}
		
	var borderOk = '2px solid green';
	var borderNo = '2px solid red';
	var passwordOk = false;
	var emailOk = false;
	
	function checkForm() {
		if(passwordOk && emailOk) {
			document.getElementById("subID").disabled = false;
			document.getElementById("errorSub").innerHTML = "";
		} else {
			document.getElementById("subID").disabled = true;
			document.getElementById("errorSub").innerHTML = "Compila i campi obbligatori";
		}
	}
	
	function validaEmailRec() {
		var input = document.forms['formRecuperoPassword']['emailRecupero'];
		if (input.value.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/)) {
			input.style.border = borderOk;
			document.getElementById("errorMailRec").innerHTML = "";
			emailOk = true;
		} else {
			input.style.border = borderNo;
			document.getElementById("errorMailRec").innerHTML = "Indirizzo mail non valido";
			emailOk = false;
		}
		checkForm();
	}
	
	function validaPassword() {
		var inputpw = document.forms['formRecuperoPassword']['password'];
		var inputpwconf = document.forms['formRecuperoPassword']['passwordConferma'];
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
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	$("document").ready(function prova(){
		$("#login").addClass("selected");
	})
</script>



<%@include file="footer.jsp"%>							