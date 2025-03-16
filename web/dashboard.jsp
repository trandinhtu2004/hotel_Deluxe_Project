<%-- 
    Document   : dashboard
    Created on : Feb 13, 2025, 1:58:23 PM
    Author     : Overlordil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <!--  This file has been downloaded from bootdey.com @bootdey on twitter -->
        <!--  All snippets are MIT license http://bootdey.com/license -->
        <title>Dashboard</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link href="${pageContext.request.contextPath}/css/StyleDashboard.css" rel="stylesheet">
        <style>
            .dashboard-content {
                margin-left: 200px;
                padding: 20px;
                min-height: 100vh;
            }
        </style>
    </head>
    <body>
        <%@include file="includes/sidebar.jsp" %>
        <div class="dashboard-content">
            <div class="container mt-4">
                <div class="row">
                    <div class="col-md-4">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Website Views</h5>
                                <p class="card-text">Last Campaign Performance</p>
                                <canvas id="websiteViewsChart"></canvas>
                                <small class="text-muted"><i class="fas fa-clock"></i> campaign sent 2 days ago</small>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Daily Sales</h5>
                                <p class="card-text"><strong>+15%</strong> increase in today sales.</p>
                                <canvas id="dailySalesChart"></canvas>
                                <small class="text-muted"><i class="fas fa-clock"></i> updated 4 min ago</small>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Completed Tasks</h5>
                                <p class="card-text">Last Campaign Performance</p>
                                <canvas id="completedTasksChart"></canvas>
                                <small class="text-muted"><i class="fas fa-clock"></i> just updated</small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/dashboardScript.js"></script>
    </body>
</html>