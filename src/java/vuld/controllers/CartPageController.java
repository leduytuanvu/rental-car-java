/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuld.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vuldt.dtos.CartDTO;

/**
 *
 * @author Le Duy Tuan Vu
 */
public class CartPageController extends HttpServlet {

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
            int number = 0;
            float total = 0;
            CartDTO cart = new CartDTO();
            if (session.getAttribute("CART") != null) {
                cart = (CartDTO) session.getAttribute("CART");
            }
            for (int i = 0; i < cart.getCart().size(); i++) {
                number += cart.getCart().get(i).getQuantity();
                total = total + (cart.getCart().get(i).getPrice() * cart.getCart().get(i).getQuantity() * cart.getCart().get(i).getNumDate());
            }
            session.setAttribute("NUM_PRO", number);
            session.setAttribute("TOTAL", total);

            session.setAttribute("RADIO", null);
            session.setAttribute("DATE_SEARCH_ORDER", null);

            session.setAttribute("DATE_SEARCH_ORDER", "");
            session.setAttribute("NAME_SEARCH_ORDER", "");
        } catch (Exception e) {
            log("Error in CartPageController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("cart.jsp").forward(request, response);
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
