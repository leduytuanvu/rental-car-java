<%-- 
    Document   : home
    Created on : Feb 13, 2021, 2:24:01 PM
    Author     : Le Duy Tuan Vu
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> HOME PAGE </title>
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
                            <c:if test="${not empty sessionScope.USER}">
                                <c:if test="${sessionScope.USER.roleID eq 'US'}">
                                <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="HistoryPageController"> HISTORY </a></li>                               
                                    <c:if test="${not empty sessionScope.CART}">
                                        <c:set var="numberOrder" value="${sessionScope.CART.cart.size()}"></c:set>
                                    <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="CartPageController"> MY CART (${numberOrder}) </a></li>
                                    </c:if>
                                    <c:if test="${empty sessionScope.CART}">
                                    <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="CartPageController"> MY CART </a></li>
                                    </c:if>
                                <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="#profile" style="color: #FF9933">${sessionScope.USER.fullName}</a></li>
                                    <c:if test="${sessionScope.USER.active == false}">
                                    <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="ActivePageController"> ACTIVE ACCOUNT </a></li>
                                    </c:if>
                                <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="SignOutController" style="margin-right: -13.5px"> SIGN OUT </a></li>
                                </c:if>
                                <c:if test="${sessionScope.USER.roleID ne 'US'}">
                                <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="#profile" style="color: #FF9933">${sessionScope.USER.fullName}</a></li>
                                <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="SignOutController" style="margin-right: -13.5px"> SIGN OUT </a></li>
                                </c:if>
                            </c:if>
                            <c:if test="${empty sessionScope.USER}">
                            <li class="nav-item" role="presentation" style="margin-right: -13.5px"><a class="nav-link js-scroll-trigger" href="SignInPageController?txtSignIn=SignIn"> SIGN IN </a></li>
                            </c:if>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container-fluid" style="margin-top: 90px">
            <div class="row">
                <form action="SearchController">
                    <div style="margin-bottom: 25px; display: flex">
                        <input type="text" value="${sessionScope.TXT_NAME}" name="txtName" placeholder=" Enter name's car to search" style="width: 18%; border-radius: 4px; font-family: 'Patua One', cursive; font-size: 17px; margin-right: 8px; text-align: center">
                        <select class="form-control" name="comboxType" style="text-align-last: center; width: 18%; font-family: 'Patua One', cursive; font-size: 17px; border: 2px solid dimgray; margin-right: 8px;">                              
                            <c:if test="${not empty sessionScope.LIST_TYPE}">
                                <option value="all" style="font-weight: 400; text-align: center">All Categories</option>
                                <c:forEach var="typeName" items="${sessionScope.LIST_TYPE}">
                                    <c:if test="${sessionScope.TXT_TYPE eq typeName}">
                                        <option selected="selected" value="${typeName}" style="font-weight: 400;">${typeName}</option>
                                    </c:if>
                                    <c:if test="${sessionScope.TXT_TYPE ne typeName}">
                                        <option value="${typeName}" style="font-weight: 400;">${typeName}</option>
                                    </c:if> 
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty sessionScope.LIST_TYPE}">
                                <option value="empty"> Don't have any categories ! </option>    
                            </c:if>
                        </select>
                        <input placeholder=" Enter date hire" type="text" onfocus="(this.type = 'date')" name="txtDateHire" value="${sessionScope.TXT_DATE_HIRE}" style="border-radius: 4px; margin-right: 8px; width: 18%; font-family: 'Patua One', cursive; font-size: 17px; text-align: center">
                        <input placeholder=" Enter date return" type="text" onfocus="(this.type = 'date')" name="txtDateReturn" value="${sessionScope.TXT_DATE_RETURN}" style="border-radius: 4px; width: 18%; font-family: 'Patua One', cursive; font-size: 17px; text-align: center; margin-right: 8px">
                        <input type="number" value="${sessionScope.TXT_QUANTITY}" min="1" name="txtQuantity" placeholder=" Enter number of car" style="width: 18%; border-radius: 4px; font-family: 'Patua One', cursive; font-size: 17px; text-align: center; margin-right: 8px">
                        <input type="submit" name="btnAction" value="SEARCH" style="font-family: 'Patua One', cursive; font-size: 17px; background-color: #FF9933; border-radius: 4px; width: 110px">
                    </div>
                </form>
                <c:if test="${not empty requestScope.MESS_SEARCH}">
                    <p style="font-family: 'Patua One', cursive; font-size: 17px; color: red; margin-top: -14px">${requestScope.MESS_SEARCH}</p>
                </c:if>
                <c:if test="${not empty sessionScope.LIST_CAR}">
                    <c:forEach var="car" items="${sessionScope.LIST_CAR}">
                        <c:if test="${not empty sessionScope.CART}">
                            <c:set var="contrain" value="false"></c:set>
                            <c:if test="${not empty sessionScope.CART}">
                                <c:forEach var="order" items="${sessionScope.CART.cart}">
                                    <c:if test="${car.carID eq order.carID}">
                                        <c:set var="contrain" value="true"></c:set>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                            <c:if test="${contrain eq 'true'}">
                                <c:forEach var="order" items="${sessionScope.CART.cart}">
                                    <c:if test="${car.carID eq order.carID}">
                                        <c:set var="tempQuantity" value="${car.quantity - order.quantity}"></c:set>
                                        <c:if test="${tempQuantity < sessionScope.TXT_QUANTITY}">
                                            <div class="col-md-3" style="margin-top: -5px; margin-bottom: 12px">
                                                <form action="RentController">
                                                    <input type="hidden" name="txtCarID" value="${car.carID}">
                                                    <div class="card mb-3 box-shadow" style="border: 2px solid #CCCCCC">
                                                        <img class="card-img-top" src="${car.image}" data-holder-rendered="true" style="height: 190px; width: 100%; display: block;">
                                                        <hr>
                                                        <div class="card-body" style="font-family: 'Patua One', cursive;">
                                                            <p class="card-text" style="margin-top: -22px; color: #FF9933;">${car.name} <span style="color: black">&nbsp;(${car.price} $)</span></p>
                                                            <p class="card-text" style="margin-top: -10px">${car.description}</p>
                                                            <p class="card-text" style="margin-top: -10px">Create date : ${car.createDate}</p>
                                                            <div class="d-flex justify-content-between align-items-center" style="margin-top: -2px">
                                                                <div class="btn-group" style="margin-bottom: -1px">
                                                                    <input style="margin-right: 2px" type="button" name="btnAction" value="VIEW">
                                                                    <c:if test="${sessionScope.USER.roleID ne 'AD'}">
                                                                        <input disabled="disabled" type="submit" name="btnAction" value="RENT">
                                                                    </c:if>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </c:if>
                                        <c:if test="${tempQuantity >= sessionScope.TXT_QUANTITY}">
                                            <div class="col-md-3" style="margin-top: -5px; margin-bottom: 12px">
                                                <form action="RentController">
                                                    <input type="hidden" name="txtCarID" value="${car.carID}">
                                                    <div class="card mb-3 box-shadow" style="border: 2px solid #CCCCCC">
                                                        <img class="card-img-top" src="${car.image}" data-holder-rendered="true" style="height: 190px; width: 100%; display: block;">
                                                        <hr>
                                                        <div class="card-body" style="font-family: 'Patua One', cursive;">
                                                            <p class="card-text" style="margin-top: -22px; color: #FF9933;">${car.name} <span style="color: black">&nbsp;(${car.price} $)</span></p>
                                                            <p class="card-text" style="margin-top: -10px">${car.description}</p>
                                                            <p class="card-text" style="margin-top: -10px">Create date : ${car.createDate}</p>
                                                            <div class="d-flex justify-content-between align-items-center" style="margin-top: -2px">
                                                                <div class="btn-group" style="margin-bottom: -1px">
                                                                    <input style="margin-right: 2px" type="button" name="btnAction" value="VIEW">                                                                   
                                                                    <c:if test="${sessionScope.USER.roleID ne 'AD'}">
                                                                        <input type="submit" name="btnAction" value="RENT">
                                                                    </c:if>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                            <c:if test="${contrain ne 'true'}">
                                <div class="col-md-3" style="margin-top: -5px; margin-bottom: 12px">
                                    <form action="RentController">
                                        <input type="hidden" name="txtCarID" value="${car.carID}">
                                        <div class="card mb-3 box-shadow" style="border: 2px solid #CCCCCC">
                                            <img class="card-img-top" src="${car.image}" data-holder-rendered="true" style="height: 190px; width: 100%; display: block;">
                                            <hr>
                                            <div class="card-body" style="font-family: 'Patua One', cursive;">
                                                <p class="card-text" style="margin-top: -22px; color: #FF9933;">${car.name} <span style="color: black">&nbsp;(${car.price} $)</span></p>
                                                <p class="card-text" style="margin-top: -10px">${car.description}</p>
                                                <p class="card-text" style="margin-top: -10px">Create date : ${car.createDate}</p>
                                                <div class="d-flex justify-content-between align-items-center" style="margin-top: -2px">
                                                    <div class="btn-group" style="margin-bottom: -1px">
                                                        <input style="margin-right: 2px" type="button" name="btnAction" value="VIEW">                                                       
                                                        <c:if test="${sessionScope.USER.roleID ne 'AD'}">
                                                            <input type="submit" name="btnAction" value="RENT">
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </c:if>
                        </c:if> 
                        <c:if test="${empty sessionScope.CART}">
                            <div class="col-md-3" style="margin-top: -5px; margin-bottom: 12px">
                                <form action="RentController">
                                    <input type="hidden" name="txtCarID" value="${car.carID}">
                                    <div class="card mb-3 box-shadow" style="border: 2px solid #CCCCCC">
                                        <img class="card-img-top" src="${car.image}" data-holder-rendered="true" style="height: 190px; width: 100%; display: block;">
                                        <hr>
                                        <div class="card-body" style="font-family: 'Patua One', cursive;">
                                            <p class="card-text" style="margin-top: -22px; color: #FF9933;">${car.name} <span style="color: black">&nbsp;(${car.price} $)</span></p>
                                            <p class="card-text" style="margin-top: -10px">${car.description}</p>
                                            <p class="card-text" style="margin-top: -10px">Create date : ${car.createDate}</p>
                                            <div class="d-flex justify-content-between align-items-center" style="margin-top: -2px">
                                                <div class="btn-group" style="margin-bottom: -1px">
                                                    <input style="margin-right: 2px" type="button" name="btnAction" value="VIEW">                                                   
                                                    <c:if test="${sessionScope.USER.roleID ne 'AD'}">
                                                        <input type="submit" name="btnAction" value="RENT">
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </c:if> 
                    </c:forEach>
                </c:if>
                <c:if test="${empty sessionScope.LIST_CAR && not empty sessionScope.TXT_DATE_HIRE && not empty sessionScope.TXT_DATE_RETURN && not empty sessionScope.TXT_QUANTITY && not empty sessionScope.TXT_TYPE}">
                    <p style="font-family: 'Patua One', cursive; font-size: 17px; color: red; margin-top: -14px">Sorry, we could not find any cars match with your request !</p>
                </c:if>
                <nav aria-label="Page navigation example" style="margin-top: 3px; margin-bottom: -35px;">
                    <ul class="pagination">
                        <c:if test="${sessionScope.NUMBER_PAGE > 1}">
                            <li class="page-item" style="margin-left: auto">
                                <a class="page-link" href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <c:forEach var="num" begin="1" end="${sessionScope.NUMBER_PAGE}">
                                <li class="page-item"><a class="page-link" href="PagingController?txtNum=${num}">${num}</a></li>
                                </c:forEach>
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>
    </body>
</html>
