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
import java.util.Calendar;
import javax.naming.NamingException;
import vuldt.dtos.CarDTO;
import vuldt.dtos.HistoryDTO;
import vuldt.dtos.OrderDTO;
import vuldt.utils.ConnectionDB;

/**
 *
 * @author Le Duy Tuan Vu
 */
public class OrderDAO {

    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public ArrayList<CarDTO> getListCarOrderReady(String name, String typeID, String dateHire, String dateReturn) throws NamingException, SQLException, ClassNotFoundException {
        ArrayList<CarDTO> list = new ArrayList<>();
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "";
                if (typeID.equals("all")) {
                    sql = "select C.carID, C.name, C.color, C.[year], C.price, OD.quantity, C.[image], C.[description], C.typeID, C.createDate, C.[status] from tblOrderDetails OD, tblCars C where OD.carID = C.carID and C.name like ? and (OD.dateHire > ? or OD.dateReturn < ?)";
                    pst = cn.prepareStatement(sql);
                    pst.setString(1, "%" + name + "%");
                    pst.setString(2, dateReturn);
                    pst.setString(3, dateHire);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        String carID = rs.getString("carID");
                        String nameCar = rs.getString("name");
                        String color = rs.getString("color");
                        String year = rs.getString("year");
                        float price = rs.getFloat("price");
                        int quantity = rs.getInt("quantity");
                        String image = rs.getString("image");
                        String categoryID = rs.getString("typeID");
                        String description = rs.getString("description");
                        String createDate = rs.getString("createDate");
                        boolean status = rs.getBoolean("status");
                        CarDTO car = new CarDTO(carID, nameCar, color, year, price, quantity, image, description, categoryID, createDate, status);
                        list.add(car);
                    }
                } else {
                    sql = "select C.carID, C.name, C.color, C.[year], C.price, OD.quantity, C.[image], C.[description], C.typeID, C.createDate, C.[status] from tblOrderDetails OD, tblCars C where OD.carID = C.carID and (C.name like ? or C.typeID = ?) and (OD.dateHire > ? or OD.dateReturn < ?)";
                    pst = cn.prepareStatement(sql);
                    pst.setString(1, "%" + name + "%");
                    pst.setString(2, typeID);
                    pst.setString(3, dateReturn);
                    pst.setString(4, dateHire);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        String carID = rs.getString("carID");
                        String nameCar = rs.getString("name");
                        String color = rs.getString("color");
                        String year = rs.getString("year");
                        float price = rs.getFloat("price");
                        int quantity = rs.getInt("quantity");
                        String image = rs.getString("image");
                        String categoryID = rs.getString("typeID");
                        String description = rs.getString("description");
                        String createDate = rs.getString("createDate");
                        boolean status = rs.getBoolean("status");
                        CarDTO car = new CarDTO(carID, nameCar, color, year, price, quantity, image, description, categoryID, createDate, status);
                        list.add(car);
                    }
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

