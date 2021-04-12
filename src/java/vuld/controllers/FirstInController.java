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
import vuldt.dtos.CarDTO;

/**
 *
 * @author Le Duy Tuan Vu
 */
public class FirstInController extends HttpServlet {

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
            CarDAO daoCar = new CarDAO();

            // GET LIST CAR
            ArrayList<CarDTO> listCar = new ArrayList<>();
            listCar = daoCar.getListCarFirst(1);
            if (listCar.size() > 0) {
                session.setAttribute("LIST_CAR", listCar);
            } else {
                session.setAttribute("LIST_CAR", "");
            }

            //GET LIST TYPE'S CAR
            ArrayList<String> listType = new ArrayList<>();
            listType = daoCar.getListType();
            if (listType.size() > 0) {
                session.setAttribute("LIST_TYPE", listType);
            } else {
                session.setAttribute("LIST_TYPE", "");
            }

            int numberPage = 0, numberCar = 0;
            numberCar = daoCar.getNumberCarFirst();
            numberPage = numberCar / 20;
            if (numberCar % 20 != 0) {
                numberPage++;
            }
            session.setAttribute("NUMBER_PAGE", numberPage);
            session.setAttribute("NUM_PRO", 0);
            session.setAttribute("TOTAL", 0);
        } catch (Exception e) {
            log("Error in FirstInController : " + e.getMessage());
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
