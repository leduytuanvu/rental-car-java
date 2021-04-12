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
public class UserDTO {

    String userID;
    String fullName;
    String password;
    String phone;
    String email;
    String address;
    String roleID;
    String codeActive;
    boolean active;
    boolean status;

    public UserDTO() {

    }

    public UserDTO(String userID, String fullName, String password, String phone, String email, String address, String roleID, String codeActive, boolean active, boolean status) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.roleID = roleID;
        this.codeActive = codeActive;
        this.active = active;
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getCodeActive() {
        return codeActive;
    }

    public void setCodeActive(String codeActive) {
        this.codeActive = codeActive;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
