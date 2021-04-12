<%-- 
    Document   : historyDetail
    Created on : Mar 5, 2021, 12:23:30 AM
    Author     : Le Duy Tuan Vu
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> DETAIL HISTORY PAGE </title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css2?family=Patua+One&display=swap" rel="stylesheet">    
        <style>
            .navbar .container-fluid .nav-link {
                color: #333333;
            }
            .navbar .container-fluid .nav-link:hover {
                color: #FF9933;
            }            
        </style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
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
            <div class="row" style="margin-top: 40px">
                <!-- ALL PRODUCT -->  
                <!-- GET PRODUCT -->
                <p>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</p>
                <c:if test="${not empty requestScope.MESS_DELETE_ORDER}">
                    <div><p style="color: red; font-size: 18px; font-family: 'Patua One', cursive; margin-bottom: 8px">${requestScope.MESS_DELETE_ORDER}</p></div>
                    </c:if>
                    <c:if test="${not empty sessionScope.LIST_ORDER_DETAIL}">
                        <c:if test="${sessionScope.LIST_ORDER_DETAIL.size() != 0}">
                            <c:forEach var="car" items="${sessionScope.LIST_ORDER_DETAIL}" varStatus="counter">  
                            <div class="col-md-3">
                                <form action="MainController" method="POST">
                                    <input type="hidden" name="txtCarID" value="${car.carID}">
                                    <div class="card mb-3 box-shadow" style="border: 2px solid #CCCCCC">
                                        <img class="card-img-top" src="${car.image}" data-holder-rendered="true" style="height: 180px; width: 100%; display: block;">
                                        <hr>
                                        <div class="card-body" style="font-family: 'Patua One', cursive;">
                                            <p class="card-text" style="margin-top: -22px; font-size: 16px">${car.name} (${car.color})</p>
                                            <p class="card-text" style="margin-top: -11px; font-size: 16px">Price : ${car.price} $</p>
                                            <p class="card-text" style="margin-top: -11px; font-size: 16px">Quantity : ${car.quantity}</p>                                                        
                                            <c:set var="amount" value="${car.price*car.quantity*car.numDate}"></c:set>
                                            <p class="card-text" style="margin-top: -10px">Amount: ${amount} $</p>
                                            <p class="card-text" style="margin-top: -10px; font-size: 16px">Date hire : ${car.dateHire}</p>
                                            <p class="card-text" style="margin-top: -10px; font-size: 16px">Date return : ${car.dateReturn}</p>                        
                                            <div class="d-flex justify-content-between align-items-center" style="margin-top: -9px; margin-bottom: -6px">
                                                <a style="text-decoration: none" href="DeleteOrderController?txtCarID=${car.carID}&txtDateHire=${car.dateHire}&txtDateReturn=${car.dateReturn}" onclick="return confirm('Do you want to delete ?')">DELETE</a>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${sessionScope.LIST_ORDER_DETAIL.size() == 0}">
                        <p style="margin-top: -2px; color: red; font-family: 'Patua One', cursive; font-size: 19px">Your order has been canceled !</p>
                    </c:if>    
                </c:if>
                <c:if test="${empty sessionScope.LIST_ORDER_DETAIL}">
                    <p style="margin-top: -2px; color: red; font-family: 'Patua One', cursive; font-size: 19px">Your order has been canceled !</p>
                </c:if>
                <p style="margin-top: ">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</p>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>
    </body>
</html>
