<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Discussion Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%@include file="includes/navbar.jsp" %>
    
    <div class="hero-wrap" style="background-image: url('images/bg_1.jpg');">
        <div class="overlay"></div>
        <div class="container text-center text-white py-5">
            <h1 class="display-4" style="color: white">Discussion Board</h1>
            <p>Share your thoughts and interact with others</p>
        </div>
    </div>
    
    <div class="container mt-5">
        <div class="card p-4 mb-4">
            <h3>Create a Post</h3>
            <form action="PostCreateControl" method="GET">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Title" name="title">
                </div>
                <div class="form-group">
                    <textarea class="form-control" rows="3" placeholder="Write something..." name="content"></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Post</button>
            </form>
        </div>
        
        <div class="posts">
            
                <c:forEach var="item" items="${postList}" varStatus="status">
                        <div class="card mb-3">
                    <div class="card-body">
                        <h5 class="card-title">${item.title}</h5>
                        <h6 class="text-muted">Posted by ${item.accountName} at ${item.createdDate}</h6>
                        <p class="card-text"><strong>${item.content}</strong></p>
                        <button class="btn btn-primary btn-sm" onclick="toggleComments(this)">Show Comments</button>
                        <div class="comments mt-3" style="display: none;">
                            <p class="no-comments">No comments yet.</p>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Write a comment..." onkeypress="addComment(event, this)">
                            </div>
                            <ul class="list-unstyled"></ul>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </div>
        </div>
    </div>
    
    <script>
        function toggleComments(button) {
            let commentsSection = button.nextElementSibling;
            commentsSection.style.display = commentsSection.style.display === 'none' ? 'block' : 'none';
        }

        function addComment(event, input) {
            if (event.key === 'Enter' && input.value.trim() !== '') {
                let commentList = input.closest('.comments').querySelector('ul');
                let newComment = document.createElement('li');
                newComment.textContent = input.value;
                commentList.appendChild(newComment);
                input.value = '';
                
                let noCommentsText = input.closest('.comments').querySelector('.no-comments');
                if (noCommentsText) {
                    noCommentsText.style.display = 'none';
                }
            }
        }
    </script>
    
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>