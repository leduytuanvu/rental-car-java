<%-- 
    Document   : cart
    Created on : Feb 20, 2021, 6:29:57 PM
    Author     : Le Duy Tuan Vu
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> CART PAGE </title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Patua+One&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Ribeye+Marrow&display=swap" rel="stylesheet">
        <script src="https://kit.fontawesome.com/88a97fe5d9.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
        <style>
            .navbar .container-fluid .nav-link {
                color: #333333;
            }
            .navbar .container-fluid .nav-link:hover {
                color: #FF9933;
            } 
            a.fa-shopping-cart {
                position: relative;
                font-size: 1.5em;
                cursor: pointer;
            }
            span.fa-circle {
                position: absolute;
                font-size: 0.6em;
                top: 13px;
                color: rgb(138, 138, 138);
                right: 98px;
            }
            span.num {
                position: absolute;
                font-size: 0.4em;
                top: 14px;
                color: #fff;
                right: 100px;
            }

            input::-webkit-outer-spin-button,
            input::-webkit-inner-spin-button {
                -webkit-appearance: none;
                margin: 0;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-dark navbar-expand-lg fixed-top bg-light" id="mainNav" style="margin-left: 7px; margin-right: 7px">
            <div class="container-fluid"><a class="navbar-brand" href="#home" style="font-family: 'Patua One', cursive; color: #FF9933; font-size: 28px; margin-left: -7px"> CAR RENTAL </a><button data-toggle="collapse" data-target="#navbarResponsive" class="navbar-toggler navbar-toggler-right" type="button" data-toogle="collapse" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><i class="fas fa-bars"></i></button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="nav navbar-nav ml-auto text-uppercase" style="margin-left: auto; font-family: 'Patua One', cursive; font-size: 19px">
                        <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="AllCarController"> ALL CAR </a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="HistoryPageController"> HISTORY </a></li>
                            <c:if test="${not empty sessionScope.CART}">
                                <c:set var="numberOrder" value="${sessionScope.CART.cart.size()}"></c:set>
                                <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="CartPageController"> MY CART (${numberOrder}) </a></li>
                            </c:if>
                            <c:if test="${empty sessionScope.CART}">
                                <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="CartPageController"> MY CART </a></li>
                            </c:if>
                            <c:if test="${not empty sessionScope.USER}">
                            <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="#profile" style="color: #FF9933">${sessionScope.USER.fullName}</a></li>
                            <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="SignOutController"> SIGN OUT </a></li>
                            </c:if>
                            <c:if test="${empty sessionScope.USER}">
                            <li class="nav-item" role="presentation" style="margin-right: -13.5px"><a class="nav-link js-scroll-trigger" href="SignInPageController" style="color: #333333"> SIGN IN </a></li>
                            </c:if>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-3" style="margin-left: -10.5px; background-color: white; margin-top: 78px; font-family: 'Patua One', cursive; font-size: 20px;">
                    <form action="RentingController" method="POST">
                        <p style="margin-left: 13px">Number of car : ${sessionScope.NUM_PRO}</p>
                        <p style="margin-left: 13px; margin-top: -10px; margin-left: 13px; margin-bottom: 11px">Total : ${sessionScope.TOTAL} $</p>
                        <input placeholder=" Enter your name" style="width: 99%; font-size: 18px; font-family: 'Patua One', cursive; margin-bottom: 12px; margin-left: 13px" type="text" name="txtNameCustomer" value="${sessionScope.NAME_CUS}">
                        <c:if test="${not empty requestScope.MESS_NAME_CUS}">
                            <p style="color: red; font-size: 18px; margin-left: 13px; margin-top: -10px; margin-bottom: 5px">${requestScope.MESS_NAME_CUS}</p>
                        </c:if>
                        <input placeholder=" Enter your phone" style="width: 99%; font-size: 18px; font-family: 'Patua One', cursive; margin-bottom: 12px; margin-left: 13px" type="number" name="txtPhoneCustomer" value="${sessionScope.PHONE_CUS}">
                        <c:if test="${not empty requestScope.MESS_PHONE_CUS}">
                            <p style="color: red; font-size: 18px; margin-left: 13px; margin-top: -10px; margin-bottom: 5px">${requestScope.MESS_PHONE_CUS}</p>
                        </c:if>
                        <input placeholder=" Enter your email" style="width: 99%; font-size: 18px; font-family: 'Patua One', cursive; margin-bottom: 12px; margin-left: 13px" type="text" name="txtEmailCustomer" value="${sessionScope.EMAIL_CUS}">
                        <c:if test="${not empty requestScope.MESS_EMAIL_CUS}">
                            <p style="color: red; font-size: 18px; margin-left: 13px; margin-top: -10px; margin-bottom: 5px">${requestScope.MESS_EMAIL_CUS}</p>
                        </c:if>
                        <input placeholder=" Enter your address" style="width: 99%; font-size: 18px; font-family: 'Patua One', cursive; margin-bottom: 12px; margin-left: 13px;" type="text" name="txtAddressCustomer" value="${sessionScope.ADDRESS_CUS}">
                        <c:if test="${not empty requestScope.MESS_ADDRESS_CUS}">
                            <p style="color: red; font-size: 18px; margin-left: 13px; margin-top: -10px; margin-bottom: 5px">${requestScope.MESS_ADDRESS_CUS}</p>
                        </c:if>
                        <div style="display: flex">
                            <input placeholder=" Discount" style="width: 99%; font-size: 18px; font-family: 'Patua One', cursive; margin-bottom: 20px; margin-left: 13px;" type="text" name="txtDiscount" value="${sessionScope.DISCOUNT}">
                            <input type="submit" name="btnAction" value="USE DISCOUNT" style="margin-left: 5px; color: #FF9933; height: 32px">
                        </div>
                        <c:if test="${not empty requestScope.MESS_DISCOUNT}">
                            <p style="color: red; font-size: 18px; margin-left: 13px; margin-top: -10px; margin-bottom: 5px">${requestScope.MESS_DISCOUNT}</p>
                        </c:if>
                        <div style="margin-top: -5px">
                            <a style="margin-left: 13px; text-decoration: none;" href="UseProfileController">USE MY PROFILE</a></br>
                            <input type="submit" name="btnAction" value="RENT NOW" style="margin-left: 13.5px; margin-top: 14px; color: #FF9933">
                        </div>
                    </form>
                </div>
                <!-- ALL PRODUCT -->
                <div class="col-md-9" style="display: flex;">   
                    <div class="album py-5 bg-light">  
                        <div class="container">
                            <div class="row"> 
                                <!-- GET PRODUCT -->
                                <p>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</p>
                                <c:if test="${not empty requestScope.MESS_CART}">
                                    <div><p style="color: red; font-size: 18px; font-family: 'Patua One', cursive; margin-top: -10px; margin-left: 0px; margin-bottom: 8px">${requestScope.MESS_CART}</p></div>
                                    </c:if>
                                    <c:if test="${not empty sessionScope.CART.cart}">
                                        <c:forEach var="car" items="${sessionScope.CART.cart}">                                       
                                        <div class="col-md-3">
                                            <form action="MainController" method="POST">
                                                <input type="hidden" name="txtCarID" value="${car.carID}">
                                                <div class="card mb-3 box-shadow" style="border: 2px solid #CCCCCC">
                                                    <img class="card-img-top" src="${car.image}" data-holder-rendered="true" style="height: 140px; width: 100%; display: block;">
                                                    <hr>
                                                    <div class="card-body" style="font-family: 'Patua One', cursive;">
                                                        <p class="card-text" style="margin-top: -22px; font-size: 16px">${car.name}</p>
                                                        <p class="card-text" style="margin-top: -11px; font-size: 16px">Price : ${car.price} $</p>
                                                        <div style="display: flex;"><p class="card-text" style="margin-top: -10px; font-size: 15px">Quantity: </p>
                                                            <a style="text-decoration: none; font-size: 25px; margin-top: -19.5px; margin-left: 10px" href="MinusQuantityController?txtCarID=${car.carID}&txtQuantity=${car.quantity}">-</a>&nbsp;&nbsp;
                                                            <input style="text-align: center; height: 20px; width: 25px; margin-top: -9px; font-size: 15px" disabled="true" type="text" value="${car.quantity}">&nbsp;&nbsp;
                                                            <a style="text-decoration: none; font-size: 25px; margin-top: -19.5px" href="AddQuantityController?txtCarID=${car.carID}&txtQuantity=${car.quantity}">+</a>
                                                        </div>                                                          
                                                        <c:set var="amount" value="${car.price*car.quantity*car.numDate}"></c:set>
                                                        <p class="card-text" style="margin-top: -10px">Amount: ${amount} $</p>
                                                        <p class="card-text" style="margin-top: -10px; font-size: 16px">Date hire : ${car.dateHire}</p>
                                                        <p class="card-text" style="margin-top: -10px; font-size: 16px">Date return : ${car.dateReturn}</p>                        
                                                        <div class="d-flex justify-content-between align-items-center" style="margin-top: -9px; margin-bottom: -6px">
                                                            <a style="text-decoration: none" href="DeleteCartController?txtCarID=${car.carID}&txtDateHire=${car.dateHire}&txtDateReturn=${car.dateReturn}" onclick="return confirm('Do you want to delete ?')">DELETE</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </c:forEach>
                                </c:if>
                                <p style="margin-top: ">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
