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
import vuldt.dtos.CarDTO;
import vuldt.dtos.CartDTO;

/**
 *
 * @author Le Duy Tuan Vu
 */
public class AddQuantityController extends HttpServlet {

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
            String carID = "", txtQuantity = "";
            if (request.getParameter("txtCarID") != null) {
                carID = (String) request.getParameter("txtCarID");
            }
            if (request.getParameter("txtQuantity") != null) {
                txtQuantity = (String) request.getParameter("txtQuantity");
            }

            ArrayList<CarDTO> listCar = new ArrayList();
            if (session.getAttribute("LIST_CAR") != null) {
                listCar = (ArrayList<CarDTO>) session.getAttribute("LIST_CAR");
                CartDTO cart = new CartDTO();
                if (session.getAttribute("CART") != null) {
                    cart = (CartDTO) session.getAttribute("CART");
                    int quantity1 = 0, quantity2 = 0;
                    quantity2 = Integer.parseInt(txtQuantity);
                    for (CarDTO car : listCar) {
                        if (car.getCarID().equals(carID)) {
                            quantity1 = car.getQuantity();
                            if (quantity1 > quantity2) {
                                for (int i = 0; i < cart.getCart().size(); i++) {
                                    if (cart.getCart().get(i).getCarID().equals(carID)) {
                                        quantity2 += 1;
                                        cart.getCart().get(i).setQuantity(quantity2);
                                        session.setAttribute("CART", cart);
                                        int numberCar = 0;
                                        float total = 0;
                                        for (int j = 0; j < cart.getCart().size(); j++) {
                                            numberCar += cart.getCart().get(j).getQuantity();
                                            total = total + (cart.getCart().get(j).getPrice() * cart.getCart().get(j).getQuantity() * cart.getCart().get(j).getNumDate());
                                        }
                                        session.setAttribute("NUM_PRO", numberCar);
                                        session.setAttribute("TOTAL", total);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log("Error at AddQuantityController: " + e.toString());
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
