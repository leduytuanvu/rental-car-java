/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuldt.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import vuldt.dtos.UserDTO;
import vuldt.utils.ConnectionDB;

/**
 *
 * @author Le Duy Tuan Vu
 */
public class UserDAO {

    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public UserDTO getUser(String userID, String password) throws NamingException, SQLException, ClassNotFoundException {
        UserDTO user = null;
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select userID, fullName, phone, email, address, roleID, codeActive, active, status from tblUsers where userID = ? and password = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, userID);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    String roleID = rs.getString("roleID");
                    String codeActive = rs.getString("codeActive");
                    boolean active = rs.getBoolean("active");
                    boolean status = rs.getBoolean("status");
                    user = new UserDTO(userID, fullName, "*******", phone, email, address, roleID, codeActive, active, status);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return user;
    }

    public UserDTO getUserByUserID(String userID) throws NamingException, SQLException, ClassNotFoundException {
        UserDTO user = null;
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select userID, fullName, phone, email, address, roleID, codeActive, active, status from tblUsers where userID = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, userID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    String roleID = rs.getString("roleID");
                    String codeActive = rs.getString("codeActive");
                    boolean active = rs.getBoolean("active");
                    boolean status = rs.getBoolean("status");
                    user = new UserDTO(userID, fullName, "*******", phone, email, address, roleID, codeActive, active, status);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return user;
    }

    public int insertUser(UserDTO user) throws NamingException, SQLException, ClassNotFoundException {
        int result = 0;
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "insert into tblUsers(userID, fullName, [password], phone, email, [address], roleID, codeActive, active, [status]) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                pst = cn.prepareStatement(sql);
                pst.setString(1, user.getUserID());
                pst.setString(2, user.getFullName());
                pst.setString(3, user.getPassword());
                pst.setString(4, user.getPhone());
                pst.setString(5, user.getEmail());
                pst.setString(6, user.getAddress());
                pst.setString(7, user.getRoleID());
                pst.setString(8, user.getCodeActive());
                pst.setBoolean(9, user.isActive());
                pst.setBoolean(10, user.isStatus());
                result = pst.executeUpdate();
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return result;
    }

    public String getCoce(UserDTO user) throws NamingException, SQLException, ClassNotFoundException {
        String result = "";
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select codeActive from tblUsers where userID = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, user.getUserID());
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getString("codeActive");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return result;
    }

    public int active(UserDTO user) throws NamingException, SQLException, ClassNotFoundException {
        int result = 0;
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "update tblUsers set active = ? where userID = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, "true");
                pst.setString(2, user.getUserID());
                result = pst.executeUpdate();
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return result;
    }

    public UserDTO getUserByEmail(String email) throws NamingException, SQLException, ClassNotFoundException {
        UserDTO user = null;
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select userID, fullName, phone, email, address, roleID, codeActive, active, status from tblUsers where email = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    String roleID = rs.getString("roleID");
                    String codeActive = rs.getString("codeActive");
                    boolean active = rs.getBoolean("active");
                    boolean status = rs.getBoolean("status");
                    user = new UserDTO(userID, fullName, "*******", phone, email, address, roleID, codeActive, active, status);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return user;
    }
}
