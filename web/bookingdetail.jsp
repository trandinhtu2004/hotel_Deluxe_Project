<%-- 
    Document   : staff
    Created on : Mar 4, 2025, 4:00:54 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Playfair+Display:400,400i,700,700i" rel="stylesheet">

        <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
        <link rel="stylesheet" href="css/animate.css">
        <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
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
        <meta charset="UTF-8">
        <title>Staff Page</title>
        <style>
            .parent {
                display: grid;
                grid-template-columns: 200px 1fr 1fr 1fr 1fr;
                grid-template-rows: auto 1fr 1fr 1fr 1fr;
                grid-column-gap: 0px;
                grid-row-gap: 0px;
                height: 100vh;
            }


            .div2 {
                grid-area: 2 / 1 / 6 / 2;
                background-color: #222;
                color: white;
                padding: 20px;
                display: flex;
                flex-direction: column;
                gap: 10px;
                background-color: #d3d3d3;
            }

            .div2 h2 {
                text-align: center;
                width: 100%;
            }

            .div3 {
                grid-area: 2 / 2 / 6 / 6;
                background-color: #f0f0f0;
                padding: 20px;
            }



            .div2 a:hover {
                color: #555;
            }

            .div3 table thead th {
                background-color: #dbeeff; /* Light Blue */
                color: #444;
                padding: 10px;
            }

        </style>
    </head>
    <body>
        <%@include file="includes/navbar.jsp" %>
        <div class="parent">

            <div class="div2">
                <h2>Menu</h2>
                <a href="index.jsp"><i class="icon-home"></i> Home</a>
                <a href="#"><i class="icon-person"></i> Information</a>
                <a href="staff.jsp"><i class="ion-ios-bookmarks"></i> View All Booking Request</a>
                <a href="CheckInLoader"><i class="ion-arrow-right-b"></i> Check In</a>
                <a href="#"><i class="icon-timer"></i> On Going</a>
                <a href="CheckOutLoader"><i class="ion-arrow-left-b"></i> Check Out</a>
                <a href="LoadAllBookingInfo"><i class="ion-ios-paper"></i> Booking History</a>
                <a href="#"><i class="icon-money"></i> View Salary</a>
            </div>
            <div class="div3">
                <h2>Booking Details</h2>
                <a href="LoadAllBookingInfo">Back to Booking History</a>
        <p>Booking Id: ${param.bookingId}</p>
        <p>Room Id: ${param.roomId}</p>
        <p>Room Type: ${param.roomType}</p>
        <p>Booking Date: ${param.bookingDate}</p>
        <p>Check In Date: ${param.checkInDate}</p>
        <p>Check Out Date: ${param.checkOutDate}</p>
        <p>Note: ${param.note}</p>
        <p>Customer Name: ${param.customerName}</p>
        <p>Status: ${param.status}</p>
        
            </div>
        </div>
    </body>
</html>
