<%-- 
    Document   : index
    Created on : Jan 25, 2025, 9:05:18 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@page import="dal.*" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Deluxe</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Playfair+Display:400,400i,700,700i" rel="stylesheet">

        <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
        <link rel="stylesheet" href="css/animate.css">

        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">

        <link rel="stylesheet" href="css/aos.css">

        <link rel="stylesheet" href="css/ionicons.min.css">

        <link rel="stylesheet" href="css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="css/jquery.timepicker.css">


        <link rel="stylesheet" href="css/flaticon.css">
        <link rel="stylesheet" href="css/icomoon.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <%@include file="includes/navbar.jsp" %>


        <section class="home-slider owl-carousel">
            <div class="slider-item" style="background-image:url(images/bg_1.jpg);">
                <div class="overlay"></div>
                <div class="container">
                    <div class="row no-gutters slider-text align-items-center justify-content-center">
                        <div class="col-md-12 ftco-animate text-center">
                            <div class="text mb-5 pb-3">
                                <h1 class="mb-3">Welcome To Deluxe</h1>
                                <h2>Hotels &amp; Resorts</h2>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="slider-item" style="background-image:url(images/bg_2.jpg);">
                <div class="overlay"></div>
                <div class="container">
                    <div class="row no-gutters slider-text align-items-center justify-content-center">
                        <div class="col-md-12 ftco-animate text-center">
                            <div class="text mb-5 pb-3">
                                <h1 class="mb-3">Enjoy A Luxury Experience</h1>
                                <h2>Join With Us</h2>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="ftco-booking">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <form action="filter" class="booking-form">
                            <div class="row">
                                <div class="col-md-3 d-flex">
                                    <div class="form-group p-4 align-self-stretch d-flex align-items-end">
                                        <div class="wrap">
                                            <label for="#">Check-in Date</label>
                                            <input type="date" name="checkin" id="checkin_date" class="form-control" 
                                                   placeholder="Check In Date" value="${sessionScope.checkin}">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3 d-flex">
                                    <div class="form-group p-4 align-self-stretch d-flex align-items-end">
                                        <div class="wrap">
                                            <label for="#">Check-out Date</label>
                                            <input type="date" name="checkout" id="checkout_date" class="form-control" 
                                                   placeholder="Check Out Date" value="${sessionScope.checkout}">
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md d-flex">
                                    <div class="form-group p-4 align-self-stretch d-flex align-items-end">
                                        <div class="wrap">
                                            <label for="#">Capacity</label>
                                            <div class="form-field">
                                                <div class="select-wrap">
                                                    <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                                                    <select name="capacity" id="" class="form-control">
                                                        <option value="">Capacity</option>
                                                        <c:forEach var="c" items="${capacities}">
                                                            <option value="${c.getCapacity()}">${c.getCapacity()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md d-flex">
                                    <div class="form-group p-4 align-self-stretch d-flex align-items-end">
                                        <div class="wrap">
                                            <label for="#">Bed</label>
                                            <div class="form-field">
                                                <div class="select-wrap">
                                                    <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                                                    <select name="bed" id="" class="form-control">
                                                        <option value="">Bed</option>
                                                        <c:forEach var="c" items="${capacities}">
                                                            <option value="${c.getCapacity()}">${c.getCapacity()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md d-flex">
                                    <div class="form-group d-flex align-self-stretch">
                                        <input type="submit" value="Check Availability" class="btn btn-primary py-3 px-4 align-self-stretch">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>


        <section class="ftco-section ftc-no-pb ftc-no-pt">
            <div class="container">
                <div class="row">
                    <div class="col-md-5 p-md-5 img img-2 d-flex justify-content-center align-items-center" style="background-image:url(images/bg_1.jpg);">
                        <!--						<a href="#" class="icon popup-vimeo d-flex justify-content-center align-items-center">
                                                                                <span class="icon-play"></span>
                                                                        </a>-->
                    </div>
                    <div class="col-md-7 py-5 wrap-about pb-md-5 ftco-animate">
                        <div class="heading-section heading-section-wo-line pt-md-5 pl-md-5 mb-5">
                            <div class="ml-md-0">
                                <span class="subheading">Welcome to Deluxe Hotel</span>
                                <h2 class="mb-4">Welcome To Our Hotel</h2>
                            </div>
                        </div>
                        <div class="pb-md-5">
                            <p>On her way she met a copy. The copy warned the Little Blind Text, that where it came from it would have been rewritten a thousand times and everything that was left from its origin would be the word "and" and the Little Blind Text should turn around and return to its own, safe country. But nothing the copy said could convince her and so it didn’t take long until a few insidious Copy Writers ambushed her, made her drunk with Longe and Parole and dragged her into their agency, where they abused her for their.</p>
                            <p>When she reached the first hills of the Italic Mountains, she had a last view back on the skyline of her hometown Bookmarksgrove, the headline of Alphabet Village and the subline of her own road, the Line Lane. Pityful a rethoric question ran over her cheek, then she continued her way.</p>
                            <ul class="ftco-social d-flex">
                                <li class="ftco-animate"><a href="#"><span class="icon-twitter"></span></a></li>
                                <li class="ftco-animate"><a href="#"><span class="icon-facebook"></span></a></li>
                                <li class="ftco-animate"><a href="#"><span class="icon-google-plus"></span></a></li>
                                <li class="ftco-animate"><a href="#"><span class="icon-instagram"></span></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </section>
                                        
        <section class="ftco-section">
            <div class="container">
                <h2 class="text-center">Information - お知らせ</h2>
                <table class="table table-bordered">
                    <tbody>
                    <a style="color: #0b0b0b">get the lastest announcement here!</a>
                    <c:forEach var="info" items="${informations}">
                        <tr>
                            <td style="width: 20%; height: 60%;">${info.dateCreated}</td>
                            <td  style="height: 60%;">
                                <a href="#" class="info-link" data-toggle="modal" data-target="#infoModal"
                                   data-title="${info.announcementName}"
                                   data-date="${info.dateCreated}"
                                   data-description="${info.description}">
                                    ${info.announcementName}
                                </a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="text-center">
                    <a href="history/info_2024.html" class="btn btn-primary">過去のお知らせ一覧を見る</a>
                </div>
            </div>
        </section>
                                        
        <!-- Modal -->
        <div class="modal fade" id="infoModal" tabindex="-1" role="dialog" aria-labelledby="infoModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="infoModalLabel">~Information~</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p><strong>Created-Date:</strong> <span id="modalDate"></span></p>
                        <p><strong>Title:</strong> <span id="modalTitle"></span></p>
                        <p><strong>Dsscription:</strong> <span id="modalDescription"></span></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <style>
            .modal-body {
                max-height: 300px; /* Giới hạn chiều cao tối đa */
                overflow-y: auto; /* Cho phép cuộn dọc nếu nội dung dài */
                word-wrap: break-word; /* Tự động xuống dòng nếu từ quá dài */
            }
        </style>
         <section class="ftco-section">
    <div class="container">
        <div class="row justify-content-center mb-5 pb-3">
                        <div class="col-md-7 heading-section text-center ftco-animate">
                            <h2 class="mb-4">Our Recommended Services</h2>
                        </div>
                    </div>    		
        <div class="row d-flex">
            <c:forEach var="service" items="${top4Services}">
                <div class="col-md-3 d-flex align-self-stretch ftco-animate">
                    <div class="media block-6 services py-4 d-block text-center">
                        <div class="d-flex justify-content-center">
                            <div class="icon d-flex align-items-center justify-content-center">
                                <span class="flaticon-spa"></span>
                            </div>
                        </div>
                        <div class="media-body p-2 mt-2">
                            <h3 class="heading mb-3">${service.serviceName}</h3>
                            <p>${service.description}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>
        
        <!-- recommended rooms -->
        <section class="ftco-section bg-light">
            <form action="home">
                <div class="container">
                    <div class="row justify-content-center mb-5 pb-3">
                        <div class="col-md-7 heading-section text-center ftco-animate">
                            <h2 class="mb-4">Our Recommended Rooms</h2>
                        </div>
                    </div>    		
                    <div class="row">
                        <c:forEach var="c" items="${topRooms}">
                            <div class="col-sm col-md-6 col-lg-4 ftco-animate">
                                <div class="room">
                                    <a href="rooms.html" class="img d-flex justify-content-center align-items-center" style="background-image: url(${c.getImage()});">
                                        <div class="icon d-flex justify-content-center align-items-center">
                                            <span class="icon-search2"></span>
                                        </div>
                                    </a>
                                    <div class="text p-3 text-center">
                                        <h3 class="mb-3"><a href="rooms.html">${c.getCategoryName()}</a></h3>
                                        <p><span class="price mr-2">${c.getFormattedPrice()}VND</span> <span class="per">per night</span></p>
                                        <hr>
                                        <p class="pt-1"><a href="room-single.html" class="btn-custom">View Room Details <span class="icon-long-arrow-right"></span></a></p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>		
                    </div>
                </div>
            </form>
        </section>
        
       
        
        

        <!-- feedback from customer -->
        <section class="ftco-section testimony-section bg-light">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-8 ftco-animate">
                        <div class="row ftco-animate">
                            <div class="col-md-12">
                                <div class="carousel-testimony owl-carousel ftco-owl">
                                    <div class="item">
                                        <div class="testimony-wrap py-4 pb-5">
                                            <div class="user-img mb-4" style="background-image: url(images/person_1.jpg)">
                                                <span class="quote d-flex align-items-center justify-content-center">
                                                    <i class="icon-quote-left"></i>
                                                </span>
                                            </div>
                                            <div class="text text-center">
                                                <p class="mb-4">A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth.</p>
                                                <p class="name">Nathan Smith</p>
                                                <span class="position">Guests</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="item">
                                        <div class="testimony-wrap py-4 pb-5">
                                            <div class="user-img mb-4" style="background-image: url(images/person_2.jpg)">
                                                <span class="quote d-flex align-items-center justify-content-center">
                                                    <i class="icon-quote-left"></i>
                                                </span>
                                            </div>
                                            <div class="text text-center">
                                                <p class="mb-4">A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth.</p>
                                                <p class="name">Nathan Smith</p>
                                                <span class="position">Guests</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="item">
                                        <div class="testimony-wrap py-4 pb-5">
                                            <div class="user-img mb-4" style="background-image: url(images/person_3.jpg)">
                                                <span class="quote d-flex align-items-center justify-content-center">
                                                    <i class="icon-quote-left"></i>
                                                </span>
                                            </div>
                                            <div class="text text-center">
                                                <p class="mb-4">A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth.</p>
                                                <p class="name">Nathan Smith</p>
                                                <span class="position">Guests</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="item">
                                        <div class="testimony-wrap py-4 pb-5">
                                            <div class="user-img mb-4" style="background-image: url(images/person_1.jpg)">
                                                <span class="quote d-flex align-items-center justify-content-center">
                                                    <i class="icon-quote-left"></i>
                                                </span>
                                            </div>
                                            <div class="text text-center">
                                                <p class="mb-4">A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth.</p>
                                                <p class="name">Nathan Smith</p>
                                                <span class="position">Guests</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="item">
                                        <div class="testimony-wrap py-4 pb-5">
                                            <div class="user-img mb-4" style="background-image: url(images/person_1.jpg)">
                                                <span class="quote d-flex align-items-center justify-content-center">
                                                    <i class="icon-quote-left"></i>
                                                </span>
                                            </div>
                                            <div class="text text-center">
                                                <p class="mb-4">A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth.</p>
                                                <p class="name">Nathan Smith</p>
                                                <span class="position">Guests</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>



        <section class="ftco-section">
            <div class="container">
                <div class="row justify-content-center mb-5 pb-3">
                    <div class="col-md-7 heading-section text-center ftco-animate">
                        <h2>Recent Blog</h2>
                    </div>
                </div>
                <div class="row d-flex">
                    <div class="col-md-3 d-flex ftco-animate">
                        <div class="blog-entry align-self-stretch">
                            <a href="blog-single.html" class="block-20" style="background-image: url('images/image_1.jpg');">
                            </a>
                            <div class="text mt-3 d-block">
                                <h3 class="heading mt-3"><a href="#">Even the all-powerful Pointing has no control about the blind texts</a></h3>
                                <div class="meta mb-3">
                                    <div><a href="#">Dec 6, 2018</a></div>
                                    <div><a href="#">Admin</a></div>
                                    <div><a href="#" class="meta-chat"><span class="icon-chat"></span> 3</a></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 d-flex ftco-animate">
                        <div class="blog-entry align-self-stretch">
                            <a href="blog-single.html" class="block-20" style="background-image: url('images/image_2.jpg');">
                            </a>
                            <div class="text mt-3">
                                <h3 class="heading mt-3"><a href="#">Even the all-powerful Pointing has no control about the blind texts</a></h3>
                                <div class="meta mb-3">
                                    <div><a href="#">Dec 6, 2018</a></div>
                                    <div><a href="#">Admin</a></div>
                                    <div><a href="#" class="meta-chat"><span class="icon-chat"></span> 3</a></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 d-flex ftco-animate">
                        <div class="blog-entry align-self-stretch">
                            <a href="blog-single.html" class="block-20" style="background-image: url('images/image_3.jpg');">
                            </a>
                            <div class="text mt-3">
                                <h3 class="heading mt-3"><a href="#">Even the all-powerful Pointing has no control about the blind texts</a></h3>
                                <div class="meta mb-3">
                                    <div><a href="#">Dec 6, 2018</a></div>
                                    <div><a href="#">Admin</a></div>
                                    <div><a href="#" class="meta-chat"><span class="icon-chat"></span> 3</a></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 d-flex ftco-animate">
                        <div class="blog-entry align-self-stretch">
                            <a href="blog-single.html" class="block-20" style="background-image: url('images/image_4.jpg');">
                            </a>
                            <div class="text mt-3">
                                <h3 class="heading mt-3"><a href="#">Even the all-powerful Pointing has no control about the blind texts</a></h3>
                                <div class="meta mb-3">
                                    <div><a href="#">Dec 6, 2018</a></div>
                                    <div><a href="#">Admin</a></div>
                                    <div><a href="#" class="meta-chat"><span class="icon-chat"></span> 3</a></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="instagram">
            <div class="container-fluid">
                <div class="row no-gutters justify-content-center pb-5">
                    <div class="col-md-7 text-center heading-section ftco-animate">
                        <h2><span>Instagram</span></h2>
                    </div>
                </div>
                <div class="row no-gutters">
                    <div class="col-sm-12 col-md ftco-animate">
                        <a href="images/insta-1.jpg" class="insta-img image-popup" style="background-image: url(images/insta-1.jpg);">
                            <div class="icon d-flex justify-content-center">
                                <span class="icon-instagram align-self-center"></span>
                            </div>
                        </a>
                    </div>
                    <div class="col-sm-12 col-md ftco-animate">
                        <a href="images/insta-2.jpg" class="insta-img image-popup" style="background-image: url(images/insta-2.jpg);">
                            <div class="icon d-flex justify-content-center">
                                <span class="icon-instagram align-self-center"></span>
                            </div>
                        </a>
                    </div>
                    <div class="col-sm-12 col-md ftco-animate">
                        <a href="images/insta-3.jpg" class="insta-img image-popup" style="background-image: url(images/insta-3.jpg);">
                            <div class="icon d-flex justify-content-center">
                                <span class="icon-instagram align-self-center"></span>
                            </div>
                        </a>
                    </div>
                    <div class="col-sm-12 col-md ftco-animate">
                        <a href="images/insta-4.jpg" class="insta-img image-popup" style="background-image: url(images/insta-4.jpg);">
                            <div class="icon d-flex justify-content-center">
                                <span class="icon-instagram align-self-center"></span>
                            </div>
                        </a>
                    </div>
                    <div class="col-sm-12 col-md ftco-animate">
                        <a href="images/insta-5.jpg" class="insta-img image-popup" style="background-image: url(images/insta-5.jpg);">
                            <div class="icon d-flex justify-content-center">
                                <span class="icon-instagram align-self-center"></span>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </section>

        <footer class="ftco-footer ftco-bg-dark ftco-section">
            <div class="container">
                <div class="row mb-5">
                    <div class="col-md">
                        <div class="ftco-footer-widget mb-4">
                            <h2 class="ftco-heading-2">Deluxe Hotel</h2>
                            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                            <ul class="ftco-footer-social list-unstyled float-md-left float-lft mt-5">
                                <li class="ftco-animate"><a href="#"><span class="icon-twitter"></span></a></li>
                                <li class="ftco-animate"><a href="#"><span class="icon-facebook"></span></a></li>
                                <li class="ftco-animate"><a href="#"><span class="icon-instagram"></span></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md">
                        <div class="ftco-footer-widget mb-4 ml-md-5">
                            <h2 class="ftco-heading-2">Useful Links</h2>
                            <ul class="list-unstyled">
                                <li><a href="#" class="py-2 d-block">Blog</a></li>
                                <li><a href="#" class="py-2 d-block">Rooms</a></li>
                                <li><a href="#" class="py-2 d-block">Amenities</a></li>
                                <li><a href="#" class="py-2 d-block">Gift Card</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md">
                        <div class="ftco-footer-widget mb-4">
                            <h2 class="ftco-heading-2">Privacy</h2>
                            <ul class="list-unstyled">
                                <li><a href="#" class="py-2 d-block">Career</a></li>
                                <li><a href="#" class="py-2 d-block">About Us</a></li>
                                <li><a href="#" class="py-2 d-block">Contact Us</a></li>
                                <li><a href="#" class="py-2 d-block">Services</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md">
                        <div class="ftco-footer-widget mb-4">
                            <h2 class="ftco-heading-2">Have a Questions?</h2>
                            <div class="block-23 mb-3">
                                <ul>
                                    <li><span class="icon icon-map-marker"></span><span class="text">203 Fake St. Mountain View, San Francisco, California, USA</span></li>
                                    <li><a href="#"><span class="icon icon-phone"></span><span class="text">+2 392 3929 210</span></a></li>
                                    <li><a href="#"><span class="icon icon-envelope"></span><span class="text">info@yourdomain.com</span></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 text-center">

                        <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                            Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart color-danger" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
                    </div>
                </div>
            </div>
        </footer>



        <!-- loader -->
        <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


        <script src="js/jquery.min.js"></script>
        <script src="js/jquery-migrate-3.0.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.easing.1.3.js"></script>
        <script src="js/jquery.waypoints.min.js"></script>
        <script src="js/jquery.stellar.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/aos.js"></script>
        <script src="js/jquery.animateNumber.min.js"></script>
        <script src="js/bootstrap-datepicker.js"></script>
        <script src="js/jquery.timepicker.min.js"></script>
        <script src="js/scrollax.min.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
        <script src="js/google-map.js"></script>
        <script src="js/main.js"></script>

    </body>
</html>