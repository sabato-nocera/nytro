package nytro.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.model.bean.AccountBean;
import nytro.model.bean.CasaEditriceBean;
import nytro.model.bean.GiocatoreBean;

public class LogFilter implements Filter {
	
	//private final String[] inaccessibile = {"footer.html", "header.jsp"};
	private final String[] noRuolo = {"Ricerca","RicercaAjax","ricerca.jsp","Index","Login","LoginForm","RecuperaPassword","RegistrazioneForm","RegistrazioneUtente","VerificaUsername", "error.jsp", "VerificaIsin", "index.jsp", "loginForm.jsp", "registrazioneForm.jsp", "image"};
	private final String[] adminRuolo = {"Ricerca","RicercaAjax","ricerca.jsp","Index","AggiornaProfilo","Catalogo","CatalogoCasaEditrice","ListaCaseEditrici","RegistrazioneCasaEditrice", "RimozioneAccount", "VerificaIsin","ListaGiocatori","Logout","Profilo","VerificaUsername","Videogioco", "error.jsp", "index.jsp","profilo.jsp", "listaCaseEditrici.jsp", "listaGiocatori.jsp", "registrazioneCasaEditrice.jsp", "rimozioneAccount.jsp","videogioco.jsp", "image", "elencoCaseEditrici.jsp","ElencoCaseEditrici"};
	private final String[] giocatoreRuolo = {"Ricerca","RicercaAjax", "RicercaGiocatoreAjax", "ricerca.jsp","Index","AggiornaProfilo","AggiungiFriend","Catalogo","CatalogoCasaEditrice","Friendlist","GestoreCarrello", "VerificaIsin","Libreria","ElencoCaseEditrici","Logout","Profilo","RimuoviFriend","VerificaUsername","Videogioco", "error.jsp", "index.jsp","profilo.jsp", "carrello.jsp", "catalogo.jsp", "catalogoCasaEditrice.jsp", "friendlist.jsp", "libreria.jsp", "elencoCaseEditrici.jsp", "videogioco.jsp", "image","acquisti.jsp","Acquisti"};
	private final String[] aziendaRuolo = {"Ricerca","RicercaAjax","ricerca.jsp","Index","AggiornaProfilo","Logout","Profilo","Catalogo","CatalogoCasaEditrice","Pubblicazioni", "VerificaIsin", "VerificaUsername","Videogioco", "error.jsp", "index.jsp", "profilo.jsp","ElencoCaseEditrici", "catalogoCasaEditrice.jsp", "pubblicazioni.jsp", "videogioco.jsp", "image", "elencoCaseEditrici.jsp"};
    
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String dest = ((HttpServletRequest) request).getRequestURI();
		System.out.println("(1) Destinazione (filtro):" + dest);
		AccountBean account = (AccountBean) ((HttpServletRequest) request).getSession().getAttribute("account");
		
		if(dest.contains("/css/") || dest.contains("/js/")|| dest.contains("/img/")) {
			chain.doFilter(request, response);	
			return ;
		}
			
		
		if(account==null || account.getUsername()==null) {
			System.out.println("Account nullo");
			for(String x : noRuolo) {
				System.out.println(dest +" contiene "+x+" ?");
				if(dest.contains(x)){
					System.out.println("\tSi', faccio il chain.doFilter(.,.)");
					chain.doFilter(request, response);	
					return ;
				}
			}
		} else {
			System.out.println("Account loggato");
			int ruolo = account.getRuolo();
			if(ruolo==0) {
				System.out.println("Ruolo: "+ruolo);
				request.setAttribute("account", account);
				for(String x : adminRuolo) {
					System.out.println(dest +" contiene "+x+" ?");
					if(dest.contains(x)){
						System.out.println("\tSi', faccio il chain.doFilter(.,.)");
						chain.doFilter(request, response);	
						return ;
					}
				}
			} else if(ruolo==1) {
				request.setAttribute("account", (GiocatoreBean) account);
				System.out.println("Ruolo: "+ruolo);
				for(String x : giocatoreRuolo) {
					System.out.println(dest +" contiene "+x+" ?");
					if(dest.contains(x)){
						System.out.println("\tSi', faccio il chain.doFilter(.,.)");
						chain.doFilter(request, response);	
						return ;
					}
				}
			} else if(ruolo==2) {
				request.setAttribute("account", (CasaEditriceBean) account);
				System.out.println("Ruolo: "+ruolo);
				for(String x : aziendaRuolo) {
					System.out.println(dest +" contiene "+x+" ?");
					if(dest.contains(x)) {
						System.out.println("\tSi', faccio il chain.doFilter(.,.)");
						chain.doFilter(request, response);	
						return ;
					}
				}
			}
		}
		
		if(account==null || account.getUsername()==null) {
			if(dest.contains("Videogioco")){
				System.out.println("(2 1/2) Destinazione (filtro): rindirizzamento a LoginForm");
				((HttpServletResponse) response).sendRedirect("/NYTRO/LoginForm");	
				return ;
			}
		}
		
		System.out.println("(2) Destinazione (filtro): rindirizzamento ad index.jsp");
		((HttpServletResponse) response).sendRedirect("/NYTRO/jsp/index.jsp");
		//((HttpServletRequest) request).getRequestDispatcher("jsp/index.jsp").forward((HttpServletRequest) request, (HttpServletResponse) response);
		return ;
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}







