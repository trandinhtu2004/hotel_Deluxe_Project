<%-- 
    Document   : manageService
    Created on : Mar 9, 2025, 11:18:38 AM
    Author     : Admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Manage Service</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styleManageService.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="includes/sidebar.jsp" %>
        <div class="container">
            <div class="col-md-12">
                <div class="page-people-directory">
                    <div class="row">
                        
                        <div class="col-md-9">
                            <div class="well">
                                <div class="row">
                                    <div class="col-md-9">
                                        <input type="text" placeholder="Search service" class="form-control">
                                    </div>
                                    <div class="col-md-3">
                                        <div class="btn-group" style="display:block">
                                            <button data-toggle="dropdown" class="btn btn-default dropdown-toggle" style="width: 100%; border-radius: 0px; background: white; color: gray; border-color: #ddd;">Last 10 days <span class="caret"></span></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-md-6">
                                    <h3 id="tittle">All Services</h3>
                                </div>
                                <div class="col-md-6">
                                    <button type="button" class="btn btn-green btn-raised btn-add-new-contact"><span class="icon-plus" data-toggle="modal" data-target="#modal-pull-right-add"> Add New Service</span></button>
                                </div>
                            </div>

                            <div class="list-group contact-group">
                                <c:forEach var="service" items="${services}">
                                    <a href="#" class="list-group-item" data-category="${service.categoryService.categoryName.toLowerCase()}">
                                        <div class="media">
                                            <div class="media-body">
                                                <h4 class="media-heading">${service.serviceName}<small> ${service.categoryService.categoryName}</small></h4>
                                                <div class="media-content">
                                                    <ul class="list-unstyled">
                                                        <li><b>Description:</b> ${service.description}</li>
                                                        <li><b>Price:</b> ${service.price} VND</li>
                                                        <li><b>Status:</b> ${service.status}</li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
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
                                            <h4 class="pull-left"><b>Create New Service</b></h4>
                                            <ul class="list-unstyled list-inline text-right">
                                                <li class="close-right-modal"><span class="fa fa-times fa-2x" data-dismiss="modal"></span></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="contact-add-content">
                                                <form class="form-horizontal tabular-form margin-top-10" action="manageService" method="post">
                                                    <div class="form-group">
                                                        <label for="serviceName" class="col-sm-2 control-label">Name</label>
                                                        <div class="col-sm-10 tabular-border">
                                                            <input type="text" class="form-control" id="serviceName" name="serviceName" placeholder="Service Name">
                                                            <label id="serviceName_error" for="serviceName" style="color: red"></label>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="description" class="col-sm-2 control-label">Description</label>
                                                        <div class="col-sm-10 tabular-border">
                                                            <textarea class="form-control" id="description" name="description" placeholder="Description"></textarea>
                                                            <label id="description_error" for="description" style="color: red"></label>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="price" class="col-sm-2 control-label">Price</label>
                                                        <div class="col-sm-10 tabular-border">
                                                            <input type="number" step="0.01" class="form-control" id="price" name="price" placeholder="Price">
                                                            <label id="price_error" for="price" style="color: red"></label>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="status" class="col-sm-2 control-label">Status</label>
                                                        <div class="col-sm-10 tabular-border">
                                                            <select class="form-control" id="status" name="status">
                                                                <option value="Available">Available</option>
                                                                <option value="Unavailable">Unavailable</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="category" class="col-sm-2 control-label">Category</label>
                                                        <div class="col-sm-10 tabular-border">
                                                            <select class="form-control" id="category" name="category">
                                                                <c:forEach var="category" items="${categoryList}">
                                                                    <option value="${category.categoryId}">${category.categoryName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="form-actions">
                                                        <button class="btn btn-silver btn-flat" data-dismiss="modal">Cancel</button> 
                                                        <button type="submit" class="btn btn-green btn-flat" value="add" name="submit">Add Service</button>
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
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const filters = document.querySelectorAll(".nav-contacts li");
                const serviceItems = document.querySelectorAll(".list-group-item");
                const title = document.getElementById("tittle");

                filters.forEach(filter => {
                    filter.addEventListener("click", function () {
                        const category = this.getAttribute("data-filter").toLowerCase();

                        if (category === "all") {
                            title.textContent = "All Services";
                        } else {
                            title.textContent = category.charAt(0).toUpperCase() + category.slice(1) + " Services";
                        }

                        filters.forEach(f => f.classList.remove("active"));
                        this.classList.add("active");

                        serviceItems.forEach(item => {
                            const serviceCategory = item.getAttribute("data-category").toLowerCase();
                            if (category === "all" || serviceCategory === category) {
                                item.style.display = "block";
                            } else {
                                item.style.display = "none";
                            }
                        });
                    });
                });
            });
        </script>
    </body>
</html>
