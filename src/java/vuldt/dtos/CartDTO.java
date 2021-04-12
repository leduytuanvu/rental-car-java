/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuldt.dtos;

import java.util.ArrayList;

/**
 *
 * @author Le Duy Tuan Vu
 */
public class CartDTO {

    ArrayList<OrderDTO> cart;

    public CartDTO() {

    }

    public CartDTO(ArrayList<OrderDTO> cart) {
        this.cart = cart;
    }

    public ArrayList<OrderDTO> getCart() {
        return cart;
    }

    public void setCart(ArrayList<OrderDTO> cart) {
        this.cart = cart;
    }

    public boolean checkExist(OrderDTO newOrder) {
        for (OrderDTO order : cart) {
            if (order.carID.equals(newOrder.getCarID()) && order.dateHire.equals(newOrder.dateHire) && order.dateReturn.equals(newOrder.dateReturn)) {
                return false;
            }
        }
        return true;
    }

    public void newCart() {
        cart = new ArrayList<>();
    }

    public boolean addCarInToCart(OrderDTO newOrder) {
        if (cart == null) {
            cart = new ArrayList<>();
        }
        if (checkExist(newOrder)) {
            cart.add(newOrder);
            return true;
        } else {
            for (OrderDTO order : cart) {
                if (order.getCarID().equals(newOrder.getCarID())) {
                    order.setQuantity(order.getQuantity() + newOrder.getQuantity());
                    return true;
                }
            }
        }
        return false;
    }

    public boolean deleteCar(String txtCarID, String txtDateHire, String txtDateReturn) {
        if (cart == null || cart.size() == 0) {
            return false;
        } else {
            for (int i = 0; i < cart.size(); i++) {
                if (cart.get(i).carID.equals(txtCarID) && cart.get(i).dateHire.equals(txtDateHire) && cart.get(i).dateReturn.equals(txtDateReturn)) {
                    cart.remove(cart.get(i));
                    return true;
                }
            }
        }
        return false;
    }

}