    public ArrayList<CarDTO> getListCarOrderNotReady(String name, String typeID, String dateHire, String dateReturn) throws NamingException, SQLException, ClassNotFoundException {
        ArrayList<CarDTO> list = new ArrayList<>();
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "";
                if (!typeID.equals("all") && !name.trim().isEmpty() && !typeID.equals("empty")) {
                    sql = "select C.carID, C.name, C.color, C.[year], C.price, OD.quantity, C.[image], C.[description], C.typeID, C.createDate, C.[status] from tblOrderDetails OD, tblCars C where OD.carID = C.carID and C.name like ? and C.typeID = ? and OD.dateHire <= ? and OD.dateReturn >= ?";
                    pst = cn.prepareStatement(sql);
                    pst.setString(1, "%" + name + "%");
                    pst.setString(2, typeID);
                    pst.setString(3, dateReturn);
                    pst.setString(4, dateHire);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        String carID = rs.getString("carID");
                        String nameCar = rs.getString("name");
                        String color = rs.getString("color");
                        String year = rs.getString("year");
                        float price = rs.getFloat("price");
                        int quantity = rs.getInt("quantity");
                        String image = rs.getString("image");
                        String categoryID = rs.getString("typeID");
                        String description = rs.getString("description");
                        String createDate = rs.getString("createDate");
                        boolean status = rs.getBoolean("status");
                        CarDTO car = new CarDTO(carID, nameCar, color, year, price, quantity, image, description, categoryID, createDate, status);
                        list.add(car);
                    }
                } else if (!typeID.equals("all") && name.trim().isEmpty()) {
                    sql = "select C.carID, C.name, C.color, C.[year], C.price, OD.quantity, C.[image], C.[description], C.typeID, C.createDate, C.[status] from tblOrderDetails OD, tblCars C where OD.carID = C.carID and C.typeID = ? and OD.dateHire <= ? and OD.dateReturn >= ?";
                    pst = cn.prepareStatement(sql);
                    pst.setString(1, typeID);
                    pst.setString(2, dateReturn);
                    pst.setString(3, dateHire);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        String carID = rs.getString("carID");
                        String nameCar = rs.getString("name");
                        String color = rs.getString("color");
                        String year = rs.getString("year");
                        float price = rs.getFloat("price");
                        int quantity = rs.getInt("quantity");
                        String image = rs.getString("image");
                        String categoryID = rs.getString("typeID");
                        String description = rs.getString("description");
                        String createDate = rs.getString("createDate");
                        boolean status = rs.getBoolean("status");
                        CarDTO car = new CarDTO(carID, nameCar, color, year, price, quantity, image, description, categoryID, createDate, status);
                        list.add(car);
                    }
                } else {
                    sql = "select C.carID, C.name, C.color, C.[year], C.price, OD.quantity, C.[image], C.[description], C.typeID, C.createDate, C.[status] from tblOrderDetails OD, tblCars C where OD.carID = C.carID and C.name like ? and OD.dateHire <= ? and OD.dateReturn >= ?";
                    pst = cn.prepareStatement(sql);
                    pst.setString(1, "%" + name + "%");
                    pst.setString(2, dateReturn);
                    pst.setString(3, dateHire);
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        String carID = rs.getString("carID");
                        String nameCar = rs.getString("name");
                        String color = rs.getString("color");
                        String year = rs.getString("year");
                        float price = rs.getFloat("price");
                        int quantity = rs.getInt("quantity");
                        String image = rs.getString("image");
                        String categoryID = rs.getString("typeID");
                        String description = rs.getString("description");
                        String createDate = rs.getString("createDate");
                        boolean status = rs.getBoolean("status");
                        CarDTO car = new CarDTO(carID, nameCar, color, year, price, quantity, image, description, categoryID, createDate, status);
                        list.add(car);
                    }
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

    public ArrayList<CarDTO> getListCarOrderNotReady2(String carID, String dateHire, String dateReturn) throws NamingException, SQLException, ClassNotFoundException {
        ArrayList<CarDTO> list = new ArrayList<>();
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select C.carID, C.name, C.color, C.[year], C.price, OD.quantity, C.[image], C.[description], C.typeID, C.createDate, C.[status] from tblOrderDetails OD, tblCars C where OD.carID = C.carID and C.carID = ? and OD.dateHire <= ? and OD.dateReturn >= ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, carID);
                pst.setString(2, dateReturn);
                pst.setString(3, dateHire);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String nameCar = rs.getString("name");
                    String color = rs.getString("color");
                    String year = rs.getString("year");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    String image = rs.getString("image");
                    String categoryID = rs.getString("typeID");
                    String description = rs.getString("description");
                    String createDate = rs.getString("createDate");
                    boolean status = rs.getBoolean("status");
                    CarDTO car = new CarDTO(carID, nameCar, color, year, price, quantity, image, description, categoryID, createDate, status);
                    list.add(car);
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

    public int getNumberOrder() throws NamingException, SQLException, ClassNotFoundException {
        int result = 0;
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select count(orderID) as num from tblOrders";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("num");
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

    public int insertOrder(String orderID, float total, String dateOrder, String userID, String discount) throws NamingException, SQLException, ClassNotFoundException {
        int result = 0;
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "insert into tblOrders(orderID, total, dateOrder, userID, discount, status) values(?, ?, ?, ?, ?, ?)";
                pst = cn.prepareStatement(sql);
                pst.setString(1, orderID);
                pst.setFloat(2, total);
                pst.setString(3, dateOrder);
                pst.setString(4, userID);
                pst.setString(5, discount);
                pst.setBoolean(6, true);
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

    public int insertOrderDetail(String carID, String orderID, float price, int quantity, String dateHire, String dateReturn, int numDate) throws NamingException, SQLException, ClassNotFoundException {
        int result = 0;
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "insert into tblOrderDetails(carID, orderID, price, quantity, dateHire, dateReturn, numberDate, status) values(?, ?, ?, ?, ?, ?, ?, ?)";
                pst = cn.prepareStatement(sql);
                pst.setString(1, carID);
                pst.setString(2, orderID);
                pst.setFloat(3, price);
                pst.setInt(4, quantity);
                pst.setString(5, dateHire);
                pst.setString(6, dateReturn);
                pst.setInt(7, numDate);
                pst.setBoolean(8, true);
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

    public float getDiscount(String discountID) throws NamingException, SQLException, ClassNotFoundException {
        float result = 0;
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select value from tblDiscounts where discountID = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, discountID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getFloat("value");
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

    public String getDiscountName(String discountID) throws NamingException, SQLException, ClassNotFoundException {
        String discountName = "";
        Calendar calender = Calendar.getInstance();
        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH) + 1;
        int day = calender.get(Calendar.DAY_OF_MONTH);
        String currentDate = year + "-" + month + "-" + day;
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select discountName from tblDiscounts where discountID = ? and ? between dateStart and dateEnd";
                pst = cn.prepareStatement(sql);
                pst.setString(1, discountID);
                pst.setString(2, currentDate);
                rs = pst.executeQuery();
                if (rs.next()) {
                    discountName = rs.getString("discountName");
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
        return discountName;
    }

    public String getDiscountOutOfDate(String discountID) throws NamingException, SQLException, ClassNotFoundException {
        String discountName = "";
        Calendar calender = Calendar.getInstance();
        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH) + 1;
        int day = calender.get(Calendar.DAY_OF_MONTH);
        String currentDate = year + "-" + month + "-" + day;
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select discountName from tblDiscounts where discountID = ? and ? not between dateStart and dateEnd";
                pst = cn.prepareStatement(sql);
                pst.setString(1, discountID);
                pst.setString(2, currentDate);
                rs = pst.executeQuery();
                if (rs.next()) {
                    discountName = rs.getString("discountName");
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
        return discountName;
    }

    public ArrayList<OrderDTO> getListOrderByID(String orderID) throws NamingException, SQLException, ClassNotFoundException {
        ArrayList<OrderDTO> list = new ArrayList<>();
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select C.name, C.color, C.year, C.image, C.description, C.typeID, C.createDate, OD.carID, C.price, OD.quantity, dateHire, dateReturn, numberDate, OD.status from tblOrderDetails OD, tblCars C where OD.carID = C.carID and OD.orderID = ? and OD.status = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, orderID);
                pst.setBoolean(2, true);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String carID = rs.getString("carID");
                    String name = rs.getString("name");;
                    String color = rs.getString("color");;
                    String year = rs.getString("year");;;
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    int numDate = rs.getInt("numberDate");
                    String image = rs.getString("image");;;
                    String description = rs.getString("description");
                    String typeID = rs.getString("typeID");
                    String createDate = rs.getString("createDate");
                    String dateHire = rs.getString("dateHire");
                    String dateReturn = rs.getString("dateReturn");
                    OrderDTO order = new OrderDTO(carID, name, color, year, price, quantity, numDate, image, description, typeID, createDate, dateHire, dateReturn);
                    list.add(order);
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

    public int deleteOrderDetail(String carID, String orderID, String dateHire, String dateReturn) throws NamingException, SQLException, ClassNotFoundException {
        int result = 0;
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "update tblOrderDetails set status = ? where orderID = ? and carID = ? and dateHire = ? and dateReturn = ?";
                pst = cn.prepareStatement(sql);
                pst.setBoolean(1, false);
                pst.setString(2, orderID);
                pst.setString(3, carID);
                pst.setString(4, dateHire);
                pst.setString(5, dateReturn);
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

    public int deleteOrder(String orderID) throws NamingException, SQLException, ClassNotFoundException {
        int result = 0;
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "update tblOrders set status = ? where orderID = ?";
                pst = cn.prepareStatement(sql);
                pst.setBoolean(1, false);
                pst.setString(2, orderID);
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

    public int updatePrice(String orderID, float total) throws NamingException, SQLException, ClassNotFoundException {
        int result = 0;
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "update tblOrders set total = ? where orderID = ?";
                pst = cn.prepareStatement(sql);
                pst.setFloat(1, total);
                pst.setString(2, orderID);
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

    public OrderDTO getOrderDetail(String orderID, String carID, String dateHire, String dateReturn) throws NamingException, SQLException, ClassNotFoundException {
        OrderDTO order = null;
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select price, quantity, numberDate, status from tblOrderDetails where orderID = ? and carID = ? and dateHire = ? and dateReturn = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, orderID);
                pst.setString(2, carID);
                pst.setString(3, dateHire);
                pst.setString(4, dateReturn);
                rs = pst.executeQuery();
                if (rs.next()) {
                    order = new OrderDTO(carID, "*", "*", "*", rs.getFloat("price"), rs.getInt("quantity"), rs.getInt("numberDate"), "*", "*", "*", "*", dateHire, dateReturn);
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
        return order;
    }

    public float getTotal(String orderID) throws NamingException, SQLException, ClassNotFoundException {
        float result = 0;
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select total from tblOrders where orderID = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, orderID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getFloat("total");
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

    public String getDiscountOrderID(String orderID) throws NamingException, SQLException, ClassNotFoundException {
        String discountName = "";
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select discount from tblOrders where orderID = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, orderID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    discountName = rs.getString("discount");
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
        return discountName;
    }

    public String getDiscountID(String discountName) throws NamingException, SQLException, ClassNotFoundException {
        String discountID = "";
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select discountID from tblDiscounts where discountName = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, discountName);
                rs = pst.executeQuery();
                if (rs.next()) {
                    discountID = rs.getString("discountID");
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
        return discountID;
    }

    public float getValueDiscount(String discountID) throws NamingException, SQLException, ClassNotFoundException {
        float result = 0;
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select value from tblDiscounts where discountID = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, discountID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getFloat("value");
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

    public ArrayList<HistoryDTO> getListOrderSearchByDate(String userID, String dateOrder, int index) throws NamingException, SQLException, ClassNotFoundException {
        ArrayList<HistoryDTO> list = new ArrayList<>();
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select orderID, total, discount, status from\n"
                        + "(select ROW_NUMBER() over (order by orderID desc) as stt, orderID, total, discount, status\n"
                        + "from tblOrders\n"
                        + "where userID = ? and dateOrder = ? and status = ?) as X where stt between ?*10-9 and ?*10";
                pst = cn.prepareStatement(sql);
                pst.setString(1, userID);
                pst.setString(2, dateOrder);
                pst.setBoolean(3, true);
                pst.setInt(4, index);
                pst.setInt(5, index);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString("orderID");
                    float total = rs.getFloat("total");
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

    public int getNumSearchOrderDate(String userID, String dateOrder) throws NamingException, SQLException, ClassNotFoundException {
        int num = 0;
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select count(orderID) as num from tblOrders where userID = ? and dateOrder = ? and status = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, userID);
                pst.setString(2, dateOrder);
                pst.setBoolean(3, true);
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

    public ArrayList<HistoryDTO> getListOrderSearchByName(String userID, String name, int index) throws NamingException, SQLException, ClassNotFoundException {
        ArrayList<HistoryDTO> list = new ArrayList<>();
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select orderID, total, dateOrder, discount, status from\n"
                        + "(select ROW_NUMBER() over (order by O.dateOrder desc) as stt, O.orderID, O.total, O.dateOrder, O.discount, O.status\n"
                        + "from tblCars C, tblOrderDetails OD, tblOrders O \n"
                        + "where C.carID = OD.carID and OD.orderID = O.orderID and C.name like ? and O.userID = ? and O.status = ? and OD.status = ? group by O.orderID, O.total, O.dateOrder, O.discount, O.status) as X where stt between ?*10-9 and ?*10";
                pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + name + "%");
                pst.setString(2, userID);
                pst.setBoolean(3, true);
                pst.setBoolean(4, true);
                pst.setInt(5, index);
                pst.setInt(6, index);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString("orderID");
                    float total = rs.getFloat("total");
                    String discount = rs.getString("discount");
                    boolean status = rs.getBoolean("status");
                    String dateOrder = rs.getString("dateOrder");
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

    public int getNumSearchOrderName(String userID, String name) throws NamingException, SQLException, ClassNotFoundException {
        int num = 0;
        ArrayList<HistoryDTO> list = new ArrayList<>();
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select O.orderID, O.total, O.dateOrder, O.discount, O.status\n"
                        + "from tblCars C, tblOrderDetails OD, tblOrders O \n"
                        + "where C.carID = OD.carID and OD.orderID = O.orderID and C.name like ? and O.userID = ? and O.status = ? and OD.status = ?\n"
                        + "group by O.orderID, O.total, O.dateOrder, O.discount, O.status";
                pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + name + "%");
                pst.setString(2, userID);
                pst.setBoolean(3, true);
                pst.setBoolean(4, true);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString("orderID");
                    float total = rs.getFloat("total");
                    String discount = rs.getString("discount");
                    boolean status = rs.getBoolean("status");
                    String dateOrder = rs.getString("dateOrder");
                    HistoryDTO history = new HistoryDTO(orderID, total, dateOrder, discount, status);
                    list.add(history);
                }
                num = list.size();
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
