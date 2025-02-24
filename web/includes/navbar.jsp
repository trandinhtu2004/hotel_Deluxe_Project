<%@ page import="model.Account" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    Account account = (Account) session.getAttribute("account");
%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">Deluxe</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a href="index.jsp" class="nav-link">Home</a></li>
                <li class="nav-item"><a href="rooms.jsp" class="nav-link">Rooms</a></li>
                <li class="nav-item"><a href="contact.jsp" class="nav-link">Contact</a></li>
                
                <% if (account != null) { %>
                    <li class="nav-item"><a href="profile.jsp" class="nav-link">Welcome, <%= account.getFullName() %></a></li>
                    <li class="nav-item"><a href="LogoutController" class="nav-link">Logout</a></li>
                <% } else { %>
                    <li class="nav-item"><a href="login.jsp" class="nav-link">Login</a></li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>
