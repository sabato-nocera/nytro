function ricerca(str) {
	//Il passaggio del parametro funziona
	var dataList = document.getElementById('ricerca-datalist');	//appendare' da qua delle <option> per <datalist id="ricerca-datalist">
	
	if (str.length == 0) {
		// rimuove elementi <option> (suggerimenti) esistenti
		dataList.innerHTML = '';
		return;
	}

	var xmlHttpReq = new XMLHttpRequest();
	xmlHttpReq.responseType = 'json';
	xmlHttpReq.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			// rimuove elementi <option> (suggerimenti) esistenti
			dataList.innerHTML = '';
			
			for ( var i in this.response) {				
				// crea un elemento option
				var option = document.createElement('option');
				// setta il valore
				option.value = this.response[i];
				// aggiunge elemento <option> a datalist
				dataList.appendChild(option);
			}
		}
	}
	xmlHttpReq.open("GET", "RicercaAjax?testoParziale=" + encodeURIComponent(str), true);
	xmlHttpReq.send();
}

function ricercaGiocatore(str) {
	//Il passaggio del parametro funziona
	var dataList = document.getElementById('ricerca-datalist-2');	//appendare' da qua delle <option> per <datalist id="ricerca-datalist">

	if (str.length == 0) {
		// rimuove elementi <option> (suggerimenti) esistenti
		dataList.innerHTML = '';
		return;
	}

	var xmlHttpReq = new XMLHttpRequest();
	xmlHttpReq.responseType = 'json';
	xmlHttpReq.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			// rimuove elementi <option> (suggerimenti) esistenti
			dataList.innerHTML = '';

			for ( var i in this.response) {
				// crea un elemento option
				var option = document.createElement('option');
				// setta il valore
				option.value = this.response[i];
				// aggiunge elemento <option> a datalist
				dataList.appendChild(option);
			}
		}
	}
	xmlHttpReq.open("GET", "RicercaGiocatoreAjax?futuroAmico=" + encodeURIComponent(str), true);
	xmlHttpReq.send();
}