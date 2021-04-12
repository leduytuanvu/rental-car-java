/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuld.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vuldt.daos.HistoryDAO;
import vuldt.daos.OrderDAO;
import vuldt.dtos.HistoryDTO;
import vuldt.dtos.OrderDTO;
import vuldt.dtos.UserDTO;

/**
 *
 * @author Le Duy Tuan Vu
 */
public class DeleteOrderController extends HttpServlet {

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
            String carID = request.getParameter("txtCarID");
            String dateHire = request.getParameter("txtDateHire");
            String dateReturn = request.getParameter("txtDateReturn");
            String orderID = "";
            if (session.getAttribute("ORDER_ID") != null) {
                orderID = (String) session.getAttribute("ORDER_ID");
            }

            if (carID.trim().isEmpty() || dateHire.trim().isEmpty() || dateReturn.trim().isEmpty() || orderID.trim().isEmpty()) {
                request.setAttribute("MESS_DELETE_ORDER", "Please choose order you want to delete !");
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dateHireTemp = sdf.parse(dateHire);
                String temp = java.time.LocalDate.now() + "";
                Date currentDate = sdf.parse(temp);
                if (currentDate.compareTo(dateHireTemp) > 0) {
                    request.setAttribute("MESS_DELETE_ORDER", "You can delete this order ! This order was or is in order !");
                } else {
                    OrderDAO daoOrder = new OrderDAO();
                    if (daoOrder.deleteOrderDetail(carID, orderID, dateHire, dateReturn) != 0) {
                        ArrayList<OrderDTO> list = daoOrder.getListOrderByID(orderID);
                        session.setAttribute("LIST_ORDER_DETAIL", list);
                        if (list.size() == 0) {
                            request.setAttribute("MESS_DELETE_ORDER", "");
                            daoOrder.deleteOrder(orderID);

                            HistoryDAO daoHistory = new HistoryDAO();
                            UserDTO user = new UserDTO();
                            if (session.getAttribute("USER") != null) {
                                user = (UserDTO) session.getAttribute("USER");
                                ArrayList<HistoryDTO> listHistory = daoHistory.getHistory(user.getUserID(), 1);
                                session.setAttribute("LIST_HISTORY", listHistory);
                                int numberPage = 0, numberOrder = 0;
                                numberOrder = daoHistory.getNumAllHistory(user.getUserID());
                                numberPage = numberOrder / 10;
                                if (numberPage % 10 != 0) {
                                    numberPage++;
                                }
                                session.setAttribute("NUM_PAGE_HISTORY", numberPage);
                                session.setAttribute("PAGING_NUM", 1);
                            }
                        } else {
                            request.setAttribute("MESS_DELETE_ORDER", "Delete successfully1 !");
                            float priceTemp = 0;
                            OrderDTO orderTemp = daoOrder.getOrderDetail(orderID, carID, dateHire, dateReturn);
                            if (orderTemp != null) {
                                priceTemp = orderTemp.getPrice() * orderTemp.getQuantity() * orderTemp.getNumDate();
                            }
                            String discountName = daoOrder.getDiscountOrderID(orderID);
                            String discountID = daoOrder.getDiscountID(discountName);
                            float valueDiscount = 0, realTotal = 0;
                            realTotal = daoOrder.getTotal(orderID);
                            if (!discountID.isEmpty()) {
                                valueDiscount = daoOrder.getValueDiscount(discountID);
                                realTotal = (float) realTotal / (1 - valueDiscount);
                                if (valueDiscount != 0) {
                                    realTotal -= priceTemp;
                                    realTotal = realTotal - (realTotal * valueDiscount);
                                }
                            } else {
                                realTotal -= priceTemp;
                            }
                            daoOrder.updatePrice(orderID, realTotal);
                        }
                    } else {
                        request.setAttribute("MESS_DELETE_ORDER", "Delete unsuccessfully2 !");
                    }
                }
            }
        } catch (Exception e) {
            log("Error in DeleteOrderController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("historyDetail.jsp").forward(request, response);
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
