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
import vuldt.daos.CarDAO;
import vuldt.daos.OrderDAO;
import vuldt.dtos.CarDTO;

/**
 *
 * @author Le Duy Tuan Vu
 */
public class SearchController extends HttpServlet {

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
            String txtName = "", comboxType = "", txtDateHire = "", txtDateReturn = "", txtQuantity = "";

            if (request.getParameter("txtName") != null) {
                txtName = request.getParameter("txtName");
            }
            if (request.getParameter("comboxType") != null) {
                comboxType = request.getParameter("comboxType");
            }
            if (request.getParameter("txtDateHire") != null) {
                txtDateHire = request.getParameter("txtDateHire");
            }
            if (request.getParameter("txtDateReturn") != null) {
                txtDateReturn = request.getParameter("txtDateReturn");
            }
            if (request.getParameter("txtQuantity") != null) {
                txtQuantity = request.getParameter("txtQuantity");
            }

            boolean check = true;
            String messTemp = "";
            if (!txtDateHire.isEmpty() && !txtDateReturn.isEmpty()) {
                if (!txtDateHire.matches("[0-9]+-[0-9]+-[0-9]+") || !txtDateReturn.matches("[0-9]+-[0-9]+-[0-9]+")) {
                    messTemp = "Format date wrong !";
                } else {
                    boolean checkDate = true;
                    String arr1[] = txtDateHire.split("-");
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
                        String arr2[] = txtDateReturn.split("-");
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
                        Date dateHire = sdf.parse(txtDateHire);
                        Date dateReturn = sdf.parse(txtDateReturn);
                        String temp = java.time.LocalDate.now() + "";
                        Date currentDate = sdf.parse(temp);
                        if (currentDate.compareTo(dateHire) > 0) {
                            messTemp = "Date hire must be earlier than current date !";
                        } else if (dateHire.compareTo(dateReturn) > 0) {
                            messTemp = "Date return must be earlier than date hire !";
                        }
                    }
                }
            }

            if (txtName.isEmpty() && (comboxType.isEmpty() || comboxType.equals("empty"))) {
                check = false;
                request.setAttribute("MESS_SEARCH", "Sorry, type of car must not empty !");
            } else if (txtDateHire.isEmpty() && txtDateReturn.isEmpty()) {
                check = false;
                request.setAttribute("MESS_SEARCH", "Please enter date hire and date return you want to rent !");
            } else if (txtDateHire.isEmpty() && !txtDateReturn.isEmpty()) {
                check = false;
                request.setAttribute("MESS_SEARCH", "Please enter date hire you want to rent !");
            } else if (!txtDateHire.isEmpty() && txtDateReturn.isEmpty()) {
                check = false;
                request.setAttribute("MESS_SEARCH", "Please enter date return you want to rent !");
            } else if (!messTemp.isEmpty()) {
                check = false;
                request.setAttribute("MESS_SEARCH", messTemp);
            } else if (txtQuantity.isEmpty()) {
                check = false;
                request.setAttribute("MESS_SEARCH", "Please enter number of car you want to rent !");
            } else if (!txtQuantity.matches("[-0-9]+")) {
                check = false;
                request.setAttribute("MESS_SEARCH", "Number of car must be a number !");
            } else if (Integer.parseInt(txtQuantity) < 1) {
                check = false;
                request.setAttribute("MESS_SEARCH", "Number of car must be bigger than 0 !");
            }

            ArrayList<CarDTO> listCar = new ArrayList<>();
            if (check) {
                request.setAttribute("MESS_SEARCH", "");
                OrderDAO daoOrder = new OrderDAO();
                CarDAO daoCar = new CarDAO();
                String typeID = "";
                if (comboxType.equals("all")) {
                    typeID = "all";
                } else {
                    typeID = daoCar.getTypeID(comboxType);
                }
                listCar = daoCar.getListCarReady(txtName, typeID, txtDateHire, txtDateReturn);
                ArrayList<CarDTO> listReady = new ArrayList<>();
                ArrayList<CarDTO> listCarOrderNotReady = daoOrder.getListCarOrderNotReady(txtName, typeID, txtDateHire, txtDateReturn);
                for (CarDTO car : listCar) {
                    for (CarDTO carNotReady : listCarOrderNotReady) {
                        if (car.getCarID().equals(carNotReady.getCarID())) {
                            car.setQuantity(car.getQuantity() - carNotReady.getQuantity());
                        }
                    }
                    if (car.getQuantity() != 0 && car.getQuantity() >= Integer.parseInt(txtQuantity)) {
                        listReady.add(car);
                    }
                }
                int numberPage = 0, numberCar = listReady.size();
                numberPage = numberCar / 20;
                if (numberCar % 20 != 0) {
                    numberPage++;
                }
                session.setAttribute("NUMBER_PAGE", numberPage);
                listCar = new ArrayList<>();
                for (int i = 0; i < listReady.size(); i++) {
                    if (i < 20) {
                        listCar.add(listReady.get(i));
                    }
                }
            } else {

            }
            if (listCar.size() == 0) {
                session.setAttribute("LIST_CAR", "");
                session.setAttribute("NUMBER_PAGE", 1);
            } else {
                session.setAttribute("LIST_CAR", listCar);
            }
            session.setAttribute("TXT_NAME", txtName);
            session.setAttribute("TXT_TYPE", comboxType);
            session.setAttribute("TXT_DATE_HIRE", txtDateHire);
            session.setAttribute("TXT_DATE_RETURN", txtDateReturn);
            session.setAttribute("TXT_QUANTITY", txtQuantity);
        } catch (Exception e) {
            log("Error in SearchController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("home.jsp").forward(request, response);
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
