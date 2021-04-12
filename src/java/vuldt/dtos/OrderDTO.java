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
public class OrderDTO {

    String carID;
    String name;
    String color;
    String year;
    float price;
    int quantity;
    int numDate;
    String image;
    String description;
    String typeID;
    String createDate;
    String dateHire;
    String dateReturn;

    public OrderDTO() {

    }

    public OrderDTO(String carID, String name, String color, String year, float price, int quantity, int numDate, String image, String description, String typeID, String createDate, String dateHire, String dateReturn) {
        this.carID = carID;
        this.name = name;
        this.color = color;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
        this.numDate = numDate;
        this.image = image;
        this.description = description;
        this.typeID = typeID;
        this.createDate = createDate;
        this.dateHire = dateHire;
        this.dateReturn = dateReturn;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getNumDate() {
        return numDate;
    }

    public void setNumDate(int numDate) {
        this.numDate = numDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDateHire() {
        return dateHire;
    }

    public void setDateHire(String dateHire) {
        this.dateHire = dateHire;
    }

    public String getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
    }

}
