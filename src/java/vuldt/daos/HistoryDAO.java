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
import java.util.ArrayList;
import javax.naming.NamingException;
import vuldt.dtos.HistoryDTO;
import vuldt.utils.ConnectionDB;

/**
 *
 * @author Le Duy Tuan Vu
 */
public class HistoryDAO {

    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public ArrayList<HistoryDTO> getHistory(String userID, int index) throws NamingException, SQLException, ClassNotFoundException {
        ArrayList<HistoryDTO> list = new ArrayList<>();
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select orderID, total, dateOrder, discount, status from (select ROW_NUMBER() over (order by dateOrder desc) as stt, orderID, total, dateOrder, discount, status from tblOrders where userID = ? and status = ?) as X where stt between ?*10-9 and ?*10";
                pst = cn.prepareStatement(sql);
                pst.setString(1, userID);
                pst.setBoolean(2, true);
                pst.setInt(3, index);
                pst.setInt(4, index);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString("orderID");
                    float total = rs.getFloat("total");
                    String dateOrder = rs.getString("dateOrder");
                    String discount = rs.getString("discount");
                    boolean status = rs.getBoolean("status");
                    HistoryDTO history = new HistoryDTO(orderID, total, dateOrder, discount, status);
                    list.add(history);
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
        return list;
    }

    public int getNumAllHistory(String userID) throws NamingException, SQLException, ClassNotFoundException {
        int num = 0;
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select count(orderID) as num from tblOrders where userID = ? and status = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, userID);
                pst.setBoolean(2, true);
                rs = pst.executeQuery();
                if (rs.next()) {
                    num = rs.getInt("num");
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
        return num;
    }
}
