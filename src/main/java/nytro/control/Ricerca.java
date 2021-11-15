package nytro.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.exceptions.MyException;
import nytro.model.bean.VideogiocoBean;
import nytro.model.dao.VideogiocoDAO;
import nytro.model.idao.IVideogiocoDAO;

@WebServlet("/Ricerca")
public class Ricerca extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();


    public void setVideogiocoDAO(IVideogiocoDAO videogiocoDAO) {
        this.videogiocoDAO = videogiocoDAO;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<VideogiocoBean> videogiochi;

        String testoParziale = request.getParameter("testoParziale");
        if (testoParziale == null || testoParziale.equals(""))
            throw new MyException("Testo parziale ricerca nullo o vuoto");

        try {
            videogiochi = videogiocoDAO.doRetrieveByTitolo(testoParziale);
        } catch (SQLException e) {
            throw new MyException("Errore servlet Ricerca");
        }


        request.setAttribute("listaVideogiochi", videogiochi);
        String url = response.encodeURL("jsp/ricerca.jsp");
        request.getRequestDispatcher(url).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
