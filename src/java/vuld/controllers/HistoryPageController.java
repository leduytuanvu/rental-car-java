/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuld.controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vuldt.daos.HistoryDAO;
import vuldt.dtos.HistoryDTO;
import vuldt.dtos.UserDTO;

/**
 *
 * @author Le Duy Tuan Vu
 */
public class HistoryPageController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            HistoryDAO daoHistory = new HistoryDAO();
            UserDTO user = new UserDTO();
            if (session.getAttribute("USER") != null) {
                user = (UserDTO) session.getAttribute("USER");
                ArrayList<HistoryDTO> list = daoHistory.getHistory(user.getUserID(), 1);
                session.setAttribute("LIST_HISTORY", list);
                int numberPage = 0, numberOrder = 0;
                numberOrder = daoHistory.getNumAllHistory(user.getUserID());
                numberPage = numberOrder / 10;
                if (numberOrder % 10 != 0) {
                    numberPage++;
                }
                session.setAttribute("NUM_PAGE_HISTORY", numberPage);
                session.setAttribute("PAGING_NUM", 1);

                session.setAttribute("RADIO", null);
                session.setAttribute("DATE_SEARCH_ORDER", null);

                session.setAttribute("DATE_SEARCH_ORDER", "");
                session.setAttribute("NAME_SEARCH_ORDER", "");
            }
        } catch (Exception e) {
            log("Error in HistoryPageController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("history.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
