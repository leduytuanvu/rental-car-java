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
import vuldt.daos.OrderDAO;
import vuldt.dtos.HistoryDTO;
import vuldt.dtos.UserDTO;

/**
 *
 * @author Le Duy Tuan Vu
 */
public class PagingHistoryController extends HttpServlet {

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
            String num = "";
            if (request.getParameter("txtNum") != null) {
                num = request.getParameter("txtNum");
            }
            if (!num.isEmpty()) {
                if (num.trim().matches("[0-9]+")) {
                    String nameSearch = "", dateSearch = "";
                    if (session.getAttribute("NAME_SEARCH_ORDER") != null) {
                        nameSearch = (String) session.getAttribute("NAME_SEARCH_ORDER");
                    }
                    if (session.getAttribute("DATE_SEARCH_ORDER") != null) {
                        dateSearch = (String) session.getAttribute("DATE_SEARCH_ORDER");
                    }

                    if (session.getAttribute("USER") != null) {
                        user = (UserDTO) session.getAttribute("USER");
                    }

                    OrderDAO daoOrder = new OrderDAO();
                    if (!nameSearch.isEmpty() && dateSearch.isEmpty()) {
                        ArrayList<HistoryDTO> list = daoOrder.getListOrderSearchByName(user.getUserID(), nameSearch, Integer.parseInt(num));
                        int numOfPage = daoOrder.getNumSearchOrderName(user.getUserID(), nameSearch);
                        int numberPage = 0;
                        numberPage = numOfPage / 10;
                        if (numOfPage % 10 != 0) {
                            numberPage++;
                        }
                        session.setAttribute("LIST_HISTORY", list);
                        session.setAttribute("NUM_PAGE_HISTORY", numberPage);
                        session.setAttribute("PAGING_NUM", Integer.parseInt(num));
                    } else if (nameSearch.isEmpty() && !dateSearch.isEmpty()) {
                        ArrayList<HistoryDTO> list = daoOrder.getListOrderSearchByDate(user.getUserID(), dateSearch, Integer.parseInt(num));
                        int numOfPage = daoOrder.getNumSearchOrderDate(user.getUserID(), dateSearch);
                        int numberPage = 0;
                        numberPage = numOfPage / 10;
                        if (numOfPage % 10 != 0) {
                            numberPage++;
                        }
                        session.setAttribute("LIST_HISTORY", list);
                        session.setAttribute("NUM_PAGE_HISTORY", numberPage);
                        session.setAttribute("PAGING_NUM", Integer.parseInt(num));
                    } else if (nameSearch.isEmpty() && dateSearch.isEmpty()) {
                        ArrayList<HistoryDTO> list = daoHistory.getHistory(user.getUserID(), Integer.parseInt(num));
                        session.setAttribute("LIST_HISTORY", list);
                        int numberPage = 0, numberOrder = 0;
                        numberOrder = daoHistory.getNumAllHistory(user.getUserID());
                        numberPage = numberOrder / 10;
                        if (numberPage % 10 != 0) {
                            numberPage++;
                        }
                        session.setAttribute("NUM_PAGE_HISTORY", numberPage);
                        session.setAttribute("PAGING_NUM", Integer.parseInt(num));
                    }
                } else {
                    request.setAttribute("MESS_HISTORY", "Number page must be a number !");
                }
            } else {
                request.setAttribute("MESS_HISTORY", "Please enter number page you want to go !");
            }
        } catch (Exception e) {
            log("Error in PagingHistoryController: " + e.getMessage());
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
