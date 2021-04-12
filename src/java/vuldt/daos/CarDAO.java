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
import vuldt.dtos.CarDTO;
import vuldt.utils.ConnectionDB;

/**
 *
 * @author Le Duy Tuan Vu
 */
public class CarDAO {

    Connection cn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public ArrayList<CarDTO> getListCarFirst(int index) throws NamingException, SQLException, ClassNotFoundException {
        ArrayList<CarDTO> list = new ArrayList<>();
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select carID, name, color, [year], price, quantity, [image], [description], typeID, createDate, [status] from (select row_number() over (order by createDate desc) as stt, carID, name, color, [year], price, quantity, [image], [description], typeID, createDate, [status] from tblCars where quantity > 0) as X where stt between ?*20-19 and ?*20";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, index);
                pst.setInt(2, index);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String carID = rs.getString("carID");
                    String name = rs.getString("name");
                    String color = rs.getString("color");
                    String year = rs.getString("year");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    String typeID = rs.getString("typeID");
                    String createDate = rs.getString("createDate");
                    boolean status = rs.getBoolean("status");
                    CarDTO car = new CarDTO(carID, name, color, year, price, quantity, image, description, typeID, createDate, status);
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

    public int getNumberCarFirst() throws NamingException, SQLException, ClassNotFoundException {
        int number = 0;
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select count(carID) as num from tblCars where quantity > 0";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    number = rs.getInt("num");
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
        return number;
    }

    public ArrayList<String> getListType() throws NamingException, SQLException, ClassNotFoundException {
        ArrayList<String> list = new ArrayList<>();
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select typeName from tblTypeCars";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String typeName = rs.getString("typeName");
                    list.add(typeName);
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

    public ArrayList<CarDTO> getListCarReady(String name, String typeID, String dateHire, String dateReturn) throws NamingException, SQLException, ClassNotFoundException {
        ArrayList<CarDTO> list = new ArrayList<>();
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "";
                if (!name.trim().isEmpty() && !typeID.equals("all") && !typeID.equals("empty")) {
                    sql = "select carID, name, color, [year], price, quantity, [image], [description], typeID, createDate, [status] from tblCars  where name like ? and typeID = ?";
                    pst = cn.prepareStatement(sql);
                    pst.setString(1, "%" + name + "%");
                    pst.setString(2, typeID);
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
                } else if (name.trim().isEmpty() && !typeID.equals("all")) {
                    sql = "select carID, name, color, [year], price, quantity, [image], [description], typeID, createDate, [status] from tblCars  where typeID = ?";
                    pst = cn.prepareStatement(sql);
                    pst.setString(1, typeID);
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
                    sql = "select carID, name, color, [year], price, quantity, [image], [description], typeID, createDate, [status] from tblCars  where name like ?";
                    pst = cn.prepareStatement(sql);
                    pst.setString(1, "%" + name + "%");
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

    public String getTypeID(String nameType) throws NamingException, SQLException, ClassNotFoundException {
        String typeID = "";
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select typeID from tblTypeCars where typeName = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, nameType);
                rs = pst.executeQuery();
                if (rs.next()) {
                    typeID = rs.getString("typeID");
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
        return typeID;
    }

    public CarDTO getCarByID(String carID) throws NamingException, SQLException, ClassNotFoundException {
        CarDTO car = null;
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select name, color, [year], price, quantity, [image], [description], typeID, createDate, [status] from tblCars where carID = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, carID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String color = rs.getString("color");
                    String year = rs.getString("year");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    String typeID = rs.getString("typeID");
                    String createDate = rs.getString("createDate");
                    boolean status = rs.getBoolean("status");
                    car = new CarDTO(carID, name, color, year, price, quantity, image, description, typeID, createDate, status);
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
        return car;
    }

    public int getQuantityByID(String carID) throws NamingException, SQLException, ClassNotFoundException {
        int quantity = 0;
        try {
            cn = ConnectionDB.getConnection();
            if (cn != null) {
                String sql = "select quantity from tblCars where carID = ?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, carID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
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
        return quantity;
    }
}
