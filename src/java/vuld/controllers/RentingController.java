/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuld.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vuldt.daos.CarDAO;
import vuldt.daos.OrderDAO;
import vuldt.dtos.CarDTO;
import vuldt.dtos.CartDTO;
import vuldt.dtos.OrderDTO;
import vuldt.dtos.UserDTO;

/**
 *
 * @author Le Duy Tuan Vu
 */
public class RentingController extends HttpServlet {

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
            String action = request.getParameter("btnAction");
            if (action.equals("USE DISCOUNT")) {
                OrderDAO daoOrder = new OrderDAO();
                String discountID = request.getParameter("txtDiscount");
                if (discountID.trim().isEmpty()) {
                    request.setAttribute("MESS_DISCOUNT", "Please enter discount you want to use !");
                    session.setAttribute("DISCOUNT", "");
                } else {
                    if (daoOrder.getDiscountName(discountID).isEmpty() && daoOrder.getDiscountOutOfDate(discountID).isEmpty()) {
                        request.setAttribute("MESS_DISCOUNT", "This discount not exist !");
                    } else if (!daoOrder.getDiscountOutOfDate(discountID).isEmpty()) {
                        request.setAttribute("MESS_DISCOUNT", "This discount has expired !");
                    } else {
                        float total = (Float) session.getAttribute("TOTAL");
                        total = total - (total * daoOrder.getDiscount(discountID));
                        request.setAttribute("MESS_DISCOUNT", "Total when use discount = " + total + " $");
                    }
                    session.setAttribute("DISCOUNT", discountID);
                }
            } else if (action.equals("RENT NOW")) {
                String name = request.getParameter("txtNameCustomer");
                String phone = request.getParameter("txtPhoneCustomer");
                String email = request.getParameter("txtEmailCustomer");
                String address = request.getParameter("txtAddressCustomer");
                String discount = request.getParameter("txtDiscount");

                boolean check = true;
                if (name.trim().isEmpty()) {
                    check = false;
                    request.setAttribute("MESS_NAME_CUS", "Name must not empty !");
                } else if (!name.matches("[a-zA-Z ]+")) {
                    check = false;
                    request.setAttribute("MESS_NAME_CUS", "Names cannot contain special characters !");
                }

                if (phone.trim().isEmpty()) {
                    check = false;
                    request.setAttribute("MESS_PHONE_CUS", "Phone must not empty !");
                } else if (!phone.matches("[0-9]{10}")) {
                    check = false;
                    request.setAttribute("MESS_PHONE_CUS", "Phone must be a number have 10 digits !");
                }

                if (email.trim().isEmpty()) {
                    check = false;
                    request.setAttribute("MESS_EMAIL_CUS", "Email must not empty !");
                } else if (!email.matches("[a-zA-Z0-9]+@gmail.com") && !email.matches("[a-zA-Z0-9]+@outlook.com") && !email.matches("[a-zA-Z0-9]+@yahoo.com.vn") && !email.matches("[a-zA-Z0-9]+@fpt.edu.vn")) {
                    check = false;
                    request.setAttribute("MESS_EMAIL_CUS", "Please enter a correct email !");
                } else if (email.charAt(0) == 0 || email.charAt(0) == 1 || email.charAt(0) == 2 || email.charAt(0) == 3 || email.charAt(0) == 4 || email.charAt(0) == 5 || email.charAt(0) == 6 || email.charAt(0) == 7 || email.charAt(0) == 8 || email.charAt(0) == 9) {
                    check = false;
                    request.setAttribute("MESS_EMAIL_CUS", "First character must be a letter !");
                }

                if (address.trim().isEmpty()) {
                    check = false;
                    request.setAttribute("MESS_ADDRESS_CUS", "Address must not empty !");
                }

                if (check) {
                    OrderDAO daoOrder = new OrderDAO();
                    CarDAO daoCar = new CarDAO();
                    CartDTO cart = new CartDTO();

                    if (session.getAttribute("CART") != null) {
                        cart = (CartDTO) session.getAttribute("CART");
                    }
                    if (cart.getCart() == null) {
                        request.setAttribute("MESS_CART", "Sorry, your cart is empty !");
                    } else if (cart.getCart().size() != 0) {
                        ArrayList<OrderDTO> listOrderNotReady = new ArrayList<>();
                        ArrayList<OrderDTO> listOrderReady = new ArrayList<>();
                        for (int i = 0; i < cart.getCart().size(); i++) {
                            CarDTO carTemp = daoCar.getCarByID(cart.getCart().get(i).getCarID());
                            if (carTemp != null) {
                                ArrayList<CarDTO> listNotReady = daoOrder.getListCarOrderNotReady2(cart.getCart().get(i).getCarID(), cart.getCart().get(i).getDateHire(), cart.getCart().get(i).getDateReturn());
                                int quantityTemp = 0;
                                //
                                int quantityAll = daoCar.getQuantityByID(cart.getCart().get(i).getCarID());
                                for (CarDTO car : listNotReady) {
                                    quantityTemp += car.getQuantity();
                                }
                                quantityAll -= quantityTemp;
                                if (cart.getCart().get(i).getQuantity() <= quantityAll) {
                                    listOrderReady.add(cart.getCart().get(i));
                                } else {
                                    listOrderNotReady.add(cart.getCart().get(i));
                                }
                            }
                        }
                        if (listOrderNotReady.size() == 0) {
                            request.setAttribute("MESS_CART", "Renting successfully");
                            session.setAttribute("NUM_PRO", 0);
                            session.setAttribute("TOTAL", 0);
                        } else {
                            int numTemp = 0;
                            float totalTemp = 0;
                            for (OrderDTO orderNotReady : listOrderNotReady) {
                                numTemp += orderNotReady.getQuantity();
                                totalTemp += (orderNotReady.getPrice() * orderNotReady.getQuantity() * orderNotReady.getNumDate());
                            }
                            request.setAttribute("MESS_CART", "Sorry, this car below not ready for rent ! Please delete out of cart !");
                            session.setAttribute("NUM_PRO", numTemp);
                            session.setAttribute("TOTAL", totalTemp);
                        }
                        int orderIDTemp = daoOrder.getNumberOrder() + 1;
                        String orderID = "order" + orderIDTemp;
                        float totalTemp = 0, discountTemp = 0;
                        for (OrderDTO orderDTO : listOrderReady) {
                            totalTemp += orderDTO.getPrice() * orderDTO.getNumDate() * orderDTO.getQuantity();
                        }
                        String discountName = daoOrder.getDiscountName(discount);
                        if (!discount.trim().isEmpty()) {
                            discountTemp = daoOrder.getDiscount(discount);
                            if (discountTemp != 0) {
                                totalTemp = totalTemp - (totalTemp * discountTemp);
                            }
                        }

                        String createDate = "";
                        Calendar calender = Calendar.getInstance();
                        int year = calender.get(Calendar.YEAR);
                        int month = calender.get(Calendar.MONTH) + 1;
                        int day = calender.get(Calendar.DAY_OF_MONTH);
                        createDate = year + "-" + month + "-" + day;
                        UserDTO user = new UserDTO();
                        if (session.getAttribute("USER") != null) {
                            user = (UserDTO) session.getAttribute("USER");
                        }
                        if (daoOrder.insertOrder(orderID, totalTemp, createDate, user.getUserID(), discountName) != 0) {
                            for (OrderDTO orderReady : listOrderReady) {
                                daoOrder.insertOrderDetail(orderReady.getCarID(), orderID, orderReady.getPrice(), orderReady.getQuantity(), orderReady.getDateHire(), orderReady.getDateReturn(), orderReady.getNumDate());
                            }
                            CartDTO newCart = new CartDTO();
                            newCart.newCart();
                            for (OrderDTO orderDTO : listOrderNotReady) {
                                newCart.addCarInToCart(orderDTO);
                            }
                            session.setAttribute("CART", newCart);
                            session.setAttribute("DISCOUNT", "");
                        }
                    } else {
                        request.setAttribute("MESS_CART", "Sorry, your cart is empty !");
                        session.setAttribute("DISCOUNT", discount);
                    }
                } else {
                    session.setAttribute("DISCOUNT", discount);
                }
                session.setAttribute("NAME_CUS", name);
                session.setAttribute("PHONE_CUS", phone);
                session.setAttribute("EMAIL_CUS", email);
                session.setAttribute("ADDRESS_CUS", address);

            }

        } catch (Exception e) {
            log("Error in RentingController: " + e.getMessage());
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
