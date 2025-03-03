<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Deluxe - Free Bootstrap 4 Template by Colorlib</title>
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

    <div class="hero-wrap" style="background-image: url('images/bg_1.jpg');">
        <div class="overlay"></div>
        <div class="container">
            <div class="row no-gutters slider-text d-flex align-itemd-end justify-content-center">
                <div class="col-md-9 ftco-animate text-center d-flex align-items-end justify-content-center">
                    <div class="text">
                        <p class="breadcrumbs mb-2"><span class="mr-2"><a href="index">Home</a></span> <span class="mr-2"><a href="rooms">Rooms</a></span> <span>${category.categoryName}</span></p>
                        <h1 class="mb-4 bread">${category.categoryName}</h1>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <section class="ftco-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-8">
                    <div class="row">
                        <div class="col-md-12 ftco-animate">
                            <h2 class="mb-4">${category.categoryName}</h2>
                            <div class="single-slider owl-carousel">
                                <div class="item">
                                    <div class="room-img" style="background-image: url(${category.image});"></div>
                                </div>
                                <!-- Additional images would go here if available -->
                            </div>
                        </div>
                        <div class="col-md-12 room-single mt-4 mb-5 ftco-animate">
                            <p>${category.description}</p>
                            <div class="d-md-flex mt-5 mb-5">
                                <ul class="list">
                                    <li><span>Max:</span> ${category.capacity} Persons</li>
                                    <li><span>Size:</span> ${category.size} m²</li>
                                </ul>
                                <ul class="list ml-md-5">
<!--                                    <li><span>Available Rooms:</span> ${availableRoomCount}</li>-->
                                    <li><span>Bed:</span> ${category.bed}</li>
                                </ul>
                            </div>
                            <p>Our ${category.categoryName} offers a luxurious and comfortable stay with all the amenities you need for a perfect vacation or business trip. The room is spacious, elegantly decorated, and equipped with modern facilities to ensure your comfort and convenience.</p>
                        </div>

                        <!-- Room Amenities Section -->
                        <div class="col-md-12 room-single ftco-animate mb-5 mt-4">
                            <h3 class="mb-4">Amenities</h3>
                            <div class="row">
                                <div class="col-md-4">
                                    <p><i class="icon-wifi"></i> Free Wifi</p>
                                </div>
                                <div class="col-md-4">
                                    <p><i class="icon-tv"></i> Smart TV</p>
                                </div>
                                <div class="col-md-4">
                                    <p><i class="icon-air-conditioning"></i> Air Conditioning</p>
                                </div>
                                <div class="col-md-4">
                                    <p><i class="icon-bath"></i> Private Bathroom</p>
                                </div>
                                <div class="col-md-4">
                                    <p><i class="icon-fridge"></i> Mini Fridge</p>
                                </div>
                                <div class="col-md-4">
                                    <p><i class="icon-safe"></i> Safe Box</p>
                                </div>
                            </div>
                        </div>

                        <!-- Services Section -->
                        <div class="col-md-12 room-single ftco-animate mb-5 mt-4">
                            <h3 class="mb-4">Available Services</h3>
                            <div class="row">
                                <c:forEach var="service" items="${services}">
                                    <div class="col-md-6 mb-3">
                                        <div class="service-item p-3 border rounded">
                                            <h5>${service.serviceName}</h5>
                                            <p>${service.description}</p>
                                            <p class="price"><strong>Price:</strong> <fmt:formatNumber value="${service.price}" type="currency"/></p>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>

                        <!-- Similar Rooms Section -->
                        <div class="col-md-12 room-single ftco-animate mb-5 mt-5">
                            <h4 class="mb-4">Similar Rooms</h4>
                            <div class="row">
                                <c:forEach var="similarCategory" items="${similarCategories}">
                                    <div class="col-sm col-md-6 ftco-animate">
                                        <div class="room">
                                            <a href="room-detail?categoryId=${similarCategory.categoryId}" class="img img-2 d-flex justify-content-center align-items-center" style="background-image: url(${similarCategory.image});">
                                                <div class="icon d-flex justify-content-center align-items-center">
                                                    <span class="icon-search2"></span>
                                                </div>
                                            </a>
                                            <div class="text p-3 text-center">
                                                <h3 class="mb-3"><a href="room-detail?categoryId=${similarCategory.categoryId}">${similarCategory.categoryName}</a></h3>
                                                <p><span class="price mr-2"><fmt:formatNumber value="${similarCategory.pricePerNight}" type="currency"/></span> <span class="per">per night</span></p>
                                                <hr>
                                                <p class="pt-1"><a href="room-detail?categoryId=${similarCategory.categoryId}" class="btn-custom">View Room Details <span class="icon-long-arrow-right"></span></a></p>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div> <!-- .col-md-8 -->
                
                <!-- Sidebar -->
                <div class="col-lg-4 sidebar ftco-animate">
                    <!-- Booking Form -->
                    <div class="sidebar-box ftco-animate">
                        <h3>Check Availability</h3>
                        <form action="booking" method="get" class="booking-form">
                            <input type="hidden" name="categoryId" value="${category.categoryId}">
                            <div class="form-group">
                                <label for="checkin_date">Check-in Date</label>
                                <input type="text" name="checkinDate" id="checkin_date" class="form-control checkin_date" placeholder="Check-in date" required>
                            </div>
                            <div class="form-group">
                                <label for="checkout_date">Check-out Date</label>
                                <input type="text" name="checkoutDate" id="checkout_date" class="form-control checkout_date" placeholder="Check-out date" required>
                            </div>
                            <div class="form-group">
                                <label for="adults">Adults</label>
                                <div class="select-wrap one-third">
                                    <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                                    <select name="adults" id="adults" class="form-control">
                                        <c:forEach var="i" begin="1" end="${category.capacity}">
                                            <option value="${i}">${i}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="children">Children</label>
                                <div class="select-wrap one-third">
                                    <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                                    <select name="children" id="children" class="form-control">
                                        <option value="0">0</option>
                                        <c:forEach var="i" begin="1" end="${category.capacity - 1}">
                                            <option value="${i}">${i}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <input type="submit" value="Check Availability" class="btn btn-primary py-3 px-5">
                            </div>
                        </form>
                    </div>
                    
                    <!-- Price Details -->
                    <div class="sidebar-box ftco-animate">
                        <h3>Room Price Details</h3>
                        <div class="price-details">
                            <p><strong>Base Price:</strong> <span class="price"><fmt:formatNumber value="${category.pricePerNight}" type="currency"/></span> / night</p>
                            <hr>
                            <p><small>* Prices may vary based on season and availability</small></p>
                        </div>
                    </div>
                    
                    <!-- Room Categories -->
                    <div class="sidebar-box ftco-animate">
                        <h3>Room Categories</h3>
                        <div class="categories">
                            <c:forEach var="cat" items="${allCategories}">
                                <li><a href="room-detail?categoryId=${cat.categoryId}">${cat.categoryName} 
                                    <c:if test="${cat.categoryId == category.categoryId}"><span class="badge badge-primary">Current</span></c:if>
                                </a></li>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section> <!-- .section -->

    <!-- Instagram Section -->
    <section class="instagram pt-5">
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