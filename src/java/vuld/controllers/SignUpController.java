/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuld.controllers;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vuldt.daos.MailDAO;
import vuldt.daos.UserDAO;
import vuldt.dtos.UserDTO;

/**
 *
 * @author Le Duy Tuan Vu
 */
public class SignUpController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final static String ERROR = "signUp.jsp";
    private final static String SUCCESS = "verify.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String userID = "", fullName = "", phone = "", email = "", address = "", password = "", rePassword = "";
            if (!request.getParameter("txtUserID").isEmpty()) {
                userID = request.getParameter("txtUserID");
            }
            if (!request.getParameter("txtFullName").isEmpty()) {
                fullName = request.getParameter("txtFullName");
            }
            if (!request.getParameter("txtPhone").isEmpty()) {
                phone = request.getParameter("txtPhone");
            }
            if (!request.getParameter("txtEmail").isEmpty()) {
                email = request.getParameter("txtEmail");
            }
            if (!request.getParameter("txtAddress").isEmpty()) {
                address = request.getParameter("txtAddress");
            }
            if (!request.getParameter("txtPassword").isEmpty()) {
                password = request.getParameter("txtPassword");
            }
            if (!request.getParameter("txtRePassword").isEmpty()) {
                rePassword = request.getParameter("txtRePassword");
            }
            UserDAO daoUser = new UserDAO();
            boolean check = true;
            if (userID.trim().isEmpty()) {
                check = false;
                request.setAttribute("MESS_USER_NAME_SIGN_UP", "User name is not empty !");
            } else if (daoUser.getUserByUserID(userID) != null) {
                check = false;
                request.setAttribute("MESS_USER_NAME_SIGN_UP", "User name is exist !");
            }

            if (fullName.trim().isEmpty()) {
                check = false;
                request.setAttribute("MESS_NAME_SIGN_UP", "Full name must not empty !");
            } else if (!fullName.matches("[a-zA-Z ]+")) {
                check = false;
                request.setAttribute("MESS_NAME_SIGN_UP", "Full name must not contain character special or number !");
            }

            if (phone.trim().isEmpty()) {
                check = false;
                request.setAttribute("MESS_PHONE_SIGN_UP", "Phone must not empty !");
            } else if (!phone.matches("[0-9]{10}")) {
                check = false;
                request.setAttribute("MESS_PHONE_SIGN_UP", "Phone must be a number have 10 digits !");
            }

            if (email.trim().isEmpty()) {
                check = false;
                request.setAttribute("MESS_EMAIL_SIGN_UP", "Email must not empty !");
            } else if (!email.matches("[a-zA-Z0-9]+@gmail.com") && !email.matches("[a-zA-Z0-9]+@yahoo.com") && !email.matches("[a-zA-Z0-9]+@fpt.edu.vn") && !email.matches("[a-zA-Z0-9]+@hotmail.com") && !email.matches("[a-zA-Z0-9]+@outlook.com") && !email.matches("[a-zA-Z0-9]+@icloud.com")) {
                check = false;
                request.setAttribute("MESS_EMAIL_SIGN_UP", "Email must like tuanvu@gmail.com or tuanvu@yahoo.com.vn or tuanvu@fpt.edu.vn !");
            } else if (email.matches("[0-9][a-zA-Z]+@gmail.com") || email.matches("[0-9][a-zA-Z]+@yahoo.com") || email.matches("[0-9][a-zA-Z]+@fpt.edu.vn") || email.matches("[0-9][a-zA-Z]+@hotmail.com") || email.matches("[0-9][a-zA-Z]+@outlook.com") || email.matches("[0-9][a-zA-Z]+@icloud.com")) {
                check = false;
                request.setAttribute("MESS_EMAIL_SIGN_UP", "First character of email must be a letter !");
            } else if (daoUser.getUserByEmail(email) != null) {
                check = false;
                request.setAttribute("MESS_EMAIL_SIGN_UP", "This email is already registered ! Please enter another email !");
            }

            if (address.trim().isEmpty()) {
                check = false;
                request.setAttribute("MESS_ADDRESS_SIGN_UP", "Address must not empty !");
            }

            if (password.trim().isEmpty()) {
                check = false;
                request.setAttribute("MESS_PASS_SIGN_UP", "Password must not empty !");
            }

            if (rePassword.trim().isEmpty()) {
                check = false;
                request.setAttribute("MESS_REPASS_SIGN_UP", "Re-password must not empty !");
            } else if (!password.equals(rePassword)) {
                check = false;
                request.setAttribute("MESS_REPASS_SIGN_UP", "Re-password must match with password !");
            }

            if (check) {
                Random ran = new Random();
                int digit1 = ran.nextInt(9);
                int digit2 = ran.nextInt(9);
                int digit3 = ran.nextInt(9);
                int digit4 = ran.nextInt(9);
                int digit5 = ran.nextInt(9);
                String temp1 = digit1 + "";
                String temp2 = digit2 + "";
                String temp3 = digit3 + "";
                String temp4 = digit4 + "";
                String temp5 = digit5 + "";
                String codeActive = temp1 + temp2 + temp3 + temp4 + temp5;
                UserDTO user = new UserDTO(userID, fullName, password, phone, email, address, "US", codeActive, false, true);
                if (daoUser.insertUser(user) != 0) {
                    request.setAttribute("MESS_SIGN_UP", "Sign up successfully !");
                    url = SUCCESS;
                    session.setAttribute("USER", user);
                    MailDAO.sendMail(user.getEmail(), "Please enter the code below to verify your account", "Your code : " + codeActive);
                    session.setAttribute("SIGN_IN", null);
                } else {
                    request.setAttribute("MESS_SIGN_UP", "Sign up unsuccessfully !");
                    url = ERROR;
                }
            }
            session.setAttribute("USER_NAME_SIGN_UP", userID);
            session.setAttribute("NAME_SIGN_UP", fullName);
            session.setAttribute("PHONE_SIGN_UP", phone);
            session.setAttribute("EMAIL_SIGN_UP", email);
            session.setAttribute("ADDRESS_SIGN_UP", address);
            session.setAttribute("PASS_SIGN_UP", password);
            session.setAttribute("REPASS_SIGN_UP", rePassword);
        } catch (Exception e) {
            log("Error in SignUpController: " + e.getMessage());
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
