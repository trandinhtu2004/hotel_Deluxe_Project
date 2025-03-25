<%-- 
    Document   : dashboard
    Created on : Feb 13, 2025, 1:58:23 PM
    Author     : Overlordil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Dashboard</title>

        <link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styleDashboard.css" rel="stylesheet">

        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2"></script>
    </head>
    <body>
        <%@include file="includes/sidebar.jsp" %>
        <div class="dashboard-content">
            <div class="container mt-4">
                <div class="row">
                    <!--Account Chart-->
                    <div class="col-md-4">
                        <canvas id="accountChart" 
                                data-totalCustomer="${totalCustomer}"
                                data-totalOwner="${totalOwner}"
                                data-totalStaff="${totalStaff}"></canvas>
                        <div id="legend-container">Total Accounts: ${totalAccount}</div>
                    </div>
                    
                    <!--Room Chart-->
                    <div class="col-md-4">
                        <canvas id="roomChart"></canvas>
                        <div id="legend-container">Total Rooms: </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/dashboardScript.js"></script>
    </body>
</html>