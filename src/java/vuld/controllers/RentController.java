/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuld.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vuldt.daos.CarDAO;
import vuldt.dtos.CarDTO;
import vuldt.dtos.CartDTO;
import vuldt.dtos.OrderDTO;
import vuldt.dtos.UserDTO;

/**
 *
 * @author Le Duy Tuan Vu
 */
public class RentController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final static String SUCCESS = "home.jsp";
    private final static String ERROR = "signIn.jsp";
    private final static String ADMIN = "admin.jsp";
    private final static String VERIFY = "verify.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "";
        try {
            HttpSession session = request.getSession();
            String dateHire = "", dateReturn = "", quantity = "", carID = "";
            if (session.getAttribute("TXT_DATE_HIRE") != null) {
                dateHire = (String) session.getAttribute("TXT_DATE_HIRE");
            }
            if (session.getAttribute("TXT_DATE_RETURN") != null) {
                dateReturn = (String) session.getAttribute("TXT_DATE_RETURN");
            }
            if (session.getAttribute("TXT_QUANTITY") != null) {
                quantity = (String) session.getAttribute("TXT_QUANTITY");
            }
            if (request.getParameter("txtCarID") != null) {
                carID = request.getParameter("txtCarID");
            } else if (session.getAttribute("CAR_ID") != null) {
                carID = (String) session.getAttribute("CAR_ID");
            }
            UserDTO user = new UserDTO();
            if (session.getAttribute("USER") != null) {
                user = (UserDTO) session.getAttribute("USER");
                if (user.getRoleID().equals("AD")) {
                    url = ADMIN;
                } else if (user.getRoleID().equals("US")) {
                    if (user.isActive()) {
                        CarDAO daoCar = new CarDAO();
                        if (dateHire.trim().isEmpty() || dateReturn.trim().isEmpty() || quantity.trim().isEmpty()) {
                            if (session.getAttribute("SIGN_IN") == null) {
                                request.setAttribute("MESS_SEARCH", "Please SEARCH the suitable car with your request (Date hire, date return and quantity is required) !");
                            } else if (session.getAttribute("SIGN_IN") != null && !carID.isEmpty()) {
                                request.setAttribute("MESS_SEARCH", "Please SEARCH the suitable car with your request (Date hire, date return and quantity is required) !");
                            }
                            session.setAttribute("SIGN_IN", null);
                            url = SUCCESS;
                        } else {
                            boolean check = true;
                            String messTemp = "";
                            if (!dateHire.matches("[0-9]+-[0-9]+-[0-9]+") || !dateReturn.matches("[0-9]+-[0-9]+-[0-9]+")) {
                                messTemp = "Format date wrong !";
                            } else {
                                boolean checkDate = true;
                                String arr1[] = dateHire.split("-");
                                if (arr1.length == 3) {
                                    if (Integer.parseInt(arr1[0]) < 1 || Integer.parseInt(arr1[0]) > 9999) {
                                        checkDate = false;
                                        messTemp = "The hire date is not in the correct format !";
                                    } else if (Integer.parseInt(arr1[1]) < 1 || Integer.parseInt(arr1[1]) > 12) {
                                        checkDate = false;
                                        messTemp = "The hire date is not in the correct format !";
                                    } else if (Integer.parseInt(arr1[2]) < 1 || Integer.parseInt(arr1[2]) > 31) {
                                        checkDate = false;
                                        messTemp = "The hire date is not in the correct format !";
                                    }
                                }
                                if (checkDate) {
                                    String arr2[] = dateReturn.split("-");
                                    if (arr2.length == 3) {
                                        if (Integer.parseInt(arr2[0]) < 1 || Integer.parseInt(arr2[0]) > 9999) {
                                            checkDate = false;
                                            messTemp = "The return date is not in the correct format !";
                                        } else if (Integer.parseInt(arr2[1]) < 1 || Integer.parseInt(arr2[1]) > 12) {
                                            checkDate = false;
                                            messTemp = "The return date is not in the correct format !";
                                        } else if (Integer.parseInt(arr2[2]) < 1 || Integer.parseInt(arr2[2]) > 31) {
                                            checkDate = false;
                                            messTemp = "The return date is not in the correct format !";
                                        }
                                    }
                                }
                                if (checkDate) {
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    Date dateHireTemp = sdf.parse(dateHire);
                                    Date dateReturnTemp = sdf.parse(dateReturn);
                                    String temp = java.time.LocalDate.now() + "";
                                    Date currentDate = sdf.parse(temp);
                                    if (currentDate.compareTo(dateHireTemp) > 0) {
                                        messTemp = "Date hire must be earlier than current date !";
                                    } else if (dateHireTemp.compareTo(dateReturnTemp) > 0) {
                                        messTemp = "Date return must be earlier than date hire !";
                                    }
                                }
                            }

                            if (!messTemp.isEmpty()) {
                                check = false;
                                request.setAttribute("MESS_SEARCH", messTemp);
                            } else if (!quantity.matches("[-0-9]+")) {
                                check = false;
                                request.setAttribute("MESS_SEARCH", "Number of car must be a number !");
                            } else if (Integer.parseInt(quantity) < 1) {
                                check = false;
                                request.setAttribute("MESS_SEARCH", "Number of car must be bigger than 0 !");
                            }
                            if (check) {
                                if (daoCar.getCarByID(carID) != null) {
                                    CarDTO car = daoCar.getCarByID(carID);
                                    CartDTO cart = new CartDTO();
                                    if (session.getAttribute("CART") != null) {
                                        cart = (CartDTO) session.getAttribute("CART");
                                    }
                                    String name = car.getName();
                                    String color = car.getColor();
                                    String year = car.getColor();
                                    float price = car.getPrice();
                                    String image = car.getImage();
                                    String description = car.getDescription();
                                    String typeID = car.getTypeID();
                                    String createDate = car.getCreateDate();
                                    int quantityReal = Integer.parseInt(quantity);

                                    int numberDate = 0;
                                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                    Date date1 = null, date2 = null;
                                    try {
                                        date1 = format.parse(dateHire);
                                        date2 = format.parse(dateReturn);
                                        long diff = date2.getTime() - date1.getTime();
                                        long diffDays = diff / (24 * 60 * 60 * 1000);
                                        String temp = diffDays + "";
                                        numberDate = Integer.parseInt(temp) + 1;
                                    } catch (Exception e) {
                                        log("Error in minus date: " + e.getMessage());
                                    }

                                    OrderDTO order = new OrderDTO(carID, name, color, year, price, quantityReal, numberDate, image, description, typeID, createDate, dateHire, dateReturn);
                                    if (cart.addCarInToCart(order)) {
                                        request.setAttribute("MESS_SEARCH", "Add " + car.getName() + " into cart successfully !");
                                        session.setAttribute("CART", cart);
                                    }
                                }
                            }
                            url = SUCCESS;
                        }
                    } else {
                        session.setAttribute("CARID", carID);
                        url = VERIFY;
                    }
                }
            } else {
                String name = "", comboxType = "";
                if (session.getAttribute("TXT_NAME") != null) {
                    name = (String) session.getAttribute("TXT_NAME");
                }
                if (session.getAttribute("TXT_TYPE") != null) {
                    comboxType = (String) session.getAttribute("TXT_TYPE");
                }
                if (comboxType.trim().isEmpty()) {
                    comboxType = "all";
                }
                session.setAttribute("NAME_TEMP", name);
                session.setAttribute("TYPE_TEMP", comboxType);
                session.setAttribute("DATE_HIRE_TEMP", dateHire);
                session.setAttribute("DATE_RETURN_TEMP", dateReturn);
                session.setAttribute("QUANTITY_TEMP", quantity);
                session.setAttribute("ID_CAR_TEMP", carID);
                url = ERROR;
            }
        } catch (Exception e) {
            log("Error in RentController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
