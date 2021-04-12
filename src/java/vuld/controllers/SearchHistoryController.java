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
import vuldt.daos.OrderDAO;
import vuldt.dtos.HistoryDTO;
import vuldt.dtos.UserDTO;

/**
 *
 * @author Le Duy Tuan Vu
 */
public class SearchHistoryController extends HttpServlet {

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
            String radio = "";
            if (request.getParameter("radioCheck") != null) {
                radio = request.getParameter("radioCheck");
            }
            if (!radio.isEmpty()) {
                UserDTO user = new UserDTO();
                if (session.getAttribute("USER") != null) {
                    user = (UserDTO) session.getAttribute("USER");
                    OrderDAO daoOrder = new OrderDAO();
                    if (radio.trim().equals("searchName")) {
                        String nameCar = "";
                        if (request.getParameter("txtName") != null) {
                            nameCar = request.getParameter("txtName");
                        }
                        if (nameCar.isEmpty()) {
                            request.setAttribute("MESS_SEARCH_HISTORY", "Please enter car's name you want to search !");
                        } else {
                            ArrayList<HistoryDTO> list = daoOrder.getListOrderSearchByName(user.getUserID(), nameCar, 1);
                            int num = daoOrder.getNumSearchOrderName(user.getUserID(), nameCar);
                            int numberPage = 0;
                            numberPage = num / 10;
                            if (num % 10 != 0) {
                                numberPage++;
                            }
                            session.setAttribute("LIST_HISTORY", list);
                            session.setAttribute("NUM_PAGE_HISTORY", numberPage);
                            session.setAttribute("PAGING_NUM", 1);
                        }
                        session.setAttribute("DATE_SEARCH_ORDER", "");
                        session.setAttribute("NAME_SEARCH_ORDER", nameCar);
                    } else if (radio.trim().equals("searchDate")) {
                        String dateOrder = "";
                        if (request.getParameter("txtDate") != null) {
                            dateOrder = request.getParameter("txtDate");
                        }
                        if (dateOrder.isEmpty()) {
                            request.setAttribute("MESS_SEARCH_HISTORY", "Please enter date you want to search !");
                        } else {
                            ArrayList<HistoryDTO> list = daoOrder.getListOrderSearchByDate(user.getUserID(), dateOrder, 1);
                            int num = daoOrder.getNumSearchOrderDate(user.getUserID(), dateOrder);
                            int numberPage = 0;
                            numberPage = num / 10;
                            if (num % 10 != 0) {
                                numberPage++;
                            }
                            session.setAttribute("LIST_HISTORY", list);
                            session.setAttribute("NUM_PAGE_HISTORY", numberPage);
                            session.setAttribute("PAGING_NUM", 1);
                        }
                        session.setAttribute("DATE_SEARCH_ORDER", dateOrder);
                        session.setAttribute("NAME_SEARCH_ORDER", "");
                    }
                    session.setAttribute("RADIO", radio);

                }
            } else {
                request.setAttribute("MESS_SEARCH_HISTORY", "Please choose one option to search !");
            }
        } catch (Exception e) {
            log("Error in SearchHistoryController: " + e.getMessage());
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
