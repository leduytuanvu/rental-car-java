/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuldt.dtos;

/**
 *
 * @author Le Duy Tuan Vu
 */
public class HistoryDTO {

    String orderID;
    float total;
    String dateOrder;
    String discount;
    boolean status;

    public HistoryDTO() {

    }

    public HistoryDTO(String orderID, float total, String dateOrder, String discount, boolean status) {
        this.orderID = orderID;
        this.total = total;
        this.dateOrder = dateOrder;
        this.discount = discount;
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
