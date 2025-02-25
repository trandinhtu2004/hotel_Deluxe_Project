<%-- 
    Document   : sidebar
    Created on : Feb 13, 2025, 2:46:22 PM
    Author     : Overlordil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/StyleSidebar.css" rel="stylesheet">
    </head>
    <body>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        <div class="container bootstrap snippets bootdey">
            <div class="row profile">
                <div class="col-md-3" id="profileCol">
                    <div class="profile-sidebar">
                        <div class="profile-userpic">
                            <img src="https://bootdey.com/img/Content/User_for_snippets.png" class="img-responsive" alt="">
                        </div>
                        <div class="profile-usertitle">
                            <div class="profile-usertitle-name">
                                A7VA
                            </div>
                        </div>
                        <%
                            String currentUrl = request.getRequestURI();
                        %>
                        <div class="profile-usermenu">
                            <ul class="nav">
                                <li class="<%= currentUrl.contains("/index.jsp") ? "active" : "" %>">
                                    <a href="index.jsp"><i class="glyphicon glyphicon-home"></i>Home</a>
                                </li>
                                <li class="<%= currentUrl.contains("/dashboard") ? "active" : "" %>">
                                    <a href="dashboard"><i class="glyphicon glyphicon-dashboard"></i>Dashboard</a>
                                </li>
                                <li class="<%= currentUrl.contains("/manageUser") ? "active" : "" %>">
                                    <a href="manageUser"><i class="glyphicon glyphicon-user"></i>Manage User</a>
                                </li>
                                <li class="<%= currentUrl.contains("/manageRoom") ? "active" : "" %>">
                                    <a href="manageRoom"><i class="glyphicon glyphicon-list-alt"></i>Manage Room</a>
                                </li>
                                <li class="<%= currentUrl.contains("/setting") ? "active" : "" %>">
                                    <a href="#" target="_blank"><i class="glyphicon glyphicon-cog"></i>Setting</a>
                                </li>
                                <li class="<%= currentUrl.contains("/help") ? "active" : "" %>">
                                    <a href="#"><i class="glyphicon glyphicon-flag"></i>Help</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>                                
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/sidebarScript.js"></script>
    </body>
</html>