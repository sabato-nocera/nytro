package nytro.control;

import java.io.IOException;

import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.exceptions.MyException;
import nytro.model.dao.AccountDAO;
import nytro.model.bean.GiocatoreBean;
import nytro.model.idao.IAccountDAO;
import nytro.other.Utils;

@WebServlet("/RegistrazioneUtente")
@MultipartConfig(maxFileSize = 16177215)
public class RegistrazioneUtente extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IAccountDAO accountDAO = new AccountDAO();

    public void setAccountDAO(IAccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GiocatoreBean utente = new GiocatoreBean();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConf = request.getParameter("passwordConferma");
        String email = request.getParameter("email");
        String emailRec = request.getParameter("emailRec");
        String phone = request.getParameter("phone");
        String date = request.getParameter("bornDate");
        String genere = request.getParameter("genere");
        boolean usernameOk, passwordOk, emailOk, emailRecOk, photoOk;

        System.out.println("Questo e' lo username : " + username);

        if (!username.matches("^[0-9a-zA-Z]+$") || username == null || username.equals("") || username.length() < 6) {
            usernameOk = false;
        } else usernameOk = true;

        if (password == null || password.equals("") || passwordConf == null || passwordConf.equals("") || !password.equals(passwordConf)
                || password.length() < 8 || password.toUpperCase().equals(password) || password.toLowerCase().equals(password)) {
            passwordOk = false;
        } else {
            passwordOk = true;
            password = Utils.generatePwd(password);
        }

        if (!email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$") || email == null || email.equals("")) {
            emailOk = false;
        } else emailOk = true;

        if (!emailRec.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$") || emailRec == null || emailRec.equals("")) {
            emailRecOk = false;
        } else emailRecOk = true;

        if (phone != null && !phone.matches("^\\d{10}$") || phone != null && phone.isEmpty()) {
            if (phone.length() > 0 && phone.length() < 10) {
                throw new MyException("Lunghezza cellulare sbagliata, diversa da 10");
            }
            phone = null;
        }

        if (date != null && date.length() != 10) {
            date = null;
        } else {
            int day = Integer.parseInt(date.substring(8, 10));
            int month = Integer.parseInt(date.substring(5, 7));
            int year = Integer.parseInt(date.substring(0, 4));

            if (date.isEmpty() || day > 31 || month > 12 || year < 1900 || year > Calendar.getInstance().get(Calendar.YEAR)) {
                date = null;
            }
        }


        if (genere != null && genere.equals("")) {
            genere = null;
        }

        if (request.getPart("photo") != null && (request.getPart("photo").getSize() == 0
                || !Utils.checkImg(request.getPart("photo").getSubmittedFileName()))) {
            photoOk = false;
        } else {
            photoOk = true;
        }

        if (usernameOk && passwordOk && emailOk && emailRecOk && photoOk) {
            utente.setUsername(username);
            utente.setPassword(password);
            utente.setEmail(email);
            utente.setEmailRecupero(emailRec);
            utente.setCellulare(phone);
            utente.setRuolo(1);
            utente.setGenere(genere);
            utente.setDataNascita(date);
            utente.setIp(request.getRemoteAddr());
            if (request.getPart("photo") != null) {
                utente.setImgProfilo(request.getPart("photo").getInputStream());
            }

            try {
                accountDAO.doSaveGiocatore(utente);
                if (request.getPart("photo") != null && request.getPart("photo").getSize() > 0
                        && Utils.checkImg(request.getPart("photo").getSubmittedFileName()))
                    accountDAO.doUploadImage(utente);
                String message = "Registrazione effettuata con successo";
                request.setAttribute("message", message);
                String url = response.encodeURL("Index");
                RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                dispatcher.forward(request, response);
            } catch (SQLException exception) {
                String url = response.encodeURL("jsp/registrazioneForm.jsp");
                RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
        } else {
            String url = response.encodeURL("jsp/registrazioneForm.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
