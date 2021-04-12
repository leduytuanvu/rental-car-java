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
import vuldt.daos.UserDAO;
import vuldt.dtos.UserDTO;
import vuldt.utils.VerifyUtils;

/**
 *
 * @author Le Duy Tuan Vu
 */
public class SignInController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final static String ADMiN = "home.jsp";
    private final static String USER = "RentController";
    private final static String ERROR = "signIn.jsp";
    private final static String VERIFY = "verify.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String userID = "", password = "";

            if (request.getParameter("txtUserID") != null) {
                userID = request.getParameter("txtUserID");
            }
            if (request.getParameter("txtPassword") != null) {
                password = request.getParameter("txtPassword");
            }

            boolean valid = true;
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            valid = VerifyUtils.verify(gRecaptchaResponse);
            boolean check = true;
            if (userID.trim().isEmpty() && password.trim().isEmpty()) {
                check = false;
                session.setAttribute("MESS_LOGIN", "Please enter user name and password !");
            } else if (userID.trim().isEmpty()) {
                check = false;
                session.setAttribute("MESS_LOGIN", "Please enter user name !");
            } else if (password.trim().isEmpty()) {
                check = false;
                session.setAttribute("MESS_LOGIN", "Please enter password !");
            } else if (!valid) {
                check = false;
                session.setAttribute("MESS_LOGIN", "Please verify !");
            }

            if (check) {
                UserDAO daoUser = new UserDAO();
                UserDTO user = daoUser.getUser(userID, password);
                if (user != null && user.isStatus()) {
                    if (user.getRoleID().equals("US")) {
                        if (user.isActive()) {
                            String txtName = "", comboxType = "", txtDateHire = "", txtDateReturn = "", txtQuantity = "", txtCarID = "";
                            if (session.getAttribute("NAME_TEMP") != null) {
                                txtName = (String) session.getAttribute("NAME_TEMP");
                            }
                            if (session.getAttribute("TYPE_TEMP") != null) {
                                comboxType = (String) session.getAttribute("TYPE_TEMP");
                            }
                            if (session.getAttribute("DATE_HIRE_TEMP") != null) {
                                txtDateHire = (String) session.getAttribute("DATE_HIRE_TEMP");
                            }
                            if (session.getAttribute("DATE_RETURN_TEMP") != null) {
                                txtDateReturn = (String) session.getAttribute("DATE_RETURN_TEMP");
                            }
                            if (session.getAttribute("QUANTITY_TEMP") != null) {
                                txtQuantity = (String) session.getAttribute("QUANTITY_TEMP");
                            }
                            if (session.getAttribute("ID_CAR_TEMP") != null) {
                                txtCarID = (String) session.getAttribute("ID_CAR_TEMP");
                            }
                            session.setAttribute("TXT_NAME", txtName);
                            session.setAttribute("TXT_TYPE", comboxType);
                            session.setAttribute("TXT_DATE_HIRE", txtDateHire);
                            session.setAttribute("TXT_DATE_RETURN", txtDateReturn);
                            session.setAttribute("TXT_QUANTITY", txtQuantity);
                            session.setAttribute("TXT_QUANTITY", txtQuantity);
                            session.setAttribute("CAR_ID", txtCarID);
                            url = USER;
                        } else {
                            url = VERIFY;
                        }
                    } else {
                        url = ADMiN;
                    }
                    session.setAttribute("USER", user);
                } else if (user != null && !user.isStatus()) {
                    session.setAttribute("MESS_LOGIN", "Sorry, your account has been locked !");
                } else {
                    session.setAttribute("MESS_LOGIN", "User name or password wrong !");
                }
            }

            session.setAttribute("USER_ID", userID);
            session.setAttribute("PASSWORD", password);
        } catch (Exception e) {
            log("Error in SignInController: " + e.getMessage());
        } finally {
            response.sendRedirect(url);
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
