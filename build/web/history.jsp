<%-- 
    Document   : history
    Created on : Mar 4, 2021, 4:34:16 PM
    Author     : Le Duy Tuan Vu
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> HISTORY PAGE </title>
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
            <div class="row">
                <div class="col-md-3" style="background-color: white; margin-top: 78px; font-family: 'Patua One', cursive; font-size: 20px;">
                    <form action="SearchHistoryController" method="POST">
                        <div style="display: flex; margin-bottom: 15px">
                            <input id="nameSearch" type="text" value="${sessionScope.NAME_SEARCH_ORDER}" name="txtName" placeholder=" Enter name's car to search" style="width: 95%; border-radius: 4px; font-family: 'Patua One', cursive; font-size: 17px; margin-right: 8px; margin-top: 5px"><br>
                            <c:if test="${sessionScope.RADIO eq 'searchName'}">
                                <input id="radioCheck" checked="checked" type="radio" name="radioCheck" value="searchName" style="width: 25px; height: 25px; margin-top: 8px">
                            </c:if>
                            <c:if test="${sessionScope.RADIO ne 'searchName'}">
                                <input id="radioCheck" type="radio" name="radioCheck" value="searchName" style="width: 25px; height: 25px; margin-top: 8px">
                            </c:if>
                        </div>
                        <div style="display: flex">
                            <input id="dateSearch" placeholder=" Enter date hire" type="text" onfocus="(this.type = 'date')" name="txtDate" value="${sessionScope.DATE_SEARCH_ORDER}" style="border-radius: 4px; margin-right: 8px; width: 95%; font-family: 'Patua One', cursive; font-size: 17px;">
                            <c:if test="${sessionScope.RADIO eq 'searchDate'}">
                                <input id="radioCheck" checked="checked" type="radio" name="radioCheck" value="searchDate" style="width: 25px; height: 25px; margin-top: 3px">
                            </c:if>
                            <c:if test="${sessionScope.RADIO ne 'searchDate'}">
                                <input id="radioCheck" type="radio" name="radioCheck" value="searchDate" style="width: 25px; height: 25px; margin-top: 3px">
                            </c:if>              
                        </div>       
                        <c:if test="${not empty requestScope.MESS_SEARCH_HISTORY}">
                            <p style="font-family: 'Patua One', cursive; color: red; font-size: 17px; margin-top: 5px; margin-left: 2px; margin-bottom: -10px">${requestScope.MESS_SEARCH_HISTORY}</p>
                        </c:if>
                        <div style="margin-top: 4px">
                            <input type="submit" name="btnAction" value="SEARCH" style="margin-top: 14px; color: #FF9933">
                        </div>
                    </form>
                </div>
                <!-- ALL PRODUCT -->
                <div class="col-md-9" style="display: flex;">   
                    <div class="album py-5 bg-light">  
                        <div class="container">
                            <div class="row" style="margin-top: -9px; margin-left: -15px"> 
                                <!-- GET PRODUCT -->
                                <p>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</p>
                                <c:if test="${not empty requestScope.MESS_HISTORY}">
                                    <div><p style="color: red; font-size: 18px; font-family: 'Patua One', cursive; margin-top: -10px; margin-left: 0px; margin-bottom: 8px">${requestScope.MESS_HISTORY}</p></div>
                                    </c:if>
                                    <c:if test="${not empty sessionScope.LIST_HISTORY}">
                                        <c:if test="${sessionScope.LIST_HISTORY.size() != 0}">
                                            <c:forEach var="history" items="${sessionScope.LIST_HISTORY}" varStatus="counter">  
                                                <c:if test="${not empty history.discount}">
                                                <div style="display: flex;">
                                                    <a style="font-family: 'Patua One', cursive; font-size: 19px; text-decoration: none" href="DetailHistoryPageController?txtOrderId=${history.orderID}">MORE DETAIL</a>                                       
                                                    <p style="font-family: 'Patua One', cursive; font-size: 19px">&emsp;${10*(sessionScope.PAGING_NUM-1)+counter.count}. Date order : ${history.dateOrder} - Total : ${history.total} $ &nbsp; (${history.discount}).</p>                                                                                            
                                                </div>
                                            </c:if>
                                            <c:if test="${empty history.discount}">
                                                <div style="display: flex">
                                                    <a style="font-family: 'Patua One', cursive; font-size: 19px; text-decoration: none" href="DetailHistoryPageController?txtOrderId=${history.orderID}">MORE DETAIL</a>
                                                    <p style="font-family: 'Patua One', cursive; font-size: 19px">&emsp;${10*(sessionScope.PAGING_NUM-1)+counter.count}. Date order : ${history.dateOrder} - Total : ${history.total} $.</p>                                                                                           
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${empty requestScope.MESS_SEARCH_HISTORY}">
                                        <c:if test="${sessionScope.LIST_HISTORY.size() == 0}">
                                            <p style="margin-top: -3px; margin-left: -4px; color: red; font-family: 'Patua One', cursive; font-size: 19px">Don't have any order !</p>
                                        </c:if>  
                                    </c:if>
                                </c:if>
                                <c:if test="${empty requestScope.MESS_SEARCH_HISTORY}">
                                    <c:if test="${empty sessionScope.LIST_HISTORY}">
                                        <p style="margin-top: -3px; margin-left: -4px; color: red; font-family: 'Patua One', cursive; font-size: 19px">Don't have any order !</p>
                                    </c:if>
                                </c:if>
                                <p style="margin-top: ">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</p>
                                <nav aria-label="Page navigation example" style="margin-top: 3px; margin-bottom: -35px;">
                                    <ul class="pagination">
                                        <c:if test="${sessionScope.NUM_PAGE_HISTORY > 1}">
                                            <li class="page-item" style="margin-left: auto">
                                                <a class="page-link" href="#" aria-label="Previous">
                                                    <span aria-hidden="true">&laquo;</span>
                                                </a>
                                            </li>
                                            <c:forEach var="num" begin="1" end="${sessionScope.NUM_PAGE_HISTORY}">
                                                <li class="page-item"><a class="page-link" href="PagingHistoryController?txtNum=${num}">${num}</a></li>
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
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>
    </body>
</html>
