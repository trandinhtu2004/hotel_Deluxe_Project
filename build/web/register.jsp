<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Registration Form</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background: linear-gradient(135deg, #6a11cb, #2575fc);
                color: #fff;
                min-height: 100vh;
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .registration-form {
                background: #ffffff;
                color: #000;
                padding: 2rem;
                border-radius: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                max-width: 400px;
                width: 100%;
            }
            .form-control:focus {
                box-shadow: 0 0 10px rgba(38, 143, 255, 0.5);
                border-color: #268fff;
            }
            .form-header {
                text-align: center;
                margin-bottom: 1.5rem;
            }
            .form-header h2 {
                font-weight: bold;
            }
            .form-header p {
                font-size: 0.9rem;
                color: #6c757d;
            }
            .btn-custom {
                background: linear-gradient(135deg, #6a11cb, #2575fc);
                color: #fff;
                border: none;
                transition: background 0.3s ease;
            }
            .btn-custom:hover {
                background: linear-gradient(135deg, #2575fc, #6a11cb);
            }
            .text-muted {
                font-size: 0.8rem;
                text-align: center;
                margin-top: 1rem;
            }
        </style>
    </head>
    <script>
        document.getElementById("registerForm").addEventListener("submit", function (event) {
            

            const fullName = document.getElementById("fullName").value.trim();
            const email = document.getElementById("email").value.trim();
            const phone = document.getElementById("phone").value.trim();
            const password = document.getElementById("password").value.trim();
            const confirmPassword = document.getElementById("confirm-password").value.trim();

            if (!fullName || !email || !phone || !password || !confirmPassword) {
                alert("Vui lòng ?i?n ??y ?? thông tin!");
                return;
            }
            if (password !== confirmPassword) {
                alert("M?t kh?u xác nh?n không kh?p!");
                return;
            }

            fetch("register", {
                method: "POST",
                body: new FormData(document.getElementById("registerForm"))
            })
                    .then(response => response.text())
                    .then(data => {
                        alert("??ng ký thành công! Chuy?n h??ng v? trang ??ng nh?p...");
                        setTimeout(() => {
                            window.location.href = "login.jsp";
                        }, 2000);
                    })
                    .catch(error => console.error("L?i:", error));
        });
    </script>


    <body>
        <div class="registration-form">
            <div class="form-header">
                <h2>Create Your Account</h2>
                <p>Sign up to get started!</p>
            </div>
            <form id="registerForm" action="register" method="post">
                <div class="mb-3">
                    <label for="fullName" class="form-label">Username</label>
                    <input type="text" class="form-control" name="fullName" id="fullName" placeholder="Enter your username" required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email Address</label>
                    <input type="email" class="form-control" name="email" id="email" placeholder="Enter your email" required>
                </div>
                <div class="mb-3">
                    <label for="phone" class="form-label">Phone Number</label>
                    <input type="tel" class="form-control" name="phone"placeholder="Enter your phone number" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" name="password"placeholder="Create a password" required>
                </div>
                <div class="mb-3">
                    <label for="confirm-password" class="form-label">Confirm Password</label>
                    <input type="password" class="form-control" name="password"placeholder="Confirm your password" required>
                </div>
                <div class="mb-3 form-check">
                    <input type="checkbox" class="form-check-input" id="terms" required>
                    <label class="form-check-label" for="terms">I agree to the <a href="#" class="text-primary">terms and conditions</a></label>
                </div>
                <button type="submit" class="btn btn-custom w-100">Register</button>
            </form>
            <div class="text-muted">
                Already have an account? <a href="#" class="text-primary">Sign In</a>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
