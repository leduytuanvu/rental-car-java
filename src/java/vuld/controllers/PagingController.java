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
import vuldt.daos.CarDAO;
import vuldt.daos.OrderDAO;
import vuldt.dtos.CarDTO;

/**
 *
 * @author Le Duy Tuan Vu
 */
public class PagingController extends HttpServlet {

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
            String txtName = "", comboxType = "", txtDateHire = "", txtDateReturn = "", txtQuantity = "", txtNum = "";
            if (session.getAttribute("TXT_NAME") != null) {
                txtName = (String) session.getAttribute("TXT_NAME");
            }
            if (session.getAttribute("TXT_TYPE") != null) {
                comboxType = (String) session.getAttribute("TXT_TYPE");
            }
            if (session.getAttribute("TXT_DATE_HIRE") != null) {
                txtDateHire = (String) session.getAttribute("TXT_DATE_HIRE");
            }
            if (session.getAttribute("TXT_DATE_RETURN") != null) {
                txtDateReturn = (String) session.getAttribute("TXT_DATE_RETURN");
            }
            if (session.getAttribute("TXT_QUANTITY") != null) {
                txtQuantity = (String) session.getAttribute("TXT_QUANTITY");
            }
            if (request.getParameter("txtNum") != null) {
                txtNum = request.getParameter("txtNum");
            }

            int number = 0;
            if (txtNum.matches("[0-9]+")) {
                number = Integer.parseInt(txtNum);
            } else {
                request.setAttribute("MESS_SEARCH", "Page number must be a number !");
            }

            CarDAO daoCar = new CarDAO();
            OrderDAO daoOrder = new OrderDAO();
            ArrayList<CarDTO> listCar = new ArrayList<>();
            if (txtName.isEmpty() && (comboxType.isEmpty() || comboxType.trim().equals("all")) && txtDateHire.isEmpty() && txtDateReturn.isEmpty() && txtQuantity.isEmpty()) {
                listCar = daoCar.getListCarFirst(number);
                if (listCar.size() > 0) {
                    session.setAttribute("LIST_CAR", listCar);
                } else {
                    session.setAttribute("LIST_CAR", "");
                }
            } else {
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
                int firstNum = (number * 20) - 20;
                int lastNumber = number * 20;
                for (int i = 0; i < listReady.size(); i++) {
                    if (i >= firstNum && i < lastNumber) {
                        listCar.add(listReady.get(i));
                    }
                }
                if (listCar.size() > 0) {
                    session.setAttribute("LIST_CAR", listCar);
                } else {
                    session.setAttribute("LIST_CAR", "");
                }
            }
        } catch (Exception e) {
            log("Error in PagingController: " + e.getMessage());
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
