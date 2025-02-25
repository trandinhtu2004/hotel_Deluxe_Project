<%-- 
    Document   : manageUser
    Created on : Feb 16, 2025, 1:00:12 AM
    Author     : Overlordil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <!--  This file has been downloaded from bootdey.com @bootdey on twitter -->
        <!--  All snippets are MIT license http://bootdey.com/license -->
        <title>Manage User</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styleManageUser.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="includes/sidebar.jsp" %>
        <div class="container">
            <div class="col-md-12">
                <div class="page-people-directory">
                    <div class="row">
                        <div class="col-md-3">
                            <h5 class="page-title"><b>Role</b></h5>
                            <ul class="nav nav-pills nav-stacked nav-contacts">
                                <li class="active" data-filter="all"><a>All</a></li>
                                <li data-filter="staff"><a>Staff</a><li>
                                <li data-filter="customer"><a>Customer</a></li>
                            </ul>
                        </div>
                        <div class="col-md-9">
                            <div class="well">
                                <div class="row">
                                    <div class="col-md-9">
                                        <input type="text" placeholder="Search people" class="form-control">
                                    </div>
                                    <div class="col-md-3">
                                        <div class="btn-group" style="display:block">
                                            <button data-toggle="dropdown" class="btn btn-default dropdown-toggle" style="width: 100%; border-radius: 0px; background: white;  color: gray; border-color: #ddd;">Last 10 days <span class="caret"></span></button>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-md-6">
                                    <h3 id="tittle">All User</h3>
                                </div>
                                <div class="col-md-6">
                                    <button type="button" class="btn btn-green btn-raised btn-add-new-contact"><span class="icon-plus" data-toggle="modal" data-target="#modal-pull-right-add"> Add New User</span></button>
                                </div>
                            </div>

                            <div class="list-group contact-group">
                                <c:forEach var="user" items="${userList}">
                                    <c:if test="${user.role.roleName ne 'Admin'}">
                                        <a href="#" class="list-group-item"  data-role="${user.role.roleName}">
                                            <div class="media">
                                                <div class="pull-left">
                                                    <img class="img-circle" src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="...">
                                                </div>
                                                <div class="media-body">
                                                    <h4 class="media-heading">${user.fullName}<small> ${user.role.roleName}</small></h4>
                                                    <div class="media-content">
                                                        <i class="fa fa-map-marker"></i>
                                                        <ul class="list-unstyled">
                                                            <li><i class="fa fa-mobile"></i> ${user.phone}</li>
                                                            <li><i class="fa fa-envelope-o"></i> ${user.email}</li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </a>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <div class="pull-right">
                                <ul class="pagination pagination-split pagination-sm pagination-contact">
                                    <li class="disabled"><a href="#"><i class="fa fa-angle-left"></i></a></li>
                                    <li class="active"><a href="#">1</a></li>
                                    <li><a href="#">2</a></li>
                                    <li><a href="#">3</a></li>
                                    <li><a href="#">4</a></li>
                                    <li><a href="#">5</a></li>
                                    <li><a href="#"><i class="fa fa-angle-right"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="modal modal-pull-right" data-easein="fadeInRight" data-easeout="fadeOutRight" id="modal-pull-right-add" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
                        <div class="modal-dialog">
                            <div class="modal-content animated fadeOutRight">
                                <div class="modal-body">
                                    <div class="row modal-close">
                                        <div class="col-md-12 padding-bottom-8 padding-top-8 bg-silver">
                                            <h4 class="pull-left"><b>Create New User</b></h4>
                                            <ul class="list-unstyled list-inline text-right">
                                                <li class="close-right-modal"><span class="fa fa-times fa-2x" data-dismiss="modal"></span></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="contact-add-content">
                                                <form class="form-horizontal tabular-form margin-top-10" action="manageUser" method="post">
                                                    <div class="form-group">
                                                        <label for="fullName" class="col-sm-2 control-label">Name</label>
                                                        <div class="col-sm-10 tabular-border">
                                                            <input type="text" class="form-control" id="fullName" name="fullName" placeholder="Full Name">
                                                            <label id="fullName_error" for="fullName" style="color: red"></label>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="email" class="col-sm-2 control-label">Email</label>
                                                        <div class="col-sm-10 tabular-border">
                                                            <input type="text" class="form-control" id="email" name="email" placeholder="Email">
                                                            <label id="email_error" for="email" style="color: red"></label>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="password" class="col-sm-2 control-label">Password</label>
                                                        <div class="col-sm-10 tabular-border">
                                                            <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                                                            <label id="password_error" for="password" style="color: red"></label>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="confirmPassword" class="col-sm-2 control-label">Confirm</label>
                                                        <div class="col-sm-10 tabular-border">
                                                            <input type="confirmPassword" class="form-control" id="password" name="confirmPassword" placeholder="Confirm Password">
                                                            <label id="confirmPassword_error" for="confirmPassword" style="color: red"></label>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="phone" class="col-sm-2 control-label">Phone</label>
                                                        <div class="col-sm-10 tabular-border">
                                                            <input type="text" class="form-control" id="phone" name="phone" placeholder="PhoneNumber">
                                                            <label id="phone_error" for="phone" style="color: red"></label>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="address" class="col-sm-2 control-label">Address</label>
                                                        <div class="col-sm-10 tabular-border">
                                                            <input type="text" class="form-control" id="address" name="address" placeholder="Address">
                                                            <label id="address_error" for="address" style="color: red"></label>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="role" class="col-sm-2 control-label">Role</label>
                                                        <div class="col-sm-10 tabular-border">
                                                            <select class="form-control" id="role" name="role">
                                                                <c:forEach var="role" items="${roleList}">
                                                                    <option value="${role.roleId}">${role.roleName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="form-actions">
                                                        <button class="btn btn-silver btn-flat" data-dismiss="modal">Cancel</button> 
                                                        <button type="submit" class="btn btn-green btn-flat" value="add" name="submit">Add User</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script><script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const filters = document.querySelectorAll(".nav-contacts li");
                const userItems = document.querySelectorAll(".list-group-item");
                const title = document.getElementById("tittle");

                filters.forEach(filter => {
                    filter.addEventListener("click", function () {
                        // Lấy giá trị lọc và chuyển về chữ thường
                        const role = this.getAttribute("data-filter").toLowerCase();

                        // Cập nhật tiêu đề
                        if (role === "all") {
                            title.textContent = "All Users";
                        } else {
                            title.textContent = role.charAt(0).toUpperCase() + role.slice(1) + " Users";
                        }

                        // Cập nhật active class cho bộ lọc
                        filters.forEach(f => f.classList.remove("active"));
                        this.classList.add("active");

                        // Lọc danh sách người dùng dựa trên role
                        userItems.forEach(item => {
                            // Lấy role của user từ data-role và chuyển về chữ thường
                            const userRole = item.getAttribute("data-role").toLowerCase();
                            if (role === "all" || userRole === role) {
                                item.style.display = "block";
                            } else {
                                item.style.display = "none";
                            }
                        });
                    });
                });
            });
            
            function validateFullName() {
                    var fullName = document.getElementById("fullName").value;

                    var fullNameRegex = /^([A-Z][a-z]{1,})(\s[A-Z][a-z]{1,})*$/;

                    if (!fullNameRegex.test(fullName)) {
                        document.getElementById("fullName_error").innerText = "Capitalize the first letter of each word please!";
                    } else {
                        document.getElementById("fullName_error").innerText = "";
                    }
                }

                function validateEmail() {
                    var email = document.getElementById("email").value;

                    var emailRegex = /\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b/;

                    if (!emailRegex.test(email)) {
                        document.getElementById("email_error").innerText = "Wrong format email,\n Example: example@gmail.com\n Enter again please!";
                    } else {
                        document.getElementById("email_error").innerText = "";
                    }
                }

                function validatePhoneNumber() {
                    var phoneNumber = document.getElementById("phoneNumber").value;

                    var phoneNumberRegex = /^0[235789]\d{8}$/;

                    if (!phoneNumberRegex.test(phoneNumber)) {
                        document.getElementById("phoneNumber_error").innerText = "Wrong format phone number,\n Example: 0943614388\n Enter again please!";
                    } else {
                        document.getElementById("phoneNumber_error").innerText = "";
                    }
                }

                function validateAddress() {
                    var address = document.getElementById("address").value;

                    var addressRegex = /^([A-Z][a-z]{1,})(\s[A-Z][a-z]{1,})*$/;

                    if (!addressRegex.test(address)) {
                        document.getElementById("address_error").innerText = "Capitalize the first letter of each word please!";
                    } else {
                        document.getElementById("address_error").innerText = "";
                    }
                }

                function validatePassword() {
                    var password = document.getElementById("password").value;

                    var passwordRegex = /^[a-zA-Z0-9]{6,18}$/;

                    if (!passwordRegex.test(password)) {
                        document.getElementById("password_error").innerText = "Password must be 6-18 characters and contain no special characters\nEnter again please!";
                    } else {
                        document.getElementById("password_error").innerText = "";
                    }
                }

                function validateConfirmPass() {
                    var password = document.getElementById("password").value;
                    var confirmPass = document.getElementById("confirmPass").value;

                    if (confirmPass !== password) {
                        document.getElementById("confirmPassword_error").innerText = "Password and Confirm password are different\n Enter again please!";
                    } else {
                        document.getElementById("confirmPassword_error").innerText = "";
                    }
                }

                document.getElementById("fullName").addEventListener("input", validateFullName);
                document.getElementById("phoneNumber").addEventListener("input", validatePhoneNumber);
                document.getElementById("address").addEventListener("input", validateAddress);
                document.getElementById("password").addEventListener("input", validatePassword);
                document.getElementById("confirmPass").addEventListener("input", validateConfirmPass);
            };
        </script>
    </body>
</html>