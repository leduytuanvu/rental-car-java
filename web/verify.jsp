<%-- 
    Document   : verify
    Created on : Feb 28, 2021, 10:06:28 AM
    Author     : Le Duy Tuan Vu
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> VERIFY PAGE </title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css2?family=Patua+One&display=swap" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <style>
            .navbar .container-fluid .nav-link {
                color: #333333;
            }
            .navbar .container-fluid .nav-link:hover {
                color: #FF9933;
            }            
            .text-center .container .row .col-md-6 .panel{
                background-color: rgba(0, 0, 0, 0.8);
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-dark navbar-expand-lg fixed-top bg-light" id="mainNav" style="margin-left: 7px; margin-right: 7px">
            <div class="container-fluid"><a class="navbar-brand" href="#home" style="font-family: 'Patua One', cursive; color: #FF9933; font-size: 28px;"> CAR RENTAL </a><button data-toggle="collapse" data-target="#navbarResponsive" class="navbar-toggler navbar-toggler-right" type="button" data-toogle="collapse" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><i class="fas fa-bars"></i></button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="nav navbar-nav ml-auto text-uppercase" style="margin-left: auto; font-family: 'Patua One', cursive; font-size: 19px">
                        <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="SignInPageController"> SIGN IN </a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link js-scroll-trigger" href="SignUpPageController"> SIGN UP </a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6" style="margin-top: 160px">
                    <div class="panel panel-default" >
                        <div class="panel-body" style="margin-top: 30px">
                            <form action="VerifyController" method="POST">
                                <fieldset style="">
                                    <p style="font-family: 'Patua One', cursive; font-size: 19px; color: #FF9933; text-transform: uppercase"> Please check your email and enter code to verify your account </p>
                                    <div class="form-group">
                                        <input type="text" class="form-control" placeholder="Enter code with 5 digits" value="${sessionScope.CODE}" name="txtCode" maxlength="5" style="margin-bottom: 28px; height: 42px; font-family: 'Patua One', cursive">
                                    </div>
                                        <c:if test="${not empty requestScope.MESS_VERIFY}">
                                        <p style="font-family: 'Patua One', cursive; color: red; margin-top: -21px; margin-bottom: 18px">${requestScope.MESS_VERIFY}</p>
                                    </c:if>
                                    <div style="margin-top: -6px;">
                                        <button type="submit" onclick="signIn()" style="font-family: 'Patua One', cursive; color: #333333; background-color: orange; border-radius: 4px; border: 0px; font-size: 25px; padding-left:9px; padding-right: 9px"> VERIFY </button>
                                        <!--<input class="btn btn-lg btn btn-block" type="submit" value="SIGN IN" style="font-family: 'Patua One', cursive; color: #333333; background-color: orange">-->
                                    </div>
                                </fieldset>
                            </form>

                        </div>
                    </div>
                </div>
                <div class="col-md-3"></div>
            </div>
        </div> 	
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>
    </body>
</html>
