package nytro.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.model.bean.AccountBean;
import nytro.model.dao.AccountDAO;
import nytro.model.idao.IAccountDAO;

/**
 * Servlet implementation class RimozioneAccount
 */
@WebServlet("/RimozioneAccount")
public class RimozioneAccount extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IAccountDAO accountDAO = new AccountDAO();

    public void setAccountDAO(IAccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        AccountBean bean;
        response.setContentType("text/xml");
        String option = request.getParameter("option");
        if (username != null && !username.equals("")) {
            if (option != null && Integer.parseInt(option) == 1) {
                try {
                    StringBuffer buffer = new StringBuffer();
                    bean = accountDAO.doRetrieveByUsername(username);
                    if (bean != null && bean.getUsername() != null) {
                        buffer.append("<ok>");
                        buffer.append("<username>");
                        buffer.append(bean.getUsername());
                        buffer.append("</username>");
                        buffer.append("<email>");
                        buffer.append(bean.getEmail());
                        buffer.append("</email>");
                        int ruolo = bean.getRuolo();
                        buffer.append("<ruolo>");
                        if (ruolo == 1) {
                            buffer.append("Giocatore");
                        } else if (ruolo == 2) {
                            buffer.append("Casa Editrice");
                        }
                        buffer.append("</ruolo>");
                        buffer.append("</ok>");
                        response.getWriter().write(buffer.toString());

                    } else {
                        response.getWriter().append("<no/>");
                    }

                } catch (SQLException e) {
                    response.getWriter().append("<no/>");
                }
            } else {
                try {
                    AccountBean account = accountDAO.doRetrieveByUsername(username);
                    if (account.getUsername() == null) {
                        String url = response.encodeURL("jsp/rimozioneAccount.jsp");
                        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                        dispatcher.forward(request, response);
                    } else {
                        accountDAO.doDelete(account);
                        String url = response.encodeURL("jsp/index.jsp");
                        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                        dispatcher.forward(request, response);
                    }
                } catch (SQLException exception) {
                    String url = response.encodeURL("jsp/rimozioneAccount.jsp");
                    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                    dispatcher.forward(request, response);
                }
            }
        } else {
            response.getWriter().append("<no/>");
        }

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
