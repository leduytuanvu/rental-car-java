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
public class DeleteCartController extends HttpServlet {

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
            String txtCarID = "", txtDateHire = "", txtDatereturn = "";
            if (request.getParameter("txtCarID") != null) {
                txtCarID = request.getParameter("txtCarID");
            }
            if (request.getParameter("txtDateHire") != null) {
                txtDateHire = request.getParameter("txtDateHire");
            }
            if (request.getParameter("txtDateReturn") != null) {
                txtDatereturn = request.getParameter("txtDateReturn");
            }
            CartDTO cart = new CartDTO();
            if (session.getAttribute("CART") != null) {
                cart = (CartDTO) session.getAttribute("CART");
                for (int i = 0; i < cart.getCart().size(); i++) {
                    if (cart.getCart().get(i).getCarID().equals(txtCarID) && cart.getCart().get(i).getDateHire().equals(txtDateHire) && cart.getCart().get(i).getDateReturn().equals(txtDatereturn)) {
                        String nameCar = cart.getCart().get(i).getName();
                        if (cart.deleteCar(txtCarID, txtDateHire, txtDatereturn)) {
                            request.setAttribute("MESS_CART", "Deleted the car named " + nameCar + " from your cart successfully");
                        } else {
                            request.setAttribute("MESS_CART", "Delete the car named " + nameCar + " from your cart unsuccessfully !");
                        }
                        session.setAttribute("CART", cart);
                        break;
                    }
                }
                int quantity = 0;
                float total = 0;
                for (int j = 0; j < cart.getCart().size(); j++) {
                    quantity += cart.getCart().get(j).getQuantity();
                    total = total + (cart.getCart().get(j).getPrice() * cart.getCart().get(j).getQuantity() * cart.getCart().get(j).getNumDate());
                }
                session.setAttribute("NUM_PRO", quantity);
                session.setAttribute("TOTAL", total);
            }
        } catch (Exception e) {
            log("Error in DeleteCartController: " + e.getMessage());
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
