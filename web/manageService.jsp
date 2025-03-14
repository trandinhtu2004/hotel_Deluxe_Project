<%-- 
    Document   : manageService
    Created on : Mar 9, 2025, 11:18:38 AM
    Author     : Admin
--%>

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
                                    <li><span>Bed:</span> ${category.bed}</li>
                                </ul>
                            </div>
                            <p>Our ${category.categoryName} offers a luxurious and comfortable stay with all the amenities you need for a perfect vacation or business trip. The room is spacious, elegantly decorated, and equipped with modern facilities to ensure your comfort and convenience.</p>
                        </div>

                        <!-- Room Amenities Section -->
                        <div class="col-md-12 room-single ftco-animate mb-5 mt-4">
                            <h3 class="mb-4">Facilities</h3>
                            <div class="row">
                                <div class="col-md-4">
                                    <c:forEach var="facility" items="${facilities}">
                                        <div>
                                            <p><i class="icon-check"></i> ${facility}</p>
                                        </div>
                                    </c:forEach>
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
                                            <p class="price"><strong>Price:</strong> <fmt:formatNumber value="${service.price}"/></p>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>

                        <!-- Similar Rooms Section -->
                        <div class="col-md-12 room-single ftco-animate mb-5 mt-5">
                            <h4 class="mb-4">Other Rooms</h4>
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
                                                <p><span class="price mr-2"><fmt:formatNumber value="${similarCategory.pricePerNight}"/></span> <span class="per"> VND/night</span></p>
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
                <div class="col-lg-4 sidebar">
                    <div class="sidebar-wrap bg-light ftco-animate">
                        <h3 class=" mb-4">Check Availability</h3>
                        <form action="check-availability" method="get" class="booking-form">
                            <input type="hidden" name="categoryId" value="${category.categoryId}">

                            <!-- Check-in Date -->
                            <div class="form-group">
                                <label for="checkin_date" class="bg-light ftco-animate d-block p-2 rounded">Check-in Date</label>
                                <input type="text" name="checkinDate" id="checkin_date" class="form-control checkin_date" 
                                       placeholder="Check-in Date" required>
                            </div>

                            <!-- Check-out Date -->
                            <div class="form-group">
                                <label for="checkout_date" class="bg-light ftco-animate d-block p-2 rounded">Check-out Date</label>
                                <input type="text" name="checkoutDate" id="checkout_date" class="form-control checkout_date" 
                                       placeholder="Check-out Date" required>
                            </div>

                            <div class="form-group">
                                <label for="num_rooms" class="bg-light ftco-animate d-block p-2 rounded">Number of Rooms</label>
                                <input type="number" name="numberOfRooms" id="number_of_rooms" class="form-control" 
                                       placeholder="Rooms" min="1" list="room-options">
                                <datalist id="room-options">
                                    <c:forEach var="i" begin="1" end="5">
                                        <option value="${i}">${i} room(s)</option>
                                    </c:forEach>
                                </datalist>
                            </div>

                            <!-- Submit Button -->
                            <div class="form-group">
                                <input type="submit" value="Check Availability" class="btn btn-primary py-3 px-5">
                            </div>

                        </form>
                    </div>
                    <c:if test="${not empty availableRooms}">
                        <c:choose>
                            <c:when test="${availableRooms > 0 && availableRooms >= param.numberOfRooms}">
                                <p style="margin-left: 40px" class="text-success">Available: ${availableRooms} rooms</p>
                                <form action="booking" method="get">
                                    <input type="hidden" name="categoryId" value="${categoryId}">
                                    <input type="hidden" name="checkinDate" value="${param.checkinDate}">
                                    <input type="hidden" name="checkoutDate" value="${param.checkoutDate}">
                                    <button  type="submit" class="btn btn-primary py-3 px-5">Book Now</button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <p style="margin-left: 40px" class="text-danger">Unavailable</p>
                            </c:otherwise>
                        </c:choose>
                    </c:if>


                    <!-- Price Details -->
                    <div class="sidebar-box ftco-animate">
                        <h3>Room Price Details</h3>
                        <div class="price-details">
                            <p><strong>Base Price:</strong> <span class="pricePerNight"><fmt:formatNumber value="${category.pricePerNight}"/></span>VND / night</p>
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
                                        <c:if test="${cat.categoryId == category.categoryId}"><span class="badge badge-primary" style="color: white; background: #b18d4a">Current</span></c:if>
                                    </a></li>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
